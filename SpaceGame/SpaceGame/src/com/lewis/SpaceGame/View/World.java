package com.lewis.SpaceGame.View;

import java.util.Iterator;

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
	
	AsteroidSpawn spawn1;
	
	Array<Asteroid> asteroids = new Array<Asteroid>();
	Iterator<Asteroid> aIter;
	Asteroid a;
	
	public World(SpaceGame game){
		this.game = game;
		ship = new Ship(10f, 0, new Vector2(10, 10), 1, 1);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
		spawn1 = new AsteroidSpawn(this, new Vector2(10 / 40, 10 / 40), 10);	//Create the spawner
		asteroids = spawn1.getAsteroids();	//Populate this array with the spawner asteroids
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){
		ship.update(wr.backgroundTexture.getWidth() / wr.width, wr.backgroundTexture.getHeight() / wr.height);
		
		lIter = lasers.iterator();
		while(lIter.hasNext()){
			l = lIter.next();
			l.update(ship);
			
			checkBulletBounds();
		}	
		
		aIter = asteroids.iterator();
		while(aIter.hasNext()){
			a = aIter.next();
			a.update();
			Gdx.app.log(SpaceGame.LOG, a.getPosition().toString());
		}
	}

	private void checkBulletBounds() {
		// Check if bullets in map
		if(l.getPosition().x < 0 || l.getPosition().x > wr.backgroundTexture.getWidth() / wr.width){
			lIter.remove();
		}
		if(l.getPosition().y < 0 || l.getPosition().y > wr.backgroundTexture.getHeight() / wr.height){
			lIter.remove();
		}
	}
	
	public AsteroidSpawn getSpawner(){
		return spawn1;
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
	}
	
}
