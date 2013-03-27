package com.lewis.SpaceGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.Models.Ship;

public class WorldRenderer {
	
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	float width, height;
	ShapeRenderer sr;
	
	Ship ship;
	World world;
	
	//Textures
	Texture shipTexture;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
		width = Gdx.graphics.getWidth() / 40;
		height = Gdx.graphics.getHeight() / 40;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		shipTexture = new Texture("data/ship.png");
		shipTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
		
		sr = new ShapeRenderer();
		
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		ship = world.getShip();
		
		camera.position.set(ship.getPosition().x, ship.getPosition().y, 0);
		camera.update();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
	
		spriteBatch.draw(shipTexture, ship.getPosition().x, ship.getPosition().y, ship.getWidth() / 2, ship.getHeight() / 2, ship.getWidth(), ship.getHeight(), 1, 1, ship.getRotation(),
				0, 0, shipTexture.getWidth(), shipTexture.getHeight(), false, false);
		
		spriteBatch.end();
		
		
		if(SpaceGame.DEBUG){
			renderShapes();
		}
	}
	
	public OrthographicCamera getCamera(){
		return camera;
	}
	
	public void dispose(){
		spriteBatch.dispose();
		sr.dispose();
	}
	
	public void renderShapes(){
		
	}

}
