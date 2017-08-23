package engine.gui;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;

public class InfoGui extends GuiTable{
	
	private Label infoLabel;

	public InfoGui(GuiManager guiManager) {
		super(guiManager);
		align(Align.bottomLeft);
	}

	@Override
	protected void components() {
		
		infoLabel = new Label("", skin);
		
		add(infoLabel);
	}
	
	public void setInfo(String text){
		infoLabel.setText(text);
	}

}
