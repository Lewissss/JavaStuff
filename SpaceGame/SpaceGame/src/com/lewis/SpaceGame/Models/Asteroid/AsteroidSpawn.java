package com.lewis.SpaceGame.Models.Asteroid;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lewis.SpaceGame.View.World;

public class AsteroidSpawn {
	
	World world;
	Rectangle spawnArea;
	Vector2 position;
	Array<Asteroid> asteroids = new Array<Asteroid>();
	
	Random random;
	
	public AsteroidSpawn(World world, Vector2 position, int maxAsteroids){
		this.world = world;
		this.position = position;
		
		spawnArea = new Rectangle(position.x, position.y, 100 / 40, 100 / 40);
		
		random = new Random();
		
		//Populate asteroid field
		int n = random.nextInt(maxAsteroids);
		
	    for(int i = 0; i < 4; i++){
	    	
			int j = random.nextInt((int)spawnArea.width);
			int x = random.nextInt((int)spawnArea.height);
	    	
			asteroids.add(new Asteroid(new Vector2(j, x), 1, 1));
		}
		
	}
	
	public Array<Asteroid> getAsteroids(){
		return asteroids;
	}
	
	
}
