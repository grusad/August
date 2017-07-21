package engine.overwrites;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;

import engine.audio.AudioManager;

public class OverwrittenClickListener extends ClickListener{
	
	@Override
	public void clicked(InputEvent event, float x, float y) {
		super.clicked(event, x, y);
		AudioManager.playSound(AudioManager.getSound("button"));
	}
	
}