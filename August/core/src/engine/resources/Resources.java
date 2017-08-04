package engine.resources;

import com.badlogic.gdx.math.Vector2;

import engine.graphics.Textures;

public class Resources {
	
	public static class PalmWood extends Resource{
		public PalmWood(Vector2 position) {
			super(position, Textures.PALM_WOOD);
		}
	}

}
