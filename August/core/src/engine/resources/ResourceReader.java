package engine.resources;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class ResourceReader {
	
	private static Json json = new Json();
	
	private static Map<String, ResourceData> data = new HashMap<>();
	
	public static void loadResourceData(){
		data.put("PalmWood", get("01PalmWood"));
		data.put("Rock", get("02Rock"));

	}
	
	private static ResourceData get(String fileName){
		ResourceData data = json.fromJson(ResourceData.class, new FileHandle("resourceData/" + fileName + ".json"));
		return data;
	}
	
	public static ResourceData getResourceData(String key){
		return data.get(key);
	}
	
	public static int getMapSize(){
		return data.size();
	}

	public static void dispose(){
		data.clear();
	}
	
	public static class ResourceData{
		
		public int id;
		public int hitBoxW;
		public int hitBoxH;
		public int layerIndex;
		public boolean isMovable;
		public boolean isSolid;
	}

}
