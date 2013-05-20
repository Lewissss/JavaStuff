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

	private SpriteBatch spriteBatch;
	private SpriteBatch GUI;
	private OrthographicCamera camera;
	private LightRenderer lRender;
	private BitmapFont font;
	private float width, height;
	private GameWorld gWorld;
	private World world;
	private Level level;
	private Player player;
	private Array<ConeLight> lights = new Array<ConeLight>();
	private Array<Recharger> rechargers = new Array<Recharger>();

	public WorldRenderer(GameWorld gworld){

		this.gWorld = gworld;
		world = gWorld.getWorld();
		level = gWorld.getLevel();

		lRender = new LightRenderer(gWorld, world, this);

		rechargers = level.getRechargers();

		font = new BitmapFont();
		font.setScale(0.5f);

		new Box2DDebugRenderer();

		width = Gdx.graphics.getWidth() / 4;
		height = Gdx.graphics.getHeight() / 4;

		camera = new OrthographicCamera(width, height);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);	
		
		GUI = new SpriteBatch();
		GUI.setProjectionMatrix(camera.combined);
	
	}

	public void render(){

		player = gWorld.getPlayer();

		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		spriteBatch.setProjectionMatrix(camera.combined);
		GUI.setProjectionMatrix(camera.combined);

		camera.position.set(player.getPosition().x,	player.getPosition().y, 0);
		camera.update();
		
		spriteBatch.begin();
		
		// Draw the level
		level.draw(spriteBatch);
		
		spriteBatch.end();	

		// Render world (DEBUG)
		//render.render(world, camera.combined);
		
		// Render the lights
		lRender.render();
		
		//Draw the GUI after the Light has been updated to avoid it been affected by light
		drawGUI();
	}

	private void drawGUI() {
		GUI.begin();
		
		font.draw(GUI, "Remaining battery: " + (int)player.getBattery() + "%", player.getPosition().x - (width / 2) + 3, player.getPosition().y + (height / 2) - 2);
		
		GUI.end();
	}


	public void dispose(){
		world.dispose();
		lRender.dispose();
		font.dispose();
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
