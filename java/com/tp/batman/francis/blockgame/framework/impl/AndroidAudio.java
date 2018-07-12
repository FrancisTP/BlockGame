package com.tp.batman.francis.blockgame.framework.impl;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;

import com.tp.batman.francis.blockgame.framework.Audio;
import com.tp.batman.francis.blockgame.framework.Music;
import com.tp.batman.francis.blockgame.framework.Sound;

import java.io.IOException;

public class AndroidAudio implements Audio {
	AssetManager assets;
	SoundPool soundPool;

	private final int MAX_STREAM = 20;

	@SuppressWarnings("deprecation")
	public AndroidAudio(Activity activity){
		activity.setVolumeControlStream(AudioManager.STREAM_MUSIC);
		this.assets = activity.getAssets();
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
			AudioAttributes attributes = new AudioAttributes.Builder()
					.setUsage(AudioAttributes.USAGE_GAME)
					.setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
					.build();
			this.soundPool = new SoundPool.Builder()
					.setAudioAttributes(attributes)
					.setMaxStreams(MAX_STREAM)
					.build();
		} else {
			this.soundPool = new SoundPool(MAX_STREAM, AudioManager.STREAM_MUSIC, 0);
		}
	}
	
	public Music newMusic(String filename){
		try{
			AssetFileDescriptor assetDescriptor=assets.openFd(filename);
			return new AndroidMusic(assetDescriptor);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load music  '"+filename+"'");
		}
	}
	
	public Sound newSound(String filename){
		try{
			AssetFileDescriptor assetDescriptor = assets.openFd(filename);
			int soundId = soundPool.load(assetDescriptor, 0);
			return new AndroidSound(soundPool, soundId);
		} catch (IOException e) {
			throw new RuntimeException("Couldn't load sound '" + filename + "'");
		}
	}

	public SoundPool getSoundPool() {
		return soundPool;
	}
}
