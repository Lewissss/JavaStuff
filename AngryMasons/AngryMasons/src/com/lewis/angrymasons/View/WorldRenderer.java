package com.lewis.angrymasons.View;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.lewis.angrymasons.Model.Bullet;
import com.lewis.angrymasons.Model.Follower;
import com.lewis.angrymasons.Model.Ship;

public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	Ship ship;
	OrthographicCamera cam;
	Texture shipTexture, followerTexture, bulletTexture;
	float width, height;
	ShapeRenderer sr;
	Follower follow;
	Array<Bullet> bullets;
	Iterator<Bullet> bIter;
	Bullet b;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
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
		
		bulletTexture = new Texture("data/bullet.png");
		bulletTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sr = new ShapeRenderer();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ship = world.getShip();	// Get ship position
		follow = world.getFollower();
		bullets = world.getBullets();
		
		cam.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2,  ship.getHeight() / 2,  ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		batch.draw(followerTexture, follow.getPosition().x, follow.getPosition().y, follow.getWidth() / 2, follow.getHeight() / 2, follow.getWidth(), follow.getHeight(), 1, 1, follow.getRotation(),
				0, 0, followerTexture.getWidth(), followerTexture.getHeight(), false, false);
		
		bIter = bullets.iterator();
		while(bIter.hasNext()){
			b = bIter.next();
			batch.draw(bulletTexture, b.getPosition().x, b.getPosition().y, b.getWidth() / 2, b.getHeight() / 2, b.getWidth(), b.getHeight(), 1, 1, b.getRotation(),
					0, 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false);
		}
		
		batch.end();
		
		// For debug, render a shape around the ship
		sr.setProjectionMatrix(cam.combined);
		sr.begin(ShapeType.Rectangle);
		sr.setColor(Color.CYAN);
		sr.rect(ship.getBounds().x, ship.getBounds().y, ship.getBounds().width, ship.getBounds().height);
		sr.setColor(Color.RED);
		sr.rect(follow.getBounds().x, follow.getBounds().y, follow.getBounds().width, follow.getBounds().width);
		sr.end();
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void dispose(){
		batch.dispose();
		shipTexture.dispose();
		followerTexture.dispose();
		sr.dispose();
	}
}
