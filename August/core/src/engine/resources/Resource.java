package engine.resources;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

import engine.entities.InteractableEntity;
import engine.utils.Box;

public class Resource extends InteractableEntity{

	protected TextureRegion region;

	public Resource(Vector2 position, TextureRegion region) {
		super(position);
		this.region = region;
	}
	
	public void update(){
		super.update();
	}
	
	public void render(SpriteBatch batch){
		batch.setColor(1, 1, 1, transparency);
		batch.draw(region, position.x, position.y);
		batch.setColor(1, 1, 1, 1);
	}
	
	public Box getHitBox(){
		return new Box(position.x, position.y, region.getRegionWidth(), region.getRegionHeight());
	}

}
