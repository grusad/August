package engine.elements;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.elements.ElementReader.ElementProperties;
import engine.entities.InteractableEntity;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.Vector2i;

public abstract class Element extends InteractableEntity{
	
	private float maxHP;
	private float currentHP;
	protected int resourcesToDrop = 0;
	
	public Element(Vector2i tilePosition, TextureRegion region) {
		super(tilePosition, region);
		
		setProperties(ElementReader.getElementProperties(this.getClass().getSimpleName()));
	}
	
	protected abstract void dropResource(Vector2i tilePostion);
	
	private void setProperties(ElementProperties properties){
		this.name = properties.name;
		this.id = properties.id;
		this.xTextureOffset = properties.textureXOffset;
		this.yTextureOffset = properties.textureYOffset;
		this.hitBoxHeight = properties.hitBoxH;
		this.hitBoxWidth = properties.hitBoxW;
		this.layerIndex = properties.layerIndex;
		this.isSolid = properties.isSolid;
		this.isMinable = properties.isMinable;
		this.maxHP = this.currentHP = properties.hp;
		int minDropResource = properties.minDropResource;
		int maxDropResource = properties.maxDropResource;
		if(minDropResource < maxDropResource){
			this.resourcesToDrop = ThreadLocalRandom.current().nextInt(minDropResource, maxDropResource + 1);			
		}
	}
	
	public void update(){
		
		super.update();
		
		if(currentHP <= 0){
			worldManager.getElementManager().removeElement(this);
			ParticleManager.spawnParticleEffect(ParticleType.MineParticle, getHitBoxCenterPos(), 100);
			this.dropResource(getTiledPosition());
		} 
		
	}

	public void render(SpriteBatch batch){
		super.render(batch);
		if(isInteracting) super.renderHP(batch, currentHP, maxHP);
			
	}
	
	public void mine(float dmg){
		currentHP -= dmg;
	}
	
	public float getCurrentHP(){
		return currentHP;
	}
	
}
