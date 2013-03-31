package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ship extends MoveableEntity {
	
	Vector2 origin;

	public Ship(float SPEED, float rotation, Vector2 position, float width, float height) {
		super(SPEED, rotation, position, width, height);
	}
	
	public void update(float mapWidth, float mapHeight){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		
		if(velocity.x != 0 || velocity.y != 0){
			rotation = velocity.angle() - 90;
		}
		
		// Reset the bounds
		bounds.x = position.x;
		bounds.y = position.y; 
		
		checkPosition(mapWidth, mapHeight);
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
	
	public Vector2 getOrigin(){
		return origin;
	}

}
