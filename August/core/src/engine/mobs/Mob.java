package engine.mobs;


import com.badlogic.gdx.math.Vector2;

import engine.entities.Entity;
import engine.graphics.Animation;
import engine.graphics.Textures;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.Psysics;

public abstract class Mob extends Entity{
	
	private Psysics psysics = new Psysics(this);
	
	protected Animation[] animations = new Animation[8];
	protected Animation[] swimAnimations = new Animation[8];
	
	private int animationSpeed = 2;
	
	private int staticWidth;
	private int staticHeight;
	
	private boolean isWalking = false;
	
	protected int direction = 4;
	
	public Mob(Vector2 worldPosition) {
		super(worldPosition, Textures.PLAYER_S[0]);
		setAnimations();
		
		this.region = animations[direction].getFrameAt(0);
		this.staticWidth = this.region.getRegionWidth();
		this.staticHeight = this.region.getRegionHeight();

	}
	
	protected abstract void setAnimations();
	
	protected void move(float xx, float yy){
		
		if(xx != 0 || yy != 0) isWalking = true;
		else isWalking = false;
		
		if(!(this instanceof Player)){
			if(xx == 0 && yy > 0) direction = 0;
			else if(xx > 0 && yy > 0) direction = 1;
			else if(xx > 0 && yy == 0) direction = 2;
			else if(xx > 0 && yy < 0) direction = 3;
			else if(xx == 0 && yy < 0) direction = 4;
			else if(xx < 0 && yy < 0) direction = 5;
			else if(xx < 0 && yy == 0) direction = 6;
			else if(xx < 0 && yy > 0) direction = 7;
		}
		
		
		if(!psysics.collision(xx, 0)){
			getWorldPosition().x += xx;
		}
		
		if(!psysics.collision(0, yy)){
			getWorldPosition().y += yy;
		}
		
		if(isInWater() && isWalking){
			
			ParticleManager.spawnParticleEffect(ParticleType.WaterParticle, new Vector2(getCenteredPositionCurrent().x, getWorldPosition().y), 2);
		}
		
	}
	
	public void update(){
		
		if(light != null) light.update(getCenteredPositionCurrent());
		
		if(isWalking && !isInWater()){
			animations[direction].update(animationSpeed);
			this.region = animations[direction].getCurrentFrame();			
		}
		else if(isInWater()){
			this.region = swimAnimations[direction].getCurrentFrame();
		}
		else{
			this.region = animations[direction].getFrameAt(0);
		}
		
	}
	
	public int getWidth(){
		return region.getRegionWidth();
	}
	
	public int getHeight(){
		return region.getRegionHeight();
	}
	
	public int getStaticWidth(){
		return staticWidth;
	}
	
	public int getStaticHeight(){
		return staticHeight;
	}
	
	public boolean isWalking(){
		return isWalking;
	}
	
	/** Gets the centered position with the current width and height.*/
	public Vector2 getCenteredPositionCurrent(){
		return new Vector2(getWorldPosition().x + getWidth() / 2, getWorldPosition().y + getHeight() / 2);
	}
	
	/** Gets the centered position with the the static width and height.*/
	public Vector2 getCenteredPosition(){
		return new Vector2(getWorldPosition().x + getStaticWidth() / 2, getWorldPosition().y + getStaticHeight() / 2);
	}
	
	public Vector2 getCenteredHitBox(){
		float x = getHitBox().x;
		float y = getHitBox().y;
		float w = getHitBox().width;
		float h = getHitBox().height;
		return new Vector2(x + w / 2, y + h / 2);
	}
	

}
