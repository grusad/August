package engine.graphics;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class SkinLoader {
	
	/** Returns a new instance of the skin with all the containing resources.*/
	public static Skin getDefaultSkin(){
		return new Skin(Gdx.files.internal("skins/uiskin.json"));
	}

}
