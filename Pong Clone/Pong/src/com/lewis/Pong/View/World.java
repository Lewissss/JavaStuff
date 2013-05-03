package com.lewis.Pong.View;

import com.badlogic.gdx.math.Vector2;
import com.lewis.Pong.Pong;
import com.lewis.Pong.Model.Paddle;

public class World {
	
	Pong game;
	
	WorldRenderer renderer;
	
	Paddle paddle;
	
	public World(Pong game){
		this.game = game;	

		paddle = new Paddle(new Vector2(15, 250), 350f, 14, 61);
	}
	
	public void update(){
		paddle.update();
	}
	
	public void setRenderer(WorldRenderer renderer){
		this.renderer = renderer;
	}
	
	public Paddle getPaddle(){
		return paddle;
	}
	


}
