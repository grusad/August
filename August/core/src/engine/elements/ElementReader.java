package engine.elements;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Json;

public class ElementReader {
	
	private static FileHandle file;
	private static Json json = new Json();
	
	public static ElementData getElementData(String fileName){
		file = new FileHandle("elementData/" + fileName);
		ElementData data = json.fromJson(ElementData.class, file);
		
		return data;
	}
	
	public static class ElementData{
		
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
