package com.lewis.boxgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.CircleShape;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.World;

public class Player {

	private Body circleBody;
	private BodyDef circleDef;
	private CircleShape circleShape = new CircleShape();
	
	private Vector2 velocity;
	private Vector2 position;
	
	private Texture playerTexture;

	private boolean isCharging = false;
	private boolean isFull = true;

	private Rectangle rectangle;

	private float radius = 8f;
	private float MAX_DISTANCE = 100f;
	private float battery = 100f;
	private float timer = 0;
	private float INTERVAL = 10f;
	private float REDUCE_SPEED = 0.75f;
	private float SPEED = 39f;

	public Player(World world){

		createBody(world);

		rectangle = new Rectangle();
		rectangle.set(13, -5, radius * 2, radius * 2);
		
		playerTexture = new Texture(Gdx.files.internal("data/Textures/player.png"));
		playerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}

	private void createBody(World world) {
		circleDef = new BodyDef();
		circleDef.type = BodyType.DynamicBody;
		circleDef.position.set(13, -5);

		circleBody = world.createBody(circleDef);

		circleShape.setRadius(radius);

		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = circleShape;
		circleFixture.density = 0.2f;
		circleFixture.friction = 1f;
		circleFixture.restitution = 0.0f;	// Bouncyness  (Higher the number the more perfect the bounce)

		circleBody.createFixture(circleFixture);
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

		circleBody.setLinearDamping(7f);

		isBatteryFull();

		reducePlayerLight();
	}

	private void isBatteryFull() {
		if(battery > MAX_DISTANCE){
			battery = MAX_DISTANCE;
			isFull = true;
		}
		
		if(battery != MAX_DISTANCE){
			isFull = false;
		}
	}

	private void setVariables() {
		position = circleBody.getPosition();
		rectangle.set(position.x, position.y, radius * 2, radius * 2);
		new Vector2(Gdx.input.getX(),Gdx.input.getY());
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
				battery -= REDUCE_SPEED;
				timer = 0;
			}
		}
	}
	
	public void draw(SpriteBatch spriteBatch){
		spriteBatch.draw(playerTexture, position.x - (radius / 2) - 2.5f, position.y - (radius / 2) - 2.5f);
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

	public float getBattery(){
		return battery;
	}

	public void addBattery(float d){
		battery += d;
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
