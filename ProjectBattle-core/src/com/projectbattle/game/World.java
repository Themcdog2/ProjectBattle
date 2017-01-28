package com.projectbattle.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class World {
	
	float worldWidth;
	float worldHeight;
	float tileSize;
	
	Tile worldTiles[][];
	
	static ArrayList<Entity> entities;
	
	public World(float worldWidth, float worldHeight, float tileSize){
		this.tileSize = tileSize;
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		
		entities = new ArrayList<Entity>();
		for(int i = 0; i < 100; i++){
			entities.add(new workerUnit((float)Math.random() * 1000,(float)Math.random() * 1000));
		}
		initTiles();
	}
	
	public void render(SpriteBatch batch){
		for(int r = 0; r < worldTiles.length; r++){
			for(int c = 0; c < worldTiles[r].length; c++){
				worldTiles[r][c].render(batch);
			}
		}
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(batch);
		}
	}
	
	public void initTiles(){
		worldTiles = new Tile[(int) (worldWidth/tileSize)][(int) (worldHeight/tileSize)];
		for(int r = 0; r < worldWidth/tileSize; r++){
			for(int c = 0; c < worldHeight/tileSize; c++){
				worldTiles[r][c] = new Tile(r * tileSize, c * tileSize, tileSize); 
			
				
			}
		}
	}
	
	

}
