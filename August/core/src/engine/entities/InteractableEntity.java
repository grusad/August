package engine.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.graphics.TextureSheet;
import engine.utils.ValueBouncer;
import engine.utils.Vector2i;

public abstract class InteractableEntity extends Entity{
	
	protected boolean isInteracting = false;
	private ValueBouncer valueBouncer = null;
	
	protected boolean isMinable;
	protected boolean isMovable;

	public InteractableEntity(Vector2i tilesPosition, TextureRegion region) {
		super(tilesPosition, region);
	}
	
	public InteractableEntity(Vector2 worldPosition, TextureRegion region) {
		super(worldPosition, region);
	}
	
	public void update(){
		
		if(isInteracting){
			transparency = valueBouncer.updateAndReturnValue();
		}
	}
	
	public void renderHP(SpriteBatch batch, float currentHP, float maxHP){
		
		float barLength = 32;
		float barHeight = 2;
			
		float x = getHitBoxCenterPos().x - barLength / 2;
		float y = getHitBox().y - 4;
			
		batch.setColor(Color.DARK_GRAY);
		batch.draw(TextureSheet.BLANK, x, y, barLength, barHeight);
		batch.setColor(Color.LIGHT_GRAY);
		float current = barLength * (currentHP / maxHP);
		if(current < 0) current = 0;
		batch.draw(TextureSheet.BLANK, x, y, current, barHeight);
		
		batch.setColor(1, 1, 1, 1);	
		
	}
	
	public void startInteract(){
		this.valueBouncer = new ValueBouncer(0.5f, 1, 1, 1.5f, false);
		this.isInteracting = true;
	}
	
	public void stopInteract(){
		this.isInteracting = false;
		transparency = 1f;
	}
	
	public void moveToTile(Vector2i tilePos){
		setPosition(tilePos);
	}
	
	public boolean isMinable(){
		return isMinable;
	}
	
	public boolean isMovable(){
		return isMovable;
	}

}
