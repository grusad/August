package engine.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.light.Light;
import engine.light.LightManager;
import engine.tiles.Tile;
import engine.utils.Box;
import engine.utils.Vector2i;
import engine.world.WorldManager;

public class Entity {
	
	protected static Random random = new Random();
	
	private Vector2 worldPosition;
	
	public WorldManager worldManager;
	protected LightManager lightManager;
	protected Light light;
	
	protected TextureRegion region;
	protected float rotation = 0;
	protected float scaleX = 1;
	protected float scaleY = 1;
	
	protected int xTextureOffset = 0;
	protected int yTextureOffset = 0;
	
	protected int hitBoxWidth = 0;
	protected int hitBoxHeight = 0;
	
	protected float transparency = 1f;
	protected String name = "";
	
	/** Make sure to render in center of tile. */
	protected int xCenterOffset = 0;
	protected int yCenterOffset = 0;
	
	protected boolean isSolid;
	private boolean inWater = false;
	
	protected int layerIndex = LayerIndex.DEFAULT;
	
	public Entity(Vector2 worldPosition, TextureRegion region){
		this.worldPosition = worldPosition;
		this.region = region;
		hitBoxWidth = region.getRegionWidth();
		hitBoxHeight = region.getRegionHeight();

	}
	
	public Entity(Vector2i tiledPosition, TextureRegion region){
		this(new Vector2(tiledPosition.getX() * Tile.SIZE, tiledPosition.y * Tile.SIZE), region);
	}
	
	public Box getHitBox(){
		return new Box(getWorldPosition().x + xCenterOffset, getWorldPosition().y + yCenterOffset, hitBoxWidth, hitBoxHeight);
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch){
		
		this.xCenterOffset = Tile.SIZE / 2 - getHitBox().width / 2;
		this.yCenterOffset = Tile.SIZE / 2 - getHitBox().height / 2;
		
		batch.setColor(1, 1, 1, transparency);
		
		
		batch.draw(region, getWorldPosition().x + xCenterOffset - xTextureOffset,
				getWorldPosition().y + yCenterOffset - yTextureOffset, region.getRegionWidth() / 2, 
				region.getRegionHeight() / 2, region.getRegionWidth(),
				region.getRegionHeight(), scaleX, scaleY, rotation);	
		
		batch.setColor(1, 1, 1, 1);
	}
		
	public Vector2 getWorldPosition(){
		
		return worldPosition;
	}
	
	public Vector2i getTiledPosition(){
		return new Vector2i((int) worldPosition.x / Tile.SIZE, (int) worldPosition.y / Tile.SIZE);
	}
	
	public void setPosition(Vector2 worldPosition){
		this.worldPosition = worldPosition;
	}
	
	public void setPosition(Vector2i tiledPosition){
		this.worldPosition.x = tiledPosition.x * Tile.SIZE;
		this.worldPosition.y = tiledPosition.y * Tile.SIZE;
	}
	
	public int getRegionWidth(){
		return region.getRegionWidth();
	}
	
	public int getRegionHeight(){
		return region.getRegionHeight();
	}
	
	/** Gets the centered point in the hitbox area.*/
	public Vector2 getHitBoxCenterPos(){
		float x = getHitBox().x + getHitBox().width / 2;
		float y = getHitBox().y + getHitBox().height / 2;
		return new Vector2(x, y);
	}
	
	public void setWorldManager(WorldManager worldManager){
		this.worldManager = worldManager;
	}
	
	public void setLightManager(LightManager lightManager){
		this.lightManager = lightManager;
	}
	
	public void setLightSource(Vector2 position){
		this.light = new Light(position, 100f, 10, 0.6f, lightManager.getRayHandler());
	}
	
	/** Returns the index of rendering order. Look at class LayerIndex.class. */
	public int getLayerIndex(){
		return layerIndex;
	}
	
	/** Sets the index of the order to render this entity. Look at class LayerIndex.class. */
	public void setLayerIndex(int layerIndex){
		this.layerIndex = layerIndex;
	}
	
	public boolean isSolid(){
		return isSolid;
	} 
	
	public boolean isInWater(){
		return inWater;
	}
	
	public void setInWater(boolean inWater){
		this.inWater = inWater;
	}
	
	public float getRotation() {
		return rotation;
	}

	public void setRotation(float rotation) {
		this.rotation = rotation;
	}

	public float getScaleX() {
		return scaleX;
	}

	public void setScaleX(float scaleX) {
		this.scaleX = scaleX;
	}

	public float getScaleY() {
		return scaleY;
	}

	public void setScaleY(float scaleY) {
		this.scaleY = scaleY;
	}
	
	public String getName(){
		return name;
	}

	public static class LayerIndex{
		
		public static int DEFAULT = 0;
		public static int BOTTOM = -1;
		public static int TOP = 1;
		
	}
	
	

}
