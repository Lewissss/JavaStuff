package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.math.Vector2;

public class SpaceStation extends Entity {

	boolean isRed;
	float HEALTH = 1000;
	int LEVEL = 1;
	float RESOURCES = 0;
	boolean isDead = false;
	
	public SpaceStation(Vector2 position, float width, float height, boolean isRed) {
		super(position, width, height);
		
		this.isRed = isRed;
	}
	
	public void update(){
		if(HEALTH <= 0){
			isDead = true;
		}
		
		bounds.x = position.x;
		bounds.y = position.y;
		bounds.width = width;
		bounds.height = height;
	}
	
	public boolean getTeam(){
		return isRed;
	}
	
	public float getHealth(){
		return HEALTH;
	}
	
	public void setHealth(float HEALTH){
		this.HEALTH = HEALTH;
	}
	
	public int getLevel(){
		return LEVEL;
	}
	
	public float getResources(){
		return RESOURCES;
	}
	
	public boolean getIsDead(){
		return isDead;
	}
	
	public void setResources(float r){
		this.RESOURCES = r;
	}
	
	public void damage(float damage){
		HEALTH -= damage;
	}

}
