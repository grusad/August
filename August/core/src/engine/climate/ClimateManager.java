package engine.climate;

import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.particles.ParticleManager;
import engine.particles.ParticleType;
import engine.utils.DataManager.WorldData;
import engine.world.WorldManager;

public class ClimateManager {
	
	private static Random random = new Random();
	
	private float windLevel = 0;
	private float rainLevel = 0;
	private float fogLevel = 0;
	private float duration = 0;
	private float temperature = 0;
	
	private float angleWave;
	private static float MAX_WAVES = 2.4f;
	private static float MIN_WAVES = 1.4f;
	private static int CHANCE_TO_RAIN = 100;
	
	private WorldManager worldManager;
	
	public ClimateManager(WorldManager worldManager){
		this.worldManager = worldManager;
	}

	public void update() {
		
		angleWave += Gdx.graphics.getDeltaTime() * 1.5f;
		while(angleWave > Math.PI * 2)
			angleWave -= Math.PI * 2;
		
		duration -= Gdx.graphics.getDeltaTime();
		
		if(duration <= 0){
			
			setRandomValues();

		}
		
		ParticleManager.spawnParticleEffect(ParticleType.RainParticle, worldManager.getMobManager().getPlayer().getPosition(), 
				(int) (rainLevel * 10));
		
	}
	
	public void render(SpriteBatch batch){
		
	}
	
	public void setRandomValues(){
		windLevel = getRoundedFloatValue(random.nextFloat());
		if(random.nextBoolean()) windLevel *= -1;
		if(random.nextInt(100) <= CHANCE_TO_RAIN){
			rainLevel = getRoundedFloatValue(random.nextFloat());
		}
		fogLevel = getRoundedFloatValue(random.nextFloat());
		duration = random.nextInt(120) + 20;
		temperature = ThreadLocalRandom.current().nextInt(28, 45);
	}
	
	private float getRoundedFloatValue(float value){
		return (float) Math.ceil(( (double) (random.nextFloat() * 10))) / 10;
	}

	/** Returns a value between (-1) and 1*/
	public float getWindLevel() {
		return windLevel;
	}

	/** Returns a value between 0 and 1*/
	public float getRainLevel() {
		return rainLevel;
	}

	/** Returns a value between 0 and 1*/
	public float getFogLevel() {
		return fogLevel;
	}

	
	public float getDuration() {
		return duration;
	}
	
	public float getTemperature(){
		return temperature;
	}
	
	public float getWavesAngle(){
		return angleWave;
	}
	
	public float getAmountOfWaves(){
		
		float amount = (float) Math.abs(getWindLevel() * 2.7);
		
		if(amount <= MIN_WAVES) amount = MIN_WAVES;
		else if(amount >= MAX_WAVES) amount = MAX_WAVES;
		
		return amount;
	}
	
	public void setData(WorldData data){
		duration = data.cliamateDuration;
		windLevel = data.windLevel;
		fogLevel = data.fogLevel;
		rainLevel = data.rainLevel;
		temperature = data.temperature;
	}

}
