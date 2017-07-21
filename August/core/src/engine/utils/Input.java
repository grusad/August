package engine.utils;

import java.awt.Point;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector3;

import engine.graphics.Camera;

public class Input {
	
	private static Vector3 screenPos = new Vector3(0, 0, 0);
	private static Vector3 worldPos = new Vector3(0, 0, 0);
	
	public static void update(Camera camera){
		screenPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		worldPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
		camera.getOrthographicCamera().unproject(worldPos);
	}
	
	/** Returns whether a button on the keyboard is pressed once.*/
	public static boolean isKeyJustPressed(int key){
		return Gdx.input.isKeyJustPressed(key);
	}
	
	/** Returns whether a button on the keyboard is down.*/
	public static boolean isKeyPressed(int key){
		return Gdx.input.isKeyPressed(key);
	}
	
	/** Returns whether a mousebutton is pressed.*/ 
	public static boolean isClicked(int button){
		return Gdx.input.isButtonPressed(button);
	}
	
	/** Returns whether any mousebutton is pressed once.*/
	public static boolean isClickedOnce(){
		return Gdx.input.justTouched();
	}
	
	/** Returns the point in the gameworld that the mouse is pointing at.*/
	public static Point getWorldPosition(){
		return new Point((int)worldPos.x, (int)worldPos.y);
	}
	
	/** Returns the point in the gameworld that the mouse is pointing at.*/
	public static Point getScreenPosition(){
		return new Point((int)screenPos.x, (int)screenPos.y);
	}

}
