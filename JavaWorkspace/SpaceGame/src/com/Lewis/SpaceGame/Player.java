
package com.Lewis.SpaceGame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class Player {
	Texture playerTexture;
	
	Vector2 playerPosition;
	Vector2 velocity;
	
	float rotation = 0;
	float scale = 0;
	float speed = 300f;
	
	Rectangle playerRectangle;
	
	public Player(Texture _texture, Vector2 _position)
	{
		playerTexture = _texture;
		playerPosition = _position;
	}
	
	public void Update()
	{	
		playerRectangle = new Rectangle();
		playerRectangle.x = playerPosition.x;
		playerRectangle.y = playerPosition.y;
		playerRectangle.width = playerTexture.getWidth();
		playerRectangle.height = playerTexture.getHeight();
		
		//Movement
		if(Gdx.input.isKeyPressed(Keys.UP)){
			playerPosition.y += speed * Gdx.graphics.getDeltaTime();
		}
		else if(Gdx.input.isKeyPressed(Keys.DOWN)){
			playerPosition.y -= speed * Gdx.graphics.getDeltaTime();
		}
		else if(Gdx.input.isKeyPressed(Keys.LEFT)){
			playerPosition.x -= speed * Gdx.graphics.getDeltaTime();
		}
		else if(Gdx.input.isKeyPressed(Keys.RIGHT)){
			playerPosition.x += speed * Gdx.graphics.getDeltaTime();
		}
		else	
		{
			velocity = Vector2.Zero;
		}
		
		if(Gdx.input.isKeyPressed(Keys.LEFT))
		{
			rotation -= 3f;
		}
		if(Gdx.input.isKeyPressed(Keys.RIGHT))
		{
			rotation += 3f;
		}
	}
	
	public void Draw(SpriteBatch spriteBatch)
	{
		spriteBatch.draw(playerTexture, playerPosition.x, playerPosition.y);
	}
}
