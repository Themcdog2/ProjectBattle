package com.projectbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Tile{

	float tileSize;
	Texture texture;
	
	float worldX;
	float worldY;
	
	boolean isBlocked;
	
	
	
	
	public Tile(float worldX, float worldY, float tileSize){
		texture = Assets.grassTile;
		this.tileSize = (tileSize);
		//System.out.println(this.tileSize);
		this.worldX = worldX;
		this.worldY = worldY;
		
	}
	
	public Tile(){
		
	}
	
	public void render(SpriteBatch batch){
		
		batch.draw(texture, worldX, worldY, tileSize , tileSize);
		
	}
	

}
