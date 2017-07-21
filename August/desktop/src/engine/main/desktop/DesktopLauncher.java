package engine.main.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import engine.main.Game;

public class DesktopLauncher {
	
	private static final int WIDTH = 1280;
	private static final int HEIGHT = 720;
	private static final String VERSION = "0.0.2";
	
	private static final boolean V_SYNC = true;
	
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = WIDTH;
		config.height = HEIGHT;
		config.title = "Version " + VERSION;
		
		if(!V_SYNC){
			config.vSyncEnabled = false;
			config.foregroundFPS = 0;
			config.backgroundFPS = 0;
		}
		
		new LwjglApplication(new Game(), config);
	}
}
