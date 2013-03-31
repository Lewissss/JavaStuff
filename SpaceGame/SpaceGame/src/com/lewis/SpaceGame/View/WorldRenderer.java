package com.lewis.SpaceGame.View;

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
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Laser;
import com.lewis.SpaceGame.Models.Ship;
import com.lewis.SpaceGame.Models.Asteroid.Asteroid;
import com.lewis.SpaceGame.Models.Asteroid.AsteroidSpawn;

public class WorldRenderer {
	
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	float width, height;
	ShapeRenderer sr;
	ShapeRenderer cr;	// For circles
	
	Ship ship;
	World world;

	Array<Laser> lasers = new Array<Laser>();
	Iterator<Laser> lIter;
	Laser laser;
	
	Array<Asteroid> asteroids = new Array<Asteroid>();
	Iterator<Asteroid> aIter;
	Asteroid asteroid;
	
	Array<AsteroidSpawn> spawners = new Array<AsteroidSpawn>();
	AsteroidSpawn spawn;
	Iterator<AsteroidSpawn> sIter;
	
	//Textures
	Texture shipTexture;
	Texture backgroundTexture;
	Texture laserTexture;
	Texture asteroidTexture;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
		width = Gdx.graphics.getWidth() / 40;
		height = Gdx.graphics.getHeight() / 40;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		shipTexture = new Texture("data/ship.png");
		shipTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		backgroundTexture = new Texture("data/background.png");
		backgroundTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		asteroidTexture = new Texture("data/asteroid.png");
		asteroidTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		laserTexture = new Texture("data/laser.png");
		
		sr = new ShapeRenderer();
		cr = new ShapeRenderer();
		
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ship = world.getShip();
		lasers = world.getLasers();
		asteroids = world.getAsteroids();
		spawners = world.getSpawners();
		
		camera.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
	
		spriteBatch.draw(backgroundTexture, 0,  0,  backgroundTexture.getWidth() / width, backgroundTexture.getHeight() / height, 1, 1, backgroundTexture.getWidth(), backgroundTexture.getHeight(), false, false);
		
		spriteBatch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2, ship.getHeight() / 2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(),
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		// Draw lasers
		lIter = lasers.iterator();
		while(lIter.hasNext()){
			laser = lIter.next();
			if(laser.getVisible())	// Only render the visible lasers
			{
				spriteBatch.draw(laserTexture, laser.getPosition().x, laser.getPosition().y, laser.getWidth() / 2, laser.getHeight() / 2, laser.getWidth(), laser.getHeight(), 1, 1, laser.getRotation(),
						0, 0, laserTexture.getWidth(), laserTexture.getHeight(), false, false);
			}
		}
		
		// Draw asteroids
		aIter = asteroids.iterator();
		while(aIter.hasNext()){
			asteroid = aIter.next();
			spriteBatch.draw(asteroidTexture, asteroid.getPosition().x, asteroid.getPosition().y, asteroid.getWidth() / 2, asteroid.getHeight() / 2, asteroid.getWidth(), asteroid.getHeight(), 1, 1, asteroid.getRotation(),
					0, 0, asteroidTexture.getWidth(), asteroidTexture.getHeight(), false, false);
		}
		
		spriteBatch.end();
		
		
		if(SpaceGame.DEBUG){
			renderShapes();
		}
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
	
	public void dispose(){
		spriteBatch.dispose();
		shipTexture.dispose();
		backgroundTexture.dispose();
		laserTexture.dispose();
		sr.dispose();
	}
	
	public void renderShapes(){
		sr.setProjectionMatrix(camera.combined);
		cr.setProjectionMatrix(camera.combined);
		
		sr.begin(ShapeType.Rectangle);
		
		//Render ship shape
		sr.setColor(Color.CYAN);
		sr.rect(ship.getBounds().x, ship.getBounds().y, ship.getWidth(), ship.getHeight());
		
		//Render mapshape
		sr.setColor(Color.RED);
		sr.rect(0, 0, backgroundTexture.getWidth() / width, backgroundTexture.getHeight() / height);
		
		// Render lasers
		lIter = lasers.iterator();
		while(lIter.hasNext()){
			laser = lIter.next();
			if(laser.getVisible())
			{
				sr.setColor(Color.PINK);
				sr.rect(laser.getBounds().x, laser.getBounds().y, laserTexture.getWidth() / (width / 2), laserTexture.getHeight() / (height / 2));
			}
		}
		
		cr.begin(ShapeType.Circle);
		// Render asteroids
		aIter = asteroids.iterator();
		while(aIter.hasNext()){
			asteroid = aIter.next();
			sr.setColor(Color.GREEN);
			sr.rect(asteroid.getBounds().x, asteroid.getBounds().y, asteroid.getBounds().width, asteroid.getBounds().height);
			
			sr.setColor(Color.WHITE);
			sr.rect(asteroid.getActivationArea().x, asteroid.getActivationArea().y, asteroid.getActivationArea().width, asteroid.getActivationArea().height);
		}
		cr.end();
		
		// Render asteroid field
		sIter = spawners.iterator();
		while(sIter.hasNext()){
			spawn = sIter.next();
			
			sr.setColor(Color.YELLOW);
			sr.rect(spawn.getPosition().x, spawn.getPosition().y, spawn.getWidth(), spawn.getHeight());
		}
		
		sr.end();
	}

}
