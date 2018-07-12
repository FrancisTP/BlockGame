package com.tp.batman.francis.blockgame.framework;

import android.media.SoundPool;

public interface Audio {
	public Music newMusic(String filename);
	
	public Sound newSound(String filename);

	public SoundPool getSoundPool();
}
