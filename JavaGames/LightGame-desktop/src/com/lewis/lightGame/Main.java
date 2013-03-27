package com.lewis.lightGame;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "LightGame";
		cfg.useGL20 = true;
		cfg.width = 1280;
		cfg.height = 720;
		cfg.vSyncEnabled = false;
		
		new LwjglApplication(new LightGame(), cfg);
	}
}
