package com.lewis.boxgame.Views;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.utils.Array;
import com.lewis.boxgame.Models.Player;
import com.lewis.boxgame.Models.Recharger;
import com.lewis.boxgame.Models.Tile;

public class Level {

	World world;
	Player player;

	Array<Tile> tiles;
	Array<Recharger> rechargers;

	public Level(World world, Player player){
		this.world = world; 
		this.player = player;

		tiles = new Array<Tile>();
		rechargers = new Array<Recharger>();

		readLevel();
	}

	public Array<Tile> getTiles(){
		return tiles;
	}

	public Array<Recharger> getRechargers(){
		return rechargers;
	}

	public void readLevel(){

		String[] map = new String[17];

		map[0] = "11111111111111111111111111111111111111111111111111111111";
		map[1] = "10000000000000000000000000000000000000000000000000000001";
		map[2] = "10000000000000000000000000000000000000000000000000000001";
		map[3] = "10000000000000000000000000000000000000000000000000000001";
		map[4] = "10000000000000000000000000000000000000000000000000000001";
		map[5] = "11110000011111111111110000011111111111111111100000000001";
		map[6] = "00010020010000000000010020010000000000000000100000000001";
		map[7] = "00010000010000000000010000010000000000000000100000000001";
		map[8] = "00010000010000000000010000010000000000000000100000000001";
		map[9] = "00011111111000000000 11111110000000000000000100000000001111111111";
		map[10] = "0000000000000000000000000000000000000000000010000000000100000001";
		map[11] = "0000000000000000000000000000000000000000000010000000000000000001";
		map[12] = "0000000000000000000000000000000000000000000010000000000000020001";
		map[13] = "0000000000000000000000000000000000000000000010000000000000000001";
		map[14] = "0000000000000000000000000000000000000000000010000000000100000001";
		map[15] = "0000000000000000000000000000000000000000000010000000000111111111";
		map[16] = "00000000000000000000000000000000000000000000111111111111";


		int y = 1;

		for(int i = 0; i < map.length; i++){

			for(int x = 0; x < map[i].length(); x++){

				char c = map[i].charAt(x);		

				// Create the level
				switch(c){
				case '0':
					break;

				case '1':
					tiles.add(new Tile(world, new Vector2(x * 8,y * 8)));
					break;

				case '2':
					rechargers.add(new Recharger(world, new Vector2(x * 8,y * 8), player));
					break;

				default:
					break;
				}

				System.out.println(x);

			}
			y--;
		}
	}

}
