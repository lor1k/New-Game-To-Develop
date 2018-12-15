package com.kyrsach.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Worker extends Unit {

    public GameObject finded;
    public String pathGether;
    public int GetherCounter = 0;
    public boolean TreeIsAlive;
    public int targetedTree;
    public boolean withTree = false;

    Animation GetherAnimation; // #3
    Texture GetherSheet; // #4
    TextureRegion[] GetherFrames; // #5
    TextureRegion CurrentGetherFrame; // #7

    public Worker(){

    }
    public Worker(float x, float y, float width, float height, String path, String Gether) {
        super(x, y, width, height, 8, 1, 0.5f);
        GetherCounter = 0;
        Init(path);
        this.pathGether = Gether;
        GetherSheet = new Texture(Gdx.files.internal(pathGether)); // #9
        TextureRegion[][] tmp = TextureRegion.split(GetherSheet, GetherSheet.getWidth()/7, GetherSheet.getHeight()/1); // #10
        GetherFrames = new TextureRegion[1 * 7];
        int index = 0;
        for (int i = 0; i < 1; i++) {
            for (int j = 0; j < 7;j++) {
                GetherFrames[index++] = tmp[i][j];
            }
        }
        GetherAnimation = new Animation(0.06f, GetherFrames);

    }
    public void SearchPass(GameObject[] obj){
        int min = 0;
        float distance=999999;
        float tempX;
        float tempY;
        float tempdist;
        for(int i = 0; i < obj.length; i++){
            if(obj[i] != null) {
                tempX = Math.abs(obj[i].x - x);
                tempY = Math.abs(obj[i].y - y);
                tempdist = tempX + tempY;
                if (tempdist < distance) {
                    min = i;
                    distance = tempdist;
                }
            }
        }
        targetedTree = min;
        finded = obj[min];
    }
    public boolean Move(SpriteBatch batch){
        withTree = true;
        if (x < finded.x - 40 && (finded.x - x) > 0){
            x+=speed;
            MoveAnim(batch);
            return false;
        } else  if (x > finded.x - 40  && (finded.x - x) < 0){
            x-=speed;
            MoveAnim(batch);
            return false;
        }
        if (finded.y - y > 0 && Math.abs(finded.y -y) > 5){
            y+=speed;
            MoveAnim(batch);
            return false;
        }else{
            if(finded.y -y < 0 && Math.abs(finded.y -y) > 5){
                y-=speed;
                MoveAnim(batch);
                return false;
            }else {
                return true;
            }

        }
    }
    public boolean MoveBack(SpriteBatch batch){
        if (x > 110 && x < Gdx.graphics.getWidth()/2){
            x-=speed;
            MoveAnim(batch);
            return false;
        }else if (x < Gdx.graphics.getWidth() - 170 && x > Gdx.graphics.getWidth()/2){
            x+=speed;
            MoveAnim(batch);
            return false;
        } else {
            return true;
        }

    }
    public void Gether(SpriteBatch batch){
        stateTime += Gdx.graphics.getDeltaTime(); // #15
        CurrentGetherFrame = (TextureRegion) GetherAnimation.getKeyFrame(stateTime, true); // #16

        batch.draw(CurrentGetherFrame, x, y, width, height);
    }
}
