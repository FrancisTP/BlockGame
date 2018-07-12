package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class MainMenuAssets {

    protected static GLGame glGame;

    public static Texture mainMenuBackgroundTexture;
    public static TextureRegion mainMenuBackground;

    public static Texture titleTexture;
    public static TextureRegion title;

    public static void load(GLGame game){
        glGame = game;
        load();
    }
    public static void load(){
        mainMenuBackgroundTexture = new Texture(glGame, "mainmenu/main_menu_background.png");
        mainMenuBackground = new TextureRegion(mainMenuBackgroundTexture, 0, 0, 800, 1280);

        titleTexture = new Texture(glGame, "mainmenu/title_arcade_font_writer.png");
        title = new TextureRegion(titleTexture, 0, 0, 320, 32);
    }


    public static void reload(){
        if (titleTexture != null) {
            titleTexture.reload();
        }
        if (mainMenuBackgroundTexture != null) {
            mainMenuBackgroundTexture.reload();
        }
    }

    public static void dispose(){
        if (titleTexture != null) {
            titleTexture.dispose();
        }
        if (mainMenuBackgroundTexture != null) {
            mainMenuBackgroundTexture.dispose();
        }
    }

    public static void clear(){
        mainMenuBackgroundTexture = null;
        mainMenuBackground = null;

        titleTexture = null;
        title = null;
    }
}
