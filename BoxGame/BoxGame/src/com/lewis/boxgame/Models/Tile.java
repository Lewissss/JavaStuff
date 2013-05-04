package com.lewis.boxgame.Models;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Tile {

	World world;
	Body squareBody;
	BodyDef squareDef;
	float width = 8f;
	float height = 8f;
	Vector2 position;
	
	public Tile(World world, Vector2 position){
		this.world = world;
		this.position = position;
		squareDef = new BodyDef();
		squareDef.type = BodyType.StaticBody;
		squareDef.position.set(position);
		
		squareBody = world.createBody(squareDef);
		
		PolygonShape boxShape = new PolygonShape();
		
		boxShape.setAsBox(width, height);
		
		squareBody.createFixture(boxShape, 0.0f);		
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}

}
