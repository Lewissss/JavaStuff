package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.math.Vector2;

public class Resource extends MoveableEntity {
	
	public Resource(float SPEED, float rotation, Vector2 position, float width,
			float height) {
		super(SPEED, rotation, position, width, height);
		
		
	}
	
	public void update(){
		
		rotation += SPEED;
		
		if(rotation == 360){
			rotation = 0;
		}
		
		bounds.x = position.x;
		bounds.y = position.y;
	}

}
