package com.lewis.angrymasons.View;

import java.io.IOException;
import java.util.Iterator;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.ParticleEmitter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.utils.Array;
import com.lewis.angrymasons.AngryMasons;
import com.lewis.angrymasons.Model.Bullet;
import com.lewis.angrymasons.Model.Enemy;
import com.lewis.angrymasons.Model.Ship;

public class WorldRenderer {
	
	World world;
	SpriteBatch batch;
	Ship ship;
	OrthographicCamera cam;
	Texture shipTexture, followerTexture, bulletTexture;
	float width, height;
	ShapeRenderer sr;
	Array<Bullet> bullets;
	Array<Enemy> enemies;
	Iterator<Bullet> bIter;
	Iterator<Enemy> eIter;
	Bullet b;
	Enemy e;
	ParticleEmitter exhaust;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
		width = Gdx.graphics.getWidth() / 40;
		height = Gdx.graphics.getHeight() / 40;
		
		cam = new OrthographicCamera();
		cam.setToOrtho(false, width, height);
		cam.update();
		
		batch = new SpriteBatch();
		batch.setProjectionMatrix(cam.combined);
		
		shipTexture = new Texture("data/ship.png");
		shipTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		followerTexture = new Texture("data/follower.png");
		followerTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		bulletTexture = new Texture("data/bullet.png");
		bulletTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		sr = new ShapeRenderer();
		
		// Particle stuff
		exhaust = new ParticleEmitter();
		
		try{
			exhaust.load(Gdx.files.internal("data/exhaust").reader(2024));
		}catch(IOException e){
			e.printStackTrace();
		}
		
		Texture particleTexture = new Texture(Gdx.files.internal("data/particle.png"));
		Sprite particle = new Sprite(particleTexture);
		exhaust.setSprite(particle);
		exhaust.getScale().setHigh(0.3f);
		exhaust.start();
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ship = world.getShip();	// Get ship position
		enemies = world.getEnemies();
		bullets = world.getBullets();
		
		// Deal with exhaust particles
		exhaust.setPosition(ship.getPosition().x + ship.getWidth() / 2, ship.getPosition().y + ship.getHeight() / 2);
		setExhaustRotation();
		
		cam.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		cam.update();
		batch.setProjectionMatrix(cam.combined);
		
		batch.begin();
		
		// Draw exhaust
		exhaust.draw(batch, Gdx.graphics.getDeltaTime());
		
		batch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2,  ship.getHeight() / 2,  ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(), 
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		
		eIter = enemies.iterator();
		
		while(eIter.hasNext()){	// Draw all enemies
			e = eIter.next();	// Loop through
		batch.draw(followerTexture, e.getPosition().x, e.getPosition().y, e.getWidth() / 2, e.getHeight() / 2, e.getWidth(), e.getHeight(), 1, 1, e.getRotation(),
				0, 0, followerTexture.getWidth(), followerTexture.getHeight(), false, false);
		}
		
		bIter = bullets.iterator();
		while(bIter.hasNext()){
			b = bIter.next();
			batch.draw(bulletTexture, b.getPosition().x, b.getPosition().y, b.getWidth() / 2, b.getHeight() / 2, b.getWidth(), b.getHeight(), 1, 1, b.getRotation(),
					0, 0, bulletTexture.getWidth(), bulletTexture.getHeight(), false, false);
		}
		
		batch.end();
		
		// For debug, render a shape around the ship
			if(AngryMasons.DEBUG){
				sr.setProjectionMatrix(cam.combined);
				sr.begin(ShapeType.Rectangle);
				sr.setColor(Color.CYAN);
				sr.rect(ship.getBounds().x, ship.getBounds().y, ship.getBounds().width, ship.getBounds().height);
				sr.setColor(Color.RED);
		
				eIter = enemies.iterator();
				while(eIter.hasNext()){	// DrawRectangle for all enemies
					e = eIter.next();	// Loop through
					sr.rect(e.getBounds().x, e.getBounds().y, e.getBounds().width, e.getBounds().height);
				}
		
				bIter = bullets.iterator();	// DrawRectangle for all bullets
				while(bIter.hasNext()){
					b = bIter.next();
					sr.rect(b.getBounds().x, b.getBounds().y, b.getBounds().width, b.getBounds().height);
				}
		
				sr.end();
		}
	}
	
	private void setExhaustRotation(){
		float angle = ship.getRotation();
		exhaust.getAngle().setLow(angle + 270);
		exhaust.getAngle().setHighMin(angle + 270 - 45);
		exhaust.getAngle().setHighMax(angle + 270 + 45);
	}
	
	public OrthographicCamera getCamera(){
		return cam;
	}
	
	public void dispose(){
		batch.dispose();
		shipTexture.dispose();
		followerTexture.dispose();
		sr.dispose();
	}
}
