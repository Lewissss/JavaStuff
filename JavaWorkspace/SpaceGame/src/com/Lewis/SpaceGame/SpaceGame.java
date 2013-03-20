package com.Lewis.SpaceGame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

public class SpaceGame implements ApplicationListener {
	// Game world
	private OrthographicCamera camera;
	private SpriteBatch spriteBatch;
	
	Player player;
	Texture playerTexture;
	
	@Override
	public void create() {		
		camera = new OrthographicCamera();
		camera.setToOrtho(false,  800, 600);
		spriteBatch = new SpriteBatch();

		playerTexture = new Texture(Gdx.files.internal("Player.png"));
		
		player = new Player(playerTexture, new Vector2(250, 150));
	}

	@Override
	public void dispose() {
		spriteBatch.dispose();
	}

	@Override
	public void render() {		
		Gdx.gl.glClearColor(1, 1, 1, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

		Draw();
		
		player.Update();

	}
	
	public void Draw()
	{
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
		
		player.Draw(spriteBatch);
		
		spriteBatch.end();
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
}
