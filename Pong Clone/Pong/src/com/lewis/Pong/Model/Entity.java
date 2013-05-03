package com.lewis.Pong.Model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {

	protected Vector2 position;
	protected float width, height;
	Rectangle bounds;
	
	public Entity(Vector2 position, float width, float height){
		
		this.position = position;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle();
		bounds.set(position.x, position.y, width, height);
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
}
