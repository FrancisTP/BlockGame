package com.tp.batman.francis.blockgame.game.Screens.OverlayMenues;


import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Input;
import com.tp.batman.francis.blockgame.framework.gl.Camera2D;
import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;
import com.tp.batman.francis.blockgame.framework.math.OverlapTester;
import com.tp.batman.francis.blockgame.framework.math.Vector2;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.Assets.Text;
import com.tp.batman.francis.blockgame.game.Screens.GameScreen;
import com.tp.batman.francis.blockgame.game.Screens.Levels.Base.GameScreenBase;
import com.tp.batman.francis.blockgame.game.Screens.LoadingScreen;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Button;

import java.util.List;

/**
 * Created by franc on 2017-01-27.
 */

public class PauseMenu {

    private float x, y;

    Button resumeButton;
    Button settingButton;
    Button quitButton;

    private Text paused;
    private String scoreString;
    private Text scoreText;

    float width;
    float height;

    public PauseMenu(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        paused = new Text("PAUSED", 14, "white", "center", 50, 1025, width, false);
        scoreText = new Text("Score: 0000000", 10, "white", "center", 237.5f, 640, 325, false);
        scoreString = "";

        resumeButton = new Button(600, 250, Assets.buttonsAssets.play_button.width*3, Assets.buttonsAssets.play_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.play_button, Assets.buttonsAssets.play_button_pressed);
        resumeButton.getBounds().setWidth(Assets.buttonsAssets.play_button.width*4);
        resumeButton.getBounds().setHeight(Assets.buttonsAssets.play_button.height*4);
        settingButton = new Button(400, 250, Assets.buttonsAssets.settings_button.width*3, Assets.buttonsAssets.settings_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.settings_button, Assets.buttonsAssets.settings_button_pressed);
        settingButton.getBounds().setWidth(Assets.buttonsAssets.settings_button.width*4);
        settingButton.getBounds().setHeight(Assets.buttonsAssets.settings_button.height*4);
        quitButton =  new Button(200, 250, Assets.buttonsAssets.no_button.width*3, Assets.buttonsAssets.no_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.no_button, Assets.buttonsAssets.no_button_pressed);
        quitButton.getBounds().setWidth(Assets.buttonsAssets.no_button.width*4);
        quitButton.getBounds().setHeight(Assets.buttonsAssets.no_button.height*4);
    }

    public void update(float deltaTime, int score) {
        if (SoundController.getShouldMusicBePlaying()) {
            SoundController.pauseMusic();
        }
    }

    public void listenToTouches(List<Input.TouchEvent> touchEvents, Vector2 touchPoint, Camera2D guiCam, Game game, GLGame glGame){
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                // If finger is lifted, all buttons are reset
                resumeButton.setState(Button.BOUNDS_NOT_TOUCHED);
                settingButton.setState(Button.BOUNDS_NOT_TOUCHED);
                quitButton.setState(Button.BOUNDS_NOT_TOUCHED);

                if(OverlapTester.pointInRectangle(resumeButton.getBounds(), touchPoint)) {
                    resumeButton.setState(Button.BOUNDS_NOT_TOUCHED);
                    GameScreenBase.state = GameScreenBase.RUNNING_STATE;
                    GameScreen.state = GameScreen.RUNNING_STATE; // to be removed
                }
                if(OverlapTester.pointInRectangle(settingButton.getBounds(), touchPoint)) {
                    settingButton.setState(Button.BOUNDS_NOT_TOUCHED);
                    GameScreenBase.state = GameScreenBase.SETTING_STATE;
                    GameScreen.state = GameScreen.SETTING_STATE; // to be removed
                }
                if(OverlapTester.pointInRectangle(quitButton.getBounds(), touchPoint)) {
                    quitButton.setState(Button.BOUNDS_NOT_TOUCHED);
                    SoundController.stopMusic();
                    SoundController.stopAllSoundEffects();
                    game.setScreen(new LoadingScreen(glGame, "MainMenuScreen"));
                }

            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if(OverlapTester.pointInRectangle(resumeButton.getBounds(), touchPoint)) {
                    resumeButton.setState(Button.BOUNDS_TOUCHED);
                }
                if(OverlapTester.pointInRectangle(settingButton.getBounds(), touchPoint)) {
                    settingButton.setState(Button.BOUNDS_TOUCHED);
                }
                if(OverlapTester.pointInRectangle(quitButton.getBounds(), touchPoint)) {
                    quitButton.setState(Button.BOUNDS_TOUCHED);
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if(!OverlapTester.pointInRectangle(resumeButton.getBounds(), touchPoint)) {
                    resumeButton.setState(Button.BOUNDS_NOT_TOUCHED);
                }
                if(!OverlapTester.pointInRectangle(settingButton.getBounds(), touchPoint)) {
                    settingButton.setState(Button.BOUNDS_NOT_TOUCHED);
                }
                if(!OverlapTester.pointInRectangle(quitButton.getBounds(), touchPoint)) {
                    quitButton.setState(Button.BOUNDS_NOT_TOUCHED);
                }
            }
        }
    }

    public void render(SpriteBatcher batcher) {
        batcher.beginBatch(Assets.menuesAssets.menuesTexture);
        batcher.drawSprite(x, y, width, height, Assets.menuesAssets.menu_frame);
        batcher.endBatch();

        paused.render(batcher);
        scoreText.render(batcher);

        resumeButton.render(batcher);
        settingButton.render(batcher);
        quitButton.render(batcher);

        batcher.endBatch();
    }

}
