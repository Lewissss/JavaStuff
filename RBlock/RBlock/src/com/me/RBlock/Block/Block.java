package com.me.RBlock.Block;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Block {

	public static final float SIZE = 1f;
	static final float TILESIZE = 5f;

	
	Vector2 position = new Vector2();
	Rectangle bounds;
	
	public Block(Vector2 position, int countX, int countY){
		this.position.x = position.x + (countX * TILESIZE);
		this.position.y = position.y + (countY * TILESIZE);
		
		bounds = new Rectangle();
		this.bounds.x = position.x;
		this.bounds.y = position.y;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
}
