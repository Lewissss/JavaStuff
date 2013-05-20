package com.lewis.boxgame.Models;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.BodyDef.BodyType;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Tile {

	private Body squareBody;
	private BodyDef squareDef;
	
	private float width = 8f;
	private float height = 8f;
	private Vector2 position;
	
	private Texture tileTexture;
	
	public Tile(World world, Vector2 position){
		
		this.position = position;
		setSquareShape(world, position);		
		
		tileTexture = new Texture(Gdx.files.internal("data/Textures/tile.png"));
		tileTexture.setFilter(TextureFilter.Nearest, TextureFilter.Nearest);
	}

	private void setSquareShape(World world, Vector2 position) {
		squareDef = new BodyDef();
		squareDef.type = BodyType.StaticBody;
		squareDef.position.set(position);
		
		squareBody = world.createBody(squareDef);
		
		PolygonShape boxShape = new PolygonShape();
		
		boxShape.setAsBox(width, height);
		
		squareBody.createFixture(boxShape, 0.0f);
	}
	
	public float getWidth(){
		return width;
	}
	
	public float getHeight(){
		return height;
	}
	
	public void draw(SpriteBatch batch){
		batch.draw(tileTexture, position.x - width, position.y - height);
	}
	
	public void dipose(){
		tileTexture.dispose();
	}

}
