package engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import engine.main.Game;

public class GuiManager {
	
	private Stage stage;
	
	private Game game;
	
	public ButtonsGui buttonsGui;
	public MenuGui menuGui;
	public OptionsGui optionsGui;
	public StatusGui statusGui;
	
	public GuiManager(Game game){
		
		this.game = game;
		
		stage = new Stage(new ScreenViewport());
		
		Gdx.input.setInputProcessor(stage);
		
		buttonsGui = new ButtonsGui(this);
		menuGui = new MenuGui(this);
		optionsGui = new OptionsGui(this);
		statusGui = new StatusGui(this);
		
		stage.addActor(menuGui);
		stage.addActor(buttonsGui);
		stage.addActor(optionsGui);
		stage.addActor(statusGui);
		
		menuGui.setVisible(false);
		optionsGui.setVisible(false);
		
		stage.setDebugAll(false);
		
	}
	
	public void update(){
		
		if(menuGui.isVisible() || optionsGui.isVisible()) Game.PAUSE = true;
		else Game.PAUSE = false;
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			if(optionsGui.isVisible()) optionsGui.setVisible(false);
			if(menuGui.isVisible()) menuGui.setVisible(false);
			else menuGui.setVisible(true);			
		}
			
		
		stage.act();
		statusGui.udpateComponents();
	}
	
	public void render(SpriteBatch batch){
		stage.draw();
	}
	
	public Game getGame(){
		return game;
	}
	
	public void dispose(){
		stage.dispose();
	}
	
	public void resize(int width, int height){
		stage.getViewport().update(width, height, true);
	}

	public Stage getStage(){
		return stage;
	}

}
