package com.tp.batman.francis.blockgame.game.Assets;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;

/**
 * Created by Francis on 2017-04-26.
 */
public class Word {

    private Character characters[];
    private float x, y;
    private float width, height;

    public static final float charSpacing = 3f;

    public Word(Character characters[], float width, float height) {
        this.characters = characters;

        this.width = width;
        this.height = height;
    }

    public void addY(float amount) {
        for (int i=0; i<characters.length; i++) {
            characters[i].addY(amount);
            y += amount;
        }
    }

    public Character[] getCharacters() {
        return characters;
    }

    public void setCharacters(Character[] characters) {
        this.characters = characters;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        float currentX = this.x;
        for (int i=0; i<characters.length; i++) {
            characters[i].setX(currentX);
            currentX += characters[i].getWidth() + charSpacing;
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        for (int i=0; i<characters.length; i++) {
            characters[i].setY(this.y, height);
        }
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

    public void render(SpriteBatcher batcher) {
        for (int i=0; i<characters.length; i++) {
            characters[i].render(batcher);
        }
    }
}
