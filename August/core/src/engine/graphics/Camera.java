package engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

import engine.mobs.Player;

public class Camera {

	public static final int SCALE = 2;
	
	private OrthographicCamera ortho;
	private Viewport viewport;

	public Camera() {
		viewport = new StretchViewport(Gdx.graphics.getWidth() / SCALE, Gdx.graphics.getHeight() / SCALE);
		ortho = new OrthographicCamera(viewport.getWorldWidth(), viewport.getWorldHeight());
		ortho.update();
	}

	public void update(Player player){
		
		if(Gdx.input.isKeyPressed(Keys.Z)) ortho.zoom += 0.02f;
		if(Gdx.input.isKeyPressed(Keys.C)) ortho.zoom -= 0.02f;
		
		setPosition(player.getCenteredPosition());
		ortho.update();
	}

	public void resize(int width, int height){
		viewport.setWorldWidth(width);
		viewport.setWorldHeight(height);
	}
	
	public float getWidth() {
		return ortho.viewportWidth;
	}

	public float getHeight() {
		return ortho.viewportHeight;
	}

	public void setPosition(Vector2 position) {
		ortho.position.set(position, 0);
	}

	public Vector2 getPosition() {
		return new Vector2(ortho.position.x, ortho.position.y);
	}

	public Matrix4 getProjectionMatrix() {
		return ortho.combined;
	}
	
	public OrthographicCamera getOrthographicCamera(){
		return ortho;
	}
	
	public void setZoom(float value){
		if(ortho.zoom <= 0.2 || ortho.zoom >= 1) return;
		ortho.zoom += value;
	}

}
