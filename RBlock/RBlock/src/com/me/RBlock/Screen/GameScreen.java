package com.me.RBlock.Screen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.me.RBlock.View.World;
import com.me.RBlock.View.WorldRenderer;

public class GameScreen implements Screen {

	private World world;
	private WorldRenderer renderer;
	
	
	@Override
	public void render(float delta) {

		Gdx.gl.glClearColor(255, 255, 255,  1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		renderer.render();
	}
	
	@Override
	public void resize(int width, int height) {
		renderer.setSize(width, height);
	}
	
	@Override
	public void show() {
		world = new World();
		renderer = new WorldRenderer(world);
	}
	
	@Override
	public void hide() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}
	
	
}
