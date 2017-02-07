package com.projectbattle.game;

import java.util.ArrayList;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.ai.pfa.Connection;
import com.badlogic.gdx.ai.pfa.HierarchicalGraph;
import com.badlogic.gdx.ai.pfa.HierarchicalPathFinder;
import com.badlogic.gdx.ai.pfa.PathFinder;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;

public class World {
	
	static float worldWidth;
	static float worldHeight;
	static float tileSize;
	
	static Tile worldTiles[][];
	
	
	static boolean regions[][];
	static boolean chunks[][];
	
	
	
	public static boolean blockedTiles[][];
	
	static ArrayList<Entity> entities;
	
	

	

	boolean smooth = false;
	boolean metrics = false;
	
	public World(float worldWidth, float worldHeight, float tileSize){
		this.tileSize = tileSize;
		this.worldWidth = worldWidth;
		this.worldHeight = worldHeight;
		
		initTiles();
		
		
		entities = new ArrayList<Entity>();
		for(int i = 0; i < 10; i++){
			entities.add(new workerUnit((float)Math.random() * 1000,(float)Math.random() * 1000));
		}
		entities.add(new FireTurret(0, 0));
		
		
	
	}
	
	public static void refreshBlockedTileArray(){
		blockedTiles = new boolean[(int) (worldWidth/tileSize)][(int) (worldHeight/tileSize)];
		for(int r = 0; r < worldTiles.length; r++){
			for(int c = 0; c < worldTiles[r].length; c++){
				if(worldTiles[r][c].isBlocked){
					blockedTiles[r][c] = true;
					}else{
						blockedTiles[r][c] = false;
					}
				}
			}
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
	
	public void renderSelection(ShapeRenderer shapeRenderer){
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).renderSelection(shapeRenderer);
		}

	}
	
	public void update(float deltaTime){
		refreshBlockedTileArray();
		
		for(int r = 0; r < worldTiles.length; r++){
			for(int c = 0; c < worldTiles[r].length; c++){
				//worldTiles[r][c].render(batch);
			}
		}
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).update(deltaTime);
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
