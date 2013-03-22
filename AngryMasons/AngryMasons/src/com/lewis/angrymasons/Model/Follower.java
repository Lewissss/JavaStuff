package com.lewis.angrymasons.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Follower extends MoveableEntity {

	float ROTATION_SPEED = 500;
	
	public Follower(float SPEED, float rotation, float width, float height,
			Vector2 position) {
		super(SPEED, rotation, width, height, position);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void update(Ship ship) {
		position.lerp(ship.getPosition(), Gdx.graphics.getDeltaTime());		// Smooth curve
		
		rotation += Gdx.graphics.getDeltaTime() * ROTATION_SPEED;
		
		if(rotation > 360)
		{
			rotation -= 360;
		}
		
		super.update(ship);
	}

}
