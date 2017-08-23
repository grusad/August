package engine.utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Base64Coder;
import com.badlogic.gdx.utils.Json;

import engine.climate.ClimateManager;
import engine.main.Game;
import engine.resources.ResourceManager;
import engine.world.WorldProperties;

public class DataManager {
	
	private static boolean ENCODE_DATA = false;
	
	private static Game game;
	
	private static Json json = new Json(); 
	
	private static FileHandle file, file2, file3;
	
	public static void saveGame(Game game){
		
		DataManager.game = game;
		
		WorldData worldData = getWorldData();
		PlayerData playerData = getPlayerData();
		PreferencesData prefData = getPreferencesData();
		
		if(ENCODE_DATA){
			
			file.writeString(Base64Coder.encodeString(json.prettyPrint(worldData)), false);
			file2.writeString(Base64Coder.encodeString(json.prettyPrint(playerData)), false);
			file3.writeString(Base64Coder.encodeString(json.prettyPrint(prefData)), false);
		}
		
		else{
			
			file.writeString(json.prettyPrint(worldData), false);
			file2.writeString(json.prettyPrint(playerData), false);
			file3.writeString(json.prettyPrint(prefData), false);
		}
		
		System.out.println("Saved.");
		
	}
	
	public static void loadGame(Game game){
		
		WorldData worldData;
		PlayerData playerData;
		PreferencesData prefData;
		
		if(ENCODE_DATA){
			worldData = json.fromJson(WorldData.class, Base64Coder.decodeString(file.readString()));
			playerData = json.fromJson(PlayerData.class, Base64Coder.decodeString(file2.readString()));
			prefData = json.fromJson(PreferencesData.class, Base64Coder.decodeString(file3.readString()));
		}
		else{
			worldData = json.fromJson(WorldData.class, file);
			playerData = json.fromJson(PlayerData.class, file2);
			prefData = json.fromJson(PreferencesData.class, file3);
		}
		
		game.getWorldManager().generateLoadedWorld(worldData, playerData, prefData);
	}
	
	public static void setDataLocation(String location){
		file = Gdx.files.local("saves/" + location + "/worldData.json");
		file2 = Gdx.files.local("saves/" + location + "/playerData.json");
		file3 = Gdx.files.local("saves/" + location + "/preferencesData.json");
	}
	
	public static String[] getAllSaves(){
		FileHandle fileHandle = Gdx.files.local("saves");
		String[] array = new String[fileHandle.list().length];
		int index = 0;
		for(FileHandle file : fileHandle.list()){
			array[index] = file.name();
			index++;
		}
		
		return array;
	}
	
	public static void deleteDataFolder(String folder){
		FileHandle fileHandle = Gdx.files.local("saves");
		for(FileHandle file : fileHandle.list())
			if(file.name().equals(folder)) file.deleteDirectory();
	}
	
	private static WorldData getWorldData(){
		
		WorldData data = new WorldData();
		
		data.tiles = game.getWorldManager().getTileManager().getTiles();
		data.elements = game.getWorldManager().getElementManager().writeElementsToIntArray();
		data.time = WorldProperties.TIME;
		data.timeSpeed = WorldProperties.TIME_SPEED;
		ClimateManager climateManager = game.getWorldManager().getClimateManager();
		data.cliamateDuration = climateManager.getDuration();
		data.fogLevel = climateManager.getFogLevel();
		data.rainLevel = climateManager.getRainLevel();
		data.windLevel = climateManager.getWindLevel();
		data.resource = ResourceManager.writeResourcesToData();
		
		return data;
	}
	
	private static PlayerData getPlayerData(){
		PlayerData data = game.getWorldManager().getMobManager().getPlayer().getData();
		return data;
	}
	
	private static PreferencesData getPreferencesData(){
		return Preferences.getPreferencesData();
	}
	
	public static class WorldData{
		
		public int[] tiles;
		public int[] elements;
		public ResourceData[] resource;
		public float time, timeSpeed;
		public float windLevel;
		public float fogLevel;
		public float rainLevel;
		public float cliamateDuration;
		
	}
	
	public static class ResourceData{
		public int id;
		public int x;
		public int y;
		public float rotation;
	}
	
	public static class PlayerData{
		
		public float x;
		public float y;
		public float moveSpeed;
		public float aimLength;
		public float health;
		public float hunger;
		public float thirst;
		
	}
	
	public static class PreferencesData{
		
		public float soundVolume;
		public float musicVolume;
		public boolean debugMode;
		
	}

}
