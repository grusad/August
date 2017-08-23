package engine.graphics;

import java.util.Random;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.tiles.Tile;

public class Textures{
	
	private static final Random random = new Random();

	public static TextureRegion[] PLAYER_N = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 0, 256, 32, true);
	public static TextureRegion[] PLAYER_W = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 1, 256, 32, true);
	public static TextureRegion[] PLAYER_S = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 2, 256, 32, true);
	public static TextureRegion[] PLAYER_E = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 3, 256, 32, true);
	public static TextureRegion[] PLAYER_NW = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 4, 256, 32, true);
	public static TextureRegion[] PLAYER_NE = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 5, 256, 32, true);
	public static TextureRegion[] PLAYER_SW = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 6, 256, 32, true);
	public static TextureRegion[] PLAYER_SE = TextureSheet.splitSheet(TextureSheet.PLAYER, 0, 32 * 7, 256, 32, true);
	
	public static TextureRegion PLAYER_SWIM_N = TextureSheet.getRegionFromRegion(0, 0, PLAYER_N[0].getRegionWidth(), PLAYER_N[0].getRegionHeight() / 2, PLAYER_N[0]);
	public static TextureRegion PLAYER_SWIM_NE = TextureSheet.getRegionFromRegion(0, 0, PLAYER_NE[0].getRegionWidth(), PLAYER_NE[0].getRegionHeight() / 2, PLAYER_NE[0]);
	public static TextureRegion PLAYER_SWIM_E = TextureSheet.getRegionFromRegion(0, 0, PLAYER_W[0].getRegionWidth(), PLAYER_E[0].getRegionHeight() / 2, PLAYER_E[0]);
	public static TextureRegion PLAYER_SWIM_SE = TextureSheet.getRegionFromRegion(0, 0, PLAYER_SE[0].getRegionWidth(), PLAYER_SE[0].getRegionHeight() / 2, PLAYER_SE[0]);
	public static TextureRegion PLAYER_SWIM_S = TextureSheet.getRegionFromRegion(0, 0, PLAYER_S[0].getRegionWidth(), PLAYER_S[0].getRegionHeight() / 2, PLAYER_S[0]);
	public static TextureRegion PLAYER_SWIM_SW = TextureSheet.getRegionFromRegion(0, 0, PLAYER_SW[0].getRegionWidth(), PLAYER_SW[0].getRegionHeight() / 2, PLAYER_SW[0]);
	public static TextureRegion PLAYER_SWIM_W = TextureSheet.getRegionFromRegion(0, 0, PLAYER_W[0].getRegionWidth(), PLAYER_W[0].getRegionHeight() / 2, PLAYER_W[0]);
	public static TextureRegion PLAYER_SWIM_NW = TextureSheet.getRegionFromRegion(0, 0, PLAYER_NW[0].getRegionWidth(), PLAYER_NW[0].getRegionHeight() / 2, PLAYER_NW[0]);
	
	public static TextureRegion[] PARTICLE_WATER = TextureSheet.splitSheet(TextureSheet.PARTICLES, 0, 4 * 0, 4 * 4, 4, true);
	public static TextureRegion[] PARTICLE_MINE = TextureSheet.splitSheet(TextureSheet.PARTICLES, 0, 4 * 1, 4 * 4, 4, true);
	
	public static TextureRegion GRASS[] = TextureSheet.splitSheet(TextureSheet.TILES, 16 * 3, 0, 16 * 3, 16 * 5, false);
	public static TextureRegion GRASS_DEFAULT = GRASS[13];

	public static TextureRegion WATER_DEFAULT = TextureSheet.TILES.getTextureRegion(6, 0, Tile.SIZE, Tile.SIZE);

	public static TextureRegion SAND[] = TextureSheet.splitSheet(TextureSheet.TILES, 0, 0, 16 * 3, 16 * 5, false);
	public static TextureRegion SAND_DEFAULT = SAND[13];

	public static TextureRegion PALM_TREE = TextureSheet.ELEMENTS.getTextureRegion(0, 0, 64, 7 * 16);	
	public static TextureRegion PALM_TREE_SMALL = TextureSheet.ELEMENTS.getTextureRegion(6, 0, 32, 48);	
	public static TextureRegion PINK_TREE_01 = TextureSheet.ELEMENTS.getTextureRegion(0, 7, 6 * 16, 6 * 16);
	public static TextureRegion PINK_TREE_02 = TextureSheet.ELEMENTS.getTextureRegion(0, 13, 7 * 16, 7 * 16);
	public static TextureRegion STONE_MEDIUM = TextureSheet.ELEMENTS.getTextureRegion(4, 0, 32, 32);
	public static TextureRegion STONE_SMALL_SINGLE = TextureSheet.ELEMENTS.getTextureRegion(5, 2, 16, 16);
	public static TextureRegion STONE_SMALL_DOUBLE = TextureSheet.ELEMENTS.getTextureRegion(4, 2, 16, 16);
	public static TextureRegion STONE_BIG = TextureSheet.ELEMENTS.getTextureRegion(4, 3, 48, 48);
	public static TextureRegion POTATO_PLANT = TextureSheet.ELEMENTS.getTextureRegion(8, 0, 32, 32);
	public static TextureRegion[] GRASS_ELEMENT = TextureSheet.splitSheet(TextureSheet.ELEMENTS, 16 * 4, 6 * 16, 16 * 3, 16, true);
	public static TextureRegion FLOWER_PINK = TextureSheet.ELEMENTS.getTextureRegion(7, 4, 16, 32); 
	public static TextureRegion FLOWER_BLUE = TextureSheet.ELEMENTS.getTextureRegion(8, 5, 16, 16);
	
	public static TextureRegion PALM_WOOD = TextureSheet.RESOURCES.getTextureRegion(0, 0, 8, 16);
	public static TextureRegion ROCK = TextureSheet.RESOURCES.getTextureRegion(1, 0, 8, 8);
	public static TextureRegion PINK_WOOD = TextureSheet.RESOURCES.getTextureRegion(2, 0, 8, 16);
	
	public static TextureRegion COCONUT = TextureSheet.FOOD.getTextureRegion(0, 0, 8, 8);
	
	public static TextureRegion getRandomRegion(TextureRegion[] regions){
		return regions[random.nextInt(regions.length)];
	}
	
}
