package com.tp.batman.francis.blockgame.game.GameObjects;

import java.lang.reflect.Array;
import java.util.List;

public class Irregular {


    // limits
    public static final int MAX_WIDTH = 5;
    public static final int MAX_HEIGHT = 5;

    public static final float MIN_SPEED = 1;
    public static final float MAX_SPEED = 5;

    // directions
    public static final String VERTICAL = "vertical";
    public static final String HORIZONTAL = "horizontal";

    // colours
    public static final String BLUE = "blue";
    public static final String GREEN = "green";
    public static final String ORANGE = "orange";
    public static final String PINK = "pink";
    public static final String RED = "red";
    public static final String YELLOW = "yellow";

    private List<Block> blocks;
    private String direction;
    private float speed;
    private String colour;
    private float[][] futurePosition;

    private float x, y;
    private float width_bounds;
    private float height_bounds;

    private boolean shouldCollide;

    public Irregular(String colour, List<Block> blocks, String direction, float speed) {
        this.colour = colour;
        this.blocks = blocks;
        this.direction = direction;
        this.speed = speed;


    }

}
