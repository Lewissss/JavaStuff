package com.lewis.lightGame.View;

import com.lewis.lightGame.LightGame;



public class World {

	LightGame game;
	WorldRenderer wr;
	
	public World(LightGame game){
		this.game = game;
		
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
