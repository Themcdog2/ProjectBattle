package com.projectbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Assets {
	
	static Texture mainMenu = null;
	static Texture menuPointer = null;
	
	static Texture grassTile = null;
	
	static Texture workerUnit = null;
	
	

	static Texture fireTurret = null;
	
	static boolean hasLoaded = false;

	public Assets(){
		loadAssets();
	}
	
	private void loadAssets(){
		mainMenu = new Texture(Gdx.files.internal("mainMenuv2.png"));
		menuPointer = new Texture(Gdx.files.internal("menuPointerv1.png"));
		
		grassTile = new Texture(Gdx.files.internal("tiles/grassTile.png"));
		workerUnit = new Texture(Gdx.files.internal("unit/workerUnit.png"));
		
		fireTurret = new Texture(Gdx.files.internal("towers/fireTower.png"));
	
		
		
		hasLoaded = true;
	}
}
