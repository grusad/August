package engine.resources;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class ResourceReader {
	
	private static Json json = new Json();
	
	private static Map<String, ResourceProperties> data = new HashMap<>();
	
	public static void loadResourceProperties(){
		data.put("PalmWood", get("01PalmWood"));
		data.put("Rock", get("02Rock"));
		data.put("PinkWood", get("03PinkWood"));

	}
	
	private static ResourceProperties get(String fileName){
		ResourceProperties data = json.fromJson(ResourceProperties.class, new FileHandle("resourceProperties/" + fileName + ".json"));
		return data;
	}
	
	public static ResourceProperties getResourceProperties(String key){
		return data.get(key);
	}
	
	public static int getMapSize(){
		return data.size();
	}

	public static void dispose(){
		data.clear();
	}
	
	public static class ResourceProperties{
		
		public String name;
		public int id;
		public int hitBoxW;
		public int hitBoxH;
		public int layerIndex;
		public boolean isMovable;
		public boolean isSolid;
	}

}