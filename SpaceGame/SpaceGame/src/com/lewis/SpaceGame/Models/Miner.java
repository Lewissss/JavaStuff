package com.lewis.SpaceGame.Models;

import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;
import com.lewis.SpaceGame.Models.Asteroid.Asteroid;
import com.lewis.SpaceGame.View.World;

public class Miner extends MoveableEntity {

	boolean inActive = false;
	boolean isDead = false;
	float HEALTH = 30f;
	Vector2 origin;
	float XDistance;
	float YDistance;
	Array<Laser> nlasers;
	Iterator<Laser> lIter;
	Laser l;
	
	Asteroid a;
	Vector2 asteroidPosition;
	
	float timer = 0;
	float interval = 1f;
	
	boolean targets = false;
	
	World world;
	Team team;
	
	public Miner(float SPEED, float rotation, Vector2 position, float width, float height, World world, Asteroid a, Team team) {
		super(SPEED, rotation, position, width, height);
		
		this.world = world;
		nlasers = new Array<Laser>();
		this.a = a;
		timer = interval;
		this.team = team;
	}
	
	public void update(float mapWidth, float mapHeight){
		
		asteroidPosition = new Vector2(a.getPosition().x - (a.getWidth() / 2), a.getPosition().y - (a.getHeight() / 2));
		
		//Check if inActive
		overlapActivation(a);
		
		rotate();
		
		shootTarget();
		
		setInitialTarget();
		
		setNewTarget();

		updateLasers();
		
		checkPosition(mapWidth, mapHeight);
		
		if(HEALTH <= 0){
			isDead = true;
		}
		
		// Reset the bounds
		bounds.x = position.x;
		bounds.y = position.y; 
		
		origin = new Vector2(position.x - (width / 2), position.y - (width / 2));
	}
	
	public Team getTeam(){
		return team;
	}
	
	public boolean getStatus(){
		return isDead;
	}
	
	public void Damage(float damage){
		this.HEALTH -= damage;
	}

	private void rotate() {
		rotation += 3;
		
		if(rotation > 360){
			rotation = 0;
		}
	}

	private void updateLasers() {
		//Update lasers
		lIter = nlasers.iterator();
		while(lIter.hasNext()){
			l = lIter.next();
			
			if(l.getVisible()){			// Only check collisions when visible
				if(l.getBounds().overlaps(a.getBounds())){
					a.damageAsteroid(l.DAMAGE);
					
					l.setVisible(false);
				}
			}
			
			if(l.getIsDead()){	// Remove dead lasers
				lIter.remove();
			}
			
			l.update(this);
		}
	}

	private void setNewTarget() {
		// Set new target if asteroid is dead
		if(a.getHealth() <= 0){
			if(world.getAsteroids().size != 0){
				a = world.getAsteroids().random();
			}else{
				targets = false;
			}
		}
	}

	private void setInitialTarget() {
		//For setting initial target
		if(!targets){
			if(world.getAsteroids().size != 0){
				a = world.getAsteroids().random();
				targets = true;
			}
		}
	}

	private void shootTarget() {
		if(targets){
			if(!inActive){
				position.lerp(a.getPosition(), Gdx.graphics.getDeltaTime() / SPEED);
			}else{
				timer += Gdx.graphics.getDeltaTime();
				if(timer > interval){
					nlasers.add(Shoot(a));
					timer = 0;
				}
			}
		}
	}
	
	public void overlapActivation(Asteroid a){
		if(this.bounds.overlaps(a.getActivationArea())){
			inActive = true;
		}else{
			inActive = false;
		}
	}
	
	public void rotateTowardsAsteroid(Asteroid a){
		XDistance = position.x - a.getPosition().x - (a.getWidth() / 2);
		YDistance = position.y - a.getPosition().y - (a.getHeight() / 2);
		
		rotation = -(float)Math.atan2(YDistance, XDistance) - 45;
		
		// TODO look at rotation
	}
	
	public Laser Shoot(Asteroid a){
		return new Laser(Laser.SPEED, 0f, new Vector2(bounds.x + width / 2, 
				bounds.y + height / 2), .1f, 8/20f, new Vector2(asteroidPosition.sub(origin)), 5f, team);
	}
	
	// Draw lasers
	public void draw(SpriteBatch batch, Texture laserTexture){
		lIter = nlasers.iterator();
		while(lIter.hasNext()){
			l = lIter.next();
			if(l.getVisible())	// Only render the visible lasers
			{
				batch.draw(laserTexture, l.getPosition().x, l.getPosition().y, l.getWidth() / 2, l.getHeight() / 2, l.getWidth(), l.getHeight(), 1, 1, l.getRotation(),
						0, 0, laserTexture.getWidth(), laserTexture.getHeight(), false, false);
			}
		}
	}

}
