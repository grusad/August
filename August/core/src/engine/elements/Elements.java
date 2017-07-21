package engine.elements;

import com.badlogic.gdx.math.Vector2;

import engine.graphics.Textures;

public class Elements {
	
	public static class PalmTree extends Element {
		public static int ID = 1;
		public PalmTree(Vector2 position) {
			super(position, Textures.PALM_TREE, ElementReader.getElementData("PalmTree.json"), ID);
		}
	}
	
	public static class PinkTree01 extends Element {
		public static int ID = 2;	
		public PinkTree01(Vector2 position) {
			super(position, Textures.PINK_TREE_01, ElementReader.getElementData("PinkTree01.json"), ID);
		}
	}
	
	public static class PinkTree02 extends Element {
		public static int ID = 3;

		public PinkTree02(Vector2 position) {
			super(position, Textures.PINK_TREE_02, ElementReader.getElementData("PinkTree02.json"), ID);
		}
	}

	public static class StoneMedium extends Element {
		public static int ID = 4;
		public StoneMedium(Vector2 position) {
			super(position, Textures.STONE_MEDIUM, ElementReader.getElementData("StoneMedium.json"), ID);
		}
	}
	
	public static class StoneSmallSingle extends Element {
		public static int ID = 5;
		public StoneSmallSingle(Vector2 position) {
			super(position, Textures.STONE_SMALL_SINGLE, ElementReader.getElementData("StoneSmallSingle.json"), ID);
		}
	}
	
	public static class StoneSmallDouble extends Element {
		public static int ID = 6;
		public StoneSmallDouble(Vector2 position) {
			super(position, Textures.STONE_SMALL_DOUBLE, ElementReader.getElementData("StoneSmallDouble.json"), ID);
		}
	}
	
	public static class StoneBig extends Element {
		public static int ID = 7;
		public StoneBig(Vector2 position) {
			super(position, Textures.STONE_BIG, ElementReader.getElementData("StoneBig.json"), ID);
		}
	}
	
	public static class PalmTreeSmall extends Element {
		public static int ID = 8;
		public PalmTreeSmall(Vector2 position) {
			super(position, Textures.PALM_TREE_SMALL, ElementReader.getElementData("PalmTreeSmall.json"), ID);
		}
	}
	
	public static class PotatoPlant extends Element {
		public static int ID = 9;
		public PotatoPlant(Vector2 position) {
			super(position, Textures.POTATO_PLANT, ElementReader.getElementData("PotatoPlant.json"), ID);
		}
	}
	

}
