package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class MenuesAssets {

    protected static GLGame glGame;

    // Menues
    public static Texture menuesTexture;

    public static TextureRegion menu_frame;
    public static TextureRegion slider_background;
    public static TextureRegion slider_bar;
    public static TextureRegion slider_knob;


    public static void load(GLGame game){
        glGame = game;
        load();
    }

    public static void load(){
        menuesTexture = new Texture(glGame, "interactive/Menues.png");

        menu_frame = new TextureRegion(menuesTexture, 3, 3, 400, 600);
        slider_background = new TextureRegion(menuesTexture, 3, 607, 300, 10);
        slider_bar = new TextureRegion(menuesTexture, 3, 621, 300, 10);
        slider_knob = new TextureRegion(menuesTexture, 307, 607, 10, 10);
    }


    public static void reload(){
        if (menuesTexture != null) {
            menuesTexture.reload();
        }
    }

    public static void dispose(){
        if (menuesTexture != null) {
            menuesTexture.dispose();
        }
    }

    public static void clear(){
        menuesTexture = null;

        menu_frame = null;
        slider_background = null;
        slider_bar = null;
        slider_knob = null;
    }
}
