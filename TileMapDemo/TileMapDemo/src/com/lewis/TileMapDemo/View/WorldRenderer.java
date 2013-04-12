package com.lewis.TileMapDemo.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

public class WorldRenderer {
	
	SpriteBatch spriteBatch;
	OrthographicCamera camera;
	OrthogonalTiledMapRenderer tileRenderer;
	
	TiledMap map;
	
	World world;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
		map = world.getMap();
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false, 40, 22);
		camera.update();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		float unitScale = 1 / 32f;
		tileRenderer = new OrthogonalTiledMapRenderer(map, unitScale);
		tileRenderer.setView(camera);
	}
	
	public void render(){
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
		spriteBatch.end();
		
		//Render map
		tileRenderer.render();

		
	}

}
