package com.lewis.boxgame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "The Lights Are Going Out - " + BoxGame.VERSION;
		cfg.useGL20 = true;
		cfg.width = 1080;
		cfg.height = 720;
		cfg.resizable = false;
		cfg.samples = 32;
		
		new LwjglApplication(new BoxGame(), cfg);
	}
}
