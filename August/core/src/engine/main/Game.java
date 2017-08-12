package engine.main;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

import engine.audio.AudioManager;
import engine.debug.DebugManager;
import engine.graphics.Camera;
import engine.graphics.CursorProperties;
import engine.graphics.TextureSheet;
import engine.gui.GuiManager;
import engine.menus.MenuManager;
import engine.shaders.ShaderLoader;
import engine.utils.DataManager;
import engine.utils.Input;
import engine.world.WorldManager;

public class Game extends ApplicationAdapter {
	
	public static boolean PAUSE = false;
	
	private static Game GAME;
	
	private static GameState State = GameState.MENU_STATE;
	
	private SpriteBatch batch;
	private ShaderLoader shaderLoader;
	private ShapeRenderer renderer;
	private WorldManager world;
	private Camera camera;
	private DebugManager debugManager; 
	private MenuManager menuManager;
	private GuiManager guiManager;
	
	public void create () {
		
		batch = new SpriteBatch();
		shaderLoader = new ShaderLoader();
		shaderLoader.loadShaders();
		
		GAME = this;
		
		renderer = new ShapeRenderer();
		menuManager = new MenuManager(this);
		AudioManager.loadMusic();
		AudioManager.loadSounds();
		camera = new Camera();
		CursorProperties.setCursor(CursorProperties.CURSOR_DEFAULT);
		
	}
	
	private void initBeforeLoading(){
		
		world = new WorldManager(this);
		debugManager = new DebugManager(this);
		DataManager.setDataLocation(WorldManager.WORLD_NAME);
		
	}
	
	private void initAfterLoading(){
		State = GameState.GAME_STATE;
		menuManager.cleanUp();
		guiManager = new GuiManager(this);
		AudioManager.loopMusic(AudioManager.getMusic("maintheme"));
	}
	
	public void startNewGame(){
		
		Gdx.app.postRunnable(new Runnable(){

			public void run() {
				initBeforeLoading();
				world.generateNewWorld();
				DataManager.saveGame(Game.GAME);
				initAfterLoading();
			}
		});
	}
	
	public void startLoadedGame(String folderName){
		
		Gdx.app.postRunnable(new Runnable(){
			
			public void run() {
				initBeforeLoading();
				DataManager.loadGame(Game.GAME);
				initAfterLoading();
			}
		});
	}
	
	private void update(){
		
		if(GameState.GAME_STATE == State){
			
			guiManager.update();
			
			if(PAUSE) return;
			
			if(DebugManager.IN_DEBUG_MODE) debugManager.update(camera);
			
			world.update(camera);
			camera.update(world.getMobManager().getPlayer());	
			Input.update(camera);
			
		} 
		
		if(GameState.MENU_STATE == State){
			menuManager.update();
		}
		
	}

	public void render () {
		
		update();
		Gdx.gl.glClearColor(0f, 0f, 0f, 1f);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		if(GameState.GAME_STATE == State){
			
			batch.setProjectionMatrix(camera.getProjectionMatrix()); 
			
			batch.begin();
			
			world.renderWater(batch, shaderLoader.getWaterShader());
			world.renderDefault(batch, shaderLoader.getDefaultShader());
			
			batch.end();
			
			world.getLightManager().getRayHandler().updateAndRender();
			
			if(DebugManager.IN_DEBUG_MODE){
				renderer.begin(ShapeRenderer.ShapeType.Line);
				renderer.setProjectionMatrix(camera.getProjectionMatrix());
				debugManager.render(renderer);
				renderer.end();
				
			}
			
			batch.begin();
			
			guiManager.render(batch);
			if(DebugManager.IN_DEBUG_MODE) debugManager.render(batch);
	
			batch.end();
		}
		
		if(GameState.MENU_STATE == State){
			batch.begin();
			menuManager.render(batch);
			batch.end();
		}
		

	}
	
	public void resize(int width, int height){
		if(State == GameState.GAME_STATE){
			camera.resize(width, height);
			guiManager.resize(width, height);
			if(debugManager != null) debugManager.resize(width, height);
		}
		else if(State == GameState.MENU_STATE)
			menuManager.resize(width, height);
	}
	
	public Camera getCamera(){
		return camera;
	}
	
	public WorldManager getWorldManager(){
		return world;
	}
	
	public void setState(GameState State){
		Game.State = State;
	}
	
	public void exitGame(){
		guiManager.dispose();
		world.cleanUp();
		setState(GameState.MENU_STATE);
		menuManager = new MenuManager(this);
		AudioManager.stopAllMusic();
	}
	
	public void dispose () {
		
		shaderLoader.dispose();
		if(world != null) world.cleanUp();
		if(debugManager != null) debugManager.dispose();
		TextureSheet.disposeAll();
		batch.dispose();
		renderer.dispose();
		AudioManager.dispose();
		CursorProperties.dispose();
		
	}
	
	
}









