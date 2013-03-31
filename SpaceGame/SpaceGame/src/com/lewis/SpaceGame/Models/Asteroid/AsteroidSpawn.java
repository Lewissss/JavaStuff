package com.lewis.SpaceGame.Models.Asteroid;

import java.util.Iterator;
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
	Iterator<Asteroid> aIter;
	Asteroid asteroid;
	float width, height;
	
	Random random;
	
	public AsteroidSpawn(World world, Vector2 position, int maxAsteroids){
		this.world = world;
		this.position = position;
		
		spawnArea = new Rectangle(position.x, position.y, 450 / 40, 450 / 40);
		
		random = new Random();
		
		//Populate asteroid field
		int n = random.nextInt(maxAsteroids);
		
		// Stop there been empty asteroid fields
		if(n <= 1){
			n = 2;
		}
		
	    for(int i = 0; i < n; i++){
	    	
			int j = random.nextInt((int)spawnArea.width);
			int x = random.nextInt((int)spawnArea.height);
	    	
			asteroids.add(new Asteroid(new Vector2(position.x + j,position.y + x), 1, 1));
			
	    }
	    
		width = spawnArea.width;
		height = spawnArea.height;
	}
	
	public Array<Asteroid> getAsteroids(){
		return asteroids;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	
}
