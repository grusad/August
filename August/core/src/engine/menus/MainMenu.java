package engine.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import engine.overwrites.OverwrittenClickListener;

public class MainMenu extends Menu{

	private TextButton NEW;
	private TextButton LOAD;
	private TextButton OPTIONS;
	private TextButton EXIT;
	
	private Table table;
	
	@Override
	protected void components() {
		
		table = new Table(skin);
		
		table.setBackground(skin.getDrawable("default-scroll"));
		
		NEW = new TextButton("NEW", skin);
		NEW.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				menuManager.setMenu(new NewMenu());
			}
		});
		LOAD = new TextButton("LOAD", skin);
		LOAD.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				menuManager.setMenu(new LoadMenu());
			}
		});
		OPTIONS = new TextButton("OPTIONS", skin);
		OPTIONS.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				menuManager.setMenu(new OptionsMenu());
			}
		});
		EXIT = new TextButton("EXIT", skin);
		EXIT.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				Gdx.app.exit();
			}
		});
		
		table.add(NEW).width(Menu.BUTTON_WIDTH).height(Menu.BUTTON_HEIGHT).pad(Menu.PADDING).row();
		table.add(LOAD).width(Menu.BUTTON_WIDTH).height(Menu.BUTTON_HEIGHT).pad(Menu.PADDING).row();
		table.add(OPTIONS).width(Menu.BUTTON_WIDTH).height(Menu.BUTTON_HEIGHT).pad(Menu.PADDING).row();
		table.add(EXIT).width(Menu.BUTTON_WIDTH).height(Menu.BUTTON_HEIGHT).pad(Menu.PADDING).row();
		
		root.add(table);
	}

}
