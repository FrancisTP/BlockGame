package com.tp.batman.francis.blockgame.game.GameObjects;

import java.util.ArrayList;
import java.util.List;

public class GameBoard {

    public static final int WIDTH_SIZE = 20;
    public static final int HEIGHT_SIZE = 60;

    IrregularFactory irregularFactory;
    private int irregularCount;

    List<Irregular> irregulars;

    public GameBoard() {
        irregulars = new ArrayList<>();
        irregularCount = 0;
        irregularFactory = new IrregularFactory();
    }

    public void createInitialIrregularsBatch(int irregularCount, int collisionCount) {
        this.irregularCount = irregularCount;

        int collisions = 0;
        for (int i=0; i < irregularCount; i++) {
            if (collisions <= collisionCount) {
                collisions++;
                generateShape(true);
            } else {
                generateShape(false);
            }
        }
    }

    public void generateShape(boolean shouldCollide) {
        irregulars.add(irregularFactory.generateShape(shouldCollide, irregulars));
    }
}
