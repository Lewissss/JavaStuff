package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Laser extends MoveableEntity {

	public static float SPEED = 6.8f;
	public static float DAMAGE = 10f;
	public static float MAX_LIFE = 30f;
	
	boolean isVisible = true;
	boolean isDead = false;
	float lifeTimer = 0;
	
	
	public Laser(float SPEED, float rotation, Vector2 position, float width, float height, Vector2 velocity) {
		super(SPEED, rotation, position, width, height);
		this.velocity = velocity;
	}
	
	@Override
	public void update(Ship ship){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		
		rotation = velocity.angle() - 90;
		
		bounds.x = position.x;
		bounds.y = position.y;
		
		checkLifeTimer();
		
		super.update(ship);
	}
	
	public void checkLifeTimer(){
		lifeTimer += Gdx.graphics.getDeltaTime();
		
		if(lifeTimer >= MAX_LIFE){
			isDead = true;
		}
	}
	
	public float getDamage(){
		return DAMAGE;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
	
	public void setDead(boolean dead){
		this.isDead = dead;
	}
	
	public void setVisible(boolean visible){
		this.isVisible = visible;
	}
	
	public boolean getVisible(){
		return isVisible;
	}

}
