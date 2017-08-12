package engine.utils;

import java.util.List;

import engine.elements.Element;
import engine.entities.Entity;
import engine.tiles.Tile;
import engine.tiles.TileManager;

public class Psysics {
	
	private Entity entity;
	
	public Psysics(Entity entity){
		this.entity = entity;
	}
	
	public boolean collision(float xx, float yy){
		
		boolean collide = false;
		
		float x = entity.getHitBox().x;
		float y = entity.getHitBox().y;
		int w = entity.getHitBox().width;
		int h = entity.getHitBox().height;
		
		Box box = new Box(x + xx, y + yy, w, h);
		
		List<Element> elements = entity.worldManager.getElementManager().getElementsOnScreen();
		
		for(int i = 0; i < elements.size(); i++){
			Element element = elements.get(i);
	
			if(element.isSolid()){
				
				if(box.intersects(element.getHitBox())) collide = true;
			}
		}
		
		TileManager tileManager = entity.worldManager.getTileManager();
		int xp = (int) (x + w / 2) / Tile.SIZE;
		int yp = (int) (y + h / 2) / Tile.SIZE;
	
		Tile currentTile = tileManager.getTile(xp, yp);
		
		if(currentTile.isSwimmable()) entity.setInWater(true);
		else entity.setInWater(false);
		
		Tile aheadTile = tileManager.getTile(xp + (int)xx, yp + (int)yy);
		
		if(aheadTile.isSolid()) collide = true;
		
		
		return collide;
		
	}

}
