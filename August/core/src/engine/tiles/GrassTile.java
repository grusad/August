package engine.tiles;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.graphics.Textures;
import engine.world.WorldManager;

public class GrassTile extends Tile{

	public GrassTile(int id){
		super(Textures.GRASS_DEFAULT, id);
	}
	
	public void render(SpriteBatch batch, int x, int y, WorldManager world){
		
		boolean up = false;
		boolean down = false;
		boolean left = false;
		boolean right = false;
		
		boolean upLeft = false;
		boolean upRight = false;
		boolean downLeft = false;
		boolean downRight = false;
		
		if(world.getTileManager().getTile(x, y + 1) == Tile.SAND) up = true;
		if(world.getTileManager().getTile(x, y - 1) == Tile.SAND) down = true;
		if(world.getTileManager().getTile(x + 1, y) == Tile.SAND) right = true;
		if(world.getTileManager().getTile(x - 1, y) == Tile.SAND) left = true;
		
		if(world.getTileManager().getTile(x - 1, y + 1) == Tile.SAND) upLeft = true;
		if(world.getTileManager().getTile(x + 1, y + 1) == Tile.SAND) upRight = true;
		if(world.getTileManager().getTile(x - 1, y - 1) == Tile.SAND) downLeft = true;
		if(world.getTileManager().getTile(x + 1, y - 1) == Tile.SAND) downRight = true;
		
		if(!up && down && !left && !right) region = Textures.GRASS[5];
		else if(up && !down && !left && !right) region = Textures.GRASS[7];
		else if(!up && !down && left && !right) region = Textures.GRASS[11];
		else if(!up && !down && !left && right) region = Textures.GRASS[1];
		else if(up && !down && left && !right) region = Textures.GRASS[9];
		else if(up && !down && !left && right) region = Textures.GRASS[4];
		else if(!up && down && left && !right) region = Textures.GRASS[8];
		else if(!up && down && !left && right) region = Textures.GRASS[3];
		else if(!up && !down && !left && !right && !upLeft && !upRight && downLeft && !downRight) region = Textures.GRASS[10];
		else if(!up && !down && !left && !right && !upLeft && !upRight && !downLeft && downRight) region = Textures.GRASS[0];
		else if(!up && !down && !left && !right && upLeft && !upRight && !downLeft && !downRight) region = Textures.GRASS[12];
		else if(!up && !down && !left && !right && !upLeft && upRight && !downLeft && !downRight) region = Textures.GRASS[2];
		
			
		else region = Textures.GRASS_DEFAULT;
		
		batch.draw(region, x * SIZE, y * SIZE);
	}
}
