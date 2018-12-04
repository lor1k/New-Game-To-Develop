package com.kyrsach.game.view;

import com.kyrsach.game.model.GameObject;

public class Icon extends GameObject {

    public Icon(float x, float y, float width, float height) {
        super(x, y, width, height);
    }
    public static boolean toggleBool(boolean a){
        return !a;
    }
}
