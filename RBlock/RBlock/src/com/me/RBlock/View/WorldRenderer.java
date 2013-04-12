package com.me.RBlock.View;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.me.RBlock.RBlock;
import com.me.RBlock.Block.Block;

public class WorldRenderer {
	
	private static final float CAMERA_WIDTH = 10f;
	private static final float CAMERA_HEIGHT = 7f;

	private World world;
	private OrthographicCamera camera;
	SpriteBatch spriteBatch;
	
	Texture blockTexture;
	
	ShapeRenderer sr;
	
	private float ppuX;
	private float ppuY;
	private int width;
	private int height;
	
	public void setSize(int w, int h){
		this.width = w;
		this.height = h;
		ppuX = (float)width / CAMERA_WIDTH / 10;
		ppuY = (float)height / CAMERA_HEIGHT / 10;
	}
	
	public WorldRenderer(World world){
		this.world = world;
		
		this.camera = new OrthographicCamera();
		camera.setToOrtho(false, width, height);
		this.camera.position.set(CAMERA_WIDTH / 2f, CAMERA_HEIGHT / 2f, 0);
		this.camera.update();
		
		spriteBatch = new SpriteBatch();
		
		sr = new ShapeRenderer();
		
		loadTextures();
	}
	
	public void loadTextures(){
		blockTexture = new Texture("data/Blocks/block.png");
	}
	
	public void render(){
		
		spriteBatch.begin();
		
		for(Block block : world.getBlocks()){
			spriteBatch.draw(blockTexture, block.getPosition().x * ppuX, block.getPosition().y * ppuY, Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
		
		spriteBatch.end();
		
		if(RBlock.DEBUG){
			debugrender();
		}
	}
	
	public void debugrender(){
		sr.setProjectionMatrix(camera.combined);
		
		sr.begin(ShapeType.Filled);
		
		for(Block block : world.getBlocks()){
			sr.setColor(Color.WHITE);
			sr.rect(block.getPosition().x, block.getPosition().y, Block.SIZE * ppuX, Block.SIZE * ppuY);
		}
		
		sr.end();
	}
	
	public void dispose(){
		
	}
	
}
