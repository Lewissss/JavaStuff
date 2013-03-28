package com.lewis.SpaceGame.View;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Laser;
import com.lewis.SpaceGame.Models.Ship;

public class World {

	SpaceGame game;
	WorldRenderer wr;
	Ship ship;
	float width, height;
	
	Array<Laser> lasers = new Array<Laser>();
	Iterator<Laser> lIter;
	Laser l;
	
	public World(SpaceGame game){
		this.game = game;
		ship = new Ship(10f, 0, new Vector2(10, 10), 1, 1);
		Gdx.input.setInputProcessor(new InputHandler(this));
	}
	
	public Ship getShip(){
		return ship;
	}
	
	public void update(){
		ship.update(wr.backgroundTexture.getWidth() / wr.width, wr.backgroundTexture.getHeight() / wr.height);
		
		lIter = lasers.iterator();
		while(lIter.hasNext()){
			l = lIter.next();
			l.update(ship);
		}
	}
	
	public void addLaser(Laser l){
		lasers.add(l);
	}
	
	public Array<Laser> getLasers(){
		return lasers;
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
