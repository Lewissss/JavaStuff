package com.lewis.lightGame.Screens;

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
import com.lewis.lightGame.LightGame;
import com.lewis.lightGame.TweenAccessors.SpriteTween;

public class SplashScreen implements Screen {

	Texture splashTexture;
	Sprite splashSprite;
	SpriteBatch batch;
	LightGame game;
	TweenManager manager;
	
	public SplashScreen(LightGame game){
		this.game = game;
	}
	
	@Override
	public void render(float delta) {
		Gdx.gl.glClearColor(0,  0,  0,  1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
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
		splashTexture = new Texture("data/SplashScreen.png");
		splashTexture.setFilter(TextureFilter.Linear, TextureFilter.Linear);
		
		splashSprite = new Sprite(splashTexture);
		splashSprite.setColor(1, 1, 1, 0);
		splashSprite.setX(Gdx.graphics.getWidth() / 2 - (splashSprite.getWidth() / 2));
		splashSprite.setY(Gdx.graphics.getHeight() / 2 - (splashSprite.getHeight() / 2));
		
		batch = new SpriteBatch();
		
		Tween.registerAccessor(Sprite.class, new SpriteTween());
		
		manager = new TweenManager();
		
		// If fade in is done
		TweenCallback cb = new TweenCallback() {
			
			@Override	// If tween complete, do tweenCompleted();
			public void onEvent(int type, BaseTween<?> source) {
				tweenCompleted();		
			}
		};
		
		//Animate splashscreen
		Tween.to(splashSprite, SpriteTween.ALPHA, 2.5f).target(1).ease(TweenEquations.easeInQuad).repeatYoyo(1, 2f)
		.setCallback(cb).setCallbackTriggers(TweenCallback.COMPLETE).start(manager);
	}
	
	private void tweenCompleted(){
		Gdx.app.log(LightGame.LOG, "Tween completed");
		game.setScreen(new GameScreen(game));
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
