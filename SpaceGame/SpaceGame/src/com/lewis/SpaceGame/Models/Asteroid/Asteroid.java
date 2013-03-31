package com.lewis.SpaceGame.Models.Asteroid;

import java.util.Random;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lewis.SpaceGame.Models.Entity;

public class Asteroid extends Entity {

	public float HEALTH = 20f;
	
	float rotation;
	Rectangle bounds;
	float SPEED;
	float RADIUS = 4f;
	
	Circle activationArea;
	
	boolean isDead = false;
	
	Random random;
	
	public Asteroid(Vector2 position, float width, float height) {
		super(position, width, height);

		random = new Random();
		
		SPEED = random.nextInt(2);
		
		if(SPEED == 0){
			SPEED = 1;
		}
		
		if(HEALTH < 0){
			isDead = true;
		}
		
		rotation = random.nextInt(360);
		
		bounds = new Rectangle(position.x, position.y, width, height);
		
		//Activation area used for mining drones. No need to update because they don't move.
		activationArea = new Circle(new Vector2(position.x + ((RADIUS / 2) / 2) - width / 2, position.y + ((RADIUS / 2) / 2) - height / 2), RADIUS);
	}
	
	public void update(){
		rotation += SPEED;
		
		if(rotation == 360){
			rotation = 0;
		}
	}
	
	public boolean getStatus(){
		return isDead;
	}
	
	public Circle getActivationArea(){
		return activationArea;
	}
	
	public float getRotation(){
		return rotation;
	}

}
