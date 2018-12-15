package com.kyrsach.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Unit extends GameObject{

    public float speed;
    public float range;
    public boolean isdied = true;
    public boolean reverseinit = false;
    private int cols;
    private int rows;


    Animation walkAnimation; // #3
    Texture walkSheet; // #4
    TextureRegion[] walkFrames; // #5
    TextureRegion currentFrame; // #7


    float stateTime; // #8
    public Unit(){

    }
    public void Init(String path){
        walkSheet = new Texture(Gdx.files.internal(path)); // #9
        TextureRegion[][] tmp = TextureRegion.split(walkSheet, walkSheet.getWidth()/cols, walkSheet.getHeight()/rows); // #10
        walkFrames = new TextureRegion[cols * rows];
        int index = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols;j++) {
                walkFrames[index++] = tmp[i][j];
            }
        }
        walkAnimation = new Animation(0.06f, walkFrames);
        stateTime = 0f; // #13
    }
    public Unit(float x, float y, float width, float height, int cols, int rows, float speed) {
        super(x, y, width, height);
        isdied = true;
        reverseinit = false;
        this.speed = speed;
        this.cols = cols;
        this.rows = rows;

    }
    public void MoveAnim(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // #15
        currentFrame = (TextureRegion) walkAnimation.getKeyFrame(stateTime, true); // #16

        batch.draw(currentFrame, x, y, width, height);

    }
}
