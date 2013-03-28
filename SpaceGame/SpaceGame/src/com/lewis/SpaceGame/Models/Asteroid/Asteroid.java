package com.lewis.SpaceGame.Models.Asteroid;

import java.util.Random;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lewis.SpaceGame.Models.Entity;

public class Asteroid extends Entity {

	float rotation;
	Rectangle bounds;
	float SPEED;
	
	Random random;
	
	public Asteroid(Vector2 position, float width, float height) {
		super(position, width, height);

		random = new Random();
		
		SPEED = random.nextInt(4);
		
		rotation = random.nextInt(360);
		
		bounds = new Rectangle(position.x, position.y, width, height);
	}
	
	public void update(){
		rotation += SPEED;
		
		if(rotation == 360){
			rotation = 0;
		}
	}
	
	public float getRotation(){
		return rotation;
	}

}
