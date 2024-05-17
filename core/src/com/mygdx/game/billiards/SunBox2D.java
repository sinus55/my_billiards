package com.mygdx.game.billiards;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.ScreenUtils;

public class SunBox2D extends ApplicationAdapter {
    // глобальные константы
    public static final float WORLD_WIDTH = 16, WORLD_HEIGHT = 9;
    public static final int TYPE_CIRCLE = 0, TYPE_BOX = 1, TYPE_POLY = 2;

    // системные объекты
    SpriteBatch batch;
    OrthographicCamera camera;
    Vector3 touch;
    World world;
    Box2DDebugRenderer debugRenderer;

  //ресурсы
    Texture imgTextureAtlas;
    TextureRegion imgCircle;
    TextureRegion imgBrick;
    TextureRegion imgBeton;
    TextureRegion imgLightBeton;
    TextureRegion imgTriangle;
    TextureRegion img;
    Sound sndKnock, sndChpok;

    // наши объекты и переменные
    StaticBody floor;
    StaticBody wallLeft, wallRight;
    StaticBody roof;

    KinematicBody platform;

    Array<DynamicBody> balls = new Array<>();

    @Override
    public void create () {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        camera.setToOrtho(false, WORLD_WIDTH, WORLD_HEIGHT);
        touch = new Vector3();
        world = new World(new Vector2(0f, 0f), true);
        world.setContactListener(new MyContactListener(this));
        debugRenderer = new Box2DDebugRenderer();
        debugRenderer.setDrawVelocities(true);

        imgTextureAtlas = new Texture("atlasloot.png");
        imgCircle = new TextureRegion(imgTextureAtlas, 3*256, 0, 256, 256);
        imgBrick = new TextureRegion(imgTextureAtlas, 2*256, 0, 256, 256);
        imgBeton = new TextureRegion(imgTextureAtlas, 256, 0, 256, 256);
        imgLightBeton = new TextureRegion(imgTextureAtlas, 0, 0, 256, 256);
        imgTriangle = new TextureRegion(imgTextureAtlas, 256, 256, 256, 256);

        floor = new StaticBody(world, 8, 0.5f, 16, 1);
        wallLeft = new StaticBody(world, 0.5f, 5, 1, 8);
        wallRight = new StaticBody(world, 15.5f, 5, 1, 8);
        roof = new StaticBody(world, 8, 8.5f, 14, 1);

        //platform = new KinematicBody(world, 0, 3, 3, 1);

        balls.add(new DynamicBody(world, 2, 4.5f, 1, 0.2f, "cue"));
        balls.add(new DynamicBody(world, 3, 4.5f, 0.4f, "ball0"));
        balls.add(new DynamicBody(world, 14, 5f, 0.4f, "ball1"));
        balls.add(new DynamicBody(world, 14, 4f, 0.4f, "ball2"));
        balls.add(new DynamicBody(world, 13, 4.5f, 0.4f, "ball3"));

		/*for (int i = 0; i < 25; i++) {
			if(i<2) {
				balls.add(new DynamicBody(world, 8 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, 0.4f));
			} else if(i<4) {
				balls.add(new DynamicBody(world, 8 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, 1, 0.5f));
			} else if(i<8) {
				Polygon polygon = new Polygon(new float[]{-1, -1, 1, -1, 0, 1});
				scalePolygon(polygon, 0.5f);
				balls.add(new DynamicBody(world, 8 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, polygon));
			} else {
				Polygon polygon0 = new Polygon(new float[]{-1, 0, 0, 1, 1, 0});
				Polygon polygon1 = new Polygon(new float[]{-1, -1, -0.5f, 0, 0.5f, 0, 1, -1});
				scalePolygon(polygon0, 0.5f);
				scalePolygon(polygon1, 0.5f);
				balls.add(new DynamicBody(world, 8 + MathUtils.random(-0.01f, 0.01f), WORLD_HEIGHT + i, polygon0, polygon1));
			}
		}*/

        // касания
        Gdx.input.setInputProcessor(new MyInputProcessor(this));
    }

    @Override
    public void render () {
		/*
		// касания
		if(Gdx.input.justTouched()){
			touch.set(Gdx.input.getX(), Gdx.input.getY(), 0);
			camera.unproject(touch);
			for (DynamicBody b: balls){
				if(b.hit(touch.x, touch.y)){
					b.setImpulse(new Vector2(0, 2));
				}
			}
		}*/

        // события
        //platform.move();

        // отрисовка
        ScreenUtils.clear(0.2f, 0, 0.3f, 1);
        debugRenderer.render(world, camera.combined);
        batch.setProjectionMatrix(camera.combined);
        batch.begin();
		/*batch.draw(imgBeton, floor.getX(), floor.getY(), floor.getWidth(), floor.getHeight());
		batch.draw(imgBeton, wallLeft.getX(), wallLeft.getY(), wallLeft.getWidth(), wallLeft.getHeight());
		batch.draw(imgBeton, wallRight.getX(), wallRight.getY(), wallRight.getWidth(), wallRight.getHeight());
		batch.draw(imgLightBeton, platform.getX(), platform.getY(), platform.getWidth()/2, platform.getHeight()/2,
				platform.getWidth(), platform.getHeight(), 1, 1, platform.getAngle());
		for (DynamicBody b: balls) {
			if(b.type == TYPE_CIRCLE) img = imgCircle;
			else if(b.type == TYPE_BOX) img = imgBrick;
			else img = imgTriangle;
			batch.draw(img, b.getX(), b.getY(), b.getWidth()/2, b.getHeight()/2, b.getWidth(), b.getHeight(), 1, 1, b.getAngle());
		}*/
        batch.end();
        world.step(1/60f, 6, 2);
    }

    @Override
    public void dispose () {
        batch.dispose();
        imgTextureAtlas.dispose();
        world.dispose();
        debugRenderer.dispose();
    }

    private void scalePolygon(Polygon p, float s) {
        for (int i = 0; i < p.getVertices().length; i++) {
            p.getVertices()[i] *= s;
        }
    }
}
