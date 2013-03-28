package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

public class Laser extends MoveableEntity {

	public static float SPEED = 6.8f;
	
	public Laser(float SPEED, float rotation, Vector2 position, float width, float height, Vector2 velocity) {
		super(SPEED, rotation, position, width, height);
		this.velocity = velocity;
	}
	
	@Override
	public void update(Ship ship){
		position.add(velocity.tmp().mul(Gdx.graphics.getDeltaTime() * SPEED));
		
		rotation = velocity.angle() - 90;
		
		bounds.x = position.x;
		bounds.y = position.y;
		
		super.update(ship);
	}

}
