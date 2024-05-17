package com.mygdx.game.billiards;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;

public class MyBilliards extends Game {
	public  static final float SCR_WIDTH = 1920, SCR_HEIGHT = 1080;


	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont font;

	ScreenMenu screenMenu;
	ScreenGame ScreenGame;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_WIDTH, SCR_HEIGHT);
		touch = new Vector3();
		fontGenerate();

		screenMenu = new ScreenMenu(this);
		ScreenGame = new ScreenGame(this);
		setScreen(screenMenu);

	}


	
	@Override
	public void dispose () {
		batch.dispose();
		font.dispose();
	}


	private void fontGenerate() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Tablon-Black.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
		generator.dispose();
	}
}
