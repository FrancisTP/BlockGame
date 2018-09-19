package com.tp.batman.francis.blockgame.game.GameObjects;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.Sprites.Sprite;

public class Block {

    // LOGIC
    public static final String RED = "RED";
    public static final String GREEN = "GREEN";
    public static final String ORANGE = "ORANGE";
    public static final String BLUE = "BLUE";

    public final static float WIDTH = 100.0f;
    public final static float HEIGHT = 100.0f;

    private float x_bottomLeft;
    private float y_bottomLeft;

    private Texture texture;
    private TextureRegion blockDark;
    private TextureRegion blockLight;
    private TextureRegion blockOutline;

    public Block() {
        x_bottomLeft = 0;
        y_bottomLeft = 0;
    }

    public Block(float x_bottomLeft, float y_bottomLeft, String colour) {
        this.x_bottomLeft = x_bottomLeft;
        this.y_bottomLeft = y_bottomLeft;

        texture = Assets.blocksAssets.blockTexture;
        if (colour.equals(RED)) {
            blockDark = Assets.blocksAssets.block_redDark;
            blockLight = Assets.blocksAssets.block_redLight;
        } else if (colour.equals(GREEN)) {
            blockDark = Assets.blocksAssets.block_greenDark;
            blockLight = Assets.blocksAssets.block_greenLight;
        } else if (colour.equals(ORANGE)) {
            blockDark = Assets.blocksAssets.block_orangeDark;
            blockLight = Assets.blocksAssets.block_orangeLight;
        } else if (colour.equals(BLUE)) {
            blockDark = Assets.blocksAssets.block_blueDark;
            blockLight = Assets.blocksAssets.block_blueLight;
        }
        blockOutline = Assets.blocksAssets.block_outline;
    }

    public float getX_bottomLeft() {
        return x_bottomLeft;
    }

    public void setX_bottomLeft(float x_bottomLeft) {
        this.x_bottomLeft = x_bottomLeft;
    }

    public float getY_bottomLeft() {
        return y_bottomLeft;
    }

    public void setY_bottomLeft(float y_bottomLeft) {
        this.y_bottomLeft = y_bottomLeft;
    }


    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureRegion getBlockDark() {
        return blockDark;
    }

    public void setBlockDark(TextureRegion blockDark) {
        this.blockDark = blockDark;
    }

    public TextureRegion getBlockLight() {
        return blockLight;
    }

    public void setBlockLight(TextureRegion blockLight) {
        this.blockLight = blockLight;
    }

    public TextureRegion getBlockOutline() {
        return blockOutline;
    }

    public void setBlockOutline(TextureRegion blockOutline) {
        this.blockOutline = blockOutline;
    }

    // RENDER
    public void rendet(SpriteBatcher batcher) {
        batcher.beginBatch(texture);
        batcher.drawSprite(x_bottomLeft + (WIDTH / 2), y_bottomLeft + (HEIGHT / 2), WIDTH, HEIGHT, blockDark);
        batcher.drawSprite(x_bottomLeft + (WIDTH / 2), y_bottomLeft + (HEIGHT / 2), WIDTH, HEIGHT, blockOutline);
        batcher.endBatch();
    }
}
