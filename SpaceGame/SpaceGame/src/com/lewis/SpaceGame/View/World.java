package com.lewis.SpaceGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Ship;

public class World {

	SpaceGame game;
	WorldRenderer wr;
	Ship ship;
	float width, height;
	
	public World(SpaceGame game){
		this.game = game;
		ship = new Ship(5f, 0, new Vector2(10, 10), 1, 1);
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){
		ship.update();
		ship.checkPosition(wr.backgroundTexture.getWidth(), wr.backgroundTexture.getHeight());
	}
	
	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public WorldRenderer getRenderer(){
		return wr;
	}
	
	public void dispose(){
	}
	
}
