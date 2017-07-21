package engine.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import engine.overwrites.OverwrittenClickListener;

public class OptionsMenu extends Menu{

	private TextButton BACK;
	
	private Table table;
	
	@Override
	protected void components() {
		BACK = new TextButton("BACK", skin);
		BACK.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				menuManager.setMenu(new MainMenu());
			}
		});
		
		table = new Table(skin);
		table.add(BACK).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		
		table.setBackground(skin.getDrawable("default-scroll"));
		
		root.add(table);
	}

}
