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
import com.lewis.angrymasons.Model.Enemy;
import com.lewis.angrymasons.Model.Ship;

public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	Ship ship;
	OrthographicCamera cam;
	Texture shipTexture, followerTexture, bulletTexture;
	float width, height;
	ShapeRenderer sr;
	Array<Bullet> bullets;
	Array<Enemy> enemies;
	Iterator<Bullet> bIter;
	Iterator<Enemy> eIter;
	Bullet b;
	Enemy e;
	
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
		enemies = world.getEnemies();
		bullets = world.getBullets();
		
		cam.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2,  ship.getHeight() / 2,  ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		
		eIter = enemies.iterator();
		
		while(eIter.hasNext()){	// Draw all enemies
			e = eIter.next();	// Loop through
		batch.draw(followerTexture, e.getPosition().x, e.getPosition().y, e.getWidth() / 2, e.getHeight() / 2, e.getWidth(), e.getHeight(), 1, 1, e.getRotation(),
				0, 0, followerTexture.getWidth(), followerTexture.getHeight(), false, false);
		}
		
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
		
		while(eIter.hasNext()){	// DrawRectangle for all enemies
			e = eIter.next();	// Loop through
			sr.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
		}
		
		bIter = bullets.iterator();	// DrawRectangle for all bullets
		while(bIter.hasNext()){
			b = bIter.next();
			sr.rect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
		}
		
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
