package engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;

import engine.audio.AudioManager;
import engine.debug.DebugManager;
import engine.overwrites.OverwrittenCheckBox;
import engine.overwrites.OverwrittenClickListener;
import engine.overwrites.OverwrittenSlider;


public class OptionsGui extends GuiTable{
	
	OverwrittenSlider soundSlider;
	OverwrittenSlider musicSlider;
	OverwrittenCheckBox fullScreenCheckBox;
	OverwrittenCheckBox debugCheckBox;

	public OptionsGui(GuiManager guiManager) {
		super(guiManager);
		align(Align.center);
	}

	@Override
	protected void components() {
		
		Table table = new Table(skin);
		table.setBackground(skin.getDrawable("default-scroll"));
		
		TextButton backButton = new TextButton("BACK", skin);
		backButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setVisible(false);
				guiManager.menuGui.setVisible(true);
			}
		});
		
		TextButton applyButton = new TextButton("APPLY", skin);
		applyButton.addListener(new ClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setPreferences();
				setVisible(false);
				guiManager.menuGui.setVisible(true);
			}
		});
		
		Label fullScreenLabel = new Label("Fullscreen", skin);
		fullScreenCheckBox = new OverwrittenCheckBox(skin, Gdx.graphics.isFullscreen());
		Label debugLabel = new Label("Debug", skin);
		debugCheckBox = new OverwrittenCheckBox(skin, DebugManager.IN_DEBUG_MODE);
		Label soundLabel = new Label("Sound", skin);
		soundSlider = new OverwrittenSlider(skin, AudioManager.getSoundVolume());
		Label musicLabel = new Label("Music", skin);
		musicSlider = new OverwrittenSlider(skin, AudioManager.getMusicVolume());
		
		
		table.add(musicLabel);
		table.add(musicSlider).row();
		table.add(soundLabel);
		table.add(soundSlider).row();
		table.add(debugLabel);
		table.add(debugCheckBox).row();
		table.add(fullScreenLabel);
		table.add(fullScreenCheckBox).row();
		table.add(applyButton).width(128).height(32).pad(8);
		table.add(backButton).width(128).height(32).pad(8).row();
		
		add(table);
	}
	
	private void setPreferences(){
		AudioManager.setMusicVolume(musicSlider.getValue());
		AudioManager.setSoundVolume(soundSlider.getValue());
		if(fullScreenCheckBox.isChecked()) Gdx.graphics.setFullscreenMode(Gdx.graphics.getDisplayMode());
		else Gdx.graphics.setWindowedMode(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
		if(debugCheckBox.isChecked()) DebugManager.IN_DEBUG_MODE = true;
		else DebugManager.IN_DEBUG_MODE = false;
	}

}
