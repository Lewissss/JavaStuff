package com.lewis.angrymasons.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Ship extends MoveableEntity{

	public Ship(Vector2 position, float width, float height, float rotation, float SPEED) {
		super(SPEED, rotation, width, height, position);
	}

	public void update() {
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));		// Add the velocity to the position, then use .mul to times by delta time and SPEED	
		
		if(velocity.x != 0 || velocity.y != 0)
		{
			rotation = velocity.angle() - 90;	// Make ship rotate towards direction of travel (SO MUCH EASIER!!)
		}
		
		// Set bounds
		bounds.x = position.x;
		bounds.y = position.y;
	}
}
