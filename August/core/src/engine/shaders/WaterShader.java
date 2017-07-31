package engine.shaders;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

public class WaterShader extends ShaderProgram{
	
	private static final String VERTEX_SHADER = "shaders/waterVertexShader.glsl";
	private static final String FRAGMENT_SHADER = "shaders/waterFragmentShader.glsl";
	
	public final String attributeWaveData = "waveData";
	
	public WaterShader() {
		super(new FileHandle(VERTEX_SHADER).readString(), new FileHandle(FRAGMENT_SHADER).readString());
	}
	
	

}
