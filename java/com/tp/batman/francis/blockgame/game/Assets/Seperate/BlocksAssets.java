package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class BlocksAssets {

	private static GLGame glGame;

	public static Texture blockTexture;

	public static TextureRegion block_blueDark;
	public static TextureRegion block_blueLight;
	public static TextureRegion block_greenDark;
	public static TextureRegion block_greenLight;
	public static TextureRegion block_orangeDark;
	public static TextureRegion block_orangeLight;
	public static TextureRegion block_outline;
	public static TextureRegion block_redDark;
	public static TextureRegion block_redLight;

	public static void load(GLGame game) {
		glGame = game;
		load();
	}

	public static void load() {
		blockTexture = new Texture (glGame, "blocks/blockTexture.png");

		block_blueDark = new TextureRegion(blockTexture, 2, 2, 10, 10);
		block_blueLight = new TextureRegion(blockTexture, 14, 2, 10, 10);
		block_greenDark = new TextureRegion(blockTexture, 2, 14, 10, 10);
		block_greenLight = new TextureRegion(blockTexture, 14, 14, 10, 10);
		block_orangeDark = new TextureRegion(blockTexture, 2, 26, 10, 10);
		block_orangeLight = new TextureRegion(blockTexture, 14, 26, 10, 10);
		block_outline = new TextureRegion(blockTexture, 2, 38, 10, 10);
		block_redDark = new TextureRegion(blockTexture, 2, 50, 10, 10);
		block_redLight = new TextureRegion(blockTexture, 14, 38, 10, 10);
	}

	public static void reload() {
		if (blockTexture != null) {
			blockTexture.reload();
		}
	}

	public static void dispose() {
		if (blockTexture != null) {
			blockTexture.dispose();
		}
	}

	public static void clear() {
		blockTexture = null;

		block_blueDark = null;
		block_blueLight = null;
		block_greenDark = null;
		block_greenLight = null;
		block_orangeDark = null;
		block_orangeLight = null;
		block_outline = null;
		block_redDark = null;
		block_redLight = null;
	}
}