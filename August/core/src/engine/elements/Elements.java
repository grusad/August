package engine.elements;

import engine.graphics.Textures;
import engine.resources.ResourceManager;
import engine.resources.Resources.PalmWood;
import engine.resources.Resources.Rock;
import engine.utils.Vector2i;

public class Elements {
	
	public static class PalmTree extends Element {

		public PalmTree(Vector2i tiledPosition)  {
			super(tiledPosition, Textures.PALM_TREE);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new PalmWood(getTiledPosition()));
		}
		
	}
	
	public static class PinkTree01 extends Element {

		public PinkTree01(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PINK_TREE_01);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class PinkTree02 extends Element {

		public PinkTree02(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PINK_TREE_02);
		}

		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}

	public static class StoneMedium extends Element {

		public StoneMedium(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_MEDIUM);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneSmallSingle extends Element {

		public StoneSmallSingle(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_SMALL_SINGLE);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneSmallDouble extends Element {

		public StoneSmallDouble(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_SMALL_DOUBLE);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class StoneBig extends Element {

		public StoneBig(Vector2i tiledPosition) {
			super(tiledPosition, Textures.STONE_BIG);
		}
		@Override
		protected void dropResource() {
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
			ResourceManager.addResource(new Rock(getTiledPosition()));
		}
	}
	
	public static class PalmTreeSmall extends Element {

		public PalmTreeSmall(Vector2i tiledPosition) {
			super(tiledPosition, Textures.PALM_TREE_SMALL);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class PotatoPlant extends Element {

		
		public PotatoPlant(Vector2i tiledPosition) {
			super(tiledPosition, Textures.POTATO_PLANT);
		}
		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
	}
	
	public static class Grass extends Element{

		public Grass(Vector2i tilePosition) {
			super(tilePosition, Textures.getRandomRegion(Textures.GRASS_ELEMENT));
		}

		@Override
		protected void dropResource() {
			
		}
		
	}
	
	public static class PinkFlower extends Element{
		public PinkFlower(Vector2i tilePosition) {
			super(tilePosition,Textures.FLOWER_PINK);
		}

		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
		
	}
	
	public static class BlueFlower extends Element{
		public BlueFlower(Vector2i tilePosition) {
			super(tilePosition,Textures.FLOWER_BLUE);
		}

		@Override
		protected void dropResource() {
			// TODO Auto-generated method stub
			
		}
		
	}
	

}
