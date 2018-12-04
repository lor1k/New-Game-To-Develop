package com.kyrsach.game.model;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class GameObject {

    public float x;
    public float y;
    public float width;
    public float height;
    public float health;

    Sprite object;

    public GameObject(float x, float y, float width, float height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }
    public float getX(){return x;}
    public float getY(){return y;}
    public float getWidth(){return width;}
    public float getHeight(){return height;}
    public float getHealth(){return health;}

    public void setX(float x){this.x = x;}
    public void setY(float y){this.y = y;}
    public void setWidth(float width){this.width = width;}
    public void setHeight(float height){this.height = height;}
    public void setHealth(float health){this.health = health;}

    public void move(float x,float y){
        this.x = x;
        this.y = y;
    }

    public void draw(SpriteBatch batch){
        object.setBounds(x,y,width,height);
        object.draw(batch);
    }

}
