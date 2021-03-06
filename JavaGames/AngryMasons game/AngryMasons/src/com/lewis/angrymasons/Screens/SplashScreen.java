package com.lewis.angrymasons.Screens;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenEquations;
import aurelienribon.tweenengine.TweenManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.lewis.TweenAccessors.SpriteTween;
import com.lewis.angrymasons.AngryMasons;

public class SplashScreen implements Screen {
	
	Texture splashTexture;
	Sprite splashSprite;	// Sprite allows you more access to the image (Image manipulation)
	SpriteBatch batch;
	AngryMasons game;
	TweenManager manager;
	
	public SplashScreen(AngryMasons game)
	{
		this.game = game;
	}

	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);	// Set back colour
		
		manager.update(delta);
		
		batch.begin();
		splashSprite.draw(batch);
		batch.end();
	}

	@Override
	public void resize(int width, int height) {

	}

	@Override
	public void show() {
		splashTexture = new Texture("data/splashscreen.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
		splashSprite.setX(Gdx.graphics.getWidth() / 2 - (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2 - (splashSprite.getHeight() / 2));
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
		manager = new TweenManager();
		
		// Is fade in done		CALLBACK TRIGGERS
		TweenCallback cb = new TweenCallback(){

			@Override		// If the tween is finished, then do tweenCompleted.
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();
			}
		};
		
		// Animate
		Tween.to(splashSprite, SpriteTween.ALPHA, 2.5f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1,  2f).setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	private void tweenCompleted()
	{
		Gdx.app.log(AngryMasons.LOG, "Tween completed");
		game.setScreen(new MainMenu(game));
	}

	@Override
	public void hide() {

	}

	@Override
	public void pause() {

	}

	@Override
	public void resume() {

	}

	@Override
	public void dispose() {

	}

}
