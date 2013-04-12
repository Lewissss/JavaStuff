package com.lewis.TileMapDemo.Screen;

import com.badlogic.gdx.Screen;
import com.lewis.TileMapDemo.TileMapDemo;
import com.lewis.TileMapDemo.View.World;
import com.lewis.TileMapDemo.View.WorldRenderer;

public class GameScreen implements Screen {
	
	TileMapDemo game;
	World world;
	WorldRenderer render;
	
	public GameScreen(TileMapDemo game){
		this.game = game;
		world = new World(game);
		render = new WorldRenderer(world);
	}

	@Override
	public void render(float delta) {
		world.update();
		render.render();
	}

	@Override
	public void resize(int width, int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
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
