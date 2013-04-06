package com.me.RBlock;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.FPSLogger;

public class RBlock extends Game {
	
	public static final String VERSION = "0.0.0.01 (PRE-ALPHA)";
	public static final boolean DEBUG = false;
	
	FPSLogger log;
	
	@Override
	public void create() {	
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
