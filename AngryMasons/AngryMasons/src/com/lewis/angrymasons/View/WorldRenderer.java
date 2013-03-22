package com.lewis.angrymasons.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.lewis.angrymasons.Model.Follower;
import com.lewis.angrymasons.Model.Ship;

public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	Ship ship;
	OrthographicCamera cam;
	Texture shipTexture, followerTexture;
	float width, height;
	ShapeRenderer sr;
	Follower follow;
	
	public WorldRenderer(World world){
		this.world = world;
		
		width = Gdx.graphics.getWidth() / 40;
		height = Gdx.graphics.getHeight() / 40;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		shipTexture = new Texture("data/ship.png");
		shipTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		followerTexture = new Texture("data/follower.png");
		followerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sr = new ShapeRenderer();
		sr.setColor(Color.CYAN);
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ship = world.getShip();	// Get ship position
		follow = world.getFollower();
		
		cam.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2,  ship.getHeight() / 2,  ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		batch.draw(followerTexture, follow.getPosition().x, follow.getPosition().y, follow.getWidth() / 2, follow.getHeight() / 2, follow.getWidth(), follow.getHeight(), 1, 1, follow.getRotation(),
				0, 0, followerTexture.getWidth(), followerTexture.getHeight(), false, false);
		
		batch.end();
		
		// For debug, render a shape around the ship
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Rectangle);
		sr.rect(ship.getPosition().x, ship.getPosition().y, ship.getWidth(), ship.getHeight());
		sr.end();
	}
	
	public void dispose(){
		batch.dispose();
		shipTexture.dispose();
	}
}
