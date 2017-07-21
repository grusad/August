package engine.overwrites;

import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class OverwrittenCheckBox extends CheckBox{

	public OverwrittenCheckBox(Skin skin, boolean isChecked) {
		super("", skin);
		setChecked(isChecked);
	}
	
}