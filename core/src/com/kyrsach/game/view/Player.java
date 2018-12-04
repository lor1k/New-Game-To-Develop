package com.kyrsach.game.view;

public class Player {
    public float coins = 0;
    public float speed = 0.001f;

    public void UpdateCoins(){
        coins += speed;
    }
}
