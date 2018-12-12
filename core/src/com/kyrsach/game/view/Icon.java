package com.kyrsach.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.kyrsach.game.model.GameObject;

public class Icon extends GameObject {

    public boolean selected;
    public Texture icon;
    String path_selected;
    String path;

    public Icon(float x, float y, float width, float height, String path, String path_selected) {
        super(x, y, width, height);
        icon = new Texture(path);
        this.path = path;
        this.path_selected = path_selected;

    }
    public static boolean toggleBool(boolean a){
        return !a;
    }

    public boolean IsTouched(float ix, float iy, float monitorW, float monitorH){
        if (
                ix > x &&
                ix < x + width &&
                iy > y &&
                iy < y + height
        ){

            selected = toggleBool(selected);//kod govno
            if(selected){
                icon = new Texture(Gdx.files.internal(path_selected));
            } else {
                icon = new Texture(Gdx.files.internal(path));
            }
            return true;
        }
        return  false;
    }
    public void Reset(){
        icon = new Texture(Gdx.files.internal((path)));
        selected = false;
    }
}
