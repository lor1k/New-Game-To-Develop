package com.mygdx.game;

import com.badlogic.gdx.Gdx;

import java.lang.reflect.Constructor;

public class Man {
    public float x;
    public float y;
    public float vx;
    public float vy;

    public Man(float x, float y, float vx, float vy) {
        this.x = x;
        this.y = y;
        this.vx = vx;
        this.vy = vy;
    }
    public float Update(){
        if(Gdx.input.isTouched() == true)
        x+=vx;
        return x;
    }
}
