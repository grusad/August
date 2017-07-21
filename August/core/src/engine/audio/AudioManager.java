package engine.audio;

import java.util.HashMap;
import java.util.Map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;

public class AudioManager {
	
	/** Volume values between 0 - 1 */
	private static float SOUND_VOLUME = 1.0f;
	private static float MUSIC_VOLUME = 1.0f;
	
	private static Map<String, Music> music = new HashMap<>();
	private static Map<String, Sound> sounds = new HashMap<>();
	
	/** Adds the specific sounds into HashMap*/
	public static void loadSounds(){
		sounds.put("button", getNewSound("button", "wav"));
		sounds.put("mine", getNewSound("mine", "wav"));
	}
	
	/** Adds the specific music into HashMap*/
	public static void loadMusic(){
		music.put("maintheme", getNewMusic("mainTheme", "wav"));
		music.put("rain", getNewMusic("rain", "wav"));
	}
	
	/** Returns the specific sound with the fileName and the given format.*/
	private static Sound getNewSound(String fileName, String fileFormat){
		return Gdx.audio.newSound(Gdx.files.internal("audio/sounds/" + fileName + "." + fileFormat));
	}
	
	/** Returns the specific music with the fileName and the given format.*/
	private static Music getNewMusic(String fileName, String fileFormat){
		return Gdx.audio.newMusic(Gdx.files.internal("audio/music/" + fileName + "." + fileFormat));
	}
	
	public static Music getMusic(String key){
		return music.get(key);
	}
	
	public static Sound getSound(String key){
		return sounds.get(key);
	}
	
	public static void playSound(Sound sound){
		sound.play(SOUND_VOLUME);
	}
	
	public static void playMusic(Music music){
		music.play();
		music.setVolume(MUSIC_VOLUME);
	}
	
	public static void loopMusic(Music music){
		playMusic(music);
		music.setLooping(true);
	}
	
	public static void setMusicVolume(float value){
		MUSIC_VOLUME = value;
		for(Music m : music.values())
			if(m.isPlaying() || m.isLooping()) m.setVolume(MUSIC_VOLUME);
	}
	
	public static void setSoundVolume(float value){
		SOUND_VOLUME = value;
	}
	
	public static float getMusicVolume(){
		return MUSIC_VOLUME;
	}
	
	public static float getSoundVolume(){
		return SOUND_VOLUME;
	}
	
	public static void stopAllMusic(){
		for(Music m : music.values())
			m.stop();
	}
	
	public static void dispose(){
		for(Music m : music.values())
			m.dispose();
		for(Sound s : sounds.values())
			s.dispose();
	}
	
}
