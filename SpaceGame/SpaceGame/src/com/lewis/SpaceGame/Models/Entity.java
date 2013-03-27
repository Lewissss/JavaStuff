package com.lewis.SpaceGame.Models;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public abstract class Entity {
	protected Vector2 position;
	protected float width, height;
	protected Rectangle bounds;
	
	public Entity(Vector2 position, float width, float height){
		this.position = position;
		this.width = width;
		this.height = height;
		
		bounds = new Rectangle(position.x, position.y, width, height);
	}
	
	public void setPosition(Vector2 position){
		this.position = position;
	}
	
	public Vector2 getPosition(){
		return position;
	}
	
	public void setWidth(float width){
		this.width = width;
	}
	
	public float getWidth(){
		return width;
	}
	
	public void setHeight(float height){
		this.height = height;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void setBounds(Rectangle bounds){
		this.bounds = bounds;
	}
	
	public Rectangle getBounds(){
		return bounds;
	}

}
