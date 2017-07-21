package engine.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.graphics.Textures;
import engine.world.WorldManager;

public class WaterTile extends Tile {

	public WaterTile(int id) {
		super(Textures.WATER_DEFAULT, id);
	}

	public void render(SpriteBatch batch, int x, int y, WorldManager world) {
		
		batch.draw(region, x * SIZE, y * SIZE);
	}
	
	public boolean isSwimmable(){
		return true;
	}

}
