package engine.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.utils.Align;

import engine.overwrites.OverwrittenClickListener;

public class ButtonsGui extends GuiTable{

	public ButtonsGui(GuiManager guiManager) {
		super(guiManager);
		align(Align.topRight);
	}

	@Override
	protected void components() {
		
		Button menuButton = new Button(skin);
		menuButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				guiManager.hideAll();

				guiManager.menuGui.setVisible(true);
			}
		});
		
		Button buildButton = new Button(skin);
		buildButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				guiManager.hideAll();
				guiManager.buildGui.setVisible(true);
			}
		});
		
		
		add(menuButton).width(buttonHeight).height(buttonHeight).pad(8).row();
		add(buildButton).width(buttonHeight).height(buttonHeight).pad(8).row();
		
	}

}
