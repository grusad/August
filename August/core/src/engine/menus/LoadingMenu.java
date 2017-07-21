package engine.menus;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;

public class LoadingMenu extends Menu{
	
	public Label loadingText;
	private Table table;
	
	public LoadingMenu(String loadText){
		loadingText.setText(loadText);
	}

	@Override
	protected void components() {
		loadingText = new Label("", skin);
		table = new Table(skin);
		table.add(loadingText);
		root.add(table);
	}

}
