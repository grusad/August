package engine.menus;

import java.util.Random;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import engine.overwrites.OverwrittenClickListener;
import engine.world.WorldManager;

public class NewMenu extends Menu{

	private TextField NAME;
	private TextButton START;
	private TextButton BACK;
	
	private Table table;
	
	@Override
	protected void components() {
		
		table = new Table(skin);
		
		table.setBackground(skin.getDrawable("default-scroll"));
		
		NAME = new TextField("", skin);
		NAME.setText("WorldName" + new Random().nextInt());
		
		START = new TextButton("START", skin);
		START.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(NAME.getText().length() == 0) return;
				
				menuManager.setMenu(new LoadingMenu("Generating new world..."));
				WorldManager.WORLD_NAME = NAME.getText();
				menuManager.getGame().startNewGame();
			}
		});
		
		BACK = new TextButton("BACK", skin);
		BACK.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				menuManager.setMenu(new MainMenu());
			}
		});
		
		table.add(NAME).width(BUTTON_WIDTH * 2).pad(PADDING).row();
		
		table.add(START).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		table.add(BACK).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		
		root.add(table);
		
	}

}
