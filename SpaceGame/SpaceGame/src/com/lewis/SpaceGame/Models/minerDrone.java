package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.lewis.SpaceGame.Models.Asteroid.Asteroid;

public class minerDrone extends MoveableEntity {

	boolean inActive = false;
	
	public minerDrone(float SPEED, float rotation, Vector2 position, float width, float height) {
		super(SPEED, rotation, position, width, height);

	}
	
	public void update(float mapWidth, float mapHeight, Asteroid a){
		
		if(!inActive){
		position.lerp(a.getPosition(), Gdx.graphics.getDeltaTime());
		}
		
		/* TODO choose a target
		-Move towards target.
		-If this. intersects with activation circle, stop.
		- Shoot asteroid
		- move to next (for now).
		*/
		
		if(velocity.x != 0 || velocity.y != 0){
			rotation = velocity.angle() - 90;
		}
		
		checkPosition(mapWidth, mapHeight);
	}
	
	public void moveTowardsAsteroid(Asteroid a){
		
	}
	
	public void overlapActivation(Asteroid a){
		if(this.bounds.overlaps(a.getActivationArea())){
			inActive = true;
		}
	}

}
