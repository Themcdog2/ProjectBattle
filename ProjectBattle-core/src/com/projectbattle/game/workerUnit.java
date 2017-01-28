package com.projectbattle.game;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class workerUnit extends Entity{

	
	public workerUnit(float worldX, float worldY){
		super(worldX, worldY);
		 width = 2 * ProjectBattle.pixelsToUnits;
		 height = 2 * ProjectBattle.pixelsToUnits;
		setTexture(Assets.workerUnit);
	}
	
	@Override
	public void update() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
		// TODO Auto-generated method stub
		if(isSelected()){
			batch.draw(texture, worldX, worldY, width * ProjectBattle.unitsToPixels, height * ProjectBattle.unitsToPixels);
			ProjectBattle.shapeRenderer.rect(worldX, worldY, width * ProjectBattle.unitsToPixels, height * ProjectBattle.unitsToPixels);
		}else{
		batch.draw(texture, worldX, worldY, width * ProjectBattle.unitsToPixels, height * ProjectBattle.unitsToPixels);
		}
	}

}
