package ru.nyancoder.solar_system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import ru.nyancoder.solar_system.bodys.body;
import ru.nyancoder.solar_system.bodys.sun;
import ru.nyancoder.solar_system.bodys.venus;
import ru.nyancoder.solar_system.bodys.earth;
import ru.nyancoder.solar_system.bodys.moon;

public class app extends ApplicationAdapter {
    
    body rootBody;
    OrthographicCamera cam;

    long lastLoopTime;
    Boolean play;
    
    Stage stage;
    TextButton button;
    Skin skin;

    @Override
    public void create () {
        
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1, 1);
        
        rootBody = new sun();
        new venus(rootBody);
        new moon(new earth(rootBody));
        
        lastLoopTime = System.nanoTime();
        play = false;
        
        // Интерфейс
        stage = new Stage();
        
        // Кнопочка
        Gdx.input.setInputProcessor(stage);
        skin = new Skin(Gdx.files.internal("ui/uiskin.json"), new TextureAtlas(Gdx.files.internal("ui/uiskin.atlas")));
        button = new TextButton("Play", skin);
        button.setWidth(100);
        button.addListener(new ClickListener() {
            @Override
            public void clicked (InputEvent event, float x, float y) {
                play = !play;
                button.setText(play ? "Pause" : "Play");
            }
        });
        stage.addActor(button); // Добавление кнопочки
        
    }

    @Override
    public void render () {
        
        long now = System.nanoTime();
        float dt = (now - lastLoopTime) * 1E-9f;
        lastLoopTime = now;
        
        cam.update();
        rootBody.update((play ? dt : 0), cam); // Если не проигрывается, то просто дельта = 0
        
	Gdx.gl.glClearColor(0, 0, 0, 1);
	Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        
        // Небесные тела
        rootBody.draw();
        
        // Интерфейс
        stage.draw();
        
    }

    @Override
    public void resize (int width, int height) {
        super.resize(width, height);
        
        // "Резиновый" дизайн
        button.setPosition(width / 2, 0, Align.bottom);
        
        // Нормализуем размеры окна в матрице проекции (ортографическая матрица проекции)
        float min = (width < height) ? width : height;
        cam.setToOrtho(true, width / min, height / min);
    }
	
    @Override
    public void dispose () {
        rootBody.dispose();
    }
    
}
