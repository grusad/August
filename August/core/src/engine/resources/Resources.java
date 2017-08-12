package engine.resources;

import engine.graphics.Textures;
import engine.utils.Vector2i;

public class Resources {
	
	public static class PalmWood extends Resource{
		public static final int ID = 1;
		public PalmWood(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PALM_WOOD, ID);
		}
	}
	
	public static class Rock extends Resource{
		public static final int ID = 2;
		public Rock(Vector2i tiledPosition) {
			super(tiledPosition, Textures.ROCK, ID);
		}
	}
	

}
