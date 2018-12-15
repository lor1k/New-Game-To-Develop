package com.kyrsach.game.view;

public class Player {
    public int coins;
    public float balanceXpos;
    public float balanceYpos;
    public Player(float balanceXpos, float balanceYpos){
        coins = 0;
        this.balanceYpos = balanceYpos;
        this.balanceXpos = balanceXpos;
    }
    public Player(int coins){
        this.coins = coins;
    }

    public void AddCoin(int ammount){
        coins += ammount;
    }
    public  void ShowBalance(){

    }
    public String strBalance(){
        String temp = ""+coins;
        return  temp;
    }
}
