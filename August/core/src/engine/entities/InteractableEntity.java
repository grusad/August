package engine.entities;

import com.badlogic.gdx.math.Vector2;

import engine.utils.ValueBouncer;

public class InteractableEntity extends Entity{
	
	protected boolean isInteracting = false;
	private ValueBouncer valueBouncer = null;
	
	protected boolean isMinable;
	
	protected float transparency = 1f;

	public InteractableEntity(Vector2 position) {
		super(position);
	}
	
	public void update(){
		
		if(isInteracting){
			transparency = valueBouncer.updateAndReturnValue();
		}
	}
	
	public void startInteract(){
		this.valueBouncer = new ValueBouncer(0.5f, 1, 1, 1.5f, false);
		this.isInteracting = true;
	}
	
	public void stopInteract(){
		this.isInteracting = false;
		transparency = 1f;
	}
	
	public boolean isMinable(){
		return isMinable;
	}

}
