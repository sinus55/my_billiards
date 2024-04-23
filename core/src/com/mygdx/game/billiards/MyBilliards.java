package com.mygdx.game.billiards;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.ScreenUtils;

public class MyBilliards extends Game {
	public  static final float SCR_WITDH = 1920, SCR_HEIGHT = 1080;


	SpriteBatch batch;
	OrthographicCamera camera;
	Vector3 touch;
	BitmapFont font;

	ScreenMenu screenMenu;

	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
		camera.setToOrtho(false, SCR_HEIGHT, SCR_WITDH);
		touch = new Vector3();
		fontGenerate();

		screenMenu = new ScreenMenu(this);
		setScreen(screenMenu);

	}


	
	@Override
	public void dispose () {
		batch.dispose();
	}

	private void fontGenerate() {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("cattedrale.ttf"));
		FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		parameter.size = 50;
		font = generator.generateFont(parameter);
	}
}
