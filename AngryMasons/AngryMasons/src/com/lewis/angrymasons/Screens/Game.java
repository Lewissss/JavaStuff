package com.lewis.angrymasons.Screens;

import com.badlogic.gdx.Screen;
import com.lewis.angrymasons.AngryMasons;
import com.lewis.angrymasons.View.World;
import com.lewis.angrymasons.View.WorldRenderer;

public class Game implements Screen{
	
	AngryMasons game;
	World world;
	WorldRenderer render;
	
	public Game(AngryMasons game){
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
		dispose();
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
		world.dispose();
	}

}
