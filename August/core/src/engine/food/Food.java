package engine.food;

import java.util.concurrent.ThreadLocalRandom;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.entities.InteractableEntity;
import engine.food.FoodReader.FoodProperties;
import engine.utils.Vector2i;

public class Food extends InteractableEntity{

	private int hpGain;

	public Food(Vector2i tiledPosition, TextureRegion region) {
		super(tiledPosition, region);
		setProperties(FoodReader.getFoodProperties(this.getClass().getSimpleName()));
	}
	
	private void setProperties(FoodProperties properties){
		this.name = properties.name;
		this.id = properties.id;
		this.hitBoxWidth = properties.hitBoxW;
		this.hitBoxHeight = properties.hitBoxH;
		this.layerIndex = properties.layerIndex;
		this.isMovable = properties.isMovable;
		this.isSolid = properties.isSolid;
		int minHPGain = properties.minHPGain;
		int maxHPGain = properties.maxHPGain;
		if(minHPGain < maxHPGain){
			this.hpGain = ThreadLocalRandom.current().nextInt(minHPGain, maxHPGain + 1);			
		}
	}

}
