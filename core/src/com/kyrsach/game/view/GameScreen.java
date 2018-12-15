package com.kyrsach.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kyrsach.game.model.GameObject;
import com.kyrsach.game.model.Tree;
import com.kyrsach.game.model.Worker;

import java.util.Random;



public class GameScreen implements Screen {

    private Icon[] IconLeft;
    private Icon[] IconRight;
    private Texture texture;
    private Texture sidebarleft;
    private Texture sidebarright;
    private Texture worker;
    private Texture warrior;
    private Texture tree;
    private SpriteBatch batch;
    private GameObject[] trees;
    private final int count = 100;
    private float left_spawn_border;
    private float right_spawn_border;
    private float top_spawn_border;
    private float bottom_spawn_border;
    private int tree_height = 70;
    private int tree_width = 35;
    private int a = 0;
    private BitmapFont FontRed1;
    private BitmapFont BalanceFont;
    private String str;
    private boolean checkerLeft;
    private boolean checkerRight;
    private Player[] players;
    private int interval;
    private int coinCounter;
    private int treeCounter;
    private Music st;
    private Worker[] units_worker;
    private Worker[] units_worker_right;



    @Override
    public void show() {
        interval = 3;
        treeCounter = 0;
        left_spawn_border = Gdx.graphics.getWidth()/100f*30f;
        right_spawn_border = Gdx.graphics.getWidth() - left_spawn_border*2;
        top_spawn_border = Gdx.graphics.getHeight();
        bottom_spawn_border = 0;
        batch = new SpriteBatch();
        texture = new Texture(Gdx.files.internal("bg.png"));
        sidebarleft = new Texture(Gdx.files.internal("side_bar_left.png"));
        sidebarright = new Texture(Gdx.files.internal("side_bar_right.png"));
        worker = new Texture(Gdx.files.internal("worker.png"));
        warrior = new Texture(Gdx.files.internal("warrior.png"));
        tree = new Texture(Gdx.files.internal("tree.png"));
        trees = new Tree[count];
        IconLeft = new Icon[2];
        IconRight = new Icon[2];
        units_worker = new Worker[3];
        for (int i = 0; i < 3; i++){
            units_worker[i] = new Worker();
        }
        units_worker_right = new Worker[3];
        for (int i = 0; i < 3; i++){
            units_worker_right[i] = new Worker();
        }
        IconRight[0] = new Icon(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/100f*1f - warrior.getWidth(), Gdx.graphics.getHeight()/100f*30f, worker.getWidth(), worker.getHeight(), "worker_right.png", "worker_right_selected.png");
        IconRight[1] = new Icon(Gdx.graphics.getWidth() - Gdx.graphics.getWidth()/100f*1f - worker.getWidth(), Gdx.graphics.getHeight()/100f*50f, warrior.getWidth(), warrior.getHeight(), "warrior_right.png", "warrior_right_selected.png");
        players = new Player[2];

        BalanceFont = new BitmapFont();
        BalanceFont.setColor(1,1,1, 1);
        BalanceFont.getData().scale(2);

        players[0] = new Player(0,0);
        players[1] = new Player(0,0);

        IconLeft[0] = new Icon(Gdx.graphics.getWidth()/100f*1f, Gdx.graphics.getHeight()/100f*30f, worker.getWidth(), worker.getHeight(), "worker.png", "worker_selected.png");
        Gdx.app.log("SUPER", "READY");
        IconLeft[1] = new Icon(Gdx.graphics.getWidth()/100f*1f, Gdx.graphics.getHeight()/100f*50f, warrior.getWidth(), warrior.getHeight(), "warrior.png", "warrior_selected.png");
        Gdx.app.log("SUPER", "READY");
        str = "gjgh";

        for (int i = 0; i < count; i++){
            trees[i] = new Tree(left_spawn_border+(float)Math.random()*(right_spawn_border - tree_width),bottom_spawn_border+(float)Math.random()*(top_spawn_border - tree_height),tree_width, tree_height);
        }

        FontRed1 = new BitmapFont();
        FontRed1.setColor(1,0,0,1);
        //FontRed1;
        //AUDIO
        st = Gdx.audio.newMusic(Gdx.files.internal("st.mp3"));
        st.setLooping(true);
        st.setVolume(0.2f);
        st.play();



    }

    @Override
    public void render(float delta) {

        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(sidebarleft, 0,0);
        batch.draw(sidebarright,Gdx.graphics.getWidth() - sidebarright.getWidth(),0);
        FontRed1.draw(batch, str, 10, 20);
        BalanceFont.draw(batch, players[0].strBalance(), 15, Gdx.graphics.getHeight() - 21);
        BalanceFont.draw(batch, players[1].strBalance(), Gdx.graphics.getWidth() - 75, Gdx.graphics.getHeight() - 21);
        for (int i = 0; i < a; i++){// Тут до count в оригинале
            if (trees[i] != null) {
                if (!((Tree) trees[i]).despawned) {

                    ((Tree) trees[i]).Spawn(batch, delta);
                } else {
                    ((Tree) trees[i]).DeSpawn(batch, delta);
                    if (!((Tree) trees[i]).despawned && !((Tree) trees[i]).spawned) {
                        trees[i] = null;
                    }
                }

            }
        }
        if (treeCounter > ((int)(30/delta))){
            for (int j = 0; j < a;j++){
                if (trees[j] == null){
                    trees[j] = new Tree(left_spawn_border+(float)Math.random()*(right_spawn_border - tree_width),bottom_spawn_border+(float)Math.random()*(top_spawn_border - tree_height),tree_width, tree_height);
                    break;
                }
            }
            treeCounter = 0;
        }
        treeCounter++;

        if(a == count-1){
            a = count - 1;
        } else{
            a++;
        }

        if(Gdx.input.justTouched()){
            if (Gdx.input.getX() < (Gdx.graphics.getWidth()/2) && Gdx.input.getX() > sidebarleft.getWidth()){
                if(IconLeft[1].selected) // спавн для первого игрока
                    for (int i = 0; i < 3; i++){
                        if (units_worker[i].isdied){
                            if (players[0].coins >= 5) {
                                players[0].coins -= 5;
                                units_worker[i] = new Worker(sidebarleft.getWidth(), Gdx.graphics.getHeight() - Gdx.input.getY(), 70, 70, "dwarfSpriteWalk.png", "dwarfSpriteGetherT.png");
                                units_worker[i].isdied = false;
                            }
                            break;
                        }
                    }
            }else{ // спавн для второго игрока
                if (Gdx.input.getX() > (Gdx.graphics.getWidth()/2) && Gdx.input.getX() < Gdx.graphics.getWidth() - sidebarright.getWidth()) {
                    if (IconRight[1].selected)
                        for (int i = 0; i < 3; i++) {
                            if (units_worker_right[i].isdied) {
                                if (players[1].coins >= 5) {
                                    players[1].coins -= 5;
                                    units_worker_right[i] = new Worker(Gdx.graphics.getWidth() - sidebarright.getWidth() - 65, Gdx.graphics.getHeight() - Gdx.input.getY(), 70, 70, "dwarfSpriteWalkReverse.png", "dwarfSpriteGetherReverse.png");
                                    units_worker_right[i].isdied = false;
                                }
                                break;
                            }
                        }
                }
            }
            for (int i = 0; i < 2; i++){
                checkerLeft = IconLeft[i].IsTouched(Gdx.input.getX(),Math.abs(Gdx.input.getY()-Gdx.graphics.getHeight()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                if(checkerLeft){
                    for (int j = 0; j < 2;j++){
                        if(j == i){
                            continue;
                        }else{
                            IconLeft[j].Reset();
                        }
                    }
                    break;
                }
            }
            for (int i = 0; i < 2; i++){
                checkerRight = IconRight[i].IsTouched(Gdx.input.getX(),Math.abs(Gdx.input.getY()-Gdx.graphics.getHeight()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                if(checkerRight){
                    for (int j = 0; j < 2;j++){
                        if(j == i){
                            continue;
                        }else{
                            IconRight[j].Reset();
                        }
                    }
                    break;
                }
            }
        }
        for (int i = 0; i < 2; i++){
            batch.draw(IconLeft[i].icon, IconLeft[i].x, IconLeft[i].y, IconLeft[i].width, IconLeft[i].height);
        }
        for (int i = 0; i < 2; i++){
            batch.draw(IconRight[i].icon, IconRight[i].x, IconRight[i].y, IconRight[i].width, IconRight[i].height);
        }
        for (int i = 0;i < 3;i++){
            if (!units_worker[i].TreeIsAlive) {
                if (!units_worker[i].isdied) {
                    if (units_worker[i].finded == null) {
                        ((Tree)trees[units_worker[i].SearchPass(trees)]).selected = true;
                    }
                    if (units_worker[i].finded != null) {
                        if (units_worker[i].Move(batch)) {
                            units_worker[i].GetherCounter++;
                            units_worker[i].Gether(batch);
                            if (units_worker[i].GetherCounter > ((int)(5/delta))){
                                units_worker[i].TreeIsAlive = true;
                                if (trees[units_worker[i].targetedTree] != null) {
                                    ((Tree) trees[units_worker[i].targetedTree]).despawned = true;
                                    ((Tree) trees[units_worker[i].targetedTree]).spawned = false;
                                }
                            }
                        }
                    }
                }
            }else{
                if (!units_worker[i].reverseinit) {
                    units_worker[i].Init("dwarfSpriteWalkReverse.png");
                    units_worker[i].reverseinit = true;
                }
                if(units_worker[i].MoveBack(batch)) { // принес дерево
                    if (units_worker[i].withTree) {
                        units_worker[i].withTree = false;
                        players[0].coins += 20;
                        units_worker[i].isdied = true;
                    }
                }
            }
        }
        ///////////////////////////////
        for (int i = 0;i < 3;i++){
            if (!units_worker_right[i].TreeIsAlive) {
                if (!units_worker_right[i].isdied) {
                    if (units_worker_right[i].finded == null) {
                        ((Tree)trees[units_worker_right[i].SearchPass(trees)]).selected = true;
                    }
                    if (units_worker_right[i].finded != null) {
                        if (units_worker_right[i].Move(batch)) {
                            units_worker_right[i].GetherCounter++;
                            units_worker_right[i].Gether(batch);
                            if (units_worker_right[i].GetherCounter > ((int)(5/delta))){
                                units_worker_right[i].TreeIsAlive = true;
                                if (trees[units_worker_right[i].targetedTree] != null) {
                                    ((Tree) trees[units_worker_right[i].targetedTree]).despawned = true;
                                    ((Tree) trees[units_worker_right[i].targetedTree]).spawned = false;
                                }
                            }
                        }
                    }
                }
            }else{
                if (!units_worker_right[i].reverseinit) {
                    units_worker_right[i].Init("dwarfSpriteWalk.png");
                    units_worker_right[i].reverseinit = true;
                }
                if(units_worker_right[i].MoveBack(batch)) { // принес дерево
                    if (units_worker_right[i].withTree) {
                        units_worker_right[i].withTree = false;
                        players[1].coins += 20;
                        units_worker_right[i].isdied = true;
                    }
                }
            }
        }
        batch.end();

        coinCounter++;
        if(coinCounter > ((int)(interval/delta))){

            for(int i = 0; i < 2; i++) {
            players[i].AddCoin(1);

            }
            coinCounter = 0;
        }


    }
            //1/delta = FPS(Срабатывания рендела в СЕК
            //if(var%(1/delta)*interval == 0){addCoin}
    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        worker.dispose();
        sidebarleft.dispose();
        sidebarright.dispose();
        FontRed1.dispose();
        texture.dispose();
        tree.dispose();
    }
}
