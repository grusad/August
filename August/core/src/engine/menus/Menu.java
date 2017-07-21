package engine.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import engine.graphics.SkinLoader;

public abstract class Menu {
	
	public static final int BUTTON_WIDTH = 32 * 4;
	public static final int BUTTON_HEIGHT = 32;
	public static final int PADDING = 8;
	
	protected MenuManager menuManager;
	
	public Stage stage;
	protected Skin skin;
	protected Table root;
	
	public Menu(){

		stage = new Stage(new ScreenViewport());
		skin = SkinLoader.getDefaultSkin();
		Gdx.input.setInputProcessor(stage);
		
		root = new Table();
		root.setFillParent(true);
		root.align(Align.center);
		
		components();

		stage.addActor(root);
		
	}
	
	protected abstract void components();
	
	public void setMenuManager(MenuManager menuManager){
		this.menuManager = menuManager;
	}
	
	public void update(){
		stage.act();
	}
	
	public void render(){
		stage.draw();
	}
	
	public void resize(int width, int height){
		stage.getViewport().update(width, height, true);
	}
	
	public void dispose(){
		stage.dispose();
	}

}
