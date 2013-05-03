package com.lewis.boxgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player {

	World world;
	Body circleBody;
	BodyDef circleDef;
	Sprite playerSprite;
	float radius = 6.5f;
	Vector2 velocity;

	CircleShape circleShape = new CircleShape();

	boolean isCharging = false;
	boolean isFull = true;

	Rectangle rectangle;

	float MAX_DISTANCE = 100f;
	float distance = 100f;
	float timer = 0;
	float INTERVAL = 10f;
	float REDUCE_SPEED = 0.25f;

	Vector2 mouse;

	float SPEED = 35f;
	Vector2 position;

	public Player(World world){

		this.world = world;

		circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		circleDef.position.set(13, -5);

		circleBody = world.createBody(circleDef);

		circleShape.setRadius(radius);

		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.4f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 0.0f;	// Bouncyness  (Higher the number the more perfect the bounce)

		circleBody.createFixture(circleFixture);

		rectangle = new Rectangle();
		rectangle.set(13, -5, radius * 2, radius * 2);
	}

	public void update(){

		setVariables();

		movePlayer();

		circleBody.setLinearDamping(3f);

		//Debug for changing speed
		if(Gdx.input.isKeyPressed(Keys.PLUS)){
			SPEED++;
		}
		if(Gdx.input.isKeyPressed(Keys.MINUS)){
			SPEED--;
		}

		circleBody.setAngularDamping(1f);

		if(distance > MAX_DISTANCE){
			distance = MAX_DISTANCE;
			isFull = true;
		}
		
		if(distance != MAX_DISTANCE){
			isFull = false;
		}

		reducePlayerLight();
	}

	private void setVariables() {
		position = circleBody.getPosition();
		rectangle.set(position.x, position.y, radius * 2, radius * 2);
		mouse = new Vector2(Gdx.input.getX(),Gdx.input.getY());
		velocity = circleBody.getLinearVelocity();
	}

	private void movePlayer() {
		if(Gdx.input.isKeyPressed(Keys.UP)){
			velocity.y = SPEED;
			circleBody.setLinearVelocity(velocity);
		}

		if(Gdx.input.isKeyPressed(Keys.DOWN)){
			velocity.y = -SPEED;
			circleBody.setLinearVelocity(velocity);
		}

		if (Gdx.input.isKeyPressed(Keys.LEFT)){
			velocity.x = -SPEED;
			circleBody.setLinearVelocity(velocity);
		}

		if (Gdx.input.isKeyPressed(Keys.RIGHT)){
			velocity.x = SPEED;
			circleBody.setLinearVelocity(velocity);
		}
	}

	private void reducePlayerLight() {

		// Only decharge if not charging
		if(!isCharging){
			timer ++;

			if(timer > INTERVAL){
				distance -= REDUCE_SPEED;
				timer = 0;
			}
		}
		
		//System.out.println("Player power: " + distance);
	}

	public void move(Vector2 v){
		circleBody.setLinearVelocity(v);
	}

	public Vector2 getPosition(){
		return position;
	}

	public float getSpeed(){
		return SPEED;
	}

	public Body getBody(){
		return circleBody;
	}

	public Rectangle getRectangle(){
		return rectangle;
	}

	public float getDistance(){
		return distance;
	}

	public void addDistance(float d){
		distance += d;
	}

	public boolean getIsFull(){
		return isFull;
	}

	public boolean getIsCharging(){
		return isCharging;
	}
	
	public void setIsCharging(boolean b){
		isCharging = b;
	}

}