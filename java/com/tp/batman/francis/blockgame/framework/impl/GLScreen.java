package com.tp.batman.francis.blockgame.framework.impl;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Screen;

public abstract class GLScreen extends Screen {
	protected final GLGraphics glGraphics;
	protected final GLGame glGame;
	
	public GLScreen(Game game){
		super(game);
		glGame = (GLGame)game;
		glGraphics = glGame.getGLGraphics();
	}
}
