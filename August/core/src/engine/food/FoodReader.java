package engine.food;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class FoodReader {
	
	private static Json json = new Json();
	
	private static Map<String, FoodProperties> data = new HashMap<>();
	
	public static void loadFoodProperties(){
		
	}
	
	private static FoodProperties get(String fileName){
		return json.fromJson(FoodProperties.class, new FileHandle("foodProperties/" +  fileName + ".json"));
	}
	
	public static FoodProperties getFoodProperties(String key){
		return data.get(key);
	}
	
	public static void dispose(){
		data.clear();
	}
	
	public static class FoodProperties{
		public int id;
		public String name;
		public int hitBoxW;
		public int hitBoxH;
		public int minHPGain;
		public int maxHPGain;
		public int layerIndex;
		public int isMovable;
		public int isSolid;
	}

}
