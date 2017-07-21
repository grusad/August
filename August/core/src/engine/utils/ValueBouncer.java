package engine.utils;

import com.badlogic.gdx.Gdx;

public class ValueBouncer{
	
	private float minValue;
	private float maxValue;
	private float currentValue;
	private float stepSize;
	
	private boolean isIncreasing = false;
	private boolean isDecreasing = false;
	
	/**
	 * A class that handles a simple task as bouncing a value between two values.
	 * 
	 * @param minValue Minimum value to bounce to.
	 * @param maxValue Maximum value to bounce to.
	 * @param startValue Starting value.
	 * @param stepSize The amount to increase each frame. If stepSize < 1 the value will change less than 1 per second.
	 * @param startIncreasing Whether the bouncer should start increase or decrease the value.
	 */
	public ValueBouncer(float minValue, float maxValue, float startValue, float stepSize, boolean startIncreasing){
		if(startValue < minValue || startValue > maxValue)
			try {
				throw new InvalidNumberException();
			} catch (InvalidNumberException e) {
				e.printStackTrace();
			}
		this.stepSize = stepSize;
		this.minValue = minValue;
		this.maxValue = maxValue;
		this.currentValue = startValue;
		if(startIncreasing) this.isIncreasing = true;
		else this.isDecreasing = true;
	}
	
	public float updateAndReturnValue(){
		
		if(isIncreasing){
			currentValue += Gdx.graphics.getDeltaTime() * stepSize;
			if(currentValue >= maxValue){
				currentValue = maxValue;
				isIncreasing = false;
				isDecreasing = true;
			}
		}
		
		if(isDecreasing){
			currentValue -= Gdx.graphics.getDeltaTime() * stepSize;
			if(currentValue <= minValue){
				currentValue = minValue;
				isDecreasing = false;
				isIncreasing = true;
			}
		}
		
		return currentValue;

	}
	
	public class InvalidNumberException extends Exception{
		private static final long serialVersionUID = 1L;
			
		public InvalidNumberException(){
			super("Start value is out of bounds.");
		}
		
	}

}
