package engine.menus;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import engine.main.Game;

public class MenuManager {
	
	private Game game;
	private Menu menu;
	
	public MenuManager(Game game){
		this.game = game;
		setMenu(new MainMenu());
	}
	
	public void update(){
		menu.update();
	}
	
	public void render(SpriteBatch batch){
		menu.render();
	}
	
	public void setMenu(Menu menu){
		if(this.menu != null)
			this.menu.dispose();
		this.menu = menu;
		this.menu.setMenuManager(this);
	}
	
	public void resize(int width, int height){
		this.menu.resize(width, height);
	}
	
	public Game getGame(){
		return game;
	}
	
	public Menu getMenu(){
		return menu;
	}
	
	public void cleanUp(){
		menu.dispose();
		menu = null;
	}

}
