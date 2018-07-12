package com.tp.batman.francis.blockgame.game.Settings;

import android.content.SharedPreferences;

import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class Saves {
	private static float musicVolume;
	private static float soundEffectVolume;
	private static int highscore;
	private static int firstTimePlaying;

	private static final String musicVolumeKey = "musicVolume";
	private static final float defaultMusicVolume = 0.5f;
	private static final float defaultMusicVolumeCheck = -0.5f;

	private static final String soundEffectVolumeKey = "soundEffectVolume";
	private static final float defaultSoundEffectVolume = 0.5f;
	private static final float defaultSoundEffectVolumeCheck = -0.5f;

	private static final String highscoreKey = "highscore";
	private static final int defaultHighscore = 0;
	private static final int defaultHighscoreCheck = -1;

	private static final String firstTimePlayingKey = "firstTimePlaying";
	private static final int defaultFirstTimePlaying = 1;
	private static final int defaultFirstTimePlayingCheck = -1;

	private static SharedPreferences sharedPreferences;
	private static SharedPreferences.Editor editor;

	private final static String delimiter = "|";
	
	public static void load(GLGame game){
		// load all keys when app is opened..

		try {
			sharedPreferences = game.getPreferences(game.MODE_PRIVATE);
			editor = sharedPreferences.edit();

			// check if keys exist, if not create them
			musicVolume = sharedPreferences.getFloat(musicVolumeKey, defaultMusicVolumeCheck);
			if (musicVolume < 0) {
				editor.putFloat(musicVolumeKey, defaultMusicVolume);
				editor.commit();
				musicVolume = defaultMusicVolume;
			}

			soundEffectVolume = sharedPreferences.getFloat(soundEffectVolumeKey, defaultSoundEffectVolumeCheck);
			if (soundEffectVolume < 0) {
				editor.putFloat(soundEffectVolumeKey, defaultSoundEffectVolume);
				editor.commit();
				soundEffectVolume = defaultSoundEffectVolume;
			}

			highscore = sharedPreferences.getInt(highscoreKey, defaultHighscoreCheck);
			if (highscore < 0) {
				editor.putInt(highscoreKey, defaultHighscore);
				editor.commit();
				highscore = defaultHighscore;
			}

			firstTimePlaying = sharedPreferences.getInt(firstTimePlayingKey, defaultFirstTimePlayingCheck);
			if (firstTimePlaying < 0) {
				editor.putInt(firstTimePlayingKey, defaultFirstTimePlaying);
				editor.commit();
				firstTimePlaying = defaultFirstTimePlaying;
			}
		} catch (Exception e) {
			System.out.println("Error in saves.. " + e.getMessage());
		}

	}



	public static void saveMusicVolume(){
		try {
			editor.putFloat(musicVolumeKey, musicVolume);
			editor.commit();
		} catch (Exception e) {
			System.out.println("Error in saves.. " + e.getMessage());
		}
	}

	public static void saveSoundEffectVolume(){
		try {
			editor.putFloat(soundEffectVolumeKey, soundEffectVolume);
			editor.commit();
		} catch (Exception e) {
			System.out.println("Error in saves.. " + e.getMessage());
		}
	}

	public static void saveHighscore(){
		try {
			editor.putInt(highscoreKey, highscore);
			editor.commit();
		} catch (Exception e) {
			System.out.println("Error in saves.. " + e.getMessage());
		}
	}

	public static void saveFirstTimePlaying(){
		try {
			editor.putInt(firstTimePlayingKey, firstTimePlaying);
			editor.commit();
		} catch (Exception e) {
			System.out.println("Error in saves.. " + e.getMessage());
		}
	}

	public static void setMusicVolume(float musicV) {
		musicVolume = musicV;
	}

	public static float getMusicVolume() {
		return musicVolume;
	}

	public static void setSoundEffectVolume(float soundEffectV) {
		soundEffectVolume = soundEffectV;
	}

	public static float getSoundEffectVolume() {
		return soundEffectVolume;
	}

	public static void setHighscore(int highs) {
		highscore = highs;
	}

	public static int getHighscore() {
		return highscore;
	}

	public static void setFirstTimePlaying(boolean firstTimeP) {
		if (firstTimeP) {
			firstTimePlaying = 1;
		} else {
			firstTimePlaying = 0;
		}
	}

	public static boolean getFirstTimePlaying() {
		if (firstTimePlaying == 0) {
			return false;
		} else {
			return true;
		}
	}
}
