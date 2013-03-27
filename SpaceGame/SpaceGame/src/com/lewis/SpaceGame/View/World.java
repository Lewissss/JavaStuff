package com.lewis.SpaceGame.View;

import com.lewis.SpaceGame.SpaceGame;

public class World {

	SpaceGame game;
	WorldRenderer wr;
	
	public World(SpaceGame game){
		this.game = game;
	}
	
	public void update(){
		
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
