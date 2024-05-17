package com.mygdx.game.billiards;

import static com.mygdx.game.billiards.MyBilliards.SCR_HEIGHT;
import static com.mygdx.game.billiards.MyBilliards.SCR_WIDTH;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Align;

public class ScreenGame implements Screen {
    MyBilliards game;
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    BitmapFont font;
    Texture imgBG;
    Texture img;

    MyButton btnExit;
    public ScreenGame(MyBilliards game ) {
        this.game = game;
        batch = game.batch;
        camera = game.camera;
        touch = game.touch;
        font = game.font;
        imgBG = new Texture("Stoll.jpeg");
        btnExit = new MyButton("Exit",font, 800, SCR_HEIGHT*75/100/10);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        // касание
        if(Gdx.input.justTouched()){
            touch.set(Gdx.input.getX(), Gdx.input.getY(), 0 );
            camera.unproject(touch);
            if (btnExit.hit(touch.x, touch.y)) {
                game.setScreen((game.screenMenu));
            }
        }
            // события



        // отрисовка
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        batch.draw(imgBG, 0, 0, SCR_WIDTH, SCR_HEIGHT);
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
