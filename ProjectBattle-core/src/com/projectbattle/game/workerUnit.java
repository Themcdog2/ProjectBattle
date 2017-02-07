package com.projectbattle.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import myPathFinding.Node;

public class workerUnit extends Entity{

	float movementSpeed = 50f; //Always multiply by delta time!!
	
	
	
	float movementFactor;
	
	public workerUnit(float worldX, float worldY){
		super(worldX, worldY);
		 width = 2 * ProjectBattle.pixelsToUnits;
		 height = 2 * ProjectBattle.pixelsToUnits;
		setTexture(Assets.workerUnit);
		
		
		
	//	System.out.println(getCurentTile());
		//World.worldTiles[(int) getCurentTile().x][(int) getCurentTile().y].texture = Assets.workerUnit;
		
		
		nodes.add(new Node((int) (Math.random()*250), (int) (Math.random()*250))); //250 bc in tile sizes not world units
		directionX = ((nodes.get(0).x * World.tileSize) - worldX);
		directionY = ((nodes.get(0).y * World.tileSize) - worldY);
		movementFactor = (float) (movementSpeed / Math.sqrt(directionX*directionX + directionY*directionY));
		
	}
	
	@Override
	public void update(float deltaTime) {
		if(nodes.size() > 0){
		float nodeWorldX = nodes.get(nodes.size()-1).x * World.tileSize;
		float nodeWorldY = nodes.get(nodes.size()-1).y * World.tileSize;
		
		System.out.println("Node world "  + nodeWorldX + " " + nodeWorldY);
		System.out.println("Unit world "  + worldX + " " + worldY);
		
		velocityX = directionX * movementFactor;
		velocityY = directionY * movementFactor;
		directionX = (nodeWorldX - worldX);
		directionY = (nodeWorldY - worldY);
		movementFactor = (float) (movementSpeed / Math.sqrt(directionX*directionX + directionY*directionY));
		
		if(Math.abs((nodeWorldX - worldX)) < 1f && Math.abs((nodeWorldY - worldY)) < 1f){
			//nextNode.set((float)Math.random()*1000, (float)Math.random()*1000);
			nodes.remove(nodes.size()-1);
			
			
			//nodes.add(new Vector2((float)Math.random()*1000, (float)Math.random()*1000));
			directionX = (nodeWorldX - worldX);
			directionY = (nodeWorldY - worldY);
			movementFactor = (float) (movementSpeed / Math.sqrt(directionX*directionX + directionY*directionY));
			
		}else{
		worldX += velocityX * deltaTime;
		worldY += velocityY * deltaTime;
		}
		// TODO Auto-generated method stub
		
		}
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		
		batch.draw(texture, worldX, worldY, width * ProjectBattle.unitsToPixels, height * ProjectBattle.unitsToPixels);
		
	}
	
	public void renderSelection(ShapeRenderer shapeRenderer){
		if(isSelected()){
			shapeRenderer.rect(worldX, worldY, width * ProjectBattle.unitsToPixels, height * ProjectBattle.unitsToPixels);
		}
		
	}
	
	

}
