package com.tp.batman.francis.blockgame.game.GameObjects;

import java.util.List;
import java.util.Random;

public class IrregularFactory {

    public static final String TOP = "TOP";
    public static final String BOTTOM = "BOTTOM";
    public static final String LEFT = "LEFT";
    public static final String RIGHT = "RIGHT";

    public IrregularFactory() {

    }

    public Irregular generateShape(boolean shouldCollide, List<Irregular> irregulars) {

        Random rand = new Random();
        /*
        int random = min + rand.nextInt() * (max - min);
        double random = min + rand.nextDouble() * (max - min);
         */

        // should be on top or sides?
        int topOrSide = 1 + rand.nextInt() * (3 - 1);

        if (shouldCollide) {

        } else { // should not collide

        }

        return null;
    }

}
