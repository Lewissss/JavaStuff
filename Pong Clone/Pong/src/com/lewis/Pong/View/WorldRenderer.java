package com.lewis.Pong.View;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.Rectangle;
import com.lewis.Pong.Pong;
import com.lewis.Pong.Model.Paddle;

public class WorldRenderer {
	
	World world;
	
	OrthographicCamera camera;
	SpriteBatch spriteBatch;
	
	ShapeRenderer sr;
	
	Paddle paddle;
	
	Rectangle backgroundPositionRectangle;
	
	//Texture
	Texture background;
	Texture paddleTexture;
	
	public WorldRenderer(World world){
		this.world = world;
		
		world.setRenderer(this);
		
		camera = new OrthographicCamera();
		camera.setToOrtho(false);
		camera.update();
		
		sr = new ShapeRenderer();
		
		spriteBatch = new SpriteBatch();
		spriteBatch.setProjectionMatrix(camera.combined);
		
		background = new Texture(Gdx.files.internal("data/Textures/background.png"));
		backgroundPositionRectangle = new Rectangle(0, 0, background.getWidth(), background.getHeight());
		
		paddleTexture = new Texture(Gdx.files.internal("data/Textures/paddle.png"));
		paddleTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}

	public void render(){
		Gdx.gl.glClearColor(255,  255,  255, 1);
		Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		
		paddle = world.getPaddle();
		
		spriteBatch.setProjectionMatrix(camera.combined);
		
		spriteBatch.begin();
		
		//Render background
		spriteBatch.draw(background, backgroundPositionRectangle.x, backgroundPositionRectangle.y);
		
		// Draw paddle
		spriteBatch.draw(paddleTexture, paddle.getPosition().x, paddle.getPosition().y);
		
		spriteBatch.end();
		
		if(Pong.DEBUG){
			debugRender();
		}
	}
	
	public void debugRender(){
		sr.begin(ShapeType.Line);
		
		sr.setColor(Color.MAGENTA);
		sr.rect(paddle.getPosition().x, paddle.getPosition().y, paddleTexture.getWidth(), paddleTexture.getHeight());
		
		sr.end();
	}
}
