package engine.particles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

import engine.graphics.Camera;
import engine.graphics.Textures;

public class RainParticle extends Particle{
	
	private float speed = 8;

	public RainParticle(Vector2 position) {
		super(position, Textures.getRandomRegion(Textures.PARTICLE_WATER), 60);
		setLayerIndex(LayerIndex.TOP);
		this.scaleX = 1.0f;
		this.scaleY = 1.0f;
		floor = yOrgin - Gdx.graphics.getHeight() / Camera.SCALE - 160;
	}
	
	@Override
	public void update() {
		
		super.update();
		
		position.x += worldManager.getClimateManager().getWindLevel();
		position.y -= speed;
		
	}

}
