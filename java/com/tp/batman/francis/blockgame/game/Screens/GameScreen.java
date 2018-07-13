package com.tp.batman.francis.blockgame.game.Screens;

import android.annotation.SuppressLint;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Input;
import com.tp.batman.francis.blockgame.framework.gl.Camera2D;
import com.tp.batman.francis.blockgame.framework.gl.FPSCounter;
import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.impl.GLScreen;
import com.tp.batman.francis.blockgame.framework.math.OverlapTester;
import com.tp.batman.francis.blockgame.framework.math.Vector2;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

import com.tp.batman.francis.blockgame.game.Assets.Text;
import com.tp.batman.francis.blockgame.game.GameObjects.GameBoard;
import com.tp.batman.francis.blockgame.game.GameObjects.IrregularFactory;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Button;
import com.tp.batman.francis.blockgame.game.Sprites.Sprite;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by franc on 2017-01-18.
 */

public class GameScreen extends GLScreen {


    // ####################
    // ALL
    // ####################
    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    FPSCounter fpsCounter = new FPSCounter();

    public static int state;
    public static final int LOADING_STATE = 0;
    public static final int READY_STATE = 1;
    public static final int RUNNING_STATE = 2;
    public static final int PAUSED_STATE = 3;
    public static final int FINISHED_STATE = 4;
    public static final int SETTING_STATE = 5;

    float stateTime = 3;

    Sprite backgroundSprite;

    // ####################
    // LOADING STATE
    // ####################


    // ####################
    // READY STATE
    // ####################
    private int flashToStartCounter;
    private int flashToStartSpeed;
    private Text touchScreenToStart;
    private Button pressToStartButton;


    // ####################
    // RUNNING STATE
    // ####################
    GameBoard gameBoard;


    // ####################
    // PAUSED STATE
    // ####################
    Button pauseButton;
    PauseMenu pauseMenu;


    // ####################
    // FINISHED STATE
    // ####################


    // ####################
    // SETTING STATE
    // ####################
    SettingMenu settingMenu;


    public GameScreen(Game game) {
        super(game);
        guiCam = new Camera2D(glGraphics, 800, 1280); // Screen resolution 1280x800
        batcher = new SpriteBatcher(glGraphics, 1000); // A maximum of 100 sprite per batch
        touchPoint = new Vector2();

        state = READY_STATE;

        backgroundSprite = new Sprite(Assets.gameScreenAssets.gameScreenBackgroundTexture, Assets.gameScreenAssets.gameScreenBackground, 400, 640, Assets.gameScreenAssets.gameScreenBackground.width, Assets.gameScreenAssets.gameScreenBackground.height);

        pauseButton = new Button(75, 1205, Assets.buttonsAssets.pause_button.width*3, Assets.buttonsAssets.pause_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.pause_button, Assets.buttonsAssets.pause_button_pressed);
        pauseButton.getBounds().setWidth(Assets.buttonsAssets.pause_button.width*4);
        pauseButton.getBounds().setHeight(Assets.buttonsAssets.pause_button.height*4);

        pauseMenu = new PauseMenu(400, 640, 700, 1100);
        settingMenu = new SettingMenu(400, 640, 700, 1100);

        flashToStartCounter = 0;
        flashToStartSpeed = 60;
        touchScreenToStart = new Text("Touch the screen to start the game", 14, "white", "center", 0, 1000, 800, true);
        pressToStartButton = new Button(400, 640, 800, 1280, null, null, null);

        SoundController.requestSong("Chiptronical.ogg");
    }


    // ################################################################################################################################
    // ################################################################################################################################


    @Override
    public void update(float deltaTime){
        SoundController.update();
        List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
        game.getInput().getKeyEvents();

        // listen to user touches
        listenToTouches(touchEvents, deltaTime);


        // update
        if (Assets.readyState) {
            if (state == LOADING_STATE) {
                updateLoadingState(deltaTime);
            } else if(state == READY_STATE) {
                updateReadyState(deltaTime);
            } else if (state == RUNNING_STATE) {
                updateRunningState(deltaTime);
            } else if(state == PAUSED_STATE) {
                updatePausedState(deltaTime);
            } else if (state == FINISHED_STATE) {
                updateFinishedState(deltaTime);
            } else if (state == SETTING_STATE) {
                updateSettingState(deltaTime);
            }
        }

        //time += deltaTime;
        //Log.d("deltaTime", "" + time);
    }

    private void updateLoadingState(float deltaTime) {

    }

    private void updateReadyState(float deltaTime) {
        flashToStartCounter++;

        if (flashToStartCounter > flashToStartSpeed) {
            flashToStartCounter = 0;
        }
    }

    private void updateRunningState(float deltaTime) {
        if (!SoundController.getShouldMusicBePlaying()) {
            SoundController.resumeMusic();
        }
    }

    private void updatePausedState(float deltaTime) {
        pauseMenu.update(deltaTime, 0);
    }

    private void updateFinishedState(float deltaTime) {

    }

    private void updateSettingState(float deltaTime) {
        settingMenu.update(deltaTime);
    }


    // ################################################################################################################################
    // ################################################################################################################################


    public void listenToTouches(List<Input.TouchEvent> touchEvents, float deltaTime){
        if (state == LOADING_STATE) {
            listenToTouchesLoadingState(touchEvents, deltaTime);
        } else if (state == READY_STATE) {
            listenToTouchesReadyState(touchEvents, deltaTime);
        } else if (state == RUNNING_STATE) {
            listenToTouchesRunningState(touchEvents, deltaTime);
        } else if (state == PAUSED_STATE) {
            listenToTouchesPausedState(touchEvents, deltaTime);
        } else if (state == FINISHED_STATE) {
            listenToTouchesFinishedState(touchEvents, deltaTime);
        } else if (state == SETTING_STATE) {
            listenToTouchesSettingState(touchEvents, deltaTime);
        }
    }


    private void listenToTouchesLoadingState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);


            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }


            // subs
        }
    }

    private void listenToTouchesReadyState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if (OverlapTester.pointInRectangle(pressToStartButton.getBounds(), touchPoint)) {
                    state = RUNNING_STATE;
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }


            // subs
        }
    }

    private void listenToTouchesRunningState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                pauseButton.setState(Button.BOUNDS_NOT_TOUCHED);

                if (OverlapTester.pointInRectangle(pauseButton.getBounds(), touchPoint)) {
                    state = PAUSED_STATE;
                }

            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if(OverlapTester.pointInRectangle(pauseButton.getBounds(), touchPoint)) {
                    pauseButton.setState(Button.BOUNDS_TOUCHED);
                }

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if (!OverlapTester.pointInRectangle(pauseButton.getBounds(), touchPoint)) {
                    pauseButton.setState(Button.BOUNDS_NOT_TOUCHED);
                }

            }


            // subs
        }
    }

    private void listenToTouchesPausedState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);


            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }


            // subs
            pauseMenu.listenToTouches(touchEvents, touchPoint, guiCam, game, glGame);
        }
    }

    private void listenToTouchesFinishedState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);


            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }


            // subs
        }
    }

    private void listenToTouchesSettingState(List<Input.TouchEvent> touchEvents, float deltaTime) {
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);


            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

            }


            // subs
            settingMenu.listenToTouches(touchEvents, touchPoint, guiCam, game, glGame);
        }
    }


    // ################################################################################################################################
    // ################################################################################################################################


    @Override
    public void present(float deltaTime) {
        if (state == LOADING_STATE) {
            presentLoading(deltaTime);
        } else if (state == READY_STATE) {
            presentReady(deltaTime);
        } else if(state == RUNNING_STATE)
            presentRunning(deltaTime);
        else if (state == PAUSED_STATE) {
            presentPause(0);
        } else if (state == FINISHED_STATE) {
            presentFinished(0);
        } else if (state == SETTING_STATE) {
            presentSetting(0);
        }
    }

    @SuppressLint("FloatMath")
    public void presentLoading(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here


        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    public void presentReady(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here
        backgroundSprite.render(batcher);

        if (flashToStartCounter > (flashToStartSpeed/3)) {
            touchScreenToStart.render(batcher);
        }

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    public void presentRunning(float deltaTime){
        stateTime += deltaTime; // updates stateTime if we want frame independent movement

        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here
        backgroundSprite.render(batcher);
        pauseButton.render(batcher);


        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);

        // Display fps on LogCat
        //fpsCounter.logFrame();
    }

    @SuppressLint("FloatMath")
    public void presentPause(float deltaTime){
        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here
        backgroundSprite.render(batcher);

        pauseMenu.render(batcher);
        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    public void presentFinished(float deltaTime) {
        // Initiates everything needed to render sprites
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here
        backgroundSprite.render(batcher);

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }

    @SuppressLint("FloatMath")
    public void presentSetting(float deltaTime) {
        GL10 gl = glGraphics.getGL();
        gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
        gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
        guiCam.setViewportAndMatrices();

        gl.glEnable(GL10.GL_TEXTURE_2D);

        gl.glEnable(GL10.GL_BLEND);
        gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);

        // render here
        backgroundSprite.render(batcher);

        settingMenu.render(batcher);

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);
    }


    @SuppressLint("FloatMath")
    public void presentChange(float deltaTime){
        presentRunning(deltaTime);
    }

    @Override
    public void pause(){
    }

    @Override
    public void resume(){
    }

    @Override
    public void dispose(){
    }
}
