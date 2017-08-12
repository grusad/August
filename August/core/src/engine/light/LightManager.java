package engine.light;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.World;

import box2dLight.RayHandler;
import engine.graphics.Camera;
import engine.world.WorldManager;
import engine.world.WorldProperties;

public class LightManager {
	
	private World world;
	private RayHandler handler;
	private Camera camera;
	private WorldManager worldManager;
	
	private float amount;
	private float ambientOffset = 0;
	
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
		
		amount = WorldProperties.TIME;
		if(amount > 1) amount = 1;
		
		ambientOffset = worldManager.getClimateManager().getRainLevel() / 2;
		
		amount -= ambientOffset;
		
		if(amount <= MAX_DARKNESS) amount = MAX_DARKNESS;
		
		//if(DebugManager.IN_DEBUG_MODE) amount = 1;
		
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
