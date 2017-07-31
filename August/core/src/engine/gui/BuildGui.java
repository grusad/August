package engine.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import engine.overwrites.OverwrittenClickListener;

public class BuildGui extends GuiTable{

	public BuildGui(GuiManager guiManager) {
		super(guiManager);
	}

	@Override
	protected void components() {
		
		Table table = new Table(skin);
		table.setBackground(skin.getDrawable("default-scroll"));
		
		TextButton close = new TextButton("CLOSE", skin);
		close.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setVisible(false);
			}
		});

		table.add(close).width(buttonWidth).height(buttonHeight).pad(8).row();
		
		add(table);
		
	}

}
