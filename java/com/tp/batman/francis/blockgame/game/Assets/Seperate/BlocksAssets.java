package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class BlocksAssets {

	private static GLGame glGame;

	public static Texture blocksTexture;

	public static TextureRegion blue_block;
	public static TextureRegion green_block;
	public static TextureRegion orange_block;
	public static TextureRegion pink_block;
	public static TextureRegion red_block;
	public static TextureRegion yellow_block;

	public static void load(GLGame game) {
		glGame = game;
		load();
	}

	public static void load() {
		blocksTexture = new Texture (glGame, "blocks/blocksTexture.png");

		blue_block = new TextureRegion(blocksTexture, 2, 2, 4, 4);
		green_block = new TextureRegion(blocksTexture, 8, 2, 4, 4);
		orange_block = new TextureRegion(blocksTexture, 2, 8, 4, 4);
		pink_block = new TextureRegion(blocksTexture, 8, 8, 4, 4);
		red_block = new TextureRegion(blocksTexture, 2, 14, 4, 4);
		yellow_block = new TextureRegion(blocksTexture, 8, 14, 4, 4);
	}

	public static void reload() {
		if (blocksTexture != null) {
			blocksTexture.reload();
		}
	}

	public static void dispose() {
		if (blocksTexture != null) {
			blocksTexture.dispose();
		}
	}

	public static void clear() {
		blocksTexture = null;

		blue_block = null;
		green_block = null;
		orange_block = null;
		pink_block = null;
		red_block = null;
		yellow_block = null;
	}
}