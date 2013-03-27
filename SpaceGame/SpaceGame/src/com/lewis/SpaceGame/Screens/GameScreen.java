package com.lewis.SpaceGame.Screens;

import com.badlogic.gdx.Screen;
import com.lewis.SpaceGame.SpaceGame;
import com.lewis.SpaceGame.View.World;
import com.lewis.SpaceGame.View.WorldRenderer;

public class GameScreen implements Screen {
	
	SpaceGame game;
	World world;
	WorldRenderer render;
	
	public GameScreen(SpaceGame game){
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
	}

	@Override
	public void show() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void dispose() {
	}

}
