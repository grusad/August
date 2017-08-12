package engine.resources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.entities.InteractableEntity;
import engine.utils.Vector2i;

public class Resource extends InteractableEntity{
	
	private int id;

	public Resource(Vector2i tiledPosition, TextureRegion region, int id) {
		super(tiledPosition, region);
		this.id = id;
		layerIndex = LayerIndex.BOTTOM;
	}
	
	public void update(){
		super.update();
	}
	
	public void render(SpriteBatch batch){
		super.render(batch);
	}
	
	public void moveToTile(Vector2i tilePos){
		ResourceManager.removeResource(this);
		super.moveToTile(tilePos);
		ResourceManager.addResource(this);
	}
	
	public int getID(){
		return id;
	}
	
}
