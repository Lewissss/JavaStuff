package com.lewis.SpaceGame.View;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Laser;
import com.lewis.SpaceGame.Models.Ship;
import com.lewis.SpaceGame.Models.Asteroid.Asteroid;
import com.lewis.SpaceGame.Models.Asteroid.AsteroidSpawn;

public class World {

	SpaceGame game;
	WorldRenderer wr;
	Ship ship;
	float width, height;
	
	Array<Laser> lasers = new Array<Laser>();
	Iterator<Laser> lIter;
	Laser l;
	
	Array<AsteroidSpawn> spawners = new Array<AsteroidSpawn>();
	Iterator<AsteroidSpawn> sIter;
	AsteroidSpawn spawner;
	
	Array<Asteroid> asteroids = new Array<Asteroid>();
	Iterator<Asteroid> aIter;
	Asteroid a;
	
	Random random;
	
	public World(SpaceGame game){
		this.game = game;
		ship = new Ship(10f, 0, new Vector2(10, 10), 1, 1);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
		random = new Random();
		
		spawners.add(new AsteroidSpawn(this, new Vector2(10 / 40, 10/ 40), 10));
		spawners.add(new AsteroidSpawn(this, new Vector2(20, 20), 16));
		spawners.add(new AsteroidSpawn(this, new Vector2(10 / 40, 15), 13));
		spawners.add(new AsteroidSpawn(this, new Vector2(35, 35), 13));
		
		sIter = spawners.iterator();
		while(sIter.hasNext()){
			spawner = sIter.next();
			asteroids.addAll(spawner.getAsteroids());	// Add all the asteroids spawned to the asteroids list
		}	
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){
		ship.update(wr.backgroundTexture.getWidth() / wr.width, wr.backgroundTexture.getHeight() / wr.height);
		
		updateLasers();	
		
		updateAsteroids();
		
	}

	private void updateAsteroids() {
		aIter = asteroids.iterator();
		while(aIter.hasNext()){
			a = aIter.next();
			a.update();
			
			if(a.getStatus()){
				aIter.remove();
			}
		}
	}

	private void updateLasers() {
		lIter = lasers.iterator();
		while(lIter.hasNext()){
			l = lIter.next();
			
			if(l.getVisible()){			// Only check collisions when visible
				checkBulletCollision();
			}
			
			if(l.getIsDead()){	// Remove dead lasers
				lIter.remove();
			}
			
			l.update(ship);
		}
	}

	private void checkBulletCollision() {
		// Check if bullets in map
		if(l.getPosition().x < 0 || l.getPosition().x > wr.backgroundTexture.getWidth() / wr.width){
			l.setVisible(false);;
		}
		if(l.getPosition().y < 0 || l.getPosition().y > wr.backgroundTexture.getHeight() / wr.height){
			l.setVisible(false);
		}
		
		aIter = asteroids.iterator();
		while(aIter.hasNext()){
			a = aIter.next();
			
			// Check bullets against asteroids
			if(l.getBounds().overlaps(a.getBounds())){
				a.damageAsteroid(l.getDamage());
				l.setVisible(false);
			}
		}
	}
	
	public Array<AsteroidSpawn> getSpawners(){
		return spawners;
	}
	
	public void addLaser(Laser l){
		lasers.add(l);
	}
	
	public Array<Laser> getLasers(){
		return lasers;
	}
	
	public Array<Asteroid> getAsteroids(){
		return asteroids;
	}
	
	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public WorldRenderer getRenderer(){
		return wr;
	}
	
	public void dispose(){
		wr.dispose();
	}
	
}
