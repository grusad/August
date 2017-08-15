package engine.resources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

import engine.entities.InteractableEntity;
import engine.resources.ResourceReader.ResourceData;
import engine.utils.Vector2i;

public class Resource extends InteractableEntity{
	
	private int id;

	public Resource(Vector2i tiledPosition, TextureRegion region) {
		super(tiledPosition, region);
		setData(ResourceReader.getResourceData(this.getClass().getSimpleName()));
	}
	
	private void setData(ResourceData data){
		this.id = data.id;
		this.hitBoxWidth = data.hitBoxW;
		this.hitBoxHeight = data.hitBoxH;
		this.isMovable = data.isMovable;
		this.isSolid = data.isSolid;
		this.layerIndex = data.layerIndex;
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
