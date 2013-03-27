package com.lewis.lightGame.Model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player extends MoveableEntity{

	public Player(float SPEED, float rotation, Vector2 position, float width, float height, World world) {
		super(SPEED, rotation, position, width, height);
		
		Body playerBody;
		
		BodyDef playerDef = new BodyDef();
		playerDef.type = BodyType.DynamicBody;
		playerDef.position.set(position);
		
		playerBody = world.createBody(playerDef);
		
		CircleShape circleShape = new CircleShape();
		circleShape.setRadius(3f);
		
		FixtureDef playerFixtureDef = new FixtureDef();
		playerFixtureDef.shape = circleShape;
		playerFixtureDef.density = 0.4f;
		playerFixtureDef.friction = 0.2f;
		playerFixtureDef.restitution = 0.4f;
		
		playerBody.createFixture(playerFixtureDef);
	}
	
	public void update() {
		position.add(velocity.mul(Gdx.graphics.getDeltaTime() * SPEED));// Add the velocity to the position, then use .mul to times by delta time and SPEED	
	}

}
