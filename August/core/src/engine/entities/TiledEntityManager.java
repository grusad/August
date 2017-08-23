package engine.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.graphics.Camera;
import engine.tiles.Tile;
import engine.utils.Vector2i;
import engine.world.WorldManager;

public class TiledEntityManager {
	
	private static int x0;
	private static int x1;
	private static int y0;
	private static int y1;
	
	private static WorldManager worldManager;
	
	public static Map<Integer, ArrayList<Entity>> entities = new HashMap<>();
	public static List<Entity> entitiesOnScreen = new ArrayList<>();
	
	public static void update(Camera camera){
		
		entitiesOnScreen.clear();
		
		x0 = (int) (camera.getPosition().x - camera.getWidth() / 2) / Tile.SIZE - 5;
		x1 = (int) (x0 + (camera.getWidth()) / Tile.SIZE) + 9;
		y0 = (int) (camera.getPosition().y - camera.getHeight() / 2) / Tile.SIZE - 5;
		y1 = (int) (y0 + (camera.getHeight()) / Tile.SIZE) + 7;
		
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				
				ArrayList<Entity> entitiesAtTile = getEntitiesAtTile(new Vector2i(x, y));
				if(entitiesAtTile == null) continue;
				
				for(int i = 0; i < entitiesAtTile.size(); i++){
					entitiesOnScreen.add(entitiesAtTile.get(i));
				}
				
			}
			
		}
		
		for(int j = 0; j < entitiesOnScreen.size(); j++){
			entitiesOnScreen.get(j).update();
		}
		
	}
	
	public static void addEntity(Entity entity){
		ArrayList<Entity> current = getEntitiesAtTile(entity.getTiledPosition());
		if(current == null){
			current = new ArrayList<>();
		}
		current.add(entity);
		
		entities.put(getInteger(entity.getTiledPosition()), current);
	}
	
	public static void removeEntity(Entity entity){
		
		Integer integer = getInteger(entity.getTiledPosition());
		ArrayList<Entity> list = getEntitiesAtTile(entity.getTiledPosition());
		if(list == null) return;

		for(int i = 0; i < list.size(); i++){
			Entity current = list.get(i);
			if(current.equals(entity)){
				list.remove(current);
				break;
			}
		}
		if(list.size() == 0){
			entities.remove(integer);
			return;
		}
		entities.put(getInteger(entity.getTiledPosition()), list);
	}
	
	public static void setWorldManager(WorldManager worldManager){
		TiledEntityManager.worldManager = worldManager;
	}
	
	private static ArrayList<Entity> getEntitiesAtTile(Vector2i tilePosition){
		return entities.get(getInteger(tilePosition));
	}
	
	private static int getInteger(Vector2i vector){
		return new Integer(vector.x + vector.y * worldManager.getWidth());
	}

}
