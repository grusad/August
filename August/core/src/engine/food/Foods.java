package engine.food;

import engine.graphics.Textures;
import engine.utils.Vector2i;

public class Foods {
	
	public static class Coconut extends Food{

		public Coconut(Vector2i tiledPosition) {
			super(tiledPosition, Textures.COCONUT);
		}
		
	}

}
