package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public abstract class GeneralForm {

    protected static GLGame glGame;

    public static void load(GLGame game){
        glGame = game;
        load();
    }
    public static void load(){

    }


    public static void reload(){

    }

    public static void dispose(){

    }

    public static void clear(){

    }
}
