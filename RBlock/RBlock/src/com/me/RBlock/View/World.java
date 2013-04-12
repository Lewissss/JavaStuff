package com.me.RBlock.View;

import java.util.Random;

import com.badlogic.gdx.utils.Array;
import com.me.RBlock.Block.Block;
import com.me.RBlock.Block.TileSet;

public class World {

	Array<Block> blocks;
	TileSet tiles;
	Random random;
	int MAXX = 15;
	int MAXY = 15;

	public World(){
		blocks = new Array<Block>();
		tiles = new TileSet();
		
		random = new Random();
		
		getMapBlocks();
	}

	private void getMapBlocks(){
		
		tiles.setInitialFlags();
		
		for(int x = 0; x < MAXX; x++){
			for(int y = 0; y < MAXY; y++){	
				
				if(tiles.getRight()){
					tiles.setLeft(true);
					tiles.setTop(random.nextBoolean());
					tiles.setBottom(random.nextBoolean());
				
				}	
				else if(tiles.getLeft()){
					tiles.setRight(true);
					tiles.setTop(random.nextBoolean());
					tiles.setBottom(random.nextBoolean());
				}
				
				if(x == MAXX - 1){
					tiles.setLeft(true);
					tiles.setRight(false);
				}
				
				if(x == 0){
					tiles.setLeft(false);
				}
				
				if(y == 0){
					tiles.setBottom(false);
				}
				
				if(y == MAXY - 1){
					tiles.setTop(false);
				}
				
				this.blocks.addAll(tiles.getSet(x, y));
			}
		}
	}

	public Array<Block> getBlocks(){
		return blocks;
	}

}
