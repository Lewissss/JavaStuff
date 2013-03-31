package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.math.Vector2;

public class MoveableEntity extends Entity {

	protected Vector2 velocity;
	protected float SPEED;
	protected float rotation;
	protected Vector2 origin;
	
	public MoveableEntity(float SPEED, float rotation, Vector2 position, float width, float height) {
		super(position, width, height);
		this.SPEED = SPEED;
		this.rotation = rotation;
		velocity = new Vector2(0, 0);
		this.origin = new Vector2(position.x + (width / 2), position.y + (height / 2));
	}
	
	public void checkPosition(float mapWidth, float mapHeight){
		
		if(bounds.x < 0){
			position.x = 0;
		}
		if(bounds.x > mapWidth - this.width){
			position.x = mapWidth - this.width;
		}
		
		if(bounds.y < 0){
			position.y = 0;
		}
		
		if(bounds.y > mapHeight - this.height){
			position.y = mapHeight - this.height;
		}
		
		origin = new Vector2(position.x + (width / 2), position.y + (height / 2));
	}
	
	public Vector2 getVelocity(){
		return velocity;
	}
	
	public void setVelocity(Vector2 velocity){
		this.velocity = velocity;
	}
	
	public float getRotation(){
		return rotation;
	}
	
	public void setRotation(float rotation){
		this.rotation = rotation;
	}
	
	public void update(Ship ship){
		bounds.x = position.x;
		bounds.y = position.y;
	}
	
	public Vector2 getOrigin(){
		return origin;
	}
}
