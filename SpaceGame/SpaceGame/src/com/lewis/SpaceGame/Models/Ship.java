package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ship extends MoveableEntity {

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
	
	

}
