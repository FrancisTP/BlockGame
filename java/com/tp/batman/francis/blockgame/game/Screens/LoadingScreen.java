package com.tp.batman.francis.blockgame.game.Screens;

import android.annotation.SuppressLint;
import android.util.Log;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Input.TouchEvent;
import com.tp.batman.francis.blockgame.framework.gl.Camera2D;
import com.tp.batman.francis.blockgame.framework.gl.FPSCounter;
import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.impl.GLScreen;
import com.tp.batman.francis.blockgame.framework.math.Vector2;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.Screens.Levels.BaseLevel;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

/**
 * Created by Francis on 2017-01-11.
 */
public class LoadingScreen extends GLScreen {
    // Variables
    Camera2D guiCam;
    SpriteBatcher batcher;
    Vector2 touchPoint;
    FPSCounter fpsCounter = new FPSCounter();

    int state;
    final int LOADING_STATE = -1;
    final int RUNNING_STATE = 0;
    final int CHANGE_SCREEN_STATE = 1;

    String screenToLoad;

    float stateTime = 3;

    boolean startLoading;

    Object objectToPass = null;


    public LoadingScreen(Game game, String screenToLoad){
        super(game);

        guiCam = new Camera2D(glGraphics, 800, 1280); // Screen resolution 480x800
        batcher = new SpriteBatcher(glGraphics, 1000); // A maximum of 100 sprite per batch
        touchPoint = new Vector2();

        this.screenToLoad = screenToLoad;

        state = RUNNING_STATE;

        startLoading = false;
    }

    public LoadingScreen(Game game, String screenToLoad, Object objectToPass){
        super(game);

        guiCam = new Camera2D(glGraphics, 800, 1280); // Screen resolution 480x800
        batcher = new SpriteBatcher(glGraphics, 1000); // A maximum of 100 sprite per batch
        touchPoint = new Vector2();

        this.screenToLoad = screenToLoad;

        state = RUNNING_STATE;

        startLoading = false;
        this.objectToPass = objectToPass;
    }

    @Override
    public void update(float deltaTime){
        SoundController.update();

        if(Assets.readyState){
            List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
            game.getInput().getKeyEvents();

            if(state == RUNNING_STATE)
                listenToTouches(touchEvents);
        }


        if(startLoading) {
            Assets.readyState = false;
            Assets.clear();
            Assets.onScreen = screenToLoad;
            Assets.load();
            Assets.readyState = true;

            if (screenToLoad.equals("MainMenuScreen")) {
                game.setScreen(new MainMenuScreen(game));
            } else if (screenToLoad.equals("GameScreen")) {
                game.setScreen(new GameScreen(game));
            } else if (screenToLoad.equals("BaseLevel")) {
                game.setScreen(new BaseLevel(game));
            }

        }

        startLoading = true;
        //time += deltaTime;
        //Log.d("deltaTime", "" + time);
    }

    public void listenToTouches(List<TouchEvent> touchEvents) {
        int len = touchEvents.size();
        for (int i = 0; i < len && touchEvents.size() != 0; i++) {
            TouchEvent event = touchEvents.get(i);

            if (event.type == TouchEvent.TOUCH_UP) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
            }
            if (event.type == TouchEvent.TOUCH_DOWN) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
            }
            if (event.type == TouchEvent.TOUCH_DRAGGED) {
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
            }
        }
    }

    @SuppressLint("FloatMath")
    @Override
    public void present(float deltaTime){

        if(state == LOADING_STATE)
            presentLoading(deltaTime);
        else if(state == RUNNING_STATE)
            presentRunning(deltaTime);
        else if(state == CHANGE_SCREEN_STATE)
            presentChange(deltaTime);

    }

    @SuppressLint("FloatMath")
    public void presentLoading(float deltaTime){
        presentRunning(deltaTime);
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

        batcher.beginBatch(Assets.alwaysLoadedAssets.SplashScreenScreen);
        batcher.drawSprite(400, 640, 800, 1280, Assets.alwaysLoadedAssets.SplashScreen);
        batcher.endBatch();

        // Stop rendering
        gl.glDisable(GL10.GL_BLEND);

        // Display fps on LogCat
        //fpsCounter.logFrame();
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

    public void getAssets(){
        Assets.readyState = false;
        Assets.clear();
        Assets.onScreen = screenToLoad;
        Assets.load();
        Assets.readyState = true;

        Log.d("getAssets() worked", "");

    }
}
