package com.lewis.SpaceGame.View;

import java.util.Iterator;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Laser;
import com.lewis.SpaceGame.Models.Miner;
import com.lewis.SpaceGame.Models.Ship;
import com.lewis.SpaceGame.Models.SpaceStation;
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
	
	Array<Miner> miners = new Array<Miner>();
	Iterator<Miner> mIter;
	Miner m;
	
	SpaceStation redTeamStation;
	SpaceStation blueTeamStation;
	
	Random random;
	
	public World(SpaceGame game){
		this.game = game;
		ship = new Ship(15f, 0, new Vector2(10, 10), 1, 1);
		Gdx.input.setInputProcessor(new InputHandler(this));
		
		random = new Random();
		
		spawners.add(new AsteroidSpawn(this, new Vector2(15, 6), 10));
		spawners.add(new AsteroidSpawn(this, new Vector2(20, 20), 16));
		spawners.add(new AsteroidSpawn(this, new Vector2(0, 15), 13));
		spawners.add(new AsteroidSpawn(this, new Vector2(25, 35), 13));
		
		
		// SpaceStations
		redTeamStation = new SpaceStation(new Vector2(3, 3), 6, 6, true);
		blueTeamStation = new SpaceStation(new Vector2(10, 10), 6, 6, false);
		
		sIter = spawners.iterator();
		while(sIter.hasNext()){
			spawner = sIter.next();
			asteroids.addAll(spawner.getAsteroids());	// Add all the asteroids spawned to the asteroids list
		}	
		
		miners.add(new Miner(6f, 9, new Vector2(10, 10), 1, 1, this, asteroids.random()));
		miners.add(new Miner(6f, 9, new Vector2(15, 10), 1, 1, this, asteroids.random()));
		miners.add(new Miner(6f, 9, new Vector2(20, 10), 1, 1, this, asteroids.random()));
		miners.add(new Miner(6f, 9, new Vector2(25, 10), 1, 1, this, asteroids.random()));
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){		
		ship.update(wr.backgroundTexture.getWidth() / wr.width, wr.backgroundTexture.getHeight() / wr.height);
		updateLasers();	
		updateAsteroids();
		
		blueTeamStation.update();
		redTeamStation.update();
		
		blueTeamStation.getPosition().x = (wr.backgroundTexture.getWidth() / wr.width) - (blueTeamStation.getWidth()) - 3;
		blueTeamStation.getPosition().y = (wr.backgroundTexture.getHeight() / wr.height) - (blueTeamStation.getHeight()) - 3;
		
		updateMiners();
	}

	private void updateMiners() {
		//Update miner
		mIter = miners.iterator();
		while(mIter.hasNext()){
			m = mIter.next();
			m.update(wr.backgroundTexture.getWidth(), wr.backgroundTexture.getHeight());
		}
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

	public void checkBulletCollision() {
		// Check if bullets in map
		if(l.getPosition().x < 0 || l.getPosition().x > wr.backgroundTexture.getWidth() / wr.width){
			l.setVisible(false);
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
		
		if(l.getBounds().overlaps(blueTeamStation.getBounds())){
			if(l.getIsRed()){
				blueTeamStation.damage(l.getDamage());
				l.setVisible(false);
			}
		}
		
		if(l.getBounds().overlaps(redTeamStation.getBounds())){
			if(!l.getIsRed()){
				redTeamStation.damage(l.getDamage());
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
	
	public Array<Miner> getMiners(){
		return miners;
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
	
	public SpaceStation getBlueTeam(){
		return blueTeamStation;
	}
	
	public SpaceStation getRedTeam(){
		return redTeamStation;
	}
	
	public void dispose(){
		wr.dispose();
	}
	
}
