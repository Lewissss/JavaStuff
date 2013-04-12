package com.lewis.TileMapDemo.View;

import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer.Cell;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.utils.Array;

public class MapLoader {

	TiledMap map;
	TextureAtlas tileAtlas;
	Array<Rectangle> rectangles = new Array<Rectangle>();
	
	public MapLoader(){
		
	}
	
	public TiledMap loadMap(TiledMap map){
		
		map = new TmxMapLoader().load("data/level.tmx");
		
		return map;
	}

	
	public Array<Rectangle> getCollisionTiles(TiledMapTileLayer layer) {
		
		//Test for getting tile rectangles
		for(int x = 0; x < 40; x++){
			for(int y = 0; y < 22; y++){
				Cell cell = layer.getCell(x, y);
				if(cell != null){
					rectangles.add(new Rectangle(x, y, 32, 32));
					System.out.println("X: " + x + "Y: " + y);
				}
			}
			System.out.println("Rectangles set");
		}
		return rectangles;
	}
}
