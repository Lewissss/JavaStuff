package com.lewis.Pong;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lewis.Pong.Screens.GameScreen;

public class Pong extends Game {
	
	public static final boolean DEBUG = false;
	public static final String VERSION = "0.0.0.03 [PRE-ALPHA]";
	
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
		super.resize(width, height);
	}

	@Override
	public void pause() {
		super.pause();
	}

	@Override
	public void resume() {
		super.resume();
	}
}
