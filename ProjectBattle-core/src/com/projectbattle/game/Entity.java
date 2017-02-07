package com.projectbattle.game;

import java.util.ArrayList;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Vector2;

import myPathFinding.Node;
import myPathFinding.Pathfind;

public abstract class Entity {
	
	protected boolean movableEntity;
	
	protected float health;
	
	protected float worldX;
	protected float worldY;
	
	protected float velocityX;
	protected float velocityY;
	
	protected float directionX;
	protected float directionY;
	protected float angle;
	
	protected ArrayList<Node> nodes;
	
	protected float width;
	protected float height;
	
	
	protected boolean selected;
	
	protected Texture texture;
	protected Animation animation;
	
	
	public Entity(){
		worldX = 0;
		worldY = 0;
		health = 100;
		
		 initVar();
	}
	
	public Entity(float worldX, float worldY){
		this.worldX = worldX;
		this.worldY = worldY;
		
		initVar();
	}
	public Entity(float worldX, float worldY, boolean movableEntity){
		this(worldX, worldY);
		this.movableEntity = movableEntity;
		
		initVar();
	}
	public Entity(float worldX, float worldY, boolean movableEntity, Texture texture){
		this(worldX, worldY, movableEntity);
		this.texture = texture;
		
		initVar();
	}
	
	private void initVar(){
		nodes = new ArrayList<Node>();
	}
	
	public abstract void update(float deltaTime);
	public abstract void render(SpriteBatch batch);
	public void renderSelection(ShapeRenderer shapeRenderer){}
	
	public Vector2 getCurentTile(){

		Vector2 tempVector = new Vector2((Math.round(worldX/World.tileSize)), (Math.round(worldY/World.tileSize)));
		if(tempVector.x == 250){
			tempVector.x = 249;
		}
		if(tempVector.y == 250){
			tempVector.y = 249;
		}
		return tempVector;
		
	}
	
	
	public void moveTo(Node endNode){
		nodes.clear();
		for(Node n : Pathfind.search(new Node((int) getCurentTile().x, (int) getCurentTile().y), endNode)){
			nodes.add(n);
			System.out.println(nodes.get(0).tilePosition);
		}
		
	}

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

	public float getVelocityX() {
		return velocityX;
	}

	public float getVelocityY() {
		return velocityY;
	}

	public float getAngle() {
		return angle;
	}

	public Animation getAnimation() {
		return animation;
	}

	public void setAnimation(Animation animation) {
		this.animation = animation;
	}

	public void setVelocityX(float velocityX) {
		this.velocityX = velocityX;
	}

	public void setVelocityY(float velocityY) {
		this.velocityY = velocityY;
	}

	public void setAngle(float angle) {
		this.angle = angle;
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
