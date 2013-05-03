package com.lewis.Pong.Model;

import com.badlogic.gdx.math.Vector2;

public class MoveableEntity extends Entity {
	
	float SPEED;
	
	public MoveableEntity(Vector2 position, float speed, float width, float height) {
		super(position, width, height);
		
		this.SPEED = speed;
	}
	
	public void update(){
		
		bounds.x = position.x;
		bounds.y = position.y;
	}

}
