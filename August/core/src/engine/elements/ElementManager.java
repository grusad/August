package engine.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import engine.elements.Elements.PalmTree;
import engine.elements.Elements.PalmTreeSmall;
import engine.elements.Elements.PinkTree01;
import engine.elements.Elements.PinkTree02;
import engine.elements.Elements.PotatoPlant;
import engine.elements.Elements.StoneBig;
import engine.elements.Elements.StoneMedium;
import engine.elements.Elements.StoneSmallDouble;
import engine.elements.Elements.StoneSmallSingle;
import engine.entities.Entity;
import engine.graphics.Camera;
import engine.tiles.Tile;
import engine.tiles.TileManager;
import engine.utils.Vector2i;
import engine.world.WorldManager;

public class ElementManager {
	
	private static final int SPAWN_CHANCE_PALM_TREE = 2;
	private static final int SPAWN_CHANCE_PALM_TREE_SMALL = 1;
	private static final int SPAWN_CHANCE_PINK_TREE = 1;
	private static final int SPAWN_CHANCE_STONES = 1;
	private static final int SPAWN_CHANCE_POTATO_PLANT = 1;
	
	private Random random = new Random();
	
	private int x0;
	private int x1;
	private int y0;
	private int y1;
	
	private static Map<Integer, Element> elements = new HashMap<>();
	private static List<Element> elementsOnScreen = new ArrayList<>();
	
	private WorldManager worldManager;
	
	public ElementManager(WorldManager worldManager){
		this.worldManager = worldManager;
		
	}
	
	public void generateLoadedElements(int[] elements){
		for(int y = 0; y < worldManager.getWidth(); y++){
			for(int x = 0; x < worldManager.getWidth(); x++){
				if(elements[x + y * worldManager.getWidth()] == 0) continue;
				addElementByID(elements[x + y * worldManager.getWidth()], new Vector2i(x, y));
			}
		}
	}
	
	public void generateNewElements(){
		
		for(int y = 0; y < worldManager.getHeight(); y++){
			for(int x = 0; x < worldManager.getWidth(); x++){
				if(denyElementAdding(x, y)) continue;
				
				int ID = 0;
				
				if(random.nextInt(100) < SPAWN_CHANCE_PALM_TREE) ID = PalmTree.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_PALM_TREE_SMALL) ID = PalmTreeSmall.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_PINK_TREE) ID = PinkTree01.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_PINK_TREE) ID = PinkTree02.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_STONES) ID = StoneSmallSingle.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_STONES) ID = StoneSmallDouble.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_STONES) ID = StoneMedium.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_STONES) ID = StoneBig.ID;
				else if(random.nextInt(100) < SPAWN_CHANCE_POTATO_PLANT) ID = PotatoPlant.ID;
				
				else continue;
				
				addElementByID(ID, new Vector2i(x, y));
			}
		}
	}
	
	/** Checks if its possible to add element on the given position.*/
	private boolean denyElementAdding(int x, int y){
		
		TileManager tileManager = worldManager.getTileManager();
		
		if(tileManager.getTile(x, y).getID() == Tile.WATER.getID()) return true;
		if(tileManager.lookAroundTileFor(Tile.WATER.getID(), x, y) > 0) return true;
		if(random.nextBoolean()) return true;
		
		return false;
	}
	
	/** Make sure to add Elements!!*/
	public void addElementByID(int ID, Vector2i tiledPosition){
		
		if(ID == PalmTree.ID) addElement(new PalmTree(tiledPosition));
		if(ID == PalmTreeSmall.ID) addElement(new PalmTreeSmall(tiledPosition));
		if(ID == PinkTree01.ID) addElement(new PinkTree01(tiledPosition));
		if(ID == PinkTree02.ID) addElement(new PinkTree02(tiledPosition));
		if(ID == StoneSmallSingle.ID) addElement(new StoneSmallSingle(tiledPosition));
		if(ID == StoneSmallDouble.ID) addElement(new StoneSmallDouble(tiledPosition));
		if(ID == StoneMedium.ID) addElement(new StoneMedium(tiledPosition));
		if(ID == StoneBig.ID) addElement(new StoneBig(tiledPosition));
		if(ID == PotatoPlant.ID) addElement(new PotatoPlant(tiledPosition));
		
	}
	
	public void addElement(Entity entity){
		entity.setWorldManager(worldManager);
		entity.setLightManager(worldManager.getLightManager());
		Element element = (Element) entity;
		elements.put(getInteger(element.getTiledPosition()), element);
	}
	
	public void removeElement(Element element){
		elements.remove(getInteger(element.getTiledPosition()));
	}
	
	public void update(Camera camera){
		
		
		elementsOnScreen.clear();
		
		x0 = (int) (camera.getPosition().x - camera.getWidth() / 2) / Tile.SIZE - 5;
		x1 = (int) (x0 + (camera.getWidth()) / Tile.SIZE) + 9;
		y0 = (int) (camera.getPosition().y - camera.getHeight() / 2) / Tile.SIZE - 5;
		y1 = (int) (y0 + (camera.getHeight()) / Tile.SIZE) + 7;
		
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				Element element = getElement(new Vector2i(x, y));
				if(element == null) continue;
				elementsOnScreen.add(element);
				element.update();
			}
		}
		
	}
	
	public Element getElement(Vector2i tiledPosition){
		return elements.get(getInteger(tiledPosition));
		
	}
	
	public Element getElement(int x, int y){
		return getElement(new Vector2i(x, y));
	}
	
	public List<Element> getElementsOnScreen(){
		return elementsOnScreen;
	}
	
	public Map<Integer, Element> getAllElements(){
		return elements;
	}
	
	private int getInteger(Vector2i vector){
		return new Integer(vector.x + vector.y * worldManager.getWidth());
	}
	
	public int[] writeElementsToIntArray(){
		
		int w = worldManager.getWidth();
		int h = worldManager.getHeight();
		
		int[] array = new int[w * h];
		
		for(int y = 0; y < h; y++){
			for(int x = 0; x < w; x++){
				if(getElement(x, y) == null){
					array[x + y * w] = 0;
					continue;
				}
				array[x + y * w] = getElement(x, y).getID();
			}
		}
		
		return array;
	
	}

}








