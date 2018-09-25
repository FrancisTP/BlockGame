package com.tp.batman.francis.blockgame.game.GameObjects;

import android.util.Log;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GameBoard {

    public static final int X_SIZE = 10;
    public static final int Y_SIZE = 14;

    public static final int MAX_SHAPE_COUNT = 10;

    private List<Map<Shape, List<Integer>>> blockMatrix[][];
    /* Format of the blockMatrix
    Each cell can have a list with the following information
        - Block
        - List of Dates (only 2) that has
            - Start time
            - End time
    */

    int frameCounter;

    private Texture texture;
    private TextureRegion textureRegion;

    private ShapeController shapeController;

    public GameBoard() {
        blockMatrix = new ArrayList[X_SIZE][Y_SIZE];
        for (int i=0; i < blockMatrix.length; i++) {
            for (int j=0; j < blockMatrix[i].length; j++) {
                blockMatrix[i][j] = new ArrayList<>();
            }
        }

        texture = Assets.blocksAssets.blockTexture;
        textureRegion = Assets.blocksAssets.block_outline;

        frameCounter = 0;
    }

    public List<Map<Shape, List<Integer>>>[][] getBlockMatrix() {
        return blockMatrix;
    }

    public void setBlockMatrix(List<Map<Shape, List<Integer>>>[][] blockMatrix) {
        this.blockMatrix = blockMatrix;
    }

    public int getFrameCounter() {
        return frameCounter;
    }

    public void setFrameCounter(int frameCounter) {
        this.frameCounter = frameCounter;
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

    public ShapeController getShapeController() {
        return shapeController;
    }

    public void setShapeController(ShapeController shapeController) {
        this.shapeController = shapeController;
    }

    public void update() {
        frameCounter++;
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



    //////////

    public void calculateShapePositionOnBoard(Shape shape) {
        boolean stillCalculatingPosition = true;

        int currentFrameCount = frameCounter;

        Shape tempShape = new Shape(shape.getOrientation(), shape.getTrack(), shape.getInitialPos(), shape.getBlocks().size(), shape.getSpeed());
        tempShape.setOrientation(shape.getOrientation());
        tempShape.setTrack(shape.getTrack());
        tempShape.setInitialPos(shape.getInitialPos());
        tempShape.setSpeed(shape.getSpeed());
        tempShape.setInCollision(false);
        tempShape.setY_bottomLeft(shape.getY_bottomLeft());
        tempShape.setX_bottomLeft(shape.getX_bottomLeft());
        tempShape.setColour(shape.getColour());

        List<Block> newBlocks = new ArrayList<>();
        for (Block block : shape.getBlocks()) {
            Block newBlock = new Block();
            newBlock.setX_bottomLeft(block.getX_bottomLeft());
            newBlock.setY_bottomLeft(block.getY_bottomLeft());
            newBlock.setBlockDark(block.getBlockDark());
            newBlock.setBlockLight(block.getBlockLight());
            newBlock.setTexture(block.getTexture());
            newBlock.setBlockOutline(block.getBlockOutline());

            newBlocks.add(block);
        }
        tempShape.setBlocks(newBlocks);


        boolean[] isInGameBoardSquareX = new boolean[X_SIZE];
        boolean[] isOutGameBoardSquareX = new boolean[X_SIZE];

        boolean[] isInGameBoardSquareY = new boolean[Y_SIZE];
        boolean[] isOutGameBoardSquareY = new boolean[Y_SIZE];

        Arrays.fill(isInGameBoardSquareX, false);
        Arrays.fill(isOutGameBoardSquareX, false);

        Arrays.fill(isInGameBoardSquareY, false);
        Arrays.fill(isOutGameBoardSquareY, false);

        while (stillCalculatingPosition) {

            // Simulating shape movement
            //Log.d("calculateShapePositionOnBoard: " , "UPDATING SHAPE POSITION");
            if (shapeController.updateShape(tempShape, 0)) {
                stillCalculatingPosition = false;
            }

            // Marking down where it is on the board for the specific frame
            // check which direction the block is going to make it easier to map out movement
            if (tempShape.getOrientation().equals(Shape.HORIZONTAL)) {
                if (tempShape.getSpeed() > 0) { // going right
                    // get when shape gets in and out of a block

                    for (int i=0; i < X_SIZE; i++) { // go through all the blocks, check if the shape just got in or just got out
                        if ((tempShape.getX_bottomLeft() + (tempShape.getBlocks().size() * Block.WIDTH)) > (i * Block.WIDTH)) {
                            if (isInGameBoardSquareX[i] == false && isOutGameBoardSquareX[i] == false) { // Just got in the block from the right
                                isInGameBoardSquareX[i] = true;
                                //private List<HashMap<Shape, List<Integer>>> blockMatrix[][];

                                Map newPositionEntry = new HashMap();
                                List newPositionList = new ArrayList();
                                newPositionList.add(currentFrameCount);


                                newPositionEntry.put(shape, newPositionList);
                                blockMatrix[i][tempShape.getTrack()-1].add(newPositionEntry);
                            }
                        }
                        if (tempShape.getX_bottomLeft() > ((i + 1) * Block.WIDTH)) {
                            if (isInGameBoardSquareX[i] == true && isOutGameBoardSquareX[i] == false) { // Just got out of the block
                                isOutGameBoardSquareX[i] = true;

                                for (Map map : blockMatrix[i][tempShape.getTrack()-1]) {
                                    if (map.containsKey(shape)) {
                                        ((List)map.get(shape)).add(currentFrameCount-1);  // Add the last frame the Shape got out of the block
                                        // HERE SET COLLISIONS

                                        if (blockMatrix[i][tempShape.getTrack()-1].size() > 1) {
                                            for (int k = 0; k < blockMatrix[i][tempShape.getTrack()-1].size(); k++) {
                                                for (int l = k + 1; l < blockMatrix[i][tempShape.getTrack()-1].size(); l++) {

                                                    Map.Entry shapeOneEntry = blockMatrix[i][tempShape.getTrack()-1].get(k).entrySet().iterator().next();
                                                    Shape shapeOne = ((Shape) shapeOneEntry.getKey());
                                                    String shapeOneColour = shapeOne.getColour();
                                                    List<Integer> shapeOneFrameList = ((List<Integer>) shapeOneEntry.getValue());
                                                    int shapeOneFrameEntry = shapeOneFrameList.get(0);
                                                    int shapeOneFrameExit = shapeOneFrameList.get(1);

                                                    Map.Entry shapeTwoEntry = blockMatrix[i][tempShape.getTrack()-1].get(l).entrySet().iterator().next();
                                                    Shape shapeTwo = ((Shape) shapeTwoEntry.getKey());
                                                    String shapeTwoColour = shapeTwo.getColour();
                                                    List<Integer> shapeTwoFrameList = ((List<Integer>) shapeTwoEntry.getValue());
                                                    int shapeTwoFrameEntry = shapeTwoFrameList.get(0);
                                                    int shapeTwoFrameExit = shapeTwoFrameList.get(1);

                                                    if (shapeOneColour.equals(shapeTwoColour)) {
                                                        if ((shapeOneFrameEntry >= shapeTwoFrameEntry && shapeOneFrameEntry <= shapeTwoFrameExit) || (shapeTwoFrameEntry >= shapeOneFrameEntry && shapeTwoFrameEntry <= shapeOneFrameExit)) {
                                                            // THESE TWO SHAPES COLLIDE
                                                            if (!shapeOne.getWillCollideWith().contains(shapeTwo)) {
                                                                shapeOne.getWillCollideWith().add(shapeTwo);
                                                            }
                                                            if (!shapeTwo.getWillCollideWith().contains(shapeOne)) {
                                                                shapeTwo.getWillCollideWith().add(shapeOne);
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                    }

                } else if (tempShape.getSpeed() < 0) { // going left
                    // get when shape gets in and out of a block

                    for (int i=X_SIZE-1; i >= 0; i--) {
                        if (tempShape.getX_bottomLeft() < ((i + 1) * Block.WIDTH)) { //
                            if (isInGameBoardSquareX[i] == false && isOutGameBoardSquareX[i] == false) { // Just got in the block from the left
                                isInGameBoardSquareX[i] = true;
                                //private List<HashMap<Shape, List<Integer>>> blockMatrix[][];

                                Map newPositionEntry = new HashMap();
                                List newPositionList = new ArrayList();
                                newPositionList.add(currentFrameCount);


                                newPositionEntry.put(shape, newPositionList);
                                blockMatrix[i][tempShape.getTrack()-1].add(newPositionEntry);
                            }
                        }
                        if ((tempShape.getX_bottomLeft() + (tempShape.getBlocks().size() * Block.WIDTH)) < (i * Block.WIDTH)) {
                            if (isInGameBoardSquareX[i] == true && isOutGameBoardSquareX[i] == false) { // Just got out of the block
                                isOutGameBoardSquareX[i] = true;

                                for (Map map : blockMatrix[i][tempShape.getTrack()-1]) {
                                    if (map.containsKey(shape)) {
                                        ((List)map.get(shape)).add(currentFrameCount-1);  // Add the last frame the Shape got out of the block
                                        // HERE SET COLLISIONS

                                        if (blockMatrix[i][tempShape.getTrack()-1].size() > 1) {
                                            for (int k = 0; k < blockMatrix[i][tempShape.getTrack()-1].size(); k++) {
                                                for (int l = k + 1; l < blockMatrix[i][tempShape.getTrack()-1].size(); l++) {

                                                    Map.Entry shapeOneEntry = blockMatrix[i][tempShape.getTrack()-1].get(k).entrySet().iterator().next();
                                                    Shape shapeOne = ((Shape) shapeOneEntry.getKey());
                                                    String shapeOneColour = shapeOne.getColour();
                                                    List<Integer> shapeOneFrameList = ((List<Integer>) shapeOneEntry.getValue());
                                                    int shapeOneFrameEntry = shapeOneFrameList.get(0);
                                                    int shapeOneFrameExit = shapeOneFrameList.get(1);

                                                    Map.Entry shapeTwoEntry = blockMatrix[i][tempShape.getTrack()-1].get(l).entrySet().iterator().next();
                                                    Shape shapeTwo = ((Shape) shapeTwoEntry.getKey());
                                                    String shapeTwoColour = shapeTwo.getColour();
                                                    List<Integer> shapeTwoFrameList = ((List<Integer>) shapeTwoEntry.getValue());
                                                    int shapeTwoFrameEntry = shapeTwoFrameList.get(0);
                                                    int shapeTwoFrameExit = shapeTwoFrameList.get(1);

                                                    if (shapeOneColour.equals(shapeTwoColour)) {
                                                        if ((shapeOneFrameEntry >= shapeTwoFrameEntry && shapeOneFrameEntry <= shapeTwoFrameExit) || (shapeTwoFrameEntry >= shapeOneFrameEntry && shapeTwoFrameEntry <= shapeOneFrameExit)) {
                                                            // THESE TWO SHAPES COLLIDE
                                                            if (!shapeOne.getWillCollideWith().contains(shapeTwo)) {
                                                                shapeOne.getWillCollideWith().add(shapeTwo);
                                                            }
                                                            if (!shapeTwo.getWillCollideWith().contains(shapeOne)) {
                                                                shapeTwo.getWillCollideWith().add(shapeOne);
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            } else if (tempShape.getOrientation().equals(Shape.VERTICAL)) {
                if (tempShape.getSpeed() > 0) { // going up
                    // get when shape goes in a block

                    for (int i=0; i < Y_SIZE; i++) { // go through all the blocks, check if the shape just got in or just got out
                        if ((tempShape.getY_bottomLeft() + (tempShape.getBlocks().size() * Block.HEIGHT)) > (i * Block.HEIGHT)) {
                            if (isInGameBoardSquareY[i] == false && isOutGameBoardSquareY[i] == false) { // Just got in the block from the right
                                isInGameBoardSquareY[i] = true;
                                //private List<HashMap<Shape, List<Integer>>> blockMatrix[][];

                                Map newPositionEntry = new HashMap();
                                List newPositionList = new ArrayList();
                                newPositionList.add(currentFrameCount);


                                newPositionEntry.put(shape, newPositionList);
                                blockMatrix[tempShape.getTrack()-1][i].add(newPositionEntry);
                            }
                        }
                        if (tempShape.getY_bottomLeft() > ((i + 1) * Block.HEIGHT)) {
                            if (isInGameBoardSquareY[i] == true && isOutGameBoardSquareY[i] == false) { // Just got out of the block
                                isOutGameBoardSquareY[i] = true;

                                for (Map map : blockMatrix[tempShape.getTrack()-1][i]) {
                                    if (map.containsKey(shape)) {
                                        ((List)map.get(shape)).add(currentFrameCount-1);  // Add the last frame the Shape got out of the block
                                        // HERE SET COLLISIONS

                                        if (blockMatrix[tempShape.getTrack()-1][i].size() > 1) {
                                            for (int k = 0; k < blockMatrix[tempShape.getTrack()-1][i].size(); k++) {
                                                for (int l = k + 1; l < blockMatrix[tempShape.getTrack()-1][i].size(); l++) {

                                                    Map.Entry shapeOneEntry = blockMatrix[tempShape.getTrack()-1][i].get(k).entrySet().iterator().next();
                                                    Shape shapeOne = ((Shape) shapeOneEntry.getKey());
                                                    String shapeOneColour = shapeOne.getColour();
                                                    List<Integer> shapeOneFrameList = ((List<Integer>) shapeOneEntry.getValue());
                                                    int shapeOneFrameEntry = shapeOneFrameList.get(0);
                                                    int shapeOneFrameExit = shapeOneFrameList.get(1);

                                                    Map.Entry shapeTwoEntry = blockMatrix[tempShape.getTrack()-1][i].get(l).entrySet().iterator().next();
                                                    Shape shapeTwo = ((Shape) shapeTwoEntry.getKey());
                                                    String shapeTwoColour = shapeTwo.getColour();
                                                    List<Integer> shapeTwoFrameList = ((List<Integer>) shapeTwoEntry.getValue());
                                                    int shapeTwoFrameEntry = shapeTwoFrameList.get(0);
                                                    int shapeTwoFrameExit = shapeTwoFrameList.get(1);

                                                    if (shapeOneColour.equals(shapeTwoColour)) {
                                                        if ((shapeOneFrameEntry >= shapeTwoFrameEntry && shapeOneFrameEntry <= shapeTwoFrameExit) || (shapeTwoFrameEntry >= shapeOneFrameEntry && shapeTwoFrameEntry <= shapeOneFrameExit)) {
                                                            // THESE TWO SHAPES COLLIDE
                                                            if (!shapeOne.getWillCollideWith().contains(shapeTwo)) {
                                                                shapeOne.getWillCollideWith().add(shapeTwo);
                                                            }
                                                            if (!shapeTwo.getWillCollideWith().contains(shapeOne)) {
                                                                shapeTwo.getWillCollideWith().add(shapeOne);
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                    }

                } else if (tempShape.getSpeed() < 0) { // going down
                    // get when shape gets in and out of a block

                    for (int i=Y_SIZE-1; i >= 0; i--) {
                        if (tempShape.getY_bottomLeft() < ((i + 1) * Block.HEIGHT)) { //

                            if (isInGameBoardSquareY[i] == false && isOutGameBoardSquareY[i] == false) { // Just got in the block from the left
                                isInGameBoardSquareY[i] = true;
                                //private List<HashMap<Shape, List<Integer>>> blockMatrix[][];

                                Map newPositionEntry = new HashMap();
                                List newPositionList = new ArrayList();
                                newPositionList.add(currentFrameCount);


                                newPositionEntry.put(shape, newPositionList);
                                blockMatrix[tempShape.getTrack()-1][i].add(newPositionEntry);

                            }
                        }
                        if ((tempShape.getY_bottomLeft() + (tempShape.getBlocks().size() * Block.HEIGHT)) < (i * Block.HEIGHT)) {
                            if (isInGameBoardSquareY[i] == true && isOutGameBoardSquareY[i] == false) { // Just got out of the block
                                isOutGameBoardSquareY[i] = true;

                                for (Map map : blockMatrix[tempShape.getTrack()-1][i]) {
                                    if (map.containsKey(shape)) {
                                        ((List)map.get(shape)).add(currentFrameCount-1);  // Add the last frame the Shape got out of the block
                                        // HERE SET COLLISIONS

                                        if (blockMatrix[tempShape.getTrack()-1][i].size() > 1) {
                                            for (int k = 0; k < blockMatrix[tempShape.getTrack()-1][i].size(); k++) {
                                                for (int l = k + 1; l < blockMatrix[tempShape.getTrack()-1][i].size(); l++) {

                                                    Map.Entry shapeOneEntry = blockMatrix[tempShape.getTrack()-1][i].get(k).entrySet().iterator().next();
                                                    Shape shapeOne = ((Shape) shapeOneEntry.getKey());
                                                    String shapeOneColour = shapeOne.getColour();
                                                    List<Integer> shapeOneFrameList = ((List<Integer>) shapeOneEntry.getValue());
                                                    int shapeOneFrameEntry = shapeOneFrameList.get(0);
                                                    int shapeOneFrameExit = shapeOneFrameList.get(1);

                                                    Map.Entry shapeTwoEntry = blockMatrix[tempShape.getTrack()-1][i].get(l).entrySet().iterator().next();
                                                    Shape shapeTwo = ((Shape) shapeTwoEntry.getKey());
                                                    String shapeTwoColour = shapeTwo.getColour();
                                                    List<Integer> shapeTwoFrameList = ((List<Integer>) shapeTwoEntry.getValue());
                                                    int shapeTwoFrameEntry = shapeTwoFrameList.get(0);
                                                    int shapeTwoFrameExit = shapeTwoFrameList.get(1);

                                                    if (shapeOneColour.equals(shapeTwoColour)) {
                                                        if ((shapeOneFrameEntry >= shapeTwoFrameEntry && shapeOneFrameEntry <= shapeTwoFrameExit) || (shapeTwoFrameEntry >= shapeOneFrameEntry && shapeTwoFrameEntry <= shapeOneFrameExit)) {
                                                            // THESE TWO SHAPES COLLIDE
                                                            if (!shapeOne.getWillCollideWith().contains(shapeTwo)) {
                                                                shapeOne.getWillCollideWith().add(shapeTwo);
                                                            }
                                                            if (!shapeTwo.getWillCollideWith().contains(shapeOne)) {
                                                                shapeTwo.getWillCollideWith().add(shapeOne);
                                                            }

                                                        }
                                                    }
                                                }
                                            }
                                        }

                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }



            currentFrameCount++;
        }
    }

    public void removeShapePositions(Shape shape) {
        for (int i=0; i < blockMatrix.length; i++) {
            for (int j=0; j < blockMatrix[i].length; j++ ) {
                for (Map map : blockMatrix[i][j]) {
                    if (map.containsKey(shape)) {
                        map.remove(shape);
                        blockMatrix[i][j].remove(map);
                        //printBlockMatrix();
                        break;
                    }
                }
            }
        }
    }

    private void printBlockMatrix() {
        for (int i=0; i < blockMatrix.length; i++) {
            for (int j=0; j < blockMatrix[i].length; j++ ) {
                for (Map map : blockMatrix[i][j]) {
                    Log.d("blockMatrix values: ", "map " + map);
                }
            }
        }
    }


    /* Format of the blockMatrix
    Each cell can have a list with the following information
        - Block
        - List of Dates (only 2) that has
            - Start time
            - End time
    */
    public void checkAndSetCollisions() {

        // reset all collisions before starting
        for (Shape shape : shapeController.getShapes()) {
            shape.setInCollision(false);
        }


        for (int i=0; i < blockMatrix.length; i++) {
            for (int j = 0; j < blockMatrix[i].length; j++) {
                if (blockMatrix[i][j].size() > 1) {
                    for (int k = 0; k < blockMatrix[i][j].size(); k++) {
                        for (int l = k+1; l < blockMatrix[i][j].size(); l++) {
                                Map.Entry shapeOneEntry = blockMatrix[i][j].get(k).entrySet().iterator().next();
                                Shape shapeOne = ((Shape) shapeOneEntry.getKey());
                                String shapeOneColour = shapeOne.getColour();
                                List<Integer> shapeOneFrameList = ((List<Integer>) shapeOneEntry.getValue());
                                int shapeOneFrameEntry = shapeOneFrameList.get(0);
                                int shapeOneFrameExit = shapeOneFrameList.get(1);

                                Map.Entry shapeTwoEntry = blockMatrix[i][j].get(l).entrySet().iterator().next();
                                Shape shapeTwo = ((Shape) shapeTwoEntry.getKey());
                                String shapeTwoColour = shapeTwo.getColour();
                                List<Integer> shapeTwoFrameList = ((List<Integer>) shapeTwoEntry.getValue());
                                int shapeTwoFrameEntry = shapeTwoFrameList.get(0);
                                int shapeTwoFrameExit = shapeTwoFrameList.get(1);

                                if (shapeOneColour.equals(shapeTwoColour)) {
                                    if (frameCounter > shapeOneFrameEntry && frameCounter < shapeOneFrameExit && frameCounter > shapeTwoFrameEntry && frameCounter < shapeTwoFrameExit) {
                                        shapeOne.setInCollision(true);
                                        shapeTwo.setInCollision(true);
                                    }
                                }

                        }
                    }
                }
            }
        }
    }
}
