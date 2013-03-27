package com.lewis.lightGame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lewis.lightGame.Screens.SplashScreen;

public class LightGame extends Game {
	
	public static final String VERSION = "0.0.0.01(Pre-Alpha)";
	public static final String LOG = "LightGame";
	public static final boolean DEBUG = true;
	
	FPSLogger logger;
	
	
	@Override
	public void create() {	
		logger = new FPSLogger();
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {	
		logger.log();
		super.render();
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
