package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.math.Vector2;

public class SpaceStation extends Entity {

	boolean isRed;
	float HEALTH = 1000;
	int LEVEL = 1;
	float RESOURCES = 0;
	boolean isDead = false;
	Vector2 origin;
	Team team;
	
	public SpaceStation(Vector2 position, float width, float height, Team team) {
		super(position, width, height);
		
		this.team = team;
		
		origin = new Vector2(position.x - (width / 2), position.y - (height / 2));
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
	
	public Vector2 getOrigin(){
		return origin;
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
