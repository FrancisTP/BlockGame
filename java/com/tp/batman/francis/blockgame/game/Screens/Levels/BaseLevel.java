package com.tp.batman.francis.blockgame.game.Screens.Levels;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.GameObjects.GameBoard;
import com.tp.batman.francis.blockgame.game.GameObjects.GameBoard_old;
import com.tp.batman.francis.blockgame.game.GameObjects.ShapeController;
import com.tp.batman.francis.blockgame.game.Screens.Levels.Base.GameScreenBase;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Sprite;

import java.util.Random;

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
    ShapeController shapeController;

    protected int generateShapeCounter;
    protected int generateShapeSpeed;

    // ===================================================================
    // ===================================================================


    public BaseLevel(Game game) {
        super(game);

        backgroundSprite = new Sprite(Assets.gameScreenAssets.gameScreenBackgroundTexture, Assets.gameScreenAssets.gameScreenBackground, 400, 640, Assets.gameScreenAssets.gameScreenBackground.width, Assets.gameScreenAssets.gameScreenBackground.height);

        // READY STATE
        gameBoard = new GameBoard();
        shapeController = new ShapeController(gameBoard);
        gameBoard.setShapeController(shapeController);

        generateShapeCounter = 0;
        generateShapeSpeed = 0;

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

    @Override
    protected void updateRunningState(float deltaTime) {
        updateRunningStateBase(deltaTime);

        Log.d("UPDATING GAME BOARD", "WHAT");
        gameBoard.update();

        generateShapeCounter++;
        if (generateShapeCounter > generateShapeSpeed) {
            if (shapeController.getShapes().size() < gameBoard.MAX_SHAPE_COUNT) {
                Random r = new Random();
                int createBlockNum = 0 + r.nextInt((3 - 0) + 1);
                boolean createBlock = false;
                if (createBlockNum == 3) {
                    createBlock = true;
                }

                if (createBlock) {
                    Log.d("CREATING NEW SHAPE", "WHAT");
                    shapeController.createNewShape();
                }
            }
            generateShapeCounter = 0;
        }

        Log.d("UPDATING SHAPES", "WHAT");
        shapeController.update(deltaTime);

        Log.d("CHECKING AND SETTING COLLISIONS FOR SHAPES", "WHAT");
        gameBoard.checkAndSetCollisions();
    }

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
        presentLoadingBase();

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
        presentReadyBase();

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

        gameBoard.render(batcher);
        shapeController.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentRunningBase();


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
        presentPauseBase();

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
        presentFinishedBase();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    @Override
    public void presentSetting(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // render here
        backgroundSprite.render(batcher);

        // This contains all the boring things (buttons, text, etc)
        presentSettingBase();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }


    @SuppressLint("FloatMath")
    @Override
    public void presentChange(float deltaTime){
        GL10 gl = glGraphics.getGL();
        setupGl(gl);

        // This contains all the boring things (buttons, text, etc)
        presentChangeBase();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    // ===================================================================
    // ===================================================================
}
