package engine.elements;

import engine.graphics.Textures;
import engine.resources.ResourceManager;
import engine.resources.Resources.PalmWood;
import engine.resources.Resources.Rock;
import engine.utils.Vector2i;

public class Elements {
	
	public static class PalmTree extends Element {
		public static int ID = 1;
		public PalmTree(Vector2i tiledPosition)  {
			super(tiledPosition, Textures.PALM_TREE, ElementReader.getElementData("PalmTree.json"), ID);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new PalmWood(getTiledPosition()));
		}
		
	}
	
	public static class PinkTree01 extends Element {
		public static int ID = 2;	
		public PinkTree01(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PINK_TREE_01, ElementReader.getElementData("PinkTree01.json"), ID);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class PinkTree02 extends Element {
		public static int ID = 3;

		public PinkTree02(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PINK_TREE_02, ElementReader.getElementData("PinkTree02.json"), ID);
		}

		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}

	public static class StoneMedium extends Element {
		public static int ID = 4;
		public StoneMedium(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_MEDIUM, ElementReader.getElementData("StoneMedium.json"), ID);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneSmallSingle extends Element {
		public static int ID = 5;
		public StoneSmallSingle(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_SMALL_SINGLE, ElementReader.getElementData("StoneSmallSingle.json"), ID);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneSmallDouble extends Element {
		public static int ID = 6;
		public StoneSmallDouble(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_SMALL_DOUBLE, ElementReader.getElementData("StoneSmallDouble.json"), ID);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneBig extends Element {
		public static int ID = 7;
		public StoneBig(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_BIG, ElementReader.getElementData("StoneBig.json"), ID);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class PalmTreeSmall extends Element {
		public static int ID = 8;
		public PalmTreeSmall(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PALM_TREE_SMALL, ElementReader.getElementData("PalmTreeSmall.json"), ID);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class PotatoPlant extends Element {
		public static int ID = 9;
		public PotatoPlant(Vector2i tiledPosition) {
			super(tiledPosition, Textures.POTATO_PLANT, ElementReader.getElementData("PotatoPlant.json"), ID);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	

}
