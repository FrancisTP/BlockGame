package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class GameScreenAssets {

    protected static GLGame glGame;

    public static Texture gameScreenBackgroundTexture;
    public static TextureRegion gameScreenBackground;


    public static void load(GLGame game){
        glGame = game;
        load();
    }
    public static void load(){
        gameScreenBackgroundTexture = new Texture(glGame, "gamescreen/game_screen_background.png");
        gameScreenBackground = new TextureRegion(gameScreenBackgroundTexture, 0, 0, 800, 1280);

    }


    public static void reload(){
        if (gameScreenBackgroundTexture != null) {
            gameScreenBackgroundTexture.reload();
        }
    }

    public static void dispose(){
        if (gameScreenBackgroundTexture != null) {
            gameScreenBackgroundTexture.dispose();
        }
    }

    public static void clear(){
        gameScreenBackgroundTexture = null;
        gameScreenBackground = null;
    }
}
