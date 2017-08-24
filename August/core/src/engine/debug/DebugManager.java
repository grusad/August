package engine.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;

import engine.climate.ClimateManager;
import engine.elements.Element;
import engine.elements.ElementManager;
import engine.entities.Entity;
import engine.entities.TiledEntityManager;
import engine.graphics.Camera;
import engine.graphics.SkinLoader;
import engine.main.Game;
import engine.mobs.Mob;
import engine.mobs.MobManager;
import engine.mobs.Player;
import engine.tiles.Tile;
import engine.world.WorldManager;
import engine.world.WorldProperties;

public class DebugManager {
	
	public static boolean IN_DEBUG_MODE = false;
	
	private WorldManager worldManager;
	
	private int x0;
	private int x1;
	private int y0;
	private int y1;
	
	private Stage stage;
	private Table table;
	
	private Label position;
	private Label tilePosition;
	private Label time;
	private Label climate;
	private Label fps;
	private Label ambientLight;
	
	public DebugManager(Game game){
		this.worldManager = game.getWorldManager();
		setupStage();
	}
	
	private void setupStage(){
		Skin skin = SkinLoader.getDefaultSkin();
		stage = new Stage(new ScreenViewport());
		table = new Table(skin);
		table.setFillParent(true);
		table.align(Align.topLeft);
		
		fps = new Label("", skin);
		position = new Label("", skin);
		tilePosition = new Label("", skin);
		time = new Label("", skin);
		climate = new Label("", skin);
		ambientLight = new Label("", skin);
		
		table.add(fps).left().row();
		table.add(position).left().row();
		table.add(tilePosition).left().row();
		table.add(time).left().row();
		table.add(climate).left().row();
		table.add(ambientLight).left().row();
		
		stage.addActor(table);
		
	}
	
	private void updateStageComponents(){
		Player player = worldManager.getMobManager().getPlayer();
		ClimateManager climateManager = worldManager.getClimateManager();
		
		fps.setText("Fps: " + Gdx.graphics.getFramesPerSecond());
		position.setText("Position:  x: " + player.getWorldPosition().x + "  y: " + player.getWorldPosition().y);
		tilePosition.setText("Tile Position:  x: " + (int) player.getWorldPosition().x / Tile.SIZE + "  y: " + (int) player.getWorldPosition().y / Tile.SIZE);
		time.setText("Time: " + WorldProperties.TIME);
	
		climate.setText("Climate: \n        Rain: " + climateManager.getRainLevel() + " \n        Wind: " + climateManager.getWindLevel() + 
				" \n        Fog: " + climateManager.getFogLevel() + " \n        Duration: " + climateManager.getDuration() + 
				" \n        WaveLevel: " + climateManager.getAmountOfWaves());
		
		ambientLight.setText("Ambient light: " + worldManager.getLightManager().getAmbientLigntValue());
	}
	
	public void update(Camera camera){
		
		if(IN_DEBUG_MODE){
			if(Gdx.input.isKeyJustPressed(Keys.F2)){
				
				Pixmap pixmap = new Pixmap(worldManager.getWidth(), worldManager.getHeight(), Format.RGB888);
				for(int y = 0; y < worldManager.getHeight(); y++){
					for(int x = 0; x < worldManager.getWidth(); x++){
						if(worldManager.getTileManager().getTile(x, y).getID() == Tile.WATER.getID())
							pixmap.drawPixel(x, y, 52428);
						else if(worldManager.getTileManager().getTile(x, y).getID() == Tile.SAND.getID()) pixmap.drawPixel(x, y, 16776960);
						else pixmap.drawPixel(x, y, 65344);
					}
				}
				
				FileHandle file = new FileHandle("map.png");
				
				PixmapIO.writePNG(file, pixmap);
				
				pixmap.dispose();
				
				System.out.println("Printed the map.");
			}
		}
		
		x0 = (int) (camera.getPosition().x - camera.getWidth() / 2) / Tile.SIZE - 5;
		x1 = (int) (x0 + (camera.getWidth()) / Tile.SIZE) + 9;
		y0 = (int) (camera.getPosition().y - camera.getHeight() / 2) / Tile.SIZE - 5;
		y1 = (int) (y0 + (camera.getHeight()) / Tile.SIZE) + 7;
		
		updateStageComponents();
		
		stage.act();
	}
	
	public void render(ShapeRenderer renderer){
		
		for (int y = y0; y <= y1; y++) {
			for (int x = x0; x <= x1; x++) {
				renderer.setColor(Color.BLACK);
				renderer.rect(x * Tile.SIZE, y * Tile.SIZE, Tile.SIZE, Tile.SIZE);
			}
		}
		
		MobManager mobManager = worldManager.getMobManager();
		ElementManager elementManager = worldManager.getElementManager();
		
		for(int i = 0; i < mobManager.getMobs().size(); i++){
			Mob mob = mobManager.getMobs().get(i);
			renderer.setColor(Color.WHITE);
			renderer.rect(mob.getHitBox().getX(), mob.getHitBox().getY(), mob.getHitBox().getWidth(), mob.getHitBox().getHeight());
			
			if(mob instanceof Player){
				Player player = (Player) mob;
				renderer.setColor(Color.RED);
				renderer.rect(player.getAimBox().x, player.getAimBox().y, player.getAimBox().width, player.getAimBox().height);
			}
				
		}
		
		for(int i = 0; i < elementManager.getElementsOnScreen().size(); i++){
			Element element = elementManager.getElementsOnScreen().get(i);
			renderer.setColor(Color.WHITE);
			renderer.rect(element.getHitBox().getX(), element.getHitBox().getY(), element.getHitBox().getWidth(), element.getHitBox().getHeight());
		}
		
		for(int i = 0; i < TiledEntityManager.entitiesOnScreen.size(); i++){
			Entity entity = TiledEntityManager.entitiesOnScreen.get(i);
			renderer.setColor(Color.WHITE);
			renderer.rect(entity.getHitBox().getX(), entity.getHitBox().getY(), entity.getHitBox().getWidth(), entity.getHitBox().getHeight());
		}
		
	}
	
	public void render(SpriteBatch batch){
		
		stage.draw();
		
	}
	
	public void resize(int width, int height){
		stage.getViewport().update(width, height, true);
	}
	
	public void dispose(){
		stage.dispose();
	}

}
