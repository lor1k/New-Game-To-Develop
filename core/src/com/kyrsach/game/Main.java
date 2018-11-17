package com.kyrsach.game;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.kyrsach.game.view.GameScreen;


public class Main extends Game {

    private float x = 50;
    private Screen gameScreen;

	@Override
	public void create() {
        if (Gdx.app.getType().equals(Application.ApplicationType.Android)) {
            Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(),Gdx.graphics.getHeight());
        } else {
            Gdx.graphics.setWindowedMode(1334, 750);
        }
		gameScreen = new GameScreen();
		setScreen(gameScreen);
	}
}
