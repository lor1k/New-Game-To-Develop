package com.kyrsach.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animator implements ApplicationListener {


	private static final int FRAME_COLLS = 8;
	private static final int FRAME_ROWS = 1;

	Animation walkAnimation;
	Texture dwarfSprite;
	TextureRegion[] walkFrames;
	SpriteBatch batch;
	TextureRegion curentFrame;

	float stateTime;

	int x;
	int y;
	float speed = 1f;
	public Animator(int x, int y){
		this.x = x;
		this.y = y;
	}
	@Override
	public void create() {

		dwarfSprite = new Texture(Gdx.files.internal("dwarfSpriteWalk.png"));
		TextureRegion [][] temp = TextureRegion.split(dwarfSprite,dwarfSprite.getWidth()/FRAME_COLLS, dwarfSprite.getHeight()/FRAME_ROWS);
		walkFrames = new TextureRegion[FRAME_ROWS*FRAME_COLLS];
		int index = 0;
		for(int i = 0; i < FRAME_ROWS; i++){
			for(int j = 0; j < FRAME_COLLS; j++){
				walkFrames[index++] = temp[i][j];
			}
		}
		walkAnimation = new Animation(0.075f, walkFrames);
		batch = new SpriteBatch();
		stateTime = 0f;

	}

	@Override
	public void resize(int width, int height) {

	}
	int a = 0;
	@Override
	public void render() {
		x+=speed;
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
		stateTime += Gdx.graphics.getDeltaTime();
		curentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true);
		batch.begin();
		batch.draw(curentFrame, x, y);
		batch.end();
	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}
}
