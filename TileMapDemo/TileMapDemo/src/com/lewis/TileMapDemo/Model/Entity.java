package com.lewis.TileMapDemo.Model;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Entity {

	Vector2 position;
	float width, height;
	Rectangle bounds;
	
	public Entity(Vector2 position, float width, float height){
		this.position = position;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(position.x, position.y, width, height);
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}
	
}
