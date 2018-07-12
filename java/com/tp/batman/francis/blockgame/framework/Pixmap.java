package com.tp.batman.francis.blockgame.framework;

import com.tp.batman.francis.blockgame.framework.Graphics.PixmapFormat;

public interface Pixmap {
	public int getWidth();
	
	public int getHeight();
	
	public PixmapFormat getFormat();
	
	public void dispose();
}
