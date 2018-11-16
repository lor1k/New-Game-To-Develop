package com.kyrsach.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;


public class Main extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;


	public static class Warior{
			public int x;
			public int y;
		public Warior(int x, int y){
			this.x = x;
			this.y = y;
		}
		public void Spawn(int x, int y){
			Animator anim = new Animator(x, y);
			anim.create();
		}
	}


	Animator anim = new Animator(200, 200);
	EventListener listener = new EventListener();
	@Override
	public void create () {
		anim.create();
	}


	@Override
	public void render () {
		Gdx.input.setInputProcessor(listener);
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		anim.render();

	}

	@Override
	public void dispose () {

		anim.dispose();
	}
}
