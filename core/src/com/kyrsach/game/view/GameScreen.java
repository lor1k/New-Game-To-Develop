package com.kyrsach.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.kyrsach.game.model.GameObject;
import com.kyrsach.game.model.Tree;

import java.util.Random;



public class GameScreen implements Screen {

    private Player[] players;
    private Icon[] IconLeft;
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
    private String str;
    private boolean checker;


    @Override
    public void show() {
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
        players = new Player[2];
        IconLeft = new Icon[2];
        Gdx.app.log("SUPER", "READY");
        IconLeft[0] = new Icon(Gdx.graphics.getWidth()/100f*1f, Gdx.graphics.getHeight()/100f*30f, worker.getWidth(), worker.getHeight(), "worker.png", "worker_selected.png");
        Gdx.app.log("SUPER", "READY");
        IconLeft[1] = new Icon(Gdx.graphics.getWidth()/100f*1f, Gdx.graphics.getHeight()/100f*50f, warrior.getWidth(), warrior.getHeight(), "warrior.png", "warrior_selected.png");
        Gdx.app.log("SUPER", "READY");
        str = "gjgh";

        for (int i = 0; i < count; i++){
            trees[i] = new Tree(left_spawn_border+(float)Math.random()*(right_spawn_border - tree_width),bottom_spawn_border+(float)Math.random()*(top_spawn_border - tree_height),tree.getWidth(), worker.getHeight());
        }

        FontRed1 = new BitmapFont();
        FontRed1.setColor(1,0,0,1);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(texture, 0, 0);
        batch.draw(sidebarleft, 0,0);
        batch.draw(sidebarright,Gdx.graphics.getWidth() - sidebarright.getWidth(),0);
        FontRed1.draw(batch, str, 10, 20);
        for (int i = 0; i < a; i++){// Тут до count в оригинале
            batch.draw(tree, trees[i].getX(), trees[i].getY(), tree_width,tree_height);
        }

        if(a == count-1){
            a = count - 1;
        } else{
            a++;
        }

        if(Gdx.input.justTouched()){
            for (int i = 0; i < 2; i++){
                checker = IconLeft[i].IsTouched(Gdx.input.getX(),Math.abs(Gdx.input.getY()-Gdx.graphics.getHeight()), Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
                if(checker){
                    for (int j = 0; j < 2;j++){
                        if(j == i){
                            continue;
                        }else{
                            IconLeft[j].Reset();
                        }
                    }
                    break;
                }
                str = "INPUTX : "+Gdx.input.getX()+"INPUTY : "+ Gdx.input.getY()+"WIDTH : "+ Gdx.graphics.getWidth()+"HEIGHT : "+ Gdx.graphics.getHeight();
            }

        }
        for (int i = 0; i < 2; i++){
            batch.draw(IconLeft[i].icon, IconLeft[i].x, IconLeft[i].y, IconLeft[i].width, IconLeft[i].height);
        }

        batch.end();

    }

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
