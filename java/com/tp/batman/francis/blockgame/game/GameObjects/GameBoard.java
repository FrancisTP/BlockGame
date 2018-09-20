package com.tp.batman.francis.blockgame.game.GameObjects;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

public class GameBoard {

    public static final int X_SIZE = 8;
    public static final int Y_SIZE = 11;

    public static final int MAX_SHAPE_COUNT = 8000;

    private List<HashMap<Block, List<Date>>> blockMatrix[][];
    /* Format of the blockMatrix
    Each cell can have a list with the following information
        - Block
        - List of Dates (only 2) that has
            - Start time
            - End time
    */

    private Texture texture;
    private TextureRegion textureRegion;

    public GameBoard() {
        blockMatrix = new ArrayList[X_SIZE][Y_SIZE];

        texture = Assets.blocksAssets.blockTexture;
        textureRegion = Assets.blocksAssets.block_outline;
    }

    public List<HashMap<Block, List<Date>>>[][] getBlockMatrix() {
        return blockMatrix;
    }

    public void setBlockMatrix(List<HashMap<Block, List<Date>>>[][] blockMatrix) {
        this.blockMatrix = blockMatrix;
    }


    // RENDER
    public void render(SpriteBatcher batcher) {
        /*
        batcher.beginBatch(texture);
        float initial_x = 0;
        float initial_y = 0;

        for (int i=0; i < blockMatrix.length; i++ ) {
            for (int j=0; j < blockMatrix[i].length; j++) {
                batcher.drawSprite((i*Block.WIDTH) + (Block.WIDTH / 2), (j*Block.HEIGHT) + (Block.HEIGHT / 2), Block.WIDTH, Block.HEIGHT, textureRegion);
                //batcher.drawSprite(block.getX_bottomLeft() + (block.WIDTH / 2), block.getY_bottomLeft() + (block.HEIGHT / 2), block.WIDTH, block.HEIGHT, block.getBlockOutline());
            }
        }

        batcher.endBatch();
        */
    }
}
