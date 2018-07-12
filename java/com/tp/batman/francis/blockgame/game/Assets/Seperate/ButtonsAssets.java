package com.tp.batman.francis.blockgame.game.Assets.Seperate;

import com.tp.batman.francis.blockgame.framework.gl.Texture;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;

public class ButtonsAssets {

    protected static GLGame glGame;

    public static Texture buttonsTexture;

    public static TextureRegion home_button;
    public static TextureRegion home_button_pressed;
    public static TextureRegion menu_button;
    public static TextureRegion menu_button_pressed;
    public static TextureRegion minus_button;
    public static TextureRegion minus_button_pressed;
    public static TextureRegion next_button;
    public static TextureRegion next_button_pressed;
    public static TextureRegion no_button;
    public static TextureRegion no_button_pressed;
    public static TextureRegion no_sound_button;
    public static TextureRegion no_sound_button_pressed;
    public static TextureRegion ok_button;
    public static TextureRegion ok_button_pressed;
    public static TextureRegion pause_button;
    public static TextureRegion pause_button_pressed;
    public static TextureRegion play_button;
    public static TextureRegion play_button_pressed;
    public static TextureRegion plus_button;
    public static TextureRegion plus_button_pressed;
    public static TextureRegion previous_button;
    public static TextureRegion previous_button_pressed;
    public static TextureRegion replay_button;
    public static TextureRegion replay_button_pressed;
    public static TextureRegion right_arrow_button;
    public static TextureRegion right_arrow_button_pressed;
    public static TextureRegion settings_button;
    public static TextureRegion settings_button_pressed;
    public static TextureRegion sound_button;
    public static TextureRegion sound_button_pressed;

    public static void load(GLGame game) {
        buttonsTexture = new Texture(game, "interactive/Buttons.png");
        load();
    }

    public static void load() {
        home_button = new TextureRegion(buttonsTexture, 3, 3, 46, 43);
        home_button_pressed = new TextureRegion(buttonsTexture, 53, 3, 46, 43);
        menu_button = new TextureRegion(buttonsTexture, 103, 3, 46, 43);
        menu_button_pressed = new TextureRegion(buttonsTexture, 153, 3, 46, 43);
        minus_button = new TextureRegion(buttonsTexture, 203, 3, 46, 43);
        minus_button_pressed = new TextureRegion(buttonsTexture, 3, 50, 46, 43);
        next_button = new TextureRegion(buttonsTexture, 53, 50, 46, 43);
        next_button_pressed = new TextureRegion(buttonsTexture, 103, 50, 46, 43);
        no_button = new TextureRegion(buttonsTexture, 153, 50, 46, 43);
        no_button_pressed = new TextureRegion(buttonsTexture, 203, 50, 46, 43);
        no_sound_button = new TextureRegion(buttonsTexture, 3, 97, 46, 43);
        no_sound_button_pressed = new TextureRegion(buttonsTexture, 53, 97, 46, 43);
        ok_button = new TextureRegion(buttonsTexture, 103, 97, 46, 43);
        ok_button_pressed = new TextureRegion(buttonsTexture, 153, 97, 46, 43);
        pause_button = new TextureRegion(buttonsTexture, 203, 97, 46, 43);
        pause_button_pressed = new TextureRegion(buttonsTexture, 3, 144, 46, 43);
        play_button = new TextureRegion(buttonsTexture, 53, 144, 46, 43);
        play_button_pressed = new TextureRegion(buttonsTexture, 103, 144, 46, 43);
        plus_button = new TextureRegion(buttonsTexture, 153, 144, 46, 43);
        plus_button_pressed = new TextureRegion(buttonsTexture, 203, 144, 46, 43);
        previous_button = new TextureRegion(buttonsTexture,  3, 191, 46, 43);
        previous_button_pressed = new TextureRegion(buttonsTexture, 53, 191, 46, 43);
        replay_button = new TextureRegion(buttonsTexture, 103, 191, 46, 43);
        replay_button_pressed = new TextureRegion(buttonsTexture, 153, 191, 46, 43);
        right_arrow_button = new TextureRegion(buttonsTexture, 203, 191, 46, 43);
        right_arrow_button_pressed = new TextureRegion(buttonsTexture, 3, 238, 46, 43);
        settings_button = new TextureRegion(buttonsTexture, 53, 238, 46, 43);
        settings_button_pressed = new TextureRegion(buttonsTexture, 103, 238, 46, 43);
        sound_button = new TextureRegion(buttonsTexture, 153, 238, 46, 43);
        sound_button_pressed = new TextureRegion(buttonsTexture, 203, 238, 46, 43);
    }

    public static void reload() {
        if (buttonsTexture != null) {
            buttonsTexture.reload();
        }
    }

    public static void dispose() {
        if (buttonsTexture != null) {
            buttonsTexture.dispose();
        }
    }

    public static void clear() {

        buttonsTexture = null;
        home_button = null;
        home_button_pressed = null;
        menu_button = null;
        menu_button_pressed = null;
        minus_button = null;
        minus_button_pressed = null;
        next_button = null;
        next_button_pressed = null;
        no_button = null;
        no_button_pressed = null;
        no_sound_button = null;
        no_sound_button_pressed = null;
        ok_button = null;
        ok_button_pressed = null;
        pause_button = null;
        pause_button_pressed = null;
        play_button = null;
        play_button_pressed = null;
        plus_button = null;
        plus_button_pressed = null;
        previous_button = null;
        previous_button_pressed = null;
        replay_button = null;
        replay_button_pressed = null;
        right_arrow_button = null;
        right_arrow_button_pressed = null;
        settings_button = null;
        settings_button_pressed = null;
        sound_button = null;
        sound_button_pressed = null;
    }
}
