package engine.mobs;

import java.util.ArrayList;
import java.util.List;

import com.badlogic.gdx.math.Vector2;

import engine.tiles.Tile;
import engine.tiles.TileManager;
import engine.utils.DataManager.PlayerData;
import engine.world.WorldManager;

public class MobManager {
	
	private List<Mob> mobs = new ArrayList<>();
	
	private static Player player;
	
	private WorldManager worldManager;
	
	public MobManager(WorldManager worldManager){
		this.worldManager = worldManager;
	}
	
	public void update(){
		for(int i = 0; i < mobs.size(); i++){
			if(mobs.get(i) instanceof Player) continue;
			mobs.get(i).update();
		}
	}
	
	public void spawnPlayerAtRandomIsland(){
		
		TileManager tileManager = worldManager.getTileManager();
		for(int y = worldManager.getHeight() / 4; y < worldManager.getHeight(); y++){
			for(int x = worldManager.getWidth() / 4; x < worldManager.getWidth(); x++){
				if(tileManager.getTile(x, y).getID() != Tile.WATER.getID()){
					addMob(player = new Player(new Vector2(x * Tile.SIZE, y * Tile.SIZE)));
					return;
				}
			}
		}
	}
	
	public void spawnPlayerAtLoadedPosition(PlayerData data){
		addMob(player = new Player(new Vector2(0, 0)));
		player.setData(data);
		
	}
	
	public Player getPlayer(){
		return player;
	}
	
	public List<Mob> getMobs(){
		return mobs;
	}
	
	public void addMob(Mob mob){
		mob.setWorldManager(worldManager);
		mob.setLightManager(worldManager.getLightManager());
		mobs.add(mob);
		player.setLightSource(player.getCenteredPositionCurrent());
	}
	
	public void removeMob(Mob mob){
		mobs.remove(mob);
	}

}
