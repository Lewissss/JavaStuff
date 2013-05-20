package com.lewis.boxgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.FixtureDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Recharger {

	private Player player;
	
	private boolean activated = false;
	private boolean initialCharge = true;
	
	private Texture rechargerTexture;
	private Body circleBody;
	private BodyDef circleDef;
	private PolygonShape chargerShape = new PolygonShape();
	
	private float width = 8;
	private float height = 8;
	private float MAX_POWER = 100f;
	private float RECHARGE_RATE = 0.3f;
	private float timer = 0;
	private float INTERVAL = 40f;
	private float distance;
	private float power = 0;
	
	private Vector2 position;
	private Rectangle rectangle;

	public Recharger(World world, Vector2 position, Player player){

		this.position = position;
		this.player = player;

		FixtureDef circleFixture = setCircleShape(world, position);

		circleBody.createFixture(circleFixture);

		rectangle = new Rectangle(position.x, position.y, width + 1, height + 1);
		
		rechargerTexture = new Texture(Gdx.files.internal("data/Textures/recharger.png"));
		rechargerTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}

	private FixtureDef setCircleShape(World world, Vector2 position) {
		circleDef = new BodyDef();
		circleDef.type = BodyType.StaticBody;
		circleDef.position.set(position);

		circleBody = world.createBody(circleDef);

		chargerShape.setAsBox(width, height);

		FixtureDef circleFixture = new FixtureDef();
		circleFixture.shape = chargerShape;
		circleFixture.density = 0.4f;
		circleFixture.friction = 0.2f;
		circleFixture.restitution = 0.0f;	// Bouncyness  (Higher the number the more perfect the bounce)
		circleFixture.isSensor = true;
		return circleFixture;
	}

	public void update(){

		// If the player is on the charge pad
		chargePlayer();

		// Slowly recharge the pad
		rechargePad();

		lockPower();
		
		// Get distance from player
		distance = position.dst(player.getPosition());
		
		// If the player is close, activate (Permanent)
		activateCharger();
	}

	private void activateCharger() {
		if(distance < 70){
			activated = true;
		}
		
		if(initialCharge && activated){
			power += 0.88;
		}
	}

	private void lockPower() {
		if(power < 0)
		{
			power = 0;
		}
		if(power > MAX_POWER){
			power = MAX_POWER;
			initialCharge = false;
		}
	}

	private void rechargePad() {

		if(!player.getIsCharging() && activated)
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
				player.addBattery(RECHARGE_RATE);
				power -= RECHARGE_RATE;
			}
		}
		else{
			player.setIsCharging(false);
		}
	}
	
	public void draw(SpriteBatch spriteBatch){
		spriteBatch.draw(rechargerTexture, position.x - width, position.y - height);
	}
	
	public void dispose(){
		rechargerTexture.dispose();
	}

	public Vector2 getPosition(){
		return position;
	}

	public float getPower(){
		return power;
	}
	
	public float getMaxPower(){
		return MAX_POWER;
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
