package com.lewis.TileMapDemo.View;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;
import com.lewis.TileMapDemo.TileMapDemo;

public class World {
	
	TileMapDemo game;
	MapLoader loader;
	WorldRenderer wr;
	Array<Rectangle> rectangles = new Array<Rectangle>();
	
	TiledMap map;
	
	public World(TileMapDemo game){
		this.game = game;
		loader = new MapLoader();
		this.map = loader.loadMap(map);	//Load the map
				
		getCollidableTiles();
	}

	private void getCollidableTiles() {
		TiledMapTileLayer layer = (TiledMapTileLayer)map.getLayers().get(0);
		System.out.println(layer.getName());

		loader.getCollisionTiles(layer);
	}
	
	public void update(){
		
	}
	
	public TiledMap getMap(){

		return map;
	}
	
	public void setRenderer(WorldRenderer wr){
		this.wr = wr;
	}
	
	public void dispose(){
		
	}

}
