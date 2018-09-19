package com.tp.batman.francis.blockgame.game.GameObjects;

import android.annotation.SuppressLint;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.game.Screens.GameScreen;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.microedition.khronos.opengles.GL10;

public class ShapeController {

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
        int track = 0;
        if (orientationNum == 0) {
            orientation = Shape.HORIZONTAL;
            track = 0 + r.nextInt((gameBoard.X_SIZE - 0) + 1) + 1;
        } else if (orientationNum == 1) {
            orientation = Shape.VERTICAL;
            track = 0 + r.nextInt((gameBoard.Y_SIZE - 0) + 1) + 1;
        }

        int initialPosNum = 0 + r.nextInt((1- 0) + 1);
        String initialPos = "";
        if (initialPosNum == 0) {
            initialPos = Shape.MINUS;
        } else if (initialPosNum == 1) {
            initialPos = Shape.PLUS;
        }

        int blockCount = Shape.MIN_BLOCK_COUNT + r.nextInt((Shape.MAX_BLOCK_COUNT - Shape.MIN_BLOCK_COUNT) + 1);

        Shape newShape = new Shape(orientation, track, initialPos, blockCount, speed);

        shapes.add(newShape);
        return newShape;
    }

    // ###################################################################
    // UPDATE
    // ###################################################################

    public void update(float deltaTime){

        List<Shape> shapesToRemove = new ArrayList<>();
        for (Shape shape : shapes) {
            if (shape.getOrientation().equals(Shape.HORIZONTAL)) {
                shape.setX_bottomLeft(shape.getX_bottomLeft() + shape.getSpeed());


                if (shape.getSpeed() < 0) { // going left
                    if (shape.getX_bottomLeft() + (Block.WIDTH * shape.getBlocks().size()) + Block.WIDTH < 0) {
                        shapesToRemove.add(shape);
                    }
                } else if (shape.getSpeed() > 0) { // going right
                    if (shape.getX_bottomLeft() - Block.WIDTH > Block.WIDTH * gameBoard.X_SIZE) {
                        shapesToRemove.add(shape);
                    }
                }

            } else if (shape.getOrientation().equals(Shape.VERTICAL)) {
                shape.setY_bottomLeft(shape.getY_bottomLeft() + shape.getSpeed());


                if (shape.getSpeed() < 0) { // going down
                    if (shape.getY_bottomLeft() + (Block.HEIGHT * shape.getBlocks().size()) + Block.HEIGHT < 0) {
                        shapesToRemove.add(shape);
                    }
                } else if (shape.getSpeed() > 0) { // going up
                    if (shape.getY_bottomLeft() - Block.HEIGHT > Block.HEIGHT * gameBoard.Y_SIZE) {
                        shapesToRemove.add(shape);
                    }
                }
            }

        }

        shapes.removeAll(shapesToRemove);
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
