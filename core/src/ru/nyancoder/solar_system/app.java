package ru.nyancoder.solar_system;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

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

    @Override
    public void create () {
        
        cam = new OrthographicCamera();
        cam.setToOrtho(false, 1, 1);
        
        rootBody = new sun();
        new venus(rootBody);
        new moon(new earth(rootBody));
        
        lastLoopTime = System.nanoTime();
        play = true;
        
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
        
        rootBody.draw();
        
    }

    @Override
    public void resize (int width, int height) {
        super.resize(width, height);
        
        // Нормализуем размеры окна в матрице проекции (ортографическая матрица проекции)
        float min = (width < height) ? width : height;
        cam.setToOrtho(true, width / min, height / min);
    }
	
    @Override
    public void dispose () {
        rootBody.dispose();
    }
    
}
