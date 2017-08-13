package engine.elements;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class ElementReader {
	
	private static Json json = new Json();
	
	private static Map<String, ElementData> data = new HashMap<>();
	
	public static void loadElementData(){
		data.put("BlueFlower", get("01BlueFlower"));
		data.put("FlowerGrass", get("02FlowerGrass"));
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
	
	private static ElementData get(String fileName){
		ElementData data = json.fromJson(ElementData.class, new FileHandle("elementData/" + fileName + ".json"));
		return data;
	}
	
	public static ElementData getElementData(String key){
		return data.get(key);
	}
	
	public static ElementData getRandomElementData(){
		
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
	
	public static class ElementData{
		
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
	}

}
