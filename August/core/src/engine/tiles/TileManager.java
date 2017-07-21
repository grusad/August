package engine.tiles;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import engine.graphics.Camera;
import engine.utils.NoiseGenerator;
import engine.world.WorldManager;

public class TileManager {

	private WorldManager worldManager;
	
	private static final float SAND_VALUE = 2.5f;
	private static final float GRASS_VALUE = 3.1f;
	
	private int[] tiles;

	private int x0;
	private int x1;
	private int y0;
	private int y1;

	public TileManager(WorldManager worldManager) {
		this.worldManager = worldManager;
	}
	
	public void generateLoadedTiles(int tiles[]){
		this.tiles = tiles;
	}

	public void generateNewTiles() {
		
		tiles = new int[worldManager.getWidth() * worldManager.getHeight()];

		NoiseGenerator generator = new NoiseGenerator(0, 30, 0);

		for (int y = 0; y < worldManager.getHeight(); y++) {
			for (int x = 0; x < worldManager.getWidth(); x++) {

				float noise = generator.generateNoise(y, x);

				int id;
				
				if(noise >= GRASS_VALUE) id = Tile.GRASS.getID();
				else if(noise >= SAND_VALUE) id = Tile.SAND.getID();
				else id = Tile.WATER.getID();
				
				tiles[x + y * worldManager.getWidth()] = id;

			}
		}

		smoothTiles();
		removeOutOfBoundsIslands();

	}

	private void smoothTiles() {
		for (int y = 0; y < worldManager.getHeight(); y++) {
			for (int x = 0; x < worldManager.getWidth(); x++) {
				if (getTile(x, y).getID() == Tile.GRASS.getID()) {
					if(lookAtCornersFor(Tile.SAND.getID(), x, y) >= 3) setTileAt(x, y, Tile.SAND.getID());
				}
				if (getTile(x, y).getID() == Tile.SAND.getID()) {
					if(lookAtCornersFor(Tile.WATER.getID(), x, y) >= 3) setTileAt(x, y, Tile.WATER.getID());
				}
			}
		}
	}
	
	private void removeOutOfBoundsIslands(){
		for(int y = 0; y < worldManager.getHeight(); y++){
			if(getTile(0, y).getID() != Tile.WATER.getID()) removeIsland(0, y);
			if(getTile(worldManager.getWidth() - 1, y).getID() != Tile.WATER.getID()) removeIsland(worldManager.getWidth() - 1, y);
		}
		
		for(int x = 0; x < worldManager.getWidth(); x++){
			if(getTile(x, 0).getID() != Tile.WATER.getID()) removeIsland(x, 0);
			if(getTile(x, worldManager.getHeight() - 1).getID() != Tile.WATER.getID()) removeIsland(x, worldManager.getHeight() - 1);
		}
	}

	private void removeIsland(int xSource, int ySource){
		
		List<Vector2> tileNode = new ArrayList<>();
		Vector2 current = new Vector2(xSource, ySource);
		tileNode.add(current);
		
		while(tileNode.size() > 0){
			
			setTileAt((int) current.x, (int) current.y, Tile.WATER.getID());
			
			for(int y = -1; y <= 1; y++){
				for(int x = -1; x <= 1; x++){
					if(getTile(x + (int) current.x, y + (int)current.y).getID() != Tile.WATER.getID()){
						tileNode.add(new Vector2(x + current.x, y + current.y));
					}
				}
			}
			
			tileNode.remove(current);
			if(tileNode.size() > 0)
				current = tileNode.get(tileNode.size() - 1);
		}
		
	}
	
	/** Returns the amount of tiles adjacent to the given x and y tile.*/
	private int lookAtCornersFor(int tileID, int x, int y){
		int index = 0;
		if(getTile(x + 1, y).getID() == tileID) index++;
		if(getTile(x - 1, y).getID() == tileID) index++;
		if(getTile(x, y + 1).getID() == tileID) index++;
		if(getTile(x, y - 1).getID() == tileID) index++;
		return index;
	}
	
	/** Return the amount of tiles adjacent all directions to the given x and y tile.*/
	public int lookAroundTileFor(int tileID, int x, int y){
		int index = 0;
		for(int yy = y - 1; yy <= y + 1; yy++){
			for(int xx = x - 1; xx <= x + 1; xx++){
				if(getTile(xx, yy).getID() == tileID) index++;
			}
		}
		
		return index;
	}

	public void update(Camera camera) {

		x0 = (int) (camera.getPosition().x - camera.getWidth() / 2) / Tile.SIZE - 1;
		x1 = (int) (x0 + (camera.getWidth()) / Tile.SIZE) + 3;
		y0 = (int) (camera.getPosition().y - camera.getHeight() / 2) / Tile.SIZE - 1;
		y1 = (int) (y0 + (camera.getHeight()) / Tile.SIZE) + 3;

	}

	public void render(SpriteBatch batch) {

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(batch, x, y, worldManager);
			}
		}

	}

	public Tile getTile(int x, int y) {
		if (x < 0 || x >= worldManager.getWidth() || y < 0 || y >= worldManager.getHeight())
			return Tile.WATER;
		return Tile.getTiles()[tiles[x + y * worldManager.getWidth()]];
	}
	
	public void setTileAt(int x, int y, int tileID){
		tiles[x + y * worldManager.getWidth()] = tileID;
	}

	public int[] getTiles() {
		return tiles;
	}

}
