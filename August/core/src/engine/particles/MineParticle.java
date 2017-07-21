package engine.particles;

import com.badlogic.gdx.math.Vector2;

import engine.graphics.TextureSheet;
import engine.graphics.Textures;

public class MineParticle extends Particle{

	public MineParticle(Vector2 position) {
		super(position, TextureSheet.getRandomRegionFromArray(Textures.PARTICLE_MINE), 0.5f);
		setLayerIndex(LayerIndex.TOP);		
		xx = (float) random.nextGaussian();
		yy = (float) random.nextGaussian();
		lifeSeconds = 0.2f;	
		angle = random.nextInt(180);
	}

	@Override
	public void update() {
		super.update();
		position.x += xx * 0.5f;
		position.y += yy * 0.5f;
		yy -= 0.1f;
		
	}

}
