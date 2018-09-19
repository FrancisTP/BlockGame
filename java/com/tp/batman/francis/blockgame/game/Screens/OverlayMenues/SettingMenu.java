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
import com.tp.batman.francis.blockgame.game.Screens.MainMenuScreen;
import com.tp.batman.francis.blockgame.game.Screens.MenueObjects.Slider;
import com.tp.batman.francis.blockgame.game.Settings.Saves;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Button;

import java.util.List;

/**
 * Created by Francis on 2017-05-01.
 */
public class SettingMenu {

    private float x, y;
    Button backButton;

    private Text settings;
    private Text music, sound;

    Slider musicSlider, soundEffectSlider;
    boolean playSoundEffectSetting;


    float width;
    float height;

    public SettingMenu(float x, float y, float width, float height) {
        this.x = x;
        this.y = y;

        this.width = width;
        this.height = height;

        settings = new Text("SETTINGS", 14, "white", "center", 50, 1025, width, false);
        music = new Text("MUSIC:", 10, "white", "left", 120, 746, width, false);
        sound = new Text("SOUND:", 10, "white", "left", 120, 534, width, false);

        backButton = new Button(400, 250, Assets.buttonsAssets.previous_button.width*3, Assets.buttonsAssets.previous_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.previous_button, Assets.buttonsAssets.previous_button_pressed);
        backButton.getBounds().setWidth(Assets.buttonsAssets.previous_button.width*4);
        backButton.getBounds().setHeight(Assets.buttonsAssets.previous_button.height*4);

        // float x, float y, float min, float max, float value
        musicSlider = new Slider(500, 763, SoundController.musicVolume);
        soundEffectSlider = new Slider(500, 553, SoundController.soundEffectVolume);
        boolean playSoundEffectSetting = false;
    }

    public void update(float deltaTime) {
        musicSlider.update(deltaTime);
        soundEffectSlider.update(deltaTime);

        if (musicSlider.getPressedDown()) {
                SoundController.resumeMusic();
                SoundController.setMusicVolume(musicSlider.getValue());
        } else {
            if (SoundController.getShouldMusicBePlaying()) {
                SoundController.pauseMusic();
            }
        }
        if (soundEffectSlider.getPressedDown()) {
            playSoundEffectSetting = true;
            SoundController.setSoundEffectVolume(soundEffectSlider.getValue());
        } else {
            if (playSoundEffectSetting) {
                playSoundEffectSetting = false;
                SoundController.playSettingSound();
            }
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
                backButton.setState(Button.BOUNDS_NOT_TOUCHED);

                if(OverlapTester.pointInRectangle(backButton.getBounds(), touchPoint)) {
                    Saves.saveMusicVolume();
                    Saves.saveSoundEffectVolume();
                    backButton.setState(Button.BOUNDS_NOT_TOUCHED);
                    if (Assets.onScreen == "MainMenuScreen") {
                        MainMenuScreen.state = MainMenuScreen.RUNNING_STATE;
                    } else if (Assets.onScreen == "GameScreen") {
                        GameScreen.state = GameScreen.PAUSED_STATE;
                    } else if (Assets.onScreen == "BaseLevel") {
                        GameScreenBase.state = GameScreenBase.PAUSED_STATE;
                    }
                }

            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if(OverlapTester.pointInRectangle(backButton.getBounds(), touchPoint)) {
                    backButton.setState(Button.BOUNDS_TOUCHED);
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);

                if(!OverlapTester.pointInRectangle(backButton.getBounds(), touchPoint)) {
                    backButton.setState(Button.BOUNDS_NOT_TOUCHED);
                }
            }

            // sliders
            musicSlider.listenToTouches(touchEvents, touchPoint, guiCam, game, glGame);
            soundEffectSlider.listenToTouches(touchEvents, touchPoint, guiCam, game, glGame);
        }
    }

    public void render(SpriteBatcher batcher) {
        batcher.beginBatch(Assets.menuesAssets.menuesTexture);
        batcher.drawSprite(x, y, width, height, Assets.menuesAssets.menu_frame);
        batcher.endBatch();
        musicSlider.render(batcher);
        soundEffectSlider.render(batcher);

        settings.render(batcher);
        music.render(batcher);
        sound.render(batcher);

        backButton.render(batcher);
    }
}
