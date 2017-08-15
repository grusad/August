package engine.resources;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import engine.graphics.Camera;
import engine.resources.Resources.PalmWood;
import engine.resources.Resources.Rock;
import engine.tiles.Tile;
import engine.utils.DataManager.ResourceData;
import engine.utils.Vector2i;
import engine.world.WorldManager;

public class ResourceManager {
	
	private static int x0;
	private static int x1;
	private static int y0;
	private static int y1;
	
	private static WorldManager worldManager;
	
	public static Map<Integer, ArrayList<Resource>> resources = new HashMap<>();
	public static List<Resource> resourcesOnScreen = new ArrayList<>();
	
	public static void update(Camera camera){
		
		resourcesOnScreen.clear();
		
		x0 = (int) (camera.getPosition().x - camera.getWidth() / 2) / Tile.SIZE - 5;
		x1 = (int) (x0 + (camera.getWidth()) / Tile.SIZE) + 9;
		y0 = (int) (camera.getPosition().y - camera.getHeight() / 2) / Tile.SIZE - 5;
		y1 = (int) (y0 + (camera.getHeight()) / Tile.SIZE) + 7;
		
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				
				ArrayList<Resource> resourcesAtTile = getResourcesAtTile(new Vector2i(x, y));
				if(resourcesAtTile == null) continue;
				
				for(int i = 0; i < resourcesAtTile.size(); i++){
					resourcesOnScreen.add(resourcesAtTile.get(i));
				}
				
			}
			
		}
		
		for(int j = 0; j < resourcesOnScreen.size(); j++){
			resourcesOnScreen.get(j).update();
		}
		
	}
	
	private static ArrayList<Resource> getResourcesAtTile(Vector2i tilePosition){
		return resources.get(getInteger(tilePosition));
	}
	
	public static void addResource(Resource resource){
		ArrayList<Resource> current = getResourcesAtTile(resource.getTiledPosition());
		if(current == null){
			current = new ArrayList<>();
		}
		current.add(resource);
		
		resources.put(getInteger(resource.getTiledPosition()), current);
	}
	
	public static void removeResource(Resource resource){
		Integer integer = getInteger(resource.getTiledPosition());
		ArrayList<Resource> list = getResourcesAtTile(resource.getTiledPosition());
		if(list == null) return;

		for(int i = 0; i < list.size(); i++){
			Resource current = list.get(i);
			if(current.equals(resource)){
				list.remove(current);
				break;
			}
		}
		if(list.size() == 0){
			resources.remove(integer);
			return;
		}
		resources.put(getInteger(resource.getTiledPosition()), list);
	}
	
	public static void setWorldManager(WorldManager worldManager){
		ResourceManager.worldManager = worldManager;
	}
	
	public static void generateLoadedResources(ResourceData[] resources) {
		for(int i = 0; i < resources.length; i++){
			addResourceByID(resources[i].id, new Vector2i(resources[i].x, resources[i].y));
		}
	}
	
	/** Make sure to add code when adding new resources into game.*/
	public static void addResourceByID(int ID, Vector2i position){
		Resource resource = null;
		
		if(ID == ResourceReader.getResourceData("PalmWood").id) resource = new PalmWood(position);
		if(ID == ResourceReader.getResourceData("Rock").id) resource = new Rock(position);
		
		addResource(resource);
	}
	
	private static int getInteger(Vector2i vector){
		return new Integer(vector.x + vector.y * worldManager.getWidth());
	}
	
	public static ResourceData[] writeResourcesToData(){
		
		ArrayList<Resource> allResources = new ArrayList<>();
		
		for(ArrayList<Resource> stack : resources.values()){
			for(int i = 0; i < stack.size(); i++){
				allResources.add(stack.get(i));
			}
		}
		
		ResourceData[] resourceData = new ResourceData[allResources.size()];
		
		for(int j = 0; j < allResources.size(); j++){
			ResourceData newData = new ResourceData();
			Resource resource = allResources.get(j);
			newData.id = resource.getID();
			newData.x = resource.getTiledPosition().x;
			newData.y = resource.getTiledPosition().y;
			resourceData[j] = newData;
		}
		
		return resourceData;
	}

}
