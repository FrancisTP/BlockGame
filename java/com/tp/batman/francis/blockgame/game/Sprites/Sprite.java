package com.tp.batman.francis.blockgame.game.Sprites;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;

public class Sprite {

    private Texture texture;
    private TextureRegion textureRegion;

    private float x, y;
    private float width, height;

    public Sprite(Texture texture, TextureRegion textureRegion, float x, float y, float width, float height) {
        this.texture = texture;
        this.textureRegion = textureRegion;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
    }

    public void render(SpriteBatcher batcher) {
        batcher.beginBatch(texture);
        batcher.drawSprite(x, y, width, height, textureRegion);
        batcher.endBatch();
    }

    public Texture getTexture() {
        return texture;
    }

    public void setTexture(Texture texture) {
        this.texture = texture;
    }

    public TextureRegion getTextureRegion() {
        return textureRegion;
    }

    public void setTextureRegion(TextureRegion textureRegion) {
        this.textureRegion = textureRegion;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
    }

    public float getWidth() {
        return width;
    }

    public void setWidth(float width) {
        this.width = width;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }
}
