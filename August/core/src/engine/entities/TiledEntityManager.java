package engine.entities;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.badlogic.gdx.Gdx;

import engine.food.Food;
import engine.food.FoodReader;
import engine.food.Foods.Coconut;
import engine.graphics.Camera;
import engine.resources.Resource;
import engine.resources.ResourceReader;
import engine.resources.Resources.PalmWood;
import engine.resources.Resources.PinkWood;
import engine.resources.Resources.Rock;
import engine.tiles.Tile;
import engine.utils.DataManager.EntityData;
import engine.utils.Vector2i;
import engine.world.WorldManager;

public class TiledEntityManager {
	
	private static int x0;
	private static int x1;
	private static int y0;
	private static int y1;
	
	private static WorldManager worldManager;
	
	private static List<InteractableEntity> entitiesToSpawn = new ArrayList<>();
	private static float spawnCooldown = 0;
	
	
	public static Map<Integer, ArrayList<Entity>> entities = new HashMap<>();
	public static List<Entity> entitiesOnScreen = new ArrayList<>();
	
	public static void update(Camera camera){
		
		entitiesOnScreen.clear();
		
		if(spawnCooldown > 0){
			spawnCooldown -= Gdx.graphics.getDeltaTime();
		}
		
		if(!entitiesToSpawn.isEmpty()){
			
			if(spawnCooldown <= 0){
				InteractableEntity entity = entitiesToSpawn.get(0);
				addEntity(entity);
				entity.runDropAnimation();
				entitiesToSpawn.remove(0);
				spawnCooldown = 0.1f;
			}
		}
		
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
	
	public static void spawnEntity(ArrayList<InteractableEntity> entities){
		TiledEntityManager.entitiesToSpawn.addAll(entities);
	}
	
	private static ArrayList<Entity> getEntitiesAtTile(Vector2i tilePosition){
		return entities.get(getInteger(tilePosition));
	}
	
	private static int getInteger(Vector2i vector){
		return new Integer(vector.x + vector.y * worldManager.getWidth());
	}
	
	public static void generateLoadedEntities(EntityData[] entities){
		for(int i = 0; i < entities.length; i++){
			addEntityByTypeAndID(entities[i]);
		}
	}
	
	private static void addEntityByTypeAndID(EntityData data){
		
		Entity entity = null;
		Vector2i position = new Vector2i(data.x, data.y);
		
		switch(data.type){
			case "Food":
				if(data.id == FoodReader.getFoodProperties("Coconut").id) entity = new Coconut(position);
			break;
			
			case "Resource":
				if(data.id == ResourceReader.getResourceProperties("PalmWood").id) entity = new PalmWood(position);
				if(data.id == ResourceReader.getResourceProperties("Rock").id) entity = new Rock(position);
				if(data.id == ResourceReader.getResourceProperties("PinkWood").id) entity= new PinkWood(position);
			break;
		}
		
		if(entity == null) return;
		
		entity.setRotation(data.rotation);
		
		addEntity(entity);
	}
	
	public static EntityData[] writeEntitiesToData(){
		ArrayList<Entity> allEntities = new ArrayList<>();
		
		for(ArrayList<Entity> stack : entities.values()){
			for(int i = 0; i < stack.size(); i++){
				allEntities.add(stack.get(i));
			}
		}
		
		EntityData[] entityData = new EntityData[allEntities.size()];
		
		for(int j = 0; j < allEntities.size(); j++){
			EntityData newData = new EntityData();
			Entity entity = allEntities.get(j);
			
			if(entity instanceof Resource) newData.type = "Resource";
			if(entity instanceof Food) newData.type = "Food";
			
			newData.id = entity.getID();
			newData.x = entity.getTiledPosition().x;
			newData.y = entity.getTiledPosition().y;
			newData.rotation = entity.getRotation();
			entityData[j] = newData;
		}
		
		return entityData;
	}
	
}
