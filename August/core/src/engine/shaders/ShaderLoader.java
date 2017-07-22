package engine.shaders;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class ShaderLoader {
	
	private ShaderProgram waterShader;
	private ShaderProgram defaultShader;
	
	public void loadShaders(){
		
		ShaderProgram.pedantic = false;
		
		final String vertexShader = new FileHandle("shaders/vertexShader.glsl").readString();
		final String fragmentShader = new FileHandle("shaders/defaultPixelShader.glsl").readString();
		
		waterShader = new ShaderProgram(vertexShader, fragmentShader);
		defaultShader = SpriteBatch.createDefaultShader();
		
	}
	
	public ShaderProgram getWaterShader(){
		return waterShader;
	}
	
	public ShaderProgram getDefaultShader(){
		return defaultShader;
	}

}
