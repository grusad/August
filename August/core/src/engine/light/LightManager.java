package engine.light;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;
import engine.debug.DebugManager;
import engine.graphics.Camera;
import engine.world.WorldManager;
import engine.world.WorldProperties;

public class LightManager {
	
	private WorldManager worldManager;
	
	private World world;
	private RayHandler handler;
	private Camera camera;
	
	private float amount;
	
	private static float MAX_DARKNESS = .2f;
	
	public LightManager(WorldManager worldManager){
		this.worldManager = worldManager;
		this.camera = worldManager.getMainGame().getCamera();
		this.world = new World(new Vector2(0, 0), false);
		this.handler = new RayHandler(world);
		RayHandler.useDiffuseLight(true);
		
		this.handler.setAmbientLight(WorldProperties.TIME);
		this.handler.setCombinedMatrix(camera.getOrthographicCamera());
	}
	
	private void updateAmbientLight(){
		
		float ambientOffset = worldManager.getClimateManager().getRainLevel();
		amount = WorldProperties.TIME;
		amount -= ambientOffset;
		
		if(amount <= MAX_DARKNESS) amount = MAX_DARKNESS;
		
		if(DebugManager.IN_DEBUG_MODE) amount = 1;
		
		this.handler.setAmbientLight(amount, amount, amount, amount);
	}
	
	public void update(){
		updateAmbientLight();
		this.handler.setCombinedMatrix(camera.getOrthographicCamera());
		
	}
	
	public RayHandler getRayHandler(){
		return handler;
	}
	
	public float getAmbientLigntValue(){
		return amount;
	}
	
	public void dispose(){
		handler.dispose();
		world.dispose();
	}

}
