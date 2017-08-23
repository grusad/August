package engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import engine.main.Game;
import engine.mobs.Player;

public class GuiManager {
	
	private Stage stage;
	
	private Game game;
	private Player player;
	
	public ButtonsGui buttonsGui;
	public MenuGui menuGui;
	public OptionsGui optionsGui;
	public StatusGui statusGui;
	public BuildGui buildGui;
	public InfoGui infoGui;
	
	public GuiManager(Game game){
		
		this.game = game;
		this.player = game.getWorldManager().getMobManager().getPlayer();
		
		stage = new Stage(new ScreenViewport());
		
		Gdx.input.setInputProcessor(stage);
		
		buttonsGui = new ButtonsGui(this);
		menuGui = new MenuGui(this);
		optionsGui = new OptionsGui(this);
		statusGui = new StatusGui(this);
		buildGui = new BuildGui(this); 
		infoGui = new InfoGui(this);
		
		stage.addActor(menuGui);
		stage.addActor(buttonsGui);
		stage.addActor(optionsGui);
		stage.addActor(statusGui);
		stage.addActor(buildGui);
		stage.addActor(infoGui);
		
		menuGui.setVisible(false);
		buildGui.setVisible(false);
		optionsGui.setVisible(false);
		
		stage.setDebugAll(false);
		
	}
	
	public void update(){
		
		if(player.getSelectedEntity() != null){
			infoGui.setVisible(true);
			infoGui.setInfo("Looking at: " + player.getSelectedEntity().getName());
		}
		else{
			infoGui.setVisible(false);
		}
		
		if(menuGui.isVisible() || optionsGui.isVisible() || buildGui.isVisible()) Game.PAUSE = true;
		else Game.PAUSE = false;
		
		if(Gdx.input.isKeyJustPressed(Keys.ESCAPE)){
			
			if(buildGui.isVisible()){
				buildGui.setVisible(false);
			}
			else{
				if(optionsGui.isVisible()) optionsGui.setVisible(false);
				if(menuGui.isVisible()) menuGui.setVisible(false);
				else menuGui.setVisible(true);
			}
						
		}
			
		
		stage.act();
		statusGui.udpateComponents();
	}
	
	public void hideAll(){
		buildGui.setVisible(false);
		optionsGui.setVisible(false);
		menuGui.setVisible(false);
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
