package com.projectbattle.game;


import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer.ShapeType;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.sun.javafx.geom.Vec3f;

public class ProjectBattle extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	
	static final float pixelsToUnits = 1/5f;
	static final float unitsToPixels = 5/1f;
	
	World world;
	OrthographicCamera camera;
	
	private Vector2 selectionPoint1 = new Vector2();
	private Vector2 selectionPoint2 = new Vector2();
	boolean selectionFlag;
	private Rectangle selectionRectangle = new Rectangle();
	private int rotationSpeed = 1;
	 static ShapeRenderer shapeRenderer;
	//2728
	
	@Override
	public void create () {
		Assets assets = new Assets();
		   float w = Gdx.graphics.getWidth();
	        float h = Gdx.graphics.getHeight();

	        // Constructs a new OrthographicCamera, using the given viewport width and height
	        // Height is multiplied by aspect ratio.
	        camera = new OrthographicCamera(500, 500 * (h / w));
		batch = new SpriteBatch();
		world = new World(1000, 1000, 10);
		world.worldTiles[5][5].texture = new Texture(Gdx.files.internal("greyTile.png"));
		img = new Texture("badlogic.jpg");
		shapeRenderer = new ShapeRenderer();
	}

	@Override
	public void render () {
		processInput();
		camera.update();
		batch.setProjectionMatrix(camera.combined);
		shapeRenderer.setProjectionMatrix(camera.combined);
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT | (Gdx.graphics.getBufferFormat().coverageSampling?GL20.GL_COVERAGE_BUFFER_BIT_NV:0));
		
		shapeRenderer.begin(ShapeType.Line);
		batch.begin();
		world.render(batch);
		
		//batch.draw(img, 0, 0);
		//batch.draw(TileAssets.grassTile, 0, 0, 32, 32);
		batch.end();
		
		
		shapeRenderer.setColor(Color.BLUE);

		shapeRenderer.rect(selectionRectangle.x, selectionRectangle.y, selectionRectangle.width, selectionRectangle.height);
		shapeRenderer.end();
	}
	
	Vector3 getMousePosInGameWorld() {
		 return camera.unproject(new Vector3(Gdx.input.getX(), Gdx.input.getY(), 0));
		}
	
	private void processInput() {
		if(Gdx.input.isButtonPressed(0)){
			if(!selectionFlag){
				selectionPoint1.set(getMousePosInGameWorld().x, getMousePosInGameWorld().y);
				selectionFlag = true;
			}else{
				selectionPoint2.set(getMousePosInGameWorld().x, getMousePosInGameWorld().y);
				selectionRectangle.setX(selectionPoint1.x);
				selectionRectangle.setY(selectionPoint1.y);
				selectionRectangle.setWidth(selectionPoint2.x - selectionPoint1.x);
				selectionRectangle.setHeight(selectionPoint2.y - selectionPoint1.y);
				System.out.println(selectionRectangle);
			
			}
			
			for(int i = 0; i < World.entities.size(); i++){
				if(selectionRectangle.width < 0 || selectionRectangle.height < 0){
					
					
					if(selectionRectangle.width < 0 && selectionRectangle.height > 0){
						
						Rectangle tempRectangle = new Rectangle(selectionRectangle);
						tempRectangle.setX(selectionRectangle.x + selectionRectangle.width);
						tempRectangle.setWidth(Math.abs(selectionRectangle.width));
						
						if(tempRectangle.contains(World.entities.get(i).worldX, World.entities.get(i).worldY)){
							World.entities.get(i).setSelected(true);
						}else{
							World.entities.get(i).setSelected(false);
						}
					}
					
					if(selectionRectangle.height < 0 && selectionRectangle.width > 0){
						
						Rectangle tempRectangle = new Rectangle(selectionRectangle);
						tempRectangle.setY(selectionRectangle.y + selectionRectangle.height);
						tempRectangle.setHeight(Math.abs(selectionRectangle.height));
						
						if(tempRectangle.contains(World.entities.get(i).worldX, World.entities.get(i).worldY)){
							World.entities.get(i).setSelected(true);
						}else{
							World.entities.get(i).setSelected(false);
						}
					}
					
					if(selectionRectangle.width < 0 && selectionRectangle.height < 0){
						
						Rectangle tempRectangle = new Rectangle(selectionRectangle);
						tempRectangle.setX(selectionRectangle.x + selectionRectangle.width);
						tempRectangle.setWidth(Math.abs(selectionRectangle.width));
						tempRectangle.setY(selectionRectangle.y + selectionRectangle.height);
						tempRectangle.setHeight(Math.abs(selectionRectangle.height));
						//System.out.println("New rect " + tempRectangle);
						if(tempRectangle.contains(World.entities.get(i).worldX, World.entities.get(i).worldY)){
							World.entities.get(i).setSelected(true);
						}else{
							World.entities.get(i).setSelected(false);
						}
					}
						
					
					
				}else{
				if(selectionRectangle.contains(World.entities.get(i).worldX, World.entities.get(i).worldY)){
					World.entities.get(i).setSelected(true);
				}else{
					World.entities.get(i).setSelected(false);
				}
			}
		}
			
		}else{
			selectionFlag = false;
			selectionRectangle.set(0, 0, 0, 0);
			
		}
		
		
		if (Gdx.input.isKeyPressed(Input.Keys.SHIFT_LEFT)) {
            camera.zoom += 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.SPACE)) {
        	camera.zoom -= 0.02;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
        	camera.translate(-3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
        	camera.translate(3, 0, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
        	camera.translate(0, -3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
        	camera.translate(0, 3, 0);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.Q)) {
        	camera.rotate(-rotationSpeed, 0, 0, 1);
        }
        if (Gdx.input.isKeyPressed(Input.Keys.E)) {
        	camera.rotate(rotationSpeed, 0, 0, 1);
        }

       // camera.zoom = MathUtils.clamp(camera.zoom, 0.1f, 100/camera.viewportWidth);

       //float effectiveViewportWidth = camera.viewportWidth * camera.zoom;
      //  float effectiveViewportHeight = camera.viewportHeight * camera.zoom;

       // camera.position.x = MathUtils.clamp(camera.position.x, effectiveViewportWidth / 2f, 100 - effectiveViewportWidth / 2f);
       // camera.position.y = MathUtils.clamp(camera.position.y, effectiveViewportHeight / 2f, 100 - effectiveViewportHeight / 2f);
		
	}

	@Override
	public void dispose () {
		shapeRenderer.dispose();
		batch.dispose();
		img.dispose();
	}
}
