package engine.particles;

import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.entities.Entity;

public class Particle extends Entity{

	protected static Random random = new Random();

	protected float lifeSeconds;
	private float timer = 0;
	protected float scaleX = 1, scaleY = 1;
	protected float xOrgin, yOrgin;
	protected float floor = -1;
	
	protected Vector2 originPosition;
	
	protected float xx;
	protected float yy;
	
	protected float angle;
	
	public Particle(Vector2 position, TextureRegion region, float lifeSeconds){
		super(position, region);
		this.lifeSeconds = lifeSeconds;
		this.originPosition = new Vector2(position);
		this.xOrgin = position.x;
		this.yOrgin = position.y;
	}
	
	public void update(){
		  timer += Gdx.graphics.getDeltaTime();
		  if(timer >= lifeSeconds) ParticleManager.removeParticle(this);
		  if(floor != -1)
			  if(getWorldPosition().y <= floor) ParticleManager.removeParticle(this);
		  
	}
	
	public void render(SpriteBatch batch){
		batch.draw(region, getWorldPosition().x, getWorldPosition().y, 0, 0, region.getRegionWidth(), region.getRegionHeight(), scaleX, scaleY, angle);	
	}

}
