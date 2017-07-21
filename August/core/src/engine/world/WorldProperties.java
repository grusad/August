package engine.world;

import com.badlogic.gdx.Gdx;

import engine.utils.DataManager.WorldData;

public class WorldProperties {
	
	public static float TIME_SPEED = .01f;
	public static float TIME = 1;
	
	public static void update(){
		
		if(TIME >= 2) TIME_SPEED = -TIME_SPEED;
		if(TIME <= -1) TIME_SPEED = (TIME_SPEED * -1);
		TIME += TIME_SPEED * Gdx.graphics.getDeltaTime();
	}
	
	public static void setProperties(WorldData data){
		TIME = data.time;
		TIME_SPEED = data.timeSpeed;
	}
	

}
