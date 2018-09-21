package com.tp.batman.francis.blockgame.game.GameObjects;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.game.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class ShapeController {

    private static final int RETRIES = 3;

    private List<Shape> shapes;
    private Random r;
    GameBoard gameBoard;

    public ShapeController(GameBoard gameBoard) {
        shapes = new ArrayList<>();
        r = new Random();

        this.gameBoard = gameBoard;
    }

    public List<Shape> getShapes() {
        return shapes;
    }

    public void setShapes(List<Shape> shapes) {
        this.shapes = shapes;
    }

    public Shape createNewShape() {
        float speed = Shape.MIN_SPEED + r.nextFloat() * (Shape.MAX_SPEED - Shape.MIN_SPEED);

        int orientationNum = 0 + r.nextInt((1 - 0) + 1);
        String orientation = "";

        int initialPosNum = 0 + r.nextInt((1- 0) + 1);
        String initialPos = "";
        if (initialPosNum == 0) {
            initialPos = Shape.MINUS;
        } else if (initialPosNum == 1) {
            initialPos = Shape.PLUS;
        }

        int track = 0;
        int retryCount = 0;
        boolean keepGoing = true;
        do {
            keepGoing = true;
            if (orientationNum == 0) {
                orientation = Shape.HORIZONTAL;
                track = 3 + r.nextInt(((gameBoard.Y_SIZE - 3) - 3) + 1) + 1;
            } else if (orientationNum == 1) {
                orientation = Shape.VERTICAL;
                track = 3 + r.nextInt(((gameBoard.X_SIZE - 3) - 3) + 1) + 1;
            }


            for (Shape shape : shapes) {
                if (shape.getOrientation().equals(orientation) && shape.getTrack() == track) {
                    if (shape.getInitialPos().equals(initialPos)) { // going in the same direction
                        float normalizedSpeed = shape.getSpeed();
                        if (normalizedSpeed < 0) {
                            normalizedSpeed *= (-1);
                        }

                            if (shape.getOrientation().equals(Shape.HORIZONTAL)) {
                                if (shape.getSpeed() > 0) { // going right
                                    if (shape.getX_bottomLeft() < Block.WIDTH) {
                                        keepGoing = false;
                                    }
                                } else if (shape.getSpeed() < 0) { // going left
                                    if (shape.getX_bottomLeft() + (Block.WIDTH * shape.getBlocks().size()) > (Block.WIDTH * (GameBoard.X_SIZE - 1))) {
                                        keepGoing = false;
                                    }
                                }
                            } else if (shape.getOrientation().equals(Shape.VERTICAL)) {
                                if (shape.getSpeed() > 0) { // going up
                                    if (shape.getY_bottomLeft() < Block.HEIGHT) {
                                        keepGoing = false;
                                    }
                                } else if (shape.getSpeed() < 0) { // going down
                                    if (shape.getY_bottomLeft() + (Block.HEIGHT * shape.getBlocks().size()) > (Block.HEIGHT * (GameBoard.Y_SIZE - 1))) {
                                        keepGoing = false;
                                    }
                                }

                        }

                        if (speed > normalizedSpeed) {
                            keepGoing = false;
                        }

                    } else { // going different directions
                        keepGoing = false;
                    }

                }
            }

            if (retryCount > RETRIES && !keepGoing) {
                return null;
            }

            retryCount++;
        } while (!keepGoing);

        int blockCount = Shape.MIN_BLOCK_COUNT + r.nextInt((Shape.MAX_BLOCK_COUNT - Shape.MIN_BLOCK_COUNT) + 1);

        Shape newShape = new Shape(orientation, track, initialPos, blockCount, speed);

        shapes.add(newShape);
        gameBoard.calculateShapePositionOnBoard(newShape);

        return newShape;
    }

    // ###################################################################
    // UPDATE
    // ###################################################################

    public void update(float deltaTime){

        List<Shape> shapesToRemove = new ArrayList<>();
        for (Shape shape : shapes) {
            if (updateShape(shape, deltaTime)) {
                shapesToRemove.add(shape);
                gameBoard.removeShapePositions(shape);
            }

        }

        shapes.removeAll(shapesToRemove);
    }

    public boolean updateShape(Shape shape, float deltaTime) {
        if (shape.getOrientation().equals(Shape.HORIZONTAL)) {
            shape.setX_bottomLeft(shape.getX_bottomLeft() + shape.getSpeed());


            if (shape.getSpeed() < 0) { // going left
                if (shape.getX_bottomLeft() + (Block.WIDTH * shape.getBlocks().size()) + Block.WIDTH < 0) {
                    return true;
                }
            } else if (shape.getSpeed() > 0) { // going right
                if (shape.getX_bottomLeft() > (Block.WIDTH * gameBoard.X_SIZE) + Block.WIDTH) {
                    return true;
                }
            }

        } else if (shape.getOrientation().equals(Shape.VERTICAL)) {
            shape.setY_bottomLeft(shape.getY_bottomLeft() + shape.getSpeed());


            if (shape.getSpeed() < 0) { // going down
                if (shape.getY_bottomLeft() + (Block.HEIGHT * shape.getBlocks().size()) + Block.HEIGHT < 0) {
                    return true;
                }
            } else if (shape.getSpeed() > 0) { // going up
                if (shape.getY_bottomLeft() > Block.HEIGHT * gameBoard.Y_SIZE + Block.HEIGHT) {
                    return true;
                }
            }
        }

        return false;
    }


    // ###################################################################
    // PRESENTING
    // ###################################################################

    @SuppressLint("FloatMath")
    public void render(SpriteBatcher batcher){
        for (Shape shape : shapes) {
            shape.render(batcher);
        }
    }
}
