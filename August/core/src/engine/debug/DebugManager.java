package engine.debug;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
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
		position.setText("Position:  x: " + player.getPosition().x + "  y: " + player.getPosition().y);
		tilePosition.setText("Tile Position:  x: " + (int) player.getPosition().x / Tile.SIZE + "  y: " + (int) player.getPosition().y / Tile.SIZE);
		time.setText("Time: " + WorldProperties.TIME);
	
		climate.setText("Climate: \n        Rain: " + climateManager.getRainLevel() + " \n        Wind: " + climateManager.getWindLevel() + 
				" \n        Fog: " + climateManager.getFogLevel() + " \n        Duration: " + climateManager.getDuration() + 
				" \n        Temperature: " + climateManager.getTemperature());
		
		ambientLight.setText("Ambient light: " + worldManager.getLightManager().getAmbientLigntValue());
	}
	
	public void update(){
		
		updateStageComponents();
		
		stage.act();
	}
	
	public void render(ShapeRenderer renderer){
		
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
			renderer.setColor(Color.GREEN);
			renderer.rect(element.getTextureBounds().getX(), element.getTextureBounds().getY(), element.getTextureBounds().getWidth(), element.getTextureBounds().getHeight());
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
