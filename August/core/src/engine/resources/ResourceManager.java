package engine.resources;

import java.util.ArrayList;
import java.util.List;

public class ResourceManager {
	
	public static List<Resource> resources = new ArrayList<>();
	
	public static void update(){
		for(int i = 0; i < resources.size(); i++){
			resources.get(i).update();
		}
	}
	
	public static void addResource(Resource resource){
		resources.add(resource);
	}
	
	public static void removeResource(Resource resource){
		resources.remove(resource);
	}

}
