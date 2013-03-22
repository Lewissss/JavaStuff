package com.lewis.angrymasons.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.lewis.angrymasons.AngryMasons;
import com.lewis.angrymasons.Model.Follower;
import com.lewis.angrymasons.Model.Ship;

public class World {
	
	AngryMasons game;
	Ship ship;
	Follower follower;
	
	public World(AngryMasons game){
		this.game = game;
		ship = new Ship(new Vector2(5, 5), 1, 1, 0, 5f);
		follower = new Follower(5f, 0, 1, 1, new Vector2(10, 10));
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public Follower getFollower(){
		return follower;
	}
	
	public void update(){
		ship.update();
		follower.update(ship);
		
		if(ship.getBounds().overlaps(follower.getBounds())){
			Gdx.app.log(AngryMasons.LOG, "Ship hitt!!");
		}
	}
	
	public void dispose(){
		
	}
}
