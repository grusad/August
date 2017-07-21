package engine.entities;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import engine.light.Light;
import engine.light.LightManager;
import engine.utils.Box;
import engine.world.WorldManager;

public class Entity {
	
	protected static Random random = new Random();
	protected Vector2 position;
	public WorldManager worldManager;
	protected LightManager lightManager;
	protected Light light;
	
	protected int layerIndex = LayerIndex.DEFAULT;
	
	public Entity(Vector2 position){
		this.position = position;
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch){
		
	}
		
	public Vector2 getPosition(){
		return position;
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
	
	public Box getHitBox(){
		return null;
	}
	
	/** Returns the index of rendering order. Look at class LayerIndex.class. */
	public int getLayerIndex(){
		return layerIndex;
	}
	
	/** Sets the index of the order to render this entity. Look at class LayerIndex.class. */
	public void setLayerIndex(int layerIndex){
		this.layerIndex = layerIndex;
	}
	
	public static class LayerIndex{
		
		public static int DEFAULT = 0;
		public static int BOTTOM = -1;
		public static int TOP = 1;
		
	}
	
	

}
