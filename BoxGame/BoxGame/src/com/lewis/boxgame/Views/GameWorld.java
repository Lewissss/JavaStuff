package com.lewis.boxgame.Views;

import java.util.Iterator;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.BoxGame;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;
import com.lewis.boxgame.Screens.GameScreen;

public class GameWorld {
	
	private BoxGame game;
	private World world;
	private Level level;
	private Player player;
	private Array<Recharger> rechargers = new Array<Recharger>();
	private Recharger charger;
	private Iterator<Recharger> rIter;
		
	public GameWorld(BoxGame game){
		
		this.game = game;
				
		world = new World(new Vector2(0, 0), false);
		
		player = new Player(world);
		level = new Level(world, player);
		rechargers = level.getRechargers();
	
	}
	
	public void update(){
		
		player.update();
		
		updateRechargers();
		
		endGame();
		
		world.step(1/60f, 6, 2);
	}

	private void endGame() {
		if(player.getBattery() < 0){
			game.setScreen(new GameScreen(game));
		}
	}

	private void updateRechargers() {
		rIter = rechargers.iterator();
		while(rIter.hasNext()){
			charger = rIter.next();
			
			charger.update();
		}
	}
	
	public float getDistance(){
		return player.getBattery();
	}
	
	public void dispose(){
		world.dispose();
	}
	
	public World getWorld(){
		return world;
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public Level getLevel(){
		return level;
	}


}
