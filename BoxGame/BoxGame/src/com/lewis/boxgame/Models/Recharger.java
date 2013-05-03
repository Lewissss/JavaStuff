package com.lewis.boxgame.Models;

import box2dLight.PointLight;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.World;

public class Recharger {

	World world;
	Player player;
	PointLight light;

	Body circleBody;
	BodyDef circleDef;
	Sprite playerSprite;
	float radius = 10f;
	Vector2 position;
	float MAX_POWER = 100f;
	float RECHARGE_RATE = 0.2f;
	float timer = 0;
	float INTERVAL = 30f;

	Rectangle rectangle;

	float power = 200;

	CircleShape circleShape = new CircleShape();

	public Recharger(World world, Vector2 position, Player player){

		this.world = world;
		this.position = position;
		this.player = player;

		circleDef = new BodyDef();
		circleDef.type = BodyType.StaticBody;
		circleDef.position.set(position);

		circleBody = world.createBody(circleDef);

		circleShape.setRadius(radius);

		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.4f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 0.0f;	// Bouncyness  (Higher the number the more perfect the bounce)
		circleFixture.isSensor = true;

		circleBody.createFixture(circleFixture);

		rectangle = new Rectangle(position.x, position.y, radius * 2, radius * 2);
	}

	public void update(){

		// If the player is on the charge pad
		chargePlayer();

		// Slowly recharge the pad
		rechargePad();

		if(power < 0)
		{
			power = 0;
		}
		if(power > MAX_POWER){
			power = MAX_POWER;
		}

		System.out.println("Recharge power: " + power);
	}

	private void rechargePad() {

		if(!player.getIsCharging())
		{
			timer++;

			if(timer > INTERVAL){

				power += RECHARGE_RATE;

				timer = 0;
			}
		}
	}

	private void chargePlayer() {
		// Player in the charge point
		if(rectangle.overlaps(player.getRectangle())){

			player.setIsCharging(true);

			if(power > 0 && !player.getIsFull()){
				player.addDistance(RECHARGE_RATE);
				power -= RECHARGE_RATE;
			}
		}
		else{
			player.setIsCharging(false);
		}
	}

	public Vector2 getPosition(){
		return position;
	}

	public float getPower(){
		return power;
	}

	public float getWidth(){
		return rectangle.getWidth();
	}

	public float getHeight(){
		return rectangle.getHeight();
	}

	public Rectangle getRectangle(){
		return rectangle;
	}
}