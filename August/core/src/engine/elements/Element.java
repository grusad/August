package engine.elements;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.audio.AudioManager;
import engine.elements.ElementReader.ElementData;
import engine.entities.InteractableEntity;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.Vector2i;

public abstract class Element extends InteractableEntity{
	
	private float maxHP;
	private float currentHP;

	private int id;
	
	public Element(Vector2i tilePosition, TextureRegion region, ElementData data, int id) {
		super(tilePosition, region);
		this.id = id;
		setData(data);
	}
	
	protected abstract void dropResource();
	
	private void setData(ElementData data){
		this.xTextureOffset = data.textureXOffset;
		this.yTextureOffset = data.textureYOffset;
		this.hitBoxHeight = data.hitBoxH;
		this.hitBoxWidth = data.hitBoxW;
		this.layerIndex = data.layerIndex;
		this.isSolid = data.isSolid;
		this.isMinable = data.isMinable;
		this.maxHP = this.currentHP = data.hp;
	}
	
	public void update(){
		
		super.update();
		
		if(currentHP <= 0){
			worldManager.getElementManager().removeElement(this);
			AudioManager.playSound(AudioManager.getSound("button"), 0.5f);
			ParticleManager.spawnParticleEffect(ParticleType.MineParticle, getHitBoxCenterPos(), 100);
			this.dropResource();
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
	
	public int getID(){
		return id;
	}
	
}
