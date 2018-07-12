package com.tp.batman.francis.blockgame.game.Sprites;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.math.Rectangle;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

public class Button {

    public final static int BOUNDS_NOT_TOUCHED = 0;
    public final static int BOUNDS_TOUCHED = 1;

    private Sprite spriteUp;
    private Sprite spriteDown;

    private Rectangle bounds;
    private int state;

    public Button(float x, float y, float width, float height, Texture texture, TextureRegion spriteUp, TextureRegion spriteDown) {
        this.spriteUp = new Sprite(Assets.buttonsAssets.buttonsTexture, spriteUp, x, y, width, height);
        this.spriteDown = new Sprite(Assets.buttonsAssets.buttonsTexture, spriteDown, x, y, width, height);
        this.bounds = new Rectangle(x, y, width, height);
    }

    public void render(SpriteBatcher batcher) {
        batcher.beginBatch(Assets.buttonsAssets.buttonsTexture);
        if (state == BOUNDS_NOT_TOUCHED) {
            batcher.drawSprite(spriteUp.getX(), spriteUp.getY(), spriteUp.getWidth(), spriteUp.getHeight(), spriteUp.getTextureRegion());
        } else {
            batcher.drawSprite(spriteDown.getX(), spriteDown.getY(), spriteDown.getWidth(), spriteDown.getHeight(), spriteDown.getTextureRegion());
        }
        batcher.endBatch();
    }

    public static int getBoundsNotTouched() {
        return BOUNDS_NOT_TOUCHED;
    }

    public static int getBoundsTouched() {
        return BOUNDS_TOUCHED;
    }

    public Sprite getSpriteUp() {
        return spriteUp;
    }

    public void setSpriteUp(Sprite spriteUp) {
        this.spriteUp = spriteUp;
    }

    public Sprite getSpriteDown() {
        return spriteDown;
    }

    public void setSpriteDown(Sprite spriteDown) {
        this.spriteDown = spriteDown;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    public void setBounds(Rectangle bounds) {
        this.bounds = bounds;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }
}
