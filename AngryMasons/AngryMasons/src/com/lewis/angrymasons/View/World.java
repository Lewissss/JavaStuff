package com.lewis.angrymasons.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.lewis.angrymasons.AngryMasons;
import com.lewis.angrymasons.Model.Ship;

public class World {
	
	AngryMasons game;
	Ship ship;
	
	public World(AngryMasons game){
		this.game = game;
		ship = new Ship(new Vector2(5, 5), 1, 1, 0, 5f);
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){
		ship.update();
	}
	
	public void dispose(){
		
	}
}
