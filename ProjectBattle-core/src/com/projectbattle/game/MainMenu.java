package com.projectbattle.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.sun.corba.se.impl.oa.poa.ActiveObjectMap.Key;

public class MainMenu {
	
	Texture mainMenuTexture;
	Texture menuPointerTexture;
	
	float interpolateX;
	
	int currentSelection;
	
	float viewportWidth;
	float viewportHeight;
	
	boolean immediateExitProtect = false;
	
	
	public MainMenu(float viewportWidth, float viewportHeight){
		this.viewportWidth = viewportWidth;
		this.viewportHeight = viewportHeight;
		
		menuPointerTexture = Assets.menuPointer;
		mainMenuTexture = Assets.mainMenu;
	}
	
	public void render(SpriteBatch batch){
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.W) || Gdx.input.isKeyJustPressed(Input.Keys.UP)){
			if(currentSelection > 0){
			currentSelection--;
			}else{
			currentSelection = 2;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.S) || Gdx.input.isKeyJustPressed(Input.Keys.DOWN)){
			if(currentSelection < 2){
			currentSelection++;
			}else{
			currentSelection = 0;
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
			if(immediateExitProtect == false){
			System.exit(0);
			}else{
				immediateExitProtect = false;
				
			}
		}
		
		if(Gdx.input.isKeyJustPressed(Input.Keys.ENTER)){
			if(currentSelection == 0){
				ProjectBattle.beginGame();
			}
			
			if(currentSelection == 2){
				System.exit(0);
			}
		}
		
		interpolateX+=4;
		if(interpolateX > 360){
			interpolateX = 0;
		}
		
		//System.out.println(interpolateX);
		batch.draw(mainMenuTexture, 0 - (viewportWidth/2), 0 - (viewportHeight/2), viewportWidth, viewportHeight);
		
		if(currentSelection == 0){
		batch.draw(menuPointerTexture, (MathUtils.sinDeg(interpolateX) * 10) - 125, 45, 64, 64);
		}
		
		if(currentSelection == 1){
		batch.draw(menuPointerTexture, (MathUtils.sinDeg(interpolateX) * 10) - 125, 0, 64, 64);
		}
		
		if(currentSelection == 2){
		batch.draw(menuPointerTexture, (MathUtils.sinDeg(interpolateX) * 10) - 125, -35, 64, 64);
		}
	}

	public void preventImmediateExit() {
		
		immediateExitProtect = true;
	}

}
