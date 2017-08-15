package engine.resources;

import engine.graphics.Textures;
import engine.utils.Vector2i;

public class Resources {
	
	public static class PalmWood extends Resource{
		public PalmWood(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PALM_WOOD);
		}
	}
	
	public static class Rock extends Resource{
		public Rock(Vector2i tiledPosition) {
			super(tiledPosition, Textures.ROCK);
		}
	}
	

}
