package engine.shaders;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderLoader {
	
	private ShaderProgram waterShader;
	private ShaderProgram defaultShader;
	
	public void loadShaders(){
		
		ShaderProgram.pedantic = false;
		
		waterShader = new WaterShader();
		defaultShader = SpriteBatch.createDefaultShader();
		
	}
	
	public ShaderProgram getWaterShader(){
		return waterShader;
	}
	
	public ShaderProgram getDefaultShader(){
		return defaultShader;
	}
	
	public void dispose(){
		waterShader.dispose();
		defaultShader.dispose();
	}

}
