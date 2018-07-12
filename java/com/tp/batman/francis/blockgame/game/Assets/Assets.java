package com.tp.batman.francis.blockgame.game.Assets;

/**
 * Created by Francis on 2017-01-11.
 */

import com.tp.batman.francis.blockgame.framework.impl.GLGame;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.AlwaysLoadedAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.BlocksAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.ButtonsAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.GameScreenAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.MainMenuAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.MenuesAssets;
import com.tp.batman.francis.blockgame.game.Assets.Seperate.TextAssets;

public class Assets {

    protected static GLGame glGame;

    public static String onScreen = "";


    // Ready state
    public static boolean readyState;


    // Assets
    public static AlwaysLoadedAssets alwaysLoadedAssets = new AlwaysLoadedAssets();
    public static TextAssets textAssets = new TextAssets();
    public static MenuesAssets menuesAssets = new MenuesAssets();


    public static MainMenuAssets mainMenuAssets = new MainMenuAssets();
    public static GameScreenAssets gameScreenAssets = new GameScreenAssets();


    public static BlocksAssets blocksAssets = new BlocksAssets();
    public static ButtonsAssets buttonsAssets = new ButtonsAssets();


    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////


    public static void load(GLGame game){
        glGame = game;

        loadAlwaysLoaded(glGame);
    }
    public static void load(){
        loadAlwaysLoaded(glGame);

        if(onScreen.equals("MainMenuScreen")) {
            loadMainMenu(glGame);
        } else if (onScreen.equals("GameScreen")) {
            loadGameScreen(glGame);
        }
    }


    public static void reload(){
        reloadAlwaysLoaded();

        if(onScreen.equals("MainMenuScreen")) {
            reloadMainMenu();
        } else if (onScreen.equals("GameScreen")) {
            reloadGameScreen();
        }
    }

    public static void dispose(){
        disposeAlwaysLoaded();

        if(onScreen.equals("MainMenuScreen")) {
            disposeMainMenu();
        } else if (onScreen.equals("GameScreen")) {
            disposeGameScreen();
        }
    }

    public static void clear(){
        clearAlwaysLoaded();

        if(onScreen.equals("MainMenuScreen")) {
            clearMainMenu();
        } else if (onScreen.equals("GameScreen")) {
            clearGameScreen();
        }
    }

    ////////////////////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////// ////////////


    public static void loadAlwaysLoaded(GLGame game){
        alwaysLoadedAssets.load(game);
        textAssets.load(game);
    }
    public static void reloadAlwaysLoaded(){
        alwaysLoadedAssets.reload();
        textAssets.reload();
    }
    public static void disposeAlwaysLoaded(){
        alwaysLoadedAssets.dispose();
        textAssets.dispose();
    }
    public static void clearAlwaysLoaded(){
        alwaysLoadedAssets.clear();
        textAssets.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////


    public static void loadMainMenu(GLGame game){
        mainMenuAssets.load(game);
        menuesAssets.load(game);
        blocksAssets.load(game);
        buttonsAssets.load(game);
    }
    public static void reloadMainMenu(){
        mainMenuAssets.reload();
        menuesAssets.reload();
        blocksAssets.reload();
        buttonsAssets.reload();
    }
    public static void disposeMainMenu(){
        mainMenuAssets.dispose();
        menuesAssets.dispose();
        blocksAssets.dispose();
        buttonsAssets.dispose();
    }
    public static void clearMainMenu(){
        mainMenuAssets.clear();
        menuesAssets.clear();
        blocksAssets.clear();
        buttonsAssets.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////


    public static void loadGameScreen(GLGame game){
        gameScreenAssets.load(game);
        menuesAssets.load(game);
        blocksAssets.load(game);
        buttonsAssets.load(game);
    }
    public static void reloadGameScreen(){
        gameScreenAssets.reload();
        menuesAssets.reload();
        blocksAssets.reload();
        buttonsAssets.reload();
    }
    public static void disposeGameScreen(){
        gameScreenAssets.dispose();
        menuesAssets.dispose();
        blocksAssets.dispose();
        buttonsAssets.dispose();
    }
    public static void clearGameScreen(){
        gameScreenAssets.clear();
        menuesAssets.clear();
        blocksAssets.clear();
        buttonsAssets.clear();
    }

    ////////////////////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////
}