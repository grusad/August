package engine.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import engine.elements.ElementReader.ElementProperties;
import engine.elements.Elements.BlueFlower;
import engine.elements.Elements.Grass;
import engine.elements.Elements.PalmTree;
import engine.elements.Elements.PalmTreeSmall;
import engine.elements.Elements.PinkFlower;
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
		
		ElementReader.loadElementProperties();
		
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
				
				ElementProperties elementProperties = ElementReader.getRandomElementProperties();
				
				if(checkSpawnPercent(elementProperties))
					addElementByID(elementProperties.id, new Vector2i(x, y));
			}
		}
	}
	
	private boolean checkSpawnPercent(ElementProperties elementProperties){
		if(random.nextInt(100) <= elementProperties.spawnPercent) return true;
		else return false;
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
		
		if(ID == ElementReader.getElementProperties("PalmTree").id) addElement(new PalmTree(tiledPosition));
		if(ID == ElementReader.getElementProperties("PalmTreeSmall").id) addElement(new PalmTreeSmall(tiledPosition));
		if(ID == ElementReader.getElementProperties("PinkTree01").id) addElement(new PinkTree01(tiledPosition));
		if(ID == ElementReader.getElementProperties("PinkTree02").id) addElement(new PinkTree02(tiledPosition));
		if(ID == ElementReader.getElementProperties("StoneSmallSingle").id) addElement(new StoneSmallSingle(tiledPosition));
		if(ID == ElementReader.getElementProperties("StoneSmallDouble").id) addElement(new StoneSmallDouble(tiledPosition));
		if(ID == ElementReader.getElementProperties("StoneMedium").id) addElement(new StoneMedium(tiledPosition));
		if(ID == ElementReader.getElementProperties("StoneBig").id) addElement(new StoneBig(tiledPosition));
		if(ID == ElementReader.getElementProperties("PotatoPlant").id) addElement(new PotatoPlant(tiledPosition));
		if(ID == ElementReader.getElementProperties("Grass").id) addElement(new Grass(tiledPosition));
		if(ID == ElementReader.getElementProperties("PinkFlower").id) addElement(new PinkFlower(tiledPosition));
		if(ID == ElementReader.getElementProperties("BlueFlower").id) addElement(new BlueFlower(tiledPosition));
		
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








