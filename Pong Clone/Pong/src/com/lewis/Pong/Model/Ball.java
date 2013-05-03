package com.lewis.Pong.Model;

import com.badlogic.gdx.math.Vector2;

public class Ball extends MoveableEntity {
	
	Vector2 velocity;
	
	public Ball(Vector2 position, float speed, float width, float height) {
		super(position, speed, width, height);
		
		velocity.x = speed;
	}
	
	public void update(){
		position.add(velocity);
	}

}
