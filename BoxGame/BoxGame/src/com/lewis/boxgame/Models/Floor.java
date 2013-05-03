package com.lewis.boxgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;

public class Floor {
	
	World world;
	BodyDef groundBodyDef;
	
	public Floor(World world){
		this.world = world;
		
		groundBodyDef = new BodyDef();
		groundBodyDef.type = BodyType.StaticBody;
		groundBodyDef.position.set(0, 3);
		
		Body groundBody = world.createBody(groundBodyDef);
		
		PolygonShape groundBox = new PolygonShape();
		groundBox.setAsBox(Gdx.graphics.getWidth() * 2, 3.0f);
		
		groundBody.createFixture(groundBox, 0.0f);
	}
}
