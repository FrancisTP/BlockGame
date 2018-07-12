package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import android.util.Log;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class AlwaysLoadedAssets{

    protected static GLGame glGame;

    // AlwaysLoaded
    public static Texture SplashScreenScreen;
    public static TextureRegion SplashScreen;

    // Line Texture
    public static Texture collisionLinesTexture;
    public static TextureRegion line;
    public static TextureRegion circle;

    public static void load(GLGame game){
        glGame = game;
        load();
    }
    public static void load(){
        SplashScreenScreen = new Texture(glGame, "SplashScreen.png");
        SplashScreen = new TextureRegion(SplashScreenScreen, 0, 0, 800, 1280);

        // Collision lines
        collisionLinesTexture = new Texture(glGame, "CollisionLines.png");
        line = new TextureRegion(collisionLinesTexture, 519, 2, 2, 2);
        circle = new TextureRegion(collisionLinesTexture, 2, 2, 515, 515);

    }


    public static void reload(){
        if (SplashScreenScreen != null) {
            SplashScreenScreen.reload();
        } else {
            System.out.println("Error reloading SplashScreenScreen");
        }
        if (collisionLinesTexture != null) {
            collisionLinesTexture.reload();
        } else {
            System.out.println("Error reloading collisionLinesTexture");
        }

    }

    public void dispose(){
        if (SplashScreenScreen != null) {
            SplashScreenScreen.dispose();
        }
        if (collisionLinesTexture != null) {
            collisionLinesTexture.dispose();
        }
    }

    public void clear(){
        SplashScreenScreen = null;
        SplashScreen = null;

        // Collision lines
        collisionLinesTexture = null;
        line = null;
        circle = null;
    }
}
