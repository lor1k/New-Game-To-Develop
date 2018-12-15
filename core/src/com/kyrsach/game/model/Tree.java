package com.kyrsach.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Tree extends GameObject{

    Texture standart;
    Animation SpawnAnimation;
    Animation DeSpawnAnimation;
    Texture TreeSheet;
    TextureRegion[] SpawnFrames;
    TextureRegion[] DeSpawnFrames;
    TextureRegion CurrentTreeFrame;

    public boolean spawned = false;
    public boolean despawned = false;
    public boolean selected = false;

    float stateTime;
    float Time = 0;
    float TimeNew = 0;

    public Tree(float x, float y, float width, float height) {

        super(x, y, width, height);
        Create();
    }

    public void Create(){
        standart = new Texture(Gdx.files.internal("tree.png"));
        TreeSheet = new Texture(Gdx.files.internal("tree_animated.png"));
        TextureRegion[][] tmp = TextureRegion.split(TreeSheet, TreeSheet.getWidth()/7, TreeSheet.getHeight()/2);
        SpawnFrames = new TextureRegion[1 * 7];
        DeSpawnFrames = new TextureRegion[1 * 7];
        int i = 0;
        for (int j = 0; j < 7;j++, i++) {
            SpawnFrames[i] = tmp[0][j];
            DeSpawnFrames[i] = tmp[1][j];
        }

        SpawnAnimation = new Animation(0.15f, SpawnFrames);
        DeSpawnAnimation = new Animation(0.075f, DeSpawnFrames);
    }
    public void Spawn(SpriteBatch batch, float delta){
        if (!spawned) {
            stateTime += Gdx.graphics.getDeltaTime();
            CurrentTreeFrame = (TextureRegion) SpawnAnimation.getKeyFrame(stateTime, true);
            batch.draw(CurrentTreeFrame, x, y, width, height);
            if (Time > ((int)(1/delta))){
                spawned = true;
            }
            Time++;
        }else{
            batch.draw(standart, x, y, width, height);
        }
    }
    public void DeSpawn(SpriteBatch batch, float delta){
        if (despawned) {
            stateTime += Gdx.graphics.getDeltaTime();
            CurrentTreeFrame = (TextureRegion) DeSpawnAnimation.getKeyFrame(stateTime, true);
            batch.draw(CurrentTreeFrame, x, y, width, height);
            if (TimeNew > ((int) (0.5 / delta))) {
                despawned = false;
            }
            TimeNew++;
        }
    }
}
