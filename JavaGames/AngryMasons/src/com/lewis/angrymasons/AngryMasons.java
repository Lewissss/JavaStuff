package com.lewis.angrymasons;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lewis.angrymasons.Screens.GameScreen;
import com.lewis.angrymasons.Screens.SplashScreen;

@SuppressWarnings("unused")
public class AngryMasons extends Game {
	
	public static final String VERSION = "0.0.0.05(Pre-Alpha)";
	public static final String LOG = "Angry Masons";
	public static final boolean DEBUG = true;
	FPSLogger log;
	
	@Override
	public void create() {	
		log = new FPSLogger();
		setScreen(new SplashScreen(this));
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
