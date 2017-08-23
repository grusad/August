package engine.entities;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.audio.AudioManager;
import engine.graphics.TextureSheet;
import engine.tiles.Tile;
import engine.utils.ValueBouncer;
import engine.utils.Vector2i;

public abstract class InteractableEntity extends Entity{
	
	protected boolean isInteracting = false;
	private ValueBouncer valueBouncer = null;
	
	protected boolean isMinable;
	protected boolean isMovable;
	
	private boolean isDropping = false;
	private float drop_speed = 4;
	private float drop_startPositionY;
	private float drop_rotationSpeed;
	private float drop_velY = 0.3f;

	public InteractableEntity(Vector2i tiledPosition, TextureRegion region) {
		this(new Vector2(tiledPosition.getX() * Tile.SIZE, tiledPosition.y * Tile.SIZE), region);
	}
	
	public InteractableEntity(Vector2 worldPosition, TextureRegion region) {
		super(worldPosition, region);
	}
	
	public void update(){
		
		if(isInteracting){
			transparency = valueBouncer.updateAndReturnValue();
		}
		if(isDropping){
			updateDropAnimation();
		}
	}
	
	public void updateDropAnimation(){
		
		setLayerIndex(LayerIndex.TOP);
		isMovable = false;
		
		getWorldPosition().y += drop_speed;
		drop_speed -= drop_velY;
		
		rotation += drop_rotationSpeed;
			
		if(getWorldPosition().y <= drop_startPositionY){
			isDropping = false;
			getWorldPosition().y  = drop_startPositionY;
			setLayerIndex(LayerIndex.BOTTOM);
			isMovable = true;
			AudioManager.playSound(AudioManager.getSound("button"));
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
	
	public void runDropAnimation(){
		drop_startPositionY = getWorldPosition().y;
		drop_rotationSpeed = random.nextInt(35) + 10;
		isDropping = true;
	}
	
	public boolean isDropAnimationRunning(){
		return isDropping;
	}

}
