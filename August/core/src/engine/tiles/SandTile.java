package engine.tiles;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.graphics.Textures;
import engine.world.WorldManager;

public class SandTile extends Tile{

	public SandTile(int id) {
		super(Textures.SAND_DEFAULT, id);
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
		
		if(world.getTileManager().getTile(x, y + 1) == Tile.WATER) up = true;
		if(world.getTileManager().getTile(x, y - 1) == Tile.WATER) down = true;
		if(world.getTileManager().getTile(x + 1, y) == Tile.WATER) right = true;
		if(world.getTileManager().getTile(x - 1, y) == Tile.WATER) left = true;
		
		if(world.getTileManager().getTile(x - 1, y + 1) == Tile.WATER) upLeft = true;
		if(world.getTileManager().getTile(x + 1, y + 1) == Tile.WATER) upRight = true;
		if(world.getTileManager().getTile(x - 1, y - 1) == Tile.WATER) downLeft = true;
		if(world.getTileManager().getTile(x + 1, y - 1) == Tile.WATER) downRight = true;
		
		if(!up && down && !left && !right) region = Textures.SAND[5];
		else if(up && !down && !left && !right) region = Textures.SAND[7];
		else if(!up && !down && left && !right) region = Textures.SAND[11];
		else if(!up && !down && !left && right) region = Textures.SAND[1];
		else if(up && !down && left && !right) region = Textures.SAND[9];
		else if(up && !down && !left && right) region = Textures.SAND[4];
		else if(!up && down && left && !right) region = Textures.SAND[8];
		else if(!up && down && !left && right) region = Textures.SAND[3];
		else if(!up && !down && !left && !right && !upLeft && !upRight && downLeft && !downRight) region = Textures.SAND[10];
		else if(!up && !down && !left && !right && !upLeft && !upRight && !downLeft && downRight) region = Textures.SAND[0];
		else if(!up && !down && !left && !right && upLeft && !upRight && !downLeft && !downRight) region = Textures.SAND[12];
		else if(!up && !down && !left && !right && !upLeft && upRight && !downLeft && !downRight) region = Textures.SAND[2];
		
			
		else region = Textures.SAND_DEFAULT;
		
		batch.draw(region, x * SIZE, y * SIZE);
	}

}
