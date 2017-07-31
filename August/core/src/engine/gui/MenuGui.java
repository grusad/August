package engine.gui;

import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Dialog;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.utils.Align;

import engine.overwrites.OverwrittenClickListener;
import engine.utils.DataManager;

public class MenuGui extends GuiTable{
	
	private Dialog dialog;

	public MenuGui(GuiManager guiManager) {
		super(guiManager);
		
		align(Align.center);
	}

	@Override
	protected void components() {
		
		Table table = new Table(skin);
		table.setBackground(skin.getDrawable("default-scroll"));
		
		TextButton saveButton = new TextButton("SAVE", skin);
		saveButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				DataManager.saveGame(guiManager.getGame());
				setVisible(false);
			}
		});
		
		TextButton exitButton = new TextButton("EXIT", skin);
		exitButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				dialog.show(guiManager.getStage());
			}
		});
		
		TextButton backButton = new TextButton("BACK", skin);
		backButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setVisible(false);
			}
		});
		
		TextButton optionsButton = new TextButton("OPTIONS", skin);
		optionsButton.addListener(new OverwrittenClickListener(){
			@Override
			public void clicked(InputEvent event, float x, float y) {
				super.clicked(event, x, y);
				setVisible(false);
				guiManager.optionsGui.setVisible(true);
			}
		});
		dialog = new Dialog("WARNING", skin){
			
			@Override
			protected void result(Object object) {
				super.result(object);
				if(object.equals("confirmed")){
					guiManager.getGame().exitGame();
				}
			}
		};
		
		dialog.getContentTable().add(new Label("Are you sure you want to exit?\n"
				+ " without saving?.", skin)).width(500);
		dialog.button("Yes", "confirmed");
		dialog.button("No", "notConfirmed");
		
		
		table.add(saveButton).width(buttonWidth).height(buttonHeight).pad(8).row();
		table.add(backButton).width(buttonWidth).height(buttonHeight).pad(8).row();
		table.add(optionsButton).width(buttonWidth).height(buttonHeight).pad(8).row();
		table.add(exitButton).width(buttonWidth).height(buttonHeight).pad(8).row();
		
		add(table);

	}

}
