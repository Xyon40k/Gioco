//#region imports
package com.notsodivinedestiny.game;
import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.utils.ScreenUtils;
//#endregion

public class NotSoDivineDestiny extends ApplicationAdapter {
	OrthographicCamera cam;
	SpriteBatch batch;
	Player player;
	StaticElement platform;
	World world;
	Texture bg;

	float accumulator = 0;
	float timeperstep = 1/60f;

	@Override
	public void create () {
		batch = new SpriteBatch();

		world = new World(new Vector2(0, -25), true);
		world.setContactListener(new ListenerClass());

		float w = Gdx.graphics.getWidth();
		float h = Gdx.graphics.getHeight();
		cam = new OrthographicCamera();
		cam.setToOrtho(false, 30, 30 * (h / w));

		bg = new Texture("ground/2 background/Background.png");
		player = new Player(0, 30, world, "sprites/1/player.png");
		platform = new StaticElement(0,-30, 100, 20, world, "ground/1 Tiles/IndustrialTile_77.png");
	}

	@Override
	public void render () {
		input();

		player.update();

		worldStep(timeperstep);

		ScreenUtils.clear(1, 0, 0, 1);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		batch.begin();
		batch.draw(bg, 0, 0);
		platform.draw(batch);
		player.draw(batch);
		batch.end();
	}

	private void worldStep(float deltaTime) {
		float frameTime = Math.min(deltaTime, 0.25f);
		accumulator += frameTime;
		while (accumulator >= timeperstep) {
			world.step(timeperstep, 6, 2);
			accumulator -= timeperstep;
		}
	}


	@Override
	public void dispose () {
		batch.dispose();
	}


	private void input() {
		if(Gdx.input.isKeyPressed(Keys.LEFT)) {
			cam.position.set(new Vector2(cam.position.x-1,cam.position.y),0f);
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT)) {
			cam.position.set(new Vector2(cam.position.x+1,cam.position.y),0f);
		}
		if(Gdx.input.isKeyPressed(Keys.DOWN)) {
			cam.position.set(new Vector2(cam.position.x,cam.position.y-1),0f);
		}
		if(Gdx.input.isKeyPressed(Keys.UP)) {
			cam.position.set(new Vector2(cam.position.x,cam.position.y+1),0f);
		}
		if(Gdx.input.isKeyPressed(Keys.Z)) {
			cam.zoom += .1;
		}
		if(Gdx.input.isKeyPressed(Keys.X)) {
			cam.zoom -= .1;
		}

		if(Gdx.input.isKeyPressed(Keys.A)) {
			player.move(20);
		}
		if(Gdx.input.isKeyPressed(Keys.D)) {
			player.move(-20);
		}
		if(Gdx.input.isKeyPressed(Keys.SPACE)) {
			player.jump(50);
		}
	}



	public class ListenerClass implements ContactListener {
		@Override
		public void beginContact(Contact contact) {
			// TODO Auto-generated method stub
		}

		@Override
		public void endContact(Contact contact) {
			// TODO Auto-generated method stub
		}

		@Override
		public void preSolve(Contact contact, Manifold oldManifold) {
			// TODO Auto-generated method stub
		}

		@Override
		public void postSolve(Contact contact, ContactImpulse impulse) {
			// TODO Auto-generated method stub
		}
	}
}
