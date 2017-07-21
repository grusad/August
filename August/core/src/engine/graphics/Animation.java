package engine.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class Animation {
	
	private TextureRegion[] regions;
	private TextureRegion currentRegion;
	private float timer = 0;
	private int index = 0;
	
	private boolean fullAnimationComplete = false;
	
	public Animation(TextureRegion[] regions){
		this.regions = regions;
		currentRegion = regions[index];
	}
	
	public Animation(TextureRegion region){
		this.regions = new TextureRegion[1];
		this.regions[0] = currentRegion = region;
	}
	
	public void update(float speed){
		timer += speed * 10;
		if(timer % 60 == 0){
			index++;
			if(index >= regions.length){
				fullAnimationComplete = true;
				index = 0;
			}
			currentRegion = regions[index];
		}
	}
	
	public TextureRegion getCurrentFrame(){
		return currentRegion;
	}
	
	public TextureRegion getFrameAt(int index){
		return this.regions[index];
	}
	
	public int getLength(){
		return regions.length;
	}
	
	public boolean atLastFrame(){
		if(index == regions.length - 1) return true;
		return false;
	}
	
	public boolean fullAnimationComplete(){
		if(fullAnimationComplete){
			fullAnimationComplete = false; 
			return true;
		}
		return false;
	}
	
	public void setFrame(int index){
		this.index = index;
	}

}
