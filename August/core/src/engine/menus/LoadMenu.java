package engine.menus;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.scenes.scene2d.ui.ScrollPane;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;

import engine.overwrites.OverwrittenClickListener;
import engine.utils.DataManager;
import engine.world.WorldManager;

public class LoadMenu extends Menu{

	private TextButton LOAD;
	private TextButton BACK;
	private TextButton DELETE;
	private ScrollPane PANE;
	private Dialog DIALOG;
	
	private Table table;
	
	private List<String> saves;
	
	@Override
	protected void components() {
		
		table = new Table(skin);
		
		table.setBackground(skin.getDrawable("default-scroll"));
		
		saves = new List<String>(skin);
		saves.setItems(DataManager.getAllSaves());
		PANE = new ScrollPane(saves, skin);
		PANE.setFadeScrollBars(false);
		
		LOAD = new TextButton("LOAD", skin);
		LOAD.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(saves.getSelected() == null) return;
				
				menuManager.setMenu(new LoadingMenu("Loading saved game..."));
				
				WorldManager.WORLD_NAME = saves.getSelected();
				menuManager.getGame().startLoadedGame(saves.getSelected());
			}
		});
		
		DELETE = new TextButton("DELETE", skin);
		DELETE.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				if(saves.getSelected() == null) return;
				DIALOG.show(stage);
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
		
		DIALOG = new Dialog("WARNING", skin){
			
			@Override
			protected void result(Object object) {
				super.result(object);
				if(object.equals("confirmed")){
					DataManager.deleteDataFolder(saves.getSelected());
					saves.setItems(DataManager.getAllSaves());
				}
			}
		};
		
		DIALOG.getContentTable().add(new Label("Are you sure you want to delete this save?\n"
				+ "This cannot be undone.", skin)).width(500);
		DIALOG.button("Yes", "confirmed");
		DIALOG.button("No", "notConfirmed");
		
			
		table.add(PANE).width(BUTTON_WIDTH * 2).height(BUTTON_HEIGHT * 6).pad(PADDING).row();
		table.add(LOAD).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		table.add(DELETE).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		table.add(BACK).width(BUTTON_WIDTH).height(BUTTON_HEIGHT).pad(PADDING).row();
		
		root.add(table);
	}
	

}
