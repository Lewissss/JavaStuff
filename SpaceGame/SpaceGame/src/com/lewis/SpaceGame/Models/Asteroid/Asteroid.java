package com.lewis.SpaceGame.Models.Asteroid;

import java.util.Random;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.lewis.SpaceGame.Models.Entity;

public class Asteroid extends Entity {

	protected float HEALTH = 20f;	
	protected float rotation;
	protected Rectangle bounds;
	protected float SPEED;
	protected float RADIUS = 4f;
	protected Rectangle activationArea;
	protected boolean isDead = false;
	float aWidth, aHeight;
	
	Random random;
	
	public Asteroid(Vector2 position, float width, float height) {
		super(position, width, height);

		random = new Random();
		
		SPEED = random.nextInt(2);
		
		if(SPEED == 0){
			SPEED = 1;
		}
		
		rotation = random.nextInt(360);
		
		bounds = new Rectangle(position.x, position.y, width - 1, height - 1);
		
		aWidth = width * 5;
		aHeight = height * 5;
		
		//Activation area used for mining drones. No need to update because they don't move.
		activationArea = new Rectangle(bounds.x - (aWidth / 2) + (width / 2), bounds.y - (aHeight / 2) + (height / 2), aWidth, aHeight);
	}
	
	public void update(){
		rotation += SPEED;
		
		if(rotation == 360){
			rotation = 0;
		}
		
		if((int)HEALTH <= 0){
			isDead = true;
		}
	}
	
	public boolean getStatus(){
		return isDead;
	}
	
	public void damageAsteroid(float damage){
		HEALTH -= damage;
	}
	
	public Rectangle getActivationArea(){
		return activationArea;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public float getHealth(){
		return HEALTH;
	}

}
