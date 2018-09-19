package com.tp.batman.francis.blockgame.game.GameObjects;

import android.util.Log;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;

import java.util.ArrayList;
import java.util.List;

public class Shape {

    public static final String VERTICAL = "VERTICAL";
    public static final String HORIZONTAL = "HORIZONTAL";
    public static final String PLUS = "PLUS";
    public static final String MINUS = "MINUS";
    public static final int MAX_BLOCK_COUNT = 4;
    public static final int MIN_BLOCK_COUNT = 1;
    public static final float MIN_SPEED = 2;
    public static final float MAX_SPEED = 10;

    private String orientation;
    private List<Block> blocks;
    private float x_bottomLeft;
    private float y_bottomLeft;
    private float speed;
    private String colour;
    private boolean inCollision;

    public Shape(String orientation, int track, String initialPos, int blockCount, float speed) {
        this.orientation = orientation;

        if (orientation.equals(HORIZONTAL)) {
            if (track > GameBoard.X_SIZE + 1 || track < 1) {
                Log.d("Shape.java: " , "Could not initialize shape, shape track invalid");
                float error = 1/0;
            } else if (initialPos.equals(PLUS)) {
                x_bottomLeft = GameBoard.X_SIZE * Block.WIDTH + Block.WIDTH;
                y_bottomLeft = (track - 1) * Block.HEIGHT;
            } else if (initialPos.equals(MINUS)) {
                x_bottomLeft = 0 - (blockCount * Block.WIDTH) - Block.WIDTH;
                y_bottomLeft = (track - 1) * Block.HEIGHT;
            } else {
                Log.d("Shape.java: " , "Could not initialize shape, shape initialPos invalid");
                float error = 1/0;
            }
        } else if (orientation.equals(VERTICAL)) {
            if (track > GameBoard.Y_SIZE + 1 || track < 1) {
                Log.d("Shape.java: " , "Could not initialize shape, shape track invalid");
                float error = 1/0;
            } else if (initialPos.equals(PLUS)) {
                x_bottomLeft = (track - 1) * Block.WIDTH;
                y_bottomLeft = GameBoard.Y_SIZE * Block.HEIGHT + Block.HEIGHT;
            } else if (initialPos.equals(MINUS)) {
                x_bottomLeft = (track - 1) * Block.WIDTH;
                y_bottomLeft = 0 - (blockCount * Block.HEIGHT) - Block.HEIGHT;
            } else {
                Log.d("Shape.java: " , "Could not initialize shape, shape initialPos invalid");
                float error = 1/0;
            }
        } else {
            Log.d("Shape.java: " , "Could not initialize shape, invalid orientation");
            float error = 1/0;
        }

        this.x_bottomLeft = x_bottomLeft;
        this.y_bottomLeft = y_bottomLeft;

        if (initialPos.equals(PLUS)) {
            this.speed = speed * (-1);
        } else if (initialPos.equals(MINUS)) {
            this.speed = speed;
        }

        inCollision = false;

        initializeBlocks(blockCount);
    }

    public String getOrientation() {
        return orientation;
    }

    public void setOrientation(String orientation) {
        this.orientation = orientation;
    }

    public float getX_bottomLeft() {
        return x_bottomLeft;
    }

    public void setX_bottomLeft(float x_bottomLeft) {
        this.x_bottomLeft = x_bottomLeft;

        updateBlockXBottomLeft(x_bottomLeft);
    }

    public float getY_bottomLeft() {
        return y_bottomLeft;
    }

    public void setY_bottomLeft(float y_bottomLeft) {
        this.y_bottomLeft = y_bottomLeft;

        updateBlockYBottomLeft(y_bottomLeft);
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public List<Block> getBlocks() {
        return blocks;
    }

    public void setBlocks(List<Block> blocks) {
        this.blocks = blocks;
    }

    public String getColour() {
        return colour;
    }

    public void setColour(String colour) {
        this.colour = colour;
    }

    public boolean isInCollision() {
        return inCollision;
    }

    public void setInCollision(boolean inCollision) {
        this.inCollision = inCollision;
    }


    //=============================================================
    // update methods
    //=============================================================



    //=============================================================
    // private methods
    //=============================================================

    private void initializeBlocks(int blockCount) {
        List<Block> initializedBlocks = new ArrayList<>();

        if (blockCount < 1 || blockCount > MAX_BLOCK_COUNT) {
            Log.d("Shape.java: " , "Could not initialize blocks, block count invalid");
            float error = 1/0;
        }

        // set colour
        if (blockCount == 1) {
            colour = Block.RED;
        } else if (blockCount == 2) {
            colour = Block.GREEN;
        } else if (blockCount == 3) {
            colour = Block.BLUE;
        } else if (blockCount == 4) {
            colour = Block.ORANGE;
        }

        if (orientation.equals(HORIZONTAL)) {

            for (int i=0; i<blockCount; i++) {
                Block newBlock = new Block(x_bottomLeft * (i+1), y_bottomLeft, colour);
                initializedBlocks.add(newBlock);
            }

        } else if(orientation.equals(VERTICAL)) {

            for (int i=0; i<blockCount; i++) {
                Block newBlock = new Block(x_bottomLeft, y_bottomLeft * (i+1), colour);
                initializedBlocks.add(newBlock);
            }

        } else {
            Log.d("Shape.java: " , "Could not initialize blocks, invalid orientation");
            float error = 1/0;
        }

        blocks = initializedBlocks;
    }

    private void updateBlockXBottomLeft(float x_bottomLeft) {
        int i = 0;
        for (Block block : blocks) {
            block.setX_bottomLeft(x_bottomLeft + (block.WIDTH * i));
            i++;
        }
    }

    private void updateBlockYBottomLeft(float y_bottomLeft) {
        int i = 0;
        for (Block block : blocks) {
            block.setY_bottomLeft(y_bottomLeft + (block.HEIGHT * i));
            i++;
        }
    }


    // RENDER
    public void render(SpriteBatcher batcher) {
        if (blocks.size() > 0) {
            batcher.beginBatch(blocks.get(0).getTexture());

            for (Block block : blocks) {
                if (inCollision) {
                    batcher.drawSprite(block.getX_bottomLeft() + (block.WIDTH / 2), block.getY_bottomLeft() + (block.HEIGHT / 2), block.WIDTH, block.HEIGHT, block.getBlockDark());
                } else {
                    batcher.drawSprite(block.getX_bottomLeft() + (block.WIDTH / 2), block.getY_bottomLeft() + (block.HEIGHT / 2), block.WIDTH, block.HEIGHT, block.getBlockLight());
                }
                batcher.drawSprite(block.getX_bottomLeft() + (block.WIDTH / 2), block.getY_bottomLeft() + (block.HEIGHT / 2), block.WIDTH, block.HEIGHT, block.getBlockOutline());
            }

            batcher.endBatch();
        }
    }
}
