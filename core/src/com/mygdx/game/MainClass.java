package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class MainClass extends ApplicationAdapter {
	SpriteBatch batch;
	Texture Man;
	float x;
	float y;
	Man generalobj = new Man(50, 50, 2.0f, 2.0f);

	@Override
	public void create () {
		batch = new SpriteBatch();
		Man = new Texture("Man.png");
	}

	@Override
	public void render () {
		update();
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		batch.begin();
		batch.draw(Man, x, y, 100, 60);
		batch.end();
	}
	public void update (){
		x = generalobj.Update();
	}
	@Override
	public void dispose () {
		batch.dispose();
		Man.dispose();
	}
}
