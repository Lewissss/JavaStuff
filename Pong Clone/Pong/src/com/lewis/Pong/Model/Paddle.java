package com.lewis.Pong.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.math.Vector2;

public class Paddle extends MoveableEntity {
	
	public Paddle(Vector2 position, float speed, float width, float height) {
		super(position, speed, width, height);
	}
	
	public void update(){
		
		if(Gdx.input.isKeyPressed(Keys.UP) || Gdx.input.isKeyPressed(Keys.W)){
			position.y += SPEED * Gdx.graphics.getDeltaTime();
		}
		
		if(Gdx.input.isKeyPressed(Keys.DOWN) || Gdx.input.isKeyPressed(Keys.S)){
			position.y -= SPEED * Gdx.graphics.getDeltaTime();
		}
		
		bounds.x = position.x;
		bounds.y = position.y;
		
		//Check bounds
		if(bounds.y < 0){
			position.y = 0;
		}
		if(bounds.y > 720 - height){
			position.y = 720 - height;
		}
		
	}

}
