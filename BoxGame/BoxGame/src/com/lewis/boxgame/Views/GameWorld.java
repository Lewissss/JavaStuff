package com.lewis.boxgame.Views;

import java.util.Iterator;

import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.BoxGame;
import com.lewis.boxgame.Models.Level;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;

public class GameWorld {
	
	BoxGame game;
	WorldRenderer wr;
	World world;
	
	Level level;
	
	Player player;
	
	Array<Recharger> rechargers = new Array<Recharger>();
	Recharger charger;
	Iterator<Recharger> rIter;
	
	ShapeRenderer sr = new ShapeRenderer();
		
	public GameWorld(BoxGame game){
		
		this.game = game;
				
		world = new World(new Vector2(0, 0), false);
		
		player = new Player(world);
		level = new Level(world, player);
		rechargers = level.getRechargers();
	
	}
	
	public void update(){
		
		player.update();
		
		rIter = rechargers.iterator();
		while(rIter.hasNext()){
			charger = rIter.next();
			
			charger.update();
		}
		
		world.step(1/60f, 6, 2);
	}
	
	public float getDistance(){
		return player.getDistance();
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
