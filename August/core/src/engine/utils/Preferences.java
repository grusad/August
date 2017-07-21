package engine.utils;

import engine.audio.AudioManager;
import engine.debug.DebugManager;
import engine.utils.DataManager.PreferencesData;

public class Preferences {
	
	public static void setPreferences(PreferencesData prefData){
		
		AudioManager.setMusicVolume(prefData.musicVolume);
		AudioManager.setSoundVolume(prefData.soundVolume);
		DebugManager.IN_DEBUG_MODE = prefData.debugMode;
		
	}
	
	public static PreferencesData getPreferencesData(){
		PreferencesData data = new PreferencesData();
		data.debugMode = DebugManager.IN_DEBUG_MODE;
		data.musicVolume = AudioManager.getMusicVolume();
		data.soundVolume = AudioManager.getSoundVolume();
		
		return data;
	}
	

}
