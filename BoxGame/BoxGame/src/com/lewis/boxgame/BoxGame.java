package com.lewis.boxgame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;
import com.lewis.boxgame.Screens.GameScreen;

public class BoxGame extends Game{

	public static final String VERSION = ("0.0.0.04 - [Pre-Release]");

	public FPSLogger log;

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
