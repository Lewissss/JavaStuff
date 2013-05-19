package com.lewis.boxgame.Screens;

import com.badlogic.gdx.Screen;
import com.lewis.boxgame.BoxGame;
import com.lewis.boxgame.Views.GameWorld;
import com.lewis.boxgame.Views.WorldRenderer;

public class GameScreen implements Screen  {

	private GameWorld gWorld;
	private WorldRenderer render;
	
	public GameScreen(BoxGame game){
		gWorld = new GameWorld(game);
		render = new WorldRenderer(gWorld);
	}
	
	@Override
	public void render(float delta) {
		gWorld.update();
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
		gWorld.dispose();
		render.dispose();
	}

}
