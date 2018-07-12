package com.tp.batman.francis.blockgame.game.Assets;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;

/**
 * Created by Francis on 2017-04-26.
 */
public class Line {

    private Word words[];
    private float x, y;
    private float width, height;

    public Line(Word words[], float width, float height) {
        this.words = words;
        this.width = width;
        this.height = height;

    }

    public void addY(float amount) {
        for (int i=0; i<words.length; i++) {
            words[i].addY(amount);
            y += amount;
        }
    }

    public Word[] getWords() {
        return words;
    }

    public void setWords(Word[] words) {
        this.words = words;
    }

    public float getX() {
        return x;
    }

    public void setX(float x) {
        this.x = x;
        float currentX = this.x;
        for (int i=0; i<words.length; i++) {
            words[i].setX(currentX);
            currentX += words[i].getWidth();
        }
    }

    public float getY() {
        return y;
    }

    public void setY(float y) {
        this.y = y;
        for (int i=0; i<words.length; i++) {
            words[i].setY(this.y);
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
        for (int i=0; i<words.length; i++) {
            words[i].render(batcher);
        }
    }
}
