package com.projectbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

public class Assets {
	
	static Texture grassTile = null;
	
	static Texture workerUnit = null;
	
	static boolean hasLoaded = false;

	public Assets(){
		loadAssets();
	}
	
	private void loadAssets(){
		grassTile = new Texture(Gdx.files.internal("grassTile.png"));
		workerUnit = new Texture(Gdx.files.internal("workerUnit.png"));
		
		
		hasLoaded = true;
	}
}
