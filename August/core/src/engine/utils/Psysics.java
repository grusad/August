package engine.utils;

import java.util.List;

import engine.elements.Element;
import engine.mobs.Mob;
import engine.tiles.Tile;
import engine.tiles.TileManager;

public class Psysics {
	
	private Mob mob;
	
	public Psysics(Mob mob){
		this.mob = mob;
	}
	
	public boolean collision(float xx, float yy){
		
		boolean collide = false;
		
		float x = mob.getHitBox().x;
		float y = mob.getHitBox().y;
		int w = mob.getHitBox().width;
		int h = mob.getHitBox().height;
		
		Box box = new Box(x + xx, y + yy, w, h);
		
		List<Element> elements = mob.worldManager.getElementManager().getElementsOnScreen();
		
		for(int i = 0; i < elements.size(); i++){
			Element element = elements.get(i);
	
			if(element.isSolid()){
				
				if(box.intersects(element.getHitBox())) collide = true;
				
			}
		}
		
		TileManager tileManager = mob.worldManager.getTileManager();
		int xp = (int) (x + w / 2) / Tile.SIZE;
		int yp = (int) (y + h / 2) / Tile.SIZE;
	
		Tile currentTile = tileManager.getTile(xp, yp);
		
		if(currentTile.isSwimmable()) mob.setSwimming(true);
		else mob.setSwimming(false);
		
		Tile aheadTile = tileManager.getTile(xp + (int)xx, yp + (int)yy);
		
		if(aheadTile.isSolid()) collide = true;
		
		
		return collide;
		
	}

}
