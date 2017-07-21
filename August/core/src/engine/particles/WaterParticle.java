package engine.particles;

import com.badlogic.gdx.math.Vector2;

import engine.graphics.TextureSheet;
import engine.graphics.Textures;

public class WaterParticle extends Particle{

	public WaterParticle(Vector2 position) {
		super(position, TextureSheet.getRandomRegionFromArray(Textures.PARTICLE_WATER), 0.5f);
		xx = (float) random.nextGaussian();
		lifeSeconds += random.nextFloat() * 0.1;	
		yy = Math.abs((float) random.nextGaussian());
		angle = random.nextInt(180);
	}

	@Override
	public void update() {
		super.update();
		
		position.x += xx * 0.05f;
		position.y += yy * 0.5f;
		yy -= 0.05f;
		
	}

}
