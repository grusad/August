package engine.buildings;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.buildings.BuildingReader.BuildingProperties;
import engine.entities.InteractableEntity;
import engine.utils.Vector2i;

public class Building extends InteractableEntity{
	
	private int id;

	public Building(Vector2i tiledPosition, TextureRegion region) {
		super(tiledPosition, region);
		setProperties(BuildingReader.getBuildingProperties(this.getClass().getSimpleName()));
	}
	
	private void setProperties(BuildingProperties properties){
		super.update();
	}
	
	public int getID(){
		return id;
	}

}
