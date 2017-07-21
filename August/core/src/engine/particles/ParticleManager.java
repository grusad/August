package engine.particles;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;

import engine.graphics.Camera;
import engine.world.WorldManager;

public class ParticleManager {
	
	public static List<Particle> particles = new ArrayList<>();
	
	private static Random random = new Random();
	
	private static WorldManager worldManager;
	
	public static void update(){
		for(int i = 0; i < particles.size(); i++)
			particles.get(i).update();
	}
	
	public static void render(SpriteBatch batch){
		for(int i = 0; i < particles.size(); i++)
			particles.get(i).render(batch);
	}
	
	public static void spawnParticleEffect(ParticleType type, Vector2 position, int amount){
		
		if(type == ParticleType.WaterParticle) for(int i = 0; i < amount; i++) addParticle(new WaterParticle(new Vector2(position)));
		if(type == ParticleType.MineParticle)  for(int i = 0; i < amount; i++) addParticle(new MineParticle(new Vector2(position)));
		if(type == ParticleType.RainParticle){
			for(int i = 0; i < amount; i++){
				float x = (position.x - (Gdx.graphics.getWidth() / 2) / Camera.SCALE) - 160 + random.nextInt((Gdx.graphics.getWidth()) / Camera.SCALE + 160 * 2);
				float y = (position.y + (Gdx.graphics.getHeight() / 2) / Camera.SCALE) + 64;
				addParticle(new RainParticle(new Vector2(x, y)));
			}
		}
			
	}
	
	private static void addParticle(Particle particle){
		particle.setWorldManager(worldManager);
		particles.add(particle);
	}
	
	public static void removeParticle(Particle particle){
		particles.remove(particle);
	}
	
	public static void setWorldManager(WorldManager worldManager){
		ParticleManager.worldManager = worldManager;
	}

}
