package engine.overwrites;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.scenes.scene2d.ui.ProgressBar;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class OverwrittenProgressBar extends ProgressBar{

	public OverwrittenProgressBar(float min, float max, float stepSize, float startValue, Skin skin, Color color) {
		super(min, max, stepSize, true, skin);
		setColor(color.r, color.g, color.b, 0.5f);
		getStyle().background.setMinWidth(32);
		getStyle().knob.setMinWidth(32);
		getStyle().knob.setMinHeight(8);
		getStyle().background.setMinWidth(32);
		getStyle().knobBefore = getStyle().knob;
		
		setValue(startValue);
		
		
	}

}
