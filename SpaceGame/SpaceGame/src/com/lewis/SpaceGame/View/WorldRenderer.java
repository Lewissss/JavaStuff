package com.lewis.SpaceGame.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.lewis.SpaceGame.SpaceGame;

public class WorldRenderer {
	
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	float width, height;
	ShapeRenderer sr;
	
	World world;
	
	public WorldRenderer(World world){
		this.world = world;
		
		width = Gdx.graphics.getWidth() / 40;
		height = Gdx.graphics.getHeight() / 40;
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		sr = new ShapeRenderer();
		
	}
	
	public void render(){
		
		if(SpaceGame.DEBUG){
			renderShapes();
		}
	}
	
	public void dispose(){
		spriteBatch.dispose();
		sr.dispose();
	}
	
	public void renderShapes(){
		
	}

}
