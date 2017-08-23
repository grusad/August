package engine.buildings;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class BuildingReader {
	
	private static Json json = new Json();
	
	private static Map<String, BuildingProperties> data = new HashMap<>();
	
	public static void loadBuildingProperties(){
		
	}
	
	private static BuildingProperties get(String fileName){
		return json.fromJson(BuildingProperties.class, new FileHandle("buildingProperties/" +  fileName + ".json"));
	}
	
	public static BuildingProperties getBuildingProperties(String key){
		return data.get(key);
	}
	
	public static void dispose(){
		data.clear();
	}
	
	public static class BuildingProperties{
	
	}

}
