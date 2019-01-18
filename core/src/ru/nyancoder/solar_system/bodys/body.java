/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ru.nyancoder.solar_system.bodys;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import java.util.ArrayList;

import ru.nyancoder.solar_system.MatrixMath;

/**
 *
 * @author NyanCoder
 */
public class body {
    
    // Константы
    final static float EPSILON = 1E-6f; // эпсилон

    // Родитель
    body parent;
    
    // Потомки
    private ArrayList<body> childs;
    
    // Спрайтик
    private SpriteBatch batch;
    private Sprite sprite;
    private Texture img;
    
    // Преобразования
    private float scale, // размеры каждого тела 1
            distance, // расстояние от родителя на количество размеров родителя
            revolvePeriod, // вращение вокруг родителя + оси (чтоб учитывались дни/ночи)
            revolve,
            rotatePeriod, // вращение вокруг оси
            rotate;
    private Boolean parentRotation;
    private Camera cam;
    private Matrix4 rotatelesTransform, transform;
    
    public body (body parent, String textureName, float scale, float distance, float revolvePeriod, float rotatePeriod, Boolean parentRotation) {
        
        this.parent = parent;
        childs = new ArrayList<body>();
        
	img = new Texture(textureName);
        sprite = new Sprite(img);
        sprite.setPosition(-0.5f, -0.5f); // чтоб нулевая координата была в центре
        sprite.setSize(1.0f, 1.0f);
	batch = new SpriteBatch();
        
        this.scale = scale;
        this.distance = distance;
        this.revolvePeriod = revolvePeriod;
        this.revolve = 0.0f;
        this.rotatePeriod = rotatePeriod;
        this.rotate = 0.0f;
        this.parentRotation = parentRotation;
        
        this.transform = new Matrix4();
        this.rotatelesTransform = new Matrix4();
        
        // Передаём this только после полной инициализации
        if (parent != null)
            parent.addChild(this);
        
    }
    
    public body (body parent, String textureName, float scale, float distance, float revolvePeriod, float rotatePeriod) {
        this(parent, textureName, scale, distance, revolvePeriod, rotatePeriod, true);
    }
    
    public void draw () {
        
        batch.setProjectionMatrix(cam.projection);
        batch.setTransformMatrix(getTransform());
        batch.begin();
        sprite.draw(batch);
        batch.end();
                
        for (body child : childs) {
            child.draw();
        }
        
    }
    public void update (float deltaTime, Camera cam) {
        
        this.cam = cam;
        
        transform = new Matrix4(); // Обнуляем матрицу, чтоб преобразования не накладывались друг на друга
        // А первая трансформация второй матрицы - с присвоением, так-что не волнуемся
        
        // Обновление времени
        if (Math.abs(rotatePeriod) > EPSILON) {
            rotate += deltaTime;
            // Ограничение периода
            while (rotate > Math.abs(rotatePeriod))
                rotate -= Math.abs(rotatePeriod);
        }
        if (Math.abs(revolvePeriod) > EPSILON) {
            revolve += deltaTime;
            // Ограничение периода
            while (revolve > Math.abs(revolvePeriod))
                revolve -= Math.abs(revolvePeriod);
        }
        
        // Сами преобразования
        rotatelesTransform = MatrixMath.MatScale(scale) // Меняем размер
                                                        // и лишь потом двигаем, чтоб удаление от родителя зависело только от размеров родителя так,
                                                        // чтоб можно было считать размер родителя за единицу, т.е. не учитывая размеры самого спутника
                .mulLeft(MatrixMath.MatTranslate(distance, 0, 0));
        if (Math.abs(revolvePeriod) > EPSILON) // Вращение вокруг родителя
            rotatelesTransform.mulLeft(MatrixMath.MatRotate(0, 0, 1, 360 * revolve / revolvePeriod));
        if (Math.abs(rotatePeriod) > EPSILON) // Вращение вокруг своей оси
            transform.mul(MatrixMath.MatRotate(0, 0, 1, 360 * rotate / rotatePeriod));

        // Учитываем трансформации родителя, если он есть
        if (parent != null)
            rotatelesTransform.mulLeft(parentRotation ? parent.getTransform() : parent.getRotatelesTransform());
        transform.mulLeft(rotatelesTransform);
        
        // Обновление потомков
        for (body child : childs)
            child.update(deltaTime, cam);
        
    }
    
    public void dispose () {
        for (body child : childs)
            child.dispose();
        batch.dispose();
        img.dispose();
    }

    protected void addChild (body child) {
        childs.add(child);
    }

    protected Matrix4 getTransform () {
        return transform;
    }
    // Ну, это когда мы не хотим вращать спутник с вращением родителя вокруг своей оси
    protected Matrix4 getRotatelesTransform () {
        return rotatelesTransform;
    }
    
}
