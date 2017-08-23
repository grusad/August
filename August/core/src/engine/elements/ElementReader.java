package engine.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class ElementReader {
	
	private static Json json = new Json();
	
	private static Map<String, ElementProperties> data = new HashMap<>();
	
	public static void loadElementProperties(){
		data.put("BlueFlower", get("01BlueFlower"));
		data.put("Grass", get("02Grass"));
		data.put("PalmTree", get("03PalmTree"));
		data.put("PalmTreeSmall", get("04PalmTreeSmall"));
		data.put("PinkFlower", get("05PinkFlower"));
		data.put("PinkTree01", get("06PinkTree01"));
		data.put("PinkTree02", get("07PinkTree02"));
		data.put("PotatoPlant", get("08PotatoPlant"));
		data.put("StoneBig", get("09StoneBig"));
		data.put("StoneMedium", get("10StoneMedium"));
		data.put("StoneSmallDouble", get("11StoneSmallDouble"));
		data.put("StoneSmallSingle", get("12StoneSmallSingle"));
	}
	
	private static ElementProperties get(String fileName){
		ElementProperties data = json.fromJson(ElementProperties.class, new FileHandle("elementProperties/" + fileName + ".json"));
		return data;
	}
	
	public static ElementProperties getElementProperties(String key){
		return data.get(key);
	}
	
	public static ElementProperties getRandomElementProperties(){
		
		List<String> keys = new ArrayList<String>(data.keySet());
		String randomKey = keys.get(new Random().nextInt(keys.size()));
		return data.get(randomKey);
	}
	
	public static int getMapSize(){
		return data.size();
	}

	public static void dispose(){
		data.clear();
	}
	
	public static class ElementProperties{
		
		public String name;
		public int spawnPercent;
		public int id;
		public int hp;
		public int textureXOffset;
		public int textureYOffset;
		public int hitBoxW;
		public int hitBoxH;
		public int layerIndex;
		public boolean isMinable;
		public boolean isSolid;
		public int minDropResource;
		public int maxDropResource;
		public int minDropFood;
		public int maxDropFood;
	}

}
