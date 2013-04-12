package com.lewis.TileMapDemo;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lewis.TileMapDemo.Screen.GameScreen;

public class TileMapDemo extends Game {
	
	FPSLogger log;
	
	@Override
	public void create() {		
		setScreen(new GameScreen(this));
		
		log = new FPSLogger();
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
		super.render();
		
		log.log();
	}

	@Override
	public void resize(int width, int height) {
		super.dispose();
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.dispose();
	}
}
