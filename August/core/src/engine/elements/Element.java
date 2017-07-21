package engine.elements;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.audio.AudioManager;
import engine.elements.ElementReader.ElementData;
import engine.entities.Entity;
import engine.graphics.TextureSheet;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.tiles.Tile;
import engine.utils.Box;

public class Element extends Entity{

	private float transparency = 1f;
	
	private float maxHP;
	private float currentHP;
	private boolean renderHP;
	
	protected TextureRegion region;
	
	protected int xOffset = 0;
	protected int yOffset = 0;
	private int hitBoxWidth;
	private int hitBoxHeight;
	private boolean isMinable;
	private boolean isSolid;

	private int id;
	
	public Element(Vector2 position, TextureRegion region, ElementData data, int id) {
		super(position);
		this.id = id;
		setData(data);
		this.region = region;	
	}
	
	private void setData(ElementData data){
		this.xOffset = data.textureXOffset;
		this.yOffset = data.textureYOffset;
		this.hitBoxHeight = data.hitBoxH;
		this.hitBoxWidth = data.hitBoxW;
		this.layerIndex = data.layerIndex;
		this.isSolid = data.isSolid;
		this.isMinable = data.isMinable;
		this.maxHP = this.currentHP = data.hp;
	}
	
	public void update(){ 
		
		if(currentHP <= 0){
			worldManager.getElementManager().removeElement(this);
			AudioManager.playSound(AudioManager.getSound("button"), 0.5f);
			ParticleManager.spawnParticleEffect(ParticleType.MineParticle, getHitBoxCenterPos(), 100);
		} 
		
	}
	
	public Box getTextureBounds(){
		float x = position.x * Tile.SIZE - region.getRegionWidth() / 2 + getHitBox().getWidth() / 2;
		float height = region.getRegionHeight() - yOffset;
		return new Box(x, position.y * Tile.SIZE, region.getRegionWidth(), (int) height);
	}
	
	public Box getHitBox(){
		return new Box(position.x * Tile.SIZE, position.y * Tile.SIZE, hitBoxWidth, hitBoxHeight);
		
	}
	
	/** Gets the centered point in the hitbox area.*/
	public Vector2 getHitBoxCenterPos(){
		float x = getHitBox().x + getHitBox().width / 2;
		float y = getHitBox().y + getHitBox().height / 2;
		return new Vector2(x, y);
	}
	 
	public void render(SpriteBatch batch){
		batch.setColor(1, 1, 1, transparency);
		batch.draw(region, position.x * Tile.SIZE - xOffset, position.y * Tile.SIZE - yOffset);	
		
		if(renderHP){
			
			float barLength = 32;
			float barHeight = 2;
			
			float x = getTextureBounds().x + getTextureBounds().width / 2 - barLength / 2;
			float y = getTextureBounds().y - 4;
			
			batch.setColor(Color.DARK_GRAY);
			batch.draw(TextureSheet.BLANK, x, y, barLength, barHeight);
			batch.setColor(Color.LIGHT_GRAY);
			batch.draw(TextureSheet.BLANK, x, y, barLength * (currentHP / maxHP), barHeight);
		}
		
		batch.setColor(1, 1, 1, 1);	
	}
	
	public void mine(float dmg){
		currentHP -= dmg;
	}
	
	public void resetElement(){
		transparency = 1;
		renderHP = false;
	}
	
	/** Returns the world coordinates for the element.*/
	public Vector2 getPosition(){
		return new Vector2(position.x * Tile.SIZE, position.y * Tile.SIZE);
	}
	
	/** Returns the tile coordinates for the element.*/
	public Vector2 getTilePosition(){
		return position;
	}
	
	public void setTransparency(float value){
		this.transparency = value;
	}
	
	public void setRenderHP(boolean bool){
		this.renderHP = bool;
	}
	
	public boolean isSolid(){
		return isSolid;
	} 
	
	public boolean isMinable(){
		return isMinable;
	}
	
	public int getWidth(){
		return region.getRegionWidth();
	}
	
	public int getHeight(){
		return region.getRegionHeight();
	}
	
	public float getCurrentHP(){
		return currentHP;
	}
	
	public int getID(){
		return id;
	}
	
}
