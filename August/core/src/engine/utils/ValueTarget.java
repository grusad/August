package engine.utils;

import com.badlogic.gdx.Gdx;

public class ValueTarget {
	
	private float start;
	private float goal;
	private float speed;
	private boolean increment = false;
	
	private boolean done = false;
	
	/** 
	 * A class that handles only one thing and one thing only. It increases or decreases a value from start to goal at a certain speed. 
	 */
	
	public ValueTarget(float start, float goal, float speed){
		this.start = start;
		this.goal = goal;
		this.speed = speed;
		if(goal > start) increment = true;
	}
	
	/** Returns true when value hits goal-value.*/
	public float updateAndReturnValue(){
		
		if(increment){
			if(start >= goal) done = true;
			return start += Gdx.graphics.getDeltaTime() * speed;
			
		}
		
		else{
			if(start <= goal) done = true;
			return start -= Gdx.graphics.getDeltaTime() * speed;
		}
		
	}
	
	public boolean isDone(){
		return done;
	}

}
