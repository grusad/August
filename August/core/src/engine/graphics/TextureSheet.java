package engine.graphics;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class TextureSheet extends Texture {

	public static final TextureSheet ELEMENTS = new TextureSheet("textureSheets/elements.png", 16, 0);
	public static final TextureSheet PLAYER = new TextureSheet("mobs/player.png", 32, 0);
	public static final TextureSheet TILES = new TextureSheet("textureSheets/tiles.png", 16, 0);
	public static final TextureSheet PARTICLES = new TextureSheet("textureSheets/particles.png", 4, 0);
	public static final TextureSheet BLANK = new TextureSheet("textureSheets/blank.png");
	public static final TextureSheet RESOURCES = new TextureSheet("textureSheets/resources.png");
	
	private static final Random random = new Random();

	private int cellSize;
	private int cellOffset;
	
	public TextureSheet(String path){
		super(Gdx.files.internal(path));
	}

	public TextureSheet(String path, int cellSize, int cellOffset) {
		super(Gdx.files.internal(path));
		this.cellSize = cellSize;
		this.cellOffset = cellOffset;
	}

	/** Returns the region of the given values. Is effected by cellsize and spacing.*/
	public TextureRegion getTextureRegion(int x, int y, int width, int height) {
		TextureRegion region = new TextureRegion(this, x * cellSize + (x * cellOffset + cellOffset),
				y * cellSize + (y * cellOffset + cellOffset), width, height);
		fixBleeding(region);
		return region;
	}
	
	/** Returns the region of the given values. Not effected by cellsize.*/
	public TextureRegion getRawTextureRegion(int x, int y, int width, int height){
		TextureRegion region = new TextureRegion(this, x, y, width, height);
		fixBleeding(region);
		return region;
	}
	
	public static TextureRegion getRegionFromRegion(int x, int y, int width, int height, TextureRegion region){
		TextureRegion newRegion = new TextureRegion(region, x, y, width, height);
		fixBleeding(newRegion);
		return newRegion;
	}
	
	public static TextureRegion getRandomRegionFromArray(TextureRegion[] regions){
		return regions[random.nextInt(regions.length)];
	}
	
	public static TextureRegion[] splitSheet(TextureSheet sheet, int x, int y, int width, int height, boolean scanHorizontal){
		
		int horSize = width / sheet.getCellSize();
		int verSize = height / sheet.getCellSize();
		
		TextureRegion[] regions = new TextureRegion[horSize * verSize];
		
		int index = 0;
		
		if(scanHorizontal){
			for(int yy = 0; yy < verSize; yy++){
				for(int xx = 0; xx < horSize; xx++){
					regions[index] = sheet.getRawTextureRegion((xx * sheet.getCellSize()) + x, (yy * sheet.getCellSize()) + y, sheet.getCellSize(), sheet.getCellSize());
					index++;
				}
			}
		}
		else{
			for(int xx = 0; xx < horSize; xx++){
				for(int yy = 0; yy < verSize; yy++){
					regions[index] = sheet.getRawTextureRegion((xx * sheet.getCellSize()) + x, (yy * sheet.getCellSize()) + y, sheet.getCellSize(), sheet.getCellSize());
					index++;
				}
			}
		}
			
		return regions;
	}

	public int getCellSize() {
		return cellSize;
	}
	
	private static void fixBleeding (TextureRegion region) {
        float fix = 0.01f;
        float x = region.getRegionX();
        float y = region.getRegionY();
        float width = region.getRegionWidth();
        float height = region.getRegionHeight();
        float invTexWidth = 1f / region.getTexture().getWidth();
        float invTexHeight = 1f / region.getTexture().getHeight();
        region.setRegion((x + fix) * invTexWidth, (y + fix) * invTexHeight, (x + width - fix) * invTexWidth, (y + height - fix) * invTexHeight);
    }

	public static void disposeAll() {
		PLAYER.dispose();
		TILES.dispose();
		ELEMENTS.dispose();
		PARTICLES.dispose();
		BLANK.dispose();
		RESOURCES.dispose();
	}

}
