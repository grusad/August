package engine.world;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShaderProgram;

import engine.climate.ClimateManager;
import engine.elements.ElementManager;
import engine.entities.Entity;
import engine.entities.Entity.LayerIndex;
import engine.entities.InteractableEntity;
import engine.graphics.Camera;
import engine.light.LightManager;
import engine.main.Game;
import engine.mobs.MobManager;
import engine.particles.ParticleManager;
import engine.resources.ResourceManager;
import engine.shaders.WaterShader;
import engine.tiles.TileManager;
import engine.utils.DataManager.PlayerData;
import engine.utils.DataManager.PreferencesData;
import engine.utils.DataManager.WorldData;
import engine.utils.Preferences;
import engine.utils.Utils;

public class WorldManager {
	
	public static String WORLD_NAME = "";
	
	private static final int width = 1024;
	private static final int height = 1024;
	
	private static final List<InteractableEntity> interactableEntities = new ArrayList<>(); 
	private static final List<Entity> entities = new ArrayList<>();
	private static final List<Entity> topLayerEntities = new ArrayList<>();
	private static final List<Entity> bottomLayerEntities = new ArrayList<>();
	
	private MobManager mobManager;
	private LightManager lightManager;
	private TileManager tileManager;
	private ElementManager elementManager;
	private ClimateManager climateManager;
	private Game mainGame;
	
	public WorldManager(Game mainGame){
		this.mainGame = mainGame;
		
		tileManager = new TileManager(this);
		elementManager = new ElementManager(this);
		lightManager = new LightManager(this);
		mobManager = new MobManager(this);
		climateManager = new ClimateManager(this);
		ParticleManager.setWorldManager(this);
		ResourceManager.setWorldManager(this);
			
	}
	
	public void generateNewWorld(){
		tileManager.generateNewTiles();
		elementManager.generateNewElements();
		mobManager.spawnPlayerAtRandomIsland();
		climateManager.setRandomValues();
		
	}
	
	public void generateLoadedWorld(WorldData data, PlayerData playerData, PreferencesData prefData){
		
		Preferences.setPreferences(prefData);
		tileManager.generateLoadedTiles(data.tiles);
		elementManager.generateLoadedElements(data.elements);
		mobManager.spawnPlayerAtLoadedPosition(playerData);
		WorldProperties.setProperties(data);
		climateManager.setData(data);
		ResourceManager.generateLoadedResources(data.resource);
		
	}
	
	public void update(Camera camera){
		
		WorldProperties.update();
		
		clearEntityLists();
		
		lightManager.update();
		tileManager.update(camera);
		elementManager.update(camera);
		mobManager.update();
		climateManager.update();
		ParticleManager.update();
		ResourceManager.update(camera);
		
		entities.addAll(elementManager.getElementsOnScreen());
		entities.addAll(mobManager.getMobs());
		entities.addAll(ParticleManager.particles);
		entities.addAll(ResourceManager.resourcesOnScreen);
		
		sortEntityLists();
		
		mobManager.getPlayer().update();
		
		Collections.sort(entities, Utils.positionSorter);
		
	}
	
	public void renderDefault(SpriteBatch batch, ShaderProgram program){
		
		batch.setShader(program);
		
		tileManager.renderStaticTiles(batch);
		
		for(int i = 0; i < bottomLayerEntities.size(); i++){
			bottomLayerEntities.get(i).render(batch);
		}
		
		for(int i = 0; i < entities.size(); i++){
			entities.get(i).render(batch);
		}
		
		for(int i = 0; i < topLayerEntities.size(); i++){
			topLayerEntities.get(i).render(batch);
		}

		climateManager.render(batch);

	}
	
	public void renderWater(SpriteBatch batch, ShaderProgram program){
		
		WaterShader shader = (WaterShader) program;
		
		shader.begin();
		shader.setUniformf(shader.attributeWaveData, climateManager.getWavesAngle(), climateManager.getAmountOfWaves());
		shader.end();
		batch.setShader(shader);
		tileManager.renderWaterTiles(batch);
		batch.flush();
		
	}
	
	
	private void sortEntityLists(){
		
		Iterator<Entity> e = entities.iterator();
		while(e.hasNext()){
			Entity entity = e.next();
			
			if(entity instanceof InteractableEntity){
				interactableEntities.add( (InteractableEntity) entity);
			}
			
			if(entity.getLayerIndex() == LayerIndex.BOTTOM){
				bottomLayerEntities.add(entity);
				e.remove();
			}
			else if(entity.getLayerIndex() == LayerIndex.TOP){
				topLayerEntities.add(entity);
				e.remove();
			}
		}
	}
	
	private void clearEntityLists(){
		entities.clear();
		bottomLayerEntities.clear();
		topLayerEntities.clear();
		interactableEntities.clear();
	}

	public int getHeight(){
		return height;
	}
	
	public int getWidth(){
		return width;
	}
	
	public TileManager getTileManager(){
		return tileManager;
	}
	
	public MobManager getMobManager(){
		return mobManager;
	}
	
	public Game getMainGame(){
		return mainGame;
	}
	
	public LightManager getLightManager(){
		return lightManager;
	}
	
	public ElementManager getElementManager(){
		return elementManager;
	}
	
	public ClimateManager getClimateManager(){
		return climateManager;
	}
	
	public List<InteractableEntity> getAllInteractableEntities(){
		return interactableEntities;
	}
	
	public List<Entity> getAllEntitiesInScene(){
		return entities;
	}
	
	/** Clean up all lists with entities and disposes all the resources.*/
	public void cleanUp(){
		clearEntityLists();
		elementManager.getAllElements().clear();
		ParticleManager.particles.clear();
		ResourceManager.resources.clear();
		ResourceManager.resourcesOnScreen.clear();
		
		if(lightManager != null){
			lightManager.dispose();
			lightManager = null;			
		}
	}
	
}
