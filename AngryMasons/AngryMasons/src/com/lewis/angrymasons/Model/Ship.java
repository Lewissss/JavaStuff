package com.lewis.angrymasons.Model;

import com.badlogic.gdx.math.Vector2;

public class Ship extends MoveableEntity{

	public Ship(Vector2 position, float width, float height, float rotation, float SPEED) {
		super(SPEED, rotation, width, height, position);
	}

	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}
}
