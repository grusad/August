package engine.food;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.entities.InteractableEntity;
import engine.food.FoodReader.FoodProperties;
import engine.utils.Vector2i;

public class Food extends InteractableEntity{
	
	private int id;

	public Food(Vector2i tiledPosition, TextureRegion region) {
		super(tiledPosition, region);
		setProperties(FoodReader.getFoodProperties(this.getClass().getSimpleName()));
	}
	
	private void setProperties(FoodProperties properties){
		
	}
	
	public int getID(){
		return id;
	}

}
