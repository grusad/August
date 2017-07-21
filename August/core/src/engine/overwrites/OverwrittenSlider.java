package engine.overwrites;

import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Slider;

public class OverwrittenSlider extends Slider{

	public OverwrittenSlider(Skin skin, float startValue) {
		super(0, 1, 0.02f, false, skin);
		setValue(startValue);
	}

}
