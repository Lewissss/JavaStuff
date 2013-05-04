package com.lewis.boxgame.Views;

import box2dLight.ConeLight;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;

public class WorldRenderer {

	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	Box2DDebugRenderer render;
	LightRenderer lRender;

	BitmapFont font;

	float width, height;

	float distance;

	GameWorld gWorld;
	World world;
	Level level;

	Player player;

	Array<ConeLight> lights = new Array<ConeLight>();
	Array<Recharger> rechargers = new Array<Recharger>();

	public WorldRenderer(GameWorld gworld){

		this.gWorld = gworld;
		world = gWorld.getWorld();
		level = gWorld.getLevel();

		lRender = new LightRenderer(gWorld, world, this);

		rechargers = level.getRechargers();

		font = new BitmapFont();
		font.setScale(0.5f);

		render = new Box2DDebugRenderer();

		width = Gdx.graphics.getWidth() / 5;
		height = Gdx.graphics.getHeight() / 5;

		camera = new OrthographicCamera(width, height);
		//camera.position.set(width * 0.5f, height * 0.5f, 0); // Sets 0 in the middle of the screen
		camera.update();

		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);	
	}

	public void render(){

		player = gWorld.getPlayer();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		// Render world
		render.render(world, camera.combined);

		spriteBatch.setProjectionMatrix(camera.combined);

		camera.position.set(player.getPosition().x,	player.getPosition().y, 0);
		lRender.render();
		camera.update();

		spriteBatch.begin();
		
		font.draw(spriteBatch, "Remaining battery: " + player.getBattery(), player.getPosition().x - (width / 2) + 3, player.getPosition().y + (height / 2) - 2);

		spriteBatch.end();	
	}


	public void dispose(){
		world.dispose();
		lRender.dispose();
	}

	public OrthographicCamera getCamera(){
		return camera;
	}

	public Array<Recharger> getRechargers(){
		return rechargers;
	}

	public Array<ConeLight> getConeLights(){
		return lights;
	}
}
