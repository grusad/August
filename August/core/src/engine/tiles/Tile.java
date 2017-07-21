package engine.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.world.WorldManager;

public class Tile{
	
	private static Tile[] tiles = new Tile[256];
	
	public static final Tile WATER = new WaterTile(0);
	public static final Tile GRASS = new GrassTile(1);
	public static final Tile SAND = new SandTile(2);
	
	public static final int SIZE = 16;
	
	protected TextureRegion region;
	private int id;
	
	public Tile(TextureRegion region, int id){
		this.region = region;
		this.id = id;
		tiles[id] = this;
	}
	
	public void update(){
		
	}
	
	public void render(SpriteBatch batch, int x, int y, WorldManager world){
		batch.draw(region, x * SIZE, y * SIZE);
	}
	
	public boolean isSolid(){
		return false;
	}
	
	public boolean isSwimmable(){
		return false;
	}
	
	public static Tile[] getTiles(){
		return tiles;
	}
	
	public int getID(){
		return id;
	}

}
