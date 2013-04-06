package com.me.RBlock.Block;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

public class TileSet {

	Array<Block> blocks;
	Rectangle areaBounds;
	
	public boolean left = false;;
	public boolean right = false;
	public boolean top = false;
	public boolean bottom = false;
	
	public TileSet(){
		blocks = new Array<Block>();
		
		// TODO : Set areabounds for each tileSet. So that it has some form of fog of war.
	}
	
	public void setInitialFlags(){
		left = false;
		right = true;
		top = false;
		bottom = false;
	}
	
	public Array<Block> getSet(int countX, int countY){

		blocks.add(new Block(new Vector2(0, 0), countX, countY));
		blocks.add(new Block(new Vector2(0, 1), countX, countY));
		blocks.add(new Block(new Vector2(0, 3), countX, countY));
		blocks.add(new Block(new Vector2(0, 4), countX, countY));

		blocks.add(new Block(new Vector2(1, 4), countX, countY));
		blocks.add(new Block(new Vector2(3, 4), countX, countY));
		blocks.add(new Block(new Vector2(4, 4), countX, countY));

		blocks.add(new Block(new Vector2(0, 0), countX, countY));
		blocks.add(new Block(new Vector2(1, 0), countX, countY));
		blocks.add(new Block(new Vector2(3, 0), countX, countY));

		blocks.add(new Block(new Vector2(4, 0), countX, countY));
		blocks.add(new Block(new Vector2(4, 1), countX, countY));
		blocks.add(new Block(new Vector2(4, 3), countX, countY));

		//Entry positions
		if(!left){	//Left

			blocks.add(new Block(new Vector2(0, 2), countX, countY));
		}

		if(!right){ 	// Right

			blocks.add(new Block(new Vector2(4, 2), countX, countY));	
		}

		if(!top){	//Top

			blocks.add(new Block(new Vector2(2, 4), countX, countY));
		}

		if(!bottom){

			blocks.add(new Block(new Vector2(2, 0), countX, countY));
		}

		return blocks;	
	}
	
	public boolean getLeft(){
		return left;
	}
	
	public boolean getRight(){
		return right;
	}
	
	public boolean getTop(){
		return top;
	}
	
	public boolean getBottom(){
		return bottom;
	}
	
	public void setLeft(boolean left){
		this.left = left;
	}
	
	public void setRight(boolean right){
		this.right = right;
	}
	
	public void setTop(boolean top){
		this.top = top;
	}
	
	public void setBottom(boolean bottom){
		this.bottom = bottom;
	}
}
