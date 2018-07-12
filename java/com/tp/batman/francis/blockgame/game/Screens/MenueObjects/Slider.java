package com.tp.batman.francis.blockgame.game.Screens.MenueObjects;

import com.tp.batman.francis.blockgame.framework.Game;
import com.tp.batman.francis.blockgame.framework.Input;
import com.tp.batman.francis.blockgame.framework.gl.Camera2D;
import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.framework.impl.GLGame;
import com.tp.batman.francis.blockgame.framework.math.OverlapTester;
import com.tp.batman.francis.blockgame.framework.math.Rectangle;
import com.tp.batman.francis.blockgame.framework.math.Vector2;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

import java.util.List;

/**
 * Created by Francis on 2017-05-03.
 */
public class Slider {

    private float x, y;
    private float knobX;
    private float sliderBarWidth;
    private float valueWidth;
    private float value;
    private TextureRegion sliderBackground, sliderBar, sliderKnob;
    private boolean pressedDown;
    private float fingerXPosition;


    Rectangle knobBounds;

    public Slider(float x, float y, float value) {
        this.x = x;
        this.y = y;

        this.value = value;

        sliderBackground = Assets.menuesAssets.slider_background;
        sliderBar = Assets.menuesAssets.slider_bar;
        sliderKnob = Assets.menuesAssets.slider_knob;

        knobBounds = new Rectangle(x, y, sliderBar.width*6, sliderBar.height*5);

        valueWidth = (value*sliderBar.width);
        sliderBarWidth = valueWidth;
        knobX = x - (sliderBar.width/2) + sliderBarWidth;
        fingerXPosition = knobX;

        pressedDown = false;
    }

    public boolean getPressedDown() {
        return pressedDown;
    }

    public float getValue() {
        return value;
    }

    public void update(float deltaTime) {
        if (pressedDown) {
            valueWidth = fingerXPosition;
            if (valueWidth < 0) {
                valueWidth = 0;
            } else if (valueWidth > sliderBar.width) {
                valueWidth = sliderBar.width;
            }
            sliderBarWidth = valueWidth;
            knobX = x - (sliderBar.width/2) + sliderBarWidth;
            value = sliderBarWidth / sliderBar.width;
        }
    }

    public void listenToTouches(List<Input.TouchEvent> touchEvents, Vector2 touchPoint, Camera2D guiCam, Game game, GLGame glGame){
        int len = touchEvents.size();
        for(int i=0; i<len; i++){
            Input.TouchEvent event = touchEvents.get(i);

            if(event.type == Input.TouchEvent.TOUCH_UP){
                touchPoint.set(event.x, event.y);
                guiCam.touchToWorld(touchPoint);
                pressedDown = false;
            }
            if(event.type == Input.TouchEvent.TOUCH_DOWN){
                if(OverlapTester.pointInRectangle(knobBounds, touchPoint)) {
                    touchPoint.set(event.x, event.y);
                    guiCam.touchToWorld(touchPoint);
                    pressedDown = true;
                    fingerXPosition = touchPoint.x - (x - (sliderBar.width/2));
                }
            }
            if(event.type == Input.TouchEvent.TOUCH_DRAGGED){
                if (pressedDown) {
                    touchPoint.set(event.x, event.y);
                    guiCam.touchToWorld(touchPoint);
                    fingerXPosition = touchPoint.x - (x - (sliderBar.width/2));
                }
            }
        }
    }

    public void render(SpriteBatcher batcher) {
        batcher.beginBatch(Assets.menuesAssets.menuesTexture);
        batcher.beginBatch(Assets.menuesAssets.menuesTexture);
        batcher.drawSprite(x, y, sliderBackground.width, sliderBackground.height*2, sliderBackground);
        batcher.drawSprite(x - (sliderBar.width/2) + (sliderBarWidth/2), y, sliderBarWidth, sliderBar.height*2, sliderBar);
        batcher.drawSprite(knobX, y, sliderKnob.width*2, sliderKnob.height*2, sliderKnob);
        batcher.endBatch();
    }




}
