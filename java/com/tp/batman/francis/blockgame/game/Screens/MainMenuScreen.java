package com.tp.batman.francis.blockgame.game.Screens;

import android.annotation.SuppressLint;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Input;
import com.tp.batman.francis.blockgame.framework.Input.TouchEvent;
import com.tp.batman.francis.blockgame.framework.gl.Camera2D;
import com.tp.batman.francis.blockgame.framework.gl.FPSCounter;
import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.impl.GLScreen;
import com.tp.batman.francis.blockgame.framework.math.OverlapTester;
import com.tp.batman.francis.blockgame.framework.math.Vector2;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.Screens.OverlayMenues.SettingMenu;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;
import com.tp.batman.francis.blockgame.game.Sprites.Button;
import com.tp.batman.francis.blockgame.game.Sprites.Sprite;

import java.util.List;

import javax.microedition.khronos.opengles.GL10;

//import com.tp.batman.francis.blockgame.framework.gl.Animation;
//import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
//import com.tp.batman.francis.blockgame.framework.math.Circle;

public class MainMenuScreen extends GLScreen {
	// Variables 
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

	Sprite backgroundSprite;

	Sprite titleSprite;
	float titleSinX;
	float titleDefaultY;

	float stateTime = 3;

	Button playButton;
	Button settingsButton;

	SettingMenu settingMenu;

	public MainMenuScreen(Game game){
		super(game);

		guiCam = new Camera2D(glGraphics, 800, 1280); // Screen resolution 1280x800
		batcher = new SpriteBatcher(glGraphics, 1000); // A maximum of 100 sprite per batch
		touchPoint = new Vector2();

		state = RUNNING_STATE;

		backgroundSprite = new Sprite(Assets.mainMenuAssets.mainMenuBackgroundTexture, Assets.mainMenuAssets.mainMenuBackground, 400, 640, Assets.mainMenuAssets.mainMenuBackground.width, Assets.mainMenuAssets.mainMenuBackground.height);

		titleDefaultY = 1000;
		titleSprite = new Sprite(Assets.mainMenuAssets.titleTexture, Assets.mainMenuAssets.title, 400, titleDefaultY, Assets.mainMenuAssets.title.width*2,  Assets.mainMenuAssets.title.height*2);

		playButton = new Button(200, 300, Assets.buttonsAssets.play_button.width*3, Assets.buttonsAssets.play_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.play_button, Assets.buttonsAssets.play_button_pressed);
		playButton.getBounds().setWidth(Assets.buttonsAssets.play_button.width*4);
		playButton.getBounds().setHeight(Assets.buttonsAssets.play_button.height*4);

		settingsButton = new Button(600, 300, Assets.buttonsAssets.settings_button.width*3, Assets.buttonsAssets.settings_button.height*3, Assets.buttonsAssets.buttonsTexture, Assets.buttonsAssets.settings_button, Assets.buttonsAssets.settings_button_pressed);
		settingsButton.getBounds().setWidth(Assets.buttonsAssets.settings_button.width*4);
		settingsButton.getBounds().setHeight(Assets.buttonsAssets.settings_button.height*4);

		settingMenu = new SettingMenu(400, 640, 700, 1100);

		SoundController.requestSong("sunset_on_the_beach_amp.mp3");

	}

	@Override
	public void update(float deltaTime){
		SoundController.update();

		if(Assets.readyState){

			if(state == RUNNING_STATE) {
				if (!SoundController.getShouldMusicBePlaying()) {
					SoundController.resumeMusic();
				}

				List<TouchEvent> touchEvents = game.getInput().getTouchEvents();
				game.getInput().getKeyEvents();

				listenToTouches(touchEvents);
			} else if (state == SETTING_STATE) {
				List<Input.TouchEvent> touchEvents = game.getInput().getTouchEvents();
				game.getInput().getKeyEvents();

				settingMenu.update(deltaTime);
				settingMenu.listenToTouches(touchEvents, touchPoint, guiCam, game, glGame);
			}
		}

		// Title sin wave
		titleSinX += 0.05;
		if(titleSinX >= 360)
			titleSinX -= 360;
		titleSprite.setY(titleDefaultY + ((float) Math.sin(titleSinX) * 2.5f));

		//time += deltaTime;
		//Log.d("deltaTime", "" + time);
	}

	public void listenToTouches(List<TouchEvent> touchEvents){
		int len = touchEvents.size();
		for(int i=0; i<len; i++){
			TouchEvent event = touchEvents.get(i);

			if(event.type == TouchEvent.TOUCH_UP){
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				playButton.setState(Button.BOUNDS_NOT_TOUCHED);
				settingsButton.setState(Button.BOUNDS_NOT_TOUCHED);

				if (OverlapTester.pointInRectangle(playButton.getBounds(), touchPoint)) {
					SoundController.stopMusic();
					SoundController.stopAllSoundEffects();
					game.setScreen(new LoadingScreen(glGame, "BaseLevel"));
				}

				if (OverlapTester.pointInRectangle(settingsButton.getBounds(), touchPoint)) {
					state = SETTING_STATE;
				}

			}
			if(event.type == TouchEvent.TOUCH_DOWN){
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (state == RUNNING_STATE) {
					if (OverlapTester.pointInRectangle(playButton.getBounds(), touchPoint)) {
						playButton.setState(Button.BOUNDS_TOUCHED);
					}

					if (OverlapTester.pointInRectangle(settingsButton.getBounds(), touchPoint)) {
						settingsButton.setState(Button.BOUNDS_TOUCHED);
					}
				}

			}
			if(event.type == TouchEvent.TOUCH_DRAGGED){
				touchPoint.set(event.x, event.y);
				guiCam.touchToWorld(touchPoint);

				if (state == RUNNING_STATE) {
					if (!OverlapTester.pointInRectangle(playButton.getBounds(), touchPoint)) {
						playButton.setState(Button.BOUNDS_NOT_TOUCHED);
					}

					if (!OverlapTester.pointInRectangle(settingsButton.getBounds(), touchPoint)) {
						settingsButton.setState(Button.BOUNDS_NOT_TOUCHED);
					}
				}
			}
		}
	}

	@Override
	public void present(float deltaTime){
		if(state == LOADING_STATE) {
			presentLoading(deltaTime);
		} else if(state == RUNNING_STATE) {
			presentRunning(deltaTime);
		} else if (state == SETTING_STATE) {
			presentSetting(0);
		}
	}

	@SuppressLint("FloatMath")
	public void presentLoading(float deltaTime){

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

		backgroundSprite.render(batcher);

		titleSprite.render(batcher);

		playButton.render(batcher);
		settingsButton.render(batcher);

		/*
		// Draw collision lines
		batcher.beginBatch(Assets.collisionLinesTexture);
		noStarZone.drawShape(batcher);
		batcher.endBatch();
		*/

		// Stop rendering
		gl.glDisable(GL10.GL_BLEND);
		
		// Display fps on LogCat
		//fpsCounter.logFrame();
	}

	@SuppressLint("FloatMath")
	public void presentSetting(float deltaTime){
		stateTime += deltaTime; // updates stateTime if we want frame independent movement

		// Initiates everything needed to render sprites
		GL10 gl = glGraphics.getGL();
		gl.glClearColor(1.0f, 1.0f, 1.0f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT);
		guiCam.setViewportAndMatrices();

		gl.glEnable(GL10.GL_TEXTURE_2D);

		gl.glEnable(GL10.GL_BLEND);
		gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);


		backgroundSprite.render(batcher);

		settingMenu.render(batcher);


		/*
		// Draw collision lines
		batcher.beginBatch(Assets.collisionLinesTexture);
		noStarZone.drawShape(batcher);
		batcher.endBatch();
		*/

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
}
