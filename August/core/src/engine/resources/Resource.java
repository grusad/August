package engine.resources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.entities.InteractableEntity;
import engine.resources.ResourceReader.ResourceProperties;
import engine.utils.Vector2i;

public class Resource extends InteractableEntity{

	public Resource(Vector2i tiledPosition, TextureRegion region) {
		super(tiledPosition, region);
		setProperties(ResourceReader.getResourceProperties(this.getClass().getSimpleName()));
	}
	
	private void setProperties(ResourceProperties properties){
		this.name = properties.name;
		this.id = properties.id;
		this.hitBoxWidth = properties.hitBoxW;
		this.hitBoxHeight = properties.hitBoxH;
		this.isMovable = properties.isMovable;
		this.isSolid = properties.isSolid;
		this.layerIndex = properties.layerIndex;
	}
	
	public void update(){
		super.update();
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
	}
	
}
