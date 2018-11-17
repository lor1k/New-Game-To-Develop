package com.kyrsach.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameScreen implements Screen {

    private Texture texture;
    private Texture sidebarleft;
    private Texture sidebarright;
    private Texture worker;
    private SpriteBatch batch;

    private BitmapFont FontRed1;
    private String str;

    @Override
    public void show() {
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bg.png"));
        sidebarleft = new Texture(Gdx.files.internal("side_bar_left.png"));
        sidebarright = new Texture(Gdx.files.internal("side_bar_right.png"));
        worker = new Texture(Gdx.files.internal("worker.png"));

        FontRed1 = new BitmapFont();
        FontRed1.setColor(1,0,0,1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(sidebarleft, 0,0);
        str = ""+Gdx.graphics.getWidth() + "TEXT: " + sidebarright.getWidth() + "FOR" + Gdx.graphics.getWidth()/100f*83f;
        batch.draw(sidebarright,Gdx.graphics.getWidth() - sidebarright.getWidth(),0);
        batch.draw(worker, Gdx.graphics.getWidth()/100f*1f, Gdx.graphics.getHeight()/100f*30f);
        FontRed1.draw(batch, str, 10, 20);
        batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        worker.dispose();
        sidebarleft.dispose();
        sidebarright.dispose();
        FontRed1.dispose();
        texture.dispose();

    }
}
