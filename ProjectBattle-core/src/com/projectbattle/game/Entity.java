package com.projectbattle.game;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public abstract class Entity {
	
	protected boolean movableEntity;
	
	protected float health;
	
	protected float worldX;
	protected float worldY;
	
	protected float width;
	protected float height;
	
	
	protected boolean selected;
	
	protected Texture texture;
	
	
	public Entity(){
		worldX = 0;
		worldY = 0;
		health = 100;
		
	}
	
	public Entity(float worldX, float worldY){
		this.worldX = worldX;
		this.worldY = worldY;
	}
	public Entity(float worldX, float worldY, boolean movableEntity){
		this(worldX, worldY);
		this.movableEntity = movableEntity;
	}
	public Entity(float worldX, float worldY, boolean movableEntity, Texture texture){
		this(worldX, worldY, movableEntity);
		this.texture = texture;
	}
	
	public abstract void update();
	public abstract void render(SpriteBatch batch);

	public boolean isMovableEntity() {
		return movableEntity;
	}

	public float getHealth() {
		return health;
	}

	public float getWorldX() {
		return worldX;
	}

	public float getWorldY() {
		return worldY;
	}

	public Texture getTexture() {
		return texture;
	}

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public void setWidth(float width) {
		this.width = width;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	public void setMovableEntity(boolean movableEntity) {
		this.movableEntity = movableEntity;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public void setWorldX(float worldX) {
		this.worldX = worldX;
	}

	public void setWorldY(float worldY) {
		this.worldY = worldY;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}
	

}
