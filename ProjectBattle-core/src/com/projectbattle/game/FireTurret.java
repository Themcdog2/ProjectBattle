package com.projectbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class FireTurret extends Entity{
	
	float stateTime;
	
	public FireTurret(float worldX, float worldY) {
		super(worldX, worldY);
		this.texture = Assets.fireTurret;
	}

	@Override
	public void update(float deltaTime) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(SpriteBatch batch) {
        stateTime += Gdx.graphics.getDeltaTime(); // Accumulate elapsed animation time

        // Get current frame of animation for the current stateTime
        //TextureRegion currentFrame = animation.getKeyFrame(stateTime, true);
        batch.draw(texture, worldX, worldY, 16, 16); // Draw current frame at (50, 50)
	}

}
