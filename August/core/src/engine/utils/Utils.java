package engine.utils;

import java.util.Comparator;

import engine.entities.Entity;

public class Utils {
	
	public static Comparator<Entity> positionSorter = new Comparator<Entity>() {
		public int compare(Entity e0, Entity e1) {
			
			float y0;
			float y1;
			if(e0.getHitBox() != null) y0 = e0.getHitBox().y;
			else y0 = e0.getPosition().y;
			if(e1.getHitBox() != null) y1 = e1.getHitBox().y;
			else y1 = e1.getPosition().y;
			
			if(y0 < y1) return 1;
			if(y0 > y1) return -1;
			return 0;

		}
		
	};

}
