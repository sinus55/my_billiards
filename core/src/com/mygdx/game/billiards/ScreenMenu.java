package com.mygdx.game.billiards;

import static com.mygdx.game.billiards.MyBilliards. *;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

public class ScreenMenu implements Screen {
    MyBilliards game;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;
    Texture imgBG;
    Texture img;


    MyButton btnGame;
    MyButton btnExit;
    public ScreenMenu(MyBilliards game ) {
        this.game = game;
        batch = game.batch;
        camera = game.camera;
        touch = game.touch;
        font = game.font;
        imgBG = new Texture("imgBG.jpg");
        img = new Texture("img.jpg");


        btnGame = new MyButton("Play",font, 800);
        btnExit = new MyButton("Exit",font, 700);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касание
        if(Gdx.input.justTouched()) {
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touch);
            if (btnGame.hit(touch.x, touch.y)) {
                game.setScreen(game.ScreenGame);
            }
            if (btnExit.hit(touch.x, touch.y)) {
                Gdx.app.exit();
            }
        }
            // события



        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
        font.draw(batch, "MENU", 0, 1000, SCR_WIDTH, Align.center, true);
        btnGame.font.draw(batch, btnGame.text, btnGame.x, btnGame.y);
        btnExit.font.draw(batch, btnExit.text, btnExit.x, btnExit.y);
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
        img.dispose();
        imgBG.dispose();
    }
}
