package com.lewis.Pong.Screens;

import com.badlogic.gdx.Screen;
import com.lewis.Pong.Pong;
import com.lewis.Pong.View.World;
import com.lewis.Pong.View.WorldRenderer;

public class GameScreen implements Screen {

	Pong game;
	World world;
	WorldRenderer render;
	
	public GameScreen(Pong game){
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
