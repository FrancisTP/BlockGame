package com.tp.batman.francis.blockgame.game.Screens.Levels;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.GameObjects.GameBoard;
import com.tp.batman.francis.blockgame.game.Screens.Levels.Base.GameScreenBase;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Sprite;

import javax.microedition.khronos.opengles.GL10;

public class BaseLevel extends GameScreenBase {

    // ###################################################################
    // VARIABLES
    // ###################################################################

    // ####################
    // ALL
    // ####################
    Sprite backgroundSprite;

    // ####################
    // RUNNING STATE
    // ####################
    GameBoard gameBoard;


    // ===================================================================
    // ===================================================================


    public BaseLevel(Game game) {
        super(game);

        backgroundSprite = new Sprite(Assets.gameScreenAssets.gameScreenBackgroundTexture, Assets.gameScreenAssets.gameScreenBackground, 400, 640, Assets.gameScreenAssets.gameScreenBackground.width, Assets.gameScreenAssets.gameScreenBackground.height);

        // READY STATE
        gameBoard = new GameBoard();

        SoundController.requestSong("Chiptronical.ogg");
    }





    // ###################################################################
    // LISTENING TO TOUCHES
    // ###################################################################



    // ===================================================================
    // ===================================================================




    // ###################################################################
    // UPDATING
    // ###################################################################



    // ===================================================================
    // ===================================================================


    // ###################################################################
    // PRESENTING
    // ###################################################################

    @SuppressLint("FloatMath")
    @Override
    public void presentLoading(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // This contains all the boring things (buttons, text, etc)
        presentLoadingInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentReady(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentReadyInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentRunning(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentRunningInitialized();


        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);

        // Display fps on LogCat
        //fpsCounter.logFrame();
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentPause(float deltaTime){
        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentPauseInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentFinished(float deltaTime) {
        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentFinishedInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentSetting(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        Log.d("This is the presentSetting", " method");

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentSettingInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }


    @SuppressLint("FloatMath")
    @Override
    public void presentChange(float deltaTime){
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // This contains all the boring things (buttons, text, etc)
        presentChangeInitialized();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    // ===================================================================
    // ===================================================================
}
