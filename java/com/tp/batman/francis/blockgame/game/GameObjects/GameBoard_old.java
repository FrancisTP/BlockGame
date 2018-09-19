package com.tp.batman.francis.blockgame.game.GameObjects;

import java.util.ArrayList;
import java.util.List;

public class GameBoard_old {

    public static final int WIDTH_SIZE = 20;
    public static final int HEIGHT_SIZE = 60;

    IrregularFactory_old irregularFactoryOld;
    private int irregularCount;

    List<Irregular_old> irregularOlds;

    public GameBoard_old() {
        irregularOlds = new ArrayList<>();
        irregularCount = 0;
        irregularFactoryOld = new IrregularFactory_old();
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
        irregularOlds.add(irregularFactoryOld.generateShape(shouldCollide, irregularOlds));
    }
}
