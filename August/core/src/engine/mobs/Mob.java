package engine.mobs;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.entities.Entity;
import engine.graphics.Animation;
import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.Box;
import engine.utils.Psysics;

public abstract class Mob extends Entity{
	
	protected TextureRegion region;
	private Psysics psysics = new Psysics(this);
	
	protected Animation[] animations = new Animation[8];
	protected Animation[] swimAnimations = new Animation[8];
	
	private int animationSpeed = 2;
	
	private int staticWidth;
	private int staticHeight;
	
	private boolean isWalking = false;
	private boolean isSwimming = false;
	
	protected int direction = 4;
	
	public Mob(Vector2 position) {
		super(position);
		setAnimations();
		
		this.region = animations[direction].getFrameAt(0);
		this.staticWidth = this.region.getRegionWidth();
		this.staticHeight = this.region.getRegionHeight();
	}
	
	protected abstract void setAnimations();
	public abstract Box getHitBox();
	
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
			position.x += xx;
		}
		
		if(!psysics.collision(0, yy)){
			position.y += yy;
		}
		
		if(isSwimming && isWalking){
			
			ParticleManager.spawnParticleEffect(ParticleType.WaterParticle, new Vector2(getCenteredPositionCurrent().x, getPosition().y), 2);
		}
		
	}
	
	public void update(){
		
		if(light != null) light.update(getCenteredPositionCurrent());
		
		if(isWalking && !isSwimming){
			animations[direction].update(animationSpeed);
			this.region = animations[direction].getCurrentFrame();			
		}
		else if(isSwimming){
			this.region = swimAnimations[direction].getCurrentFrame();
		}
		else{
			this.region = animations[direction].getFrameAt(0);
		}
		
	}
	
	public void render(SpriteBatch batch){
		batch.draw(region, position.x, position.y);
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
	
	public boolean isSwimming(){
		return isSwimming;
	}
	
	public void setSwimming(boolean swimming){
		this.isSwimming = swimming;
	}
	
	/** Gets the centered position with the current width and height.*/
	public Vector2 getCenteredPositionCurrent(){
		return new Vector2(position.x + getWidth() / 2, position.y + getHeight() / 2);
	}
	
	/** Gets the centered position with the the static width and height.*/
	public Vector2 getCenteredPosition(){
		return new Vector2(position.x + getStaticWidth() / 2, position.y + getStaticHeight() / 2);
	}
	
	public Vector2 getCenteredHitBox(){
		float x = getHitBox().x;
		float y = getHitBox().y;
		float w = getHitBox().width;
		float h = getHitBox().height;
		return new Vector2(x + w / 2, y + h / 2);
	}
	

}
