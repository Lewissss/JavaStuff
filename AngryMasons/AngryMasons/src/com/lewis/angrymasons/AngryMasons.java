package com.lewis.angrymasons;

import com.badlogic.gdx.Game;
import com.lewis.angrymasons.Screens.SplashScreen;

public class AngryMasons extends Game {
	
	public static final String VERSION = "0.0.0.01 Pre-Alpha";
	public static final String LOG = "Angry Masons";
	
	
	@Override
	public void create() {		
		setScreen(new SplashScreen(this));
	}

	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {		
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
