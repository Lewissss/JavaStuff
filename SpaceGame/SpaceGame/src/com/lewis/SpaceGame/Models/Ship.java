package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ship extends MoveableEntity {

	public Ship(float SPEED, float rotation, Vector2 position, float width, float height) {
		super(SPEED, rotation, position, width, height);
	}
	
	public void update(){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		
		if(velocity.x != 0 || velocity.y != 0){
			rotation = velocity.angle() - 90;
		}
		
		// Reset the bounds
		bounds.x = position.x;
		bounds.y = position.y; 
	}
	
	public void checkPosition(float mapWidth, float mapHeight){
		
		if(bounds.x < 0 || bounds.x > mapWidth){
			position.x = 0;
		}
		if(bounds.y < 0 || bounds.y > mapHeight){
			position.y = 0;
		}
	}

}
