package com.tp.batman.francis.blockgame.game.Main;

import com.tp.batman.francis.blockgame.framework.Screen;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;
import com.tp.batman.francis.blockgame.game.Assets.Assets;
import com.tp.batman.francis.blockgame.game.Screens.LoadingScreen;
import com.tp.batman.francis.blockgame.game.Settings.Saves;
import com.tp.batman.francis.blockgame.game.Settings.SoundController;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class BlockGame extends GLGame {

        public boolean firstTimeCreate = true;

        public Screen getStartScreen(){
            return new LoadingScreen(this, "MainMenuScreen");
        }

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config){
            super.onSurfaceCreated(gl, config);
            if(firstTimeCreate){
                Saves.load(this);
                Assets.load(this);
                SoundController.load(this);

                firstTimeCreate = false;
                Assets.readyState = true;
            } else {
                Assets.reload();
                System.out.println("only reloaded");
            }
        }

        @Override
        public void onPause(){
            SoundController.pauseMusicAppClosed();
            SoundController.pauseAllSoundEffects();
            super.onPause();
        }

        @Override
        public void onResume() {
            SoundController.resumeMusicAppOpened();
            SoundController.resumeAllSoundEffects();
            super.onResume();
        }

        @Override
        public void onBackPressed(){
            // Je suis un baller
        }

}
