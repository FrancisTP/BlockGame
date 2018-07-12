package com.tp.batman.francis.blockgame.framework;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

public class GameObjectCircle {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	
	public float x, y;
	public float radius;
	public float angle;
	
	public GameObjectCircle(float x, float y, float radius){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = 0;
	}
	
	public GameObjectCircle(float x, float y, float radius, float angle){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
	}
	
	public GameObjectCircle(float x, float y, float radius, float circleX, float circleY, float angle){
		this.x = x;
		this.y = y;
		this.radius = radius;
		this.angle = angle;
		
		rotateAroundPoint(circleX, circleY, this.angle);
	}
	
	public void setPosition(float x, float y){
		this.x = x;
		this.y = y;
	}
	
	public void add(float x, float y){
		this.x += x;
		this.y += y;
	}
	
	public void rotateCenter(float angle){
		this.angle = angle;
	}
	
	public void rotateAroundPoint(float circleX, float circleY, float angle){
		this.angle = angle;
		
		float rad = (this.angle + 180) * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		this.x = (circleX*cos - circleY*sin) + this.x;
		this.y = (circleX*sin + circleY*cos) + this.y;
	}
	
	public void rotateAroundPoint(float shapeX, float shapeY, float r, float angle){
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		this.angle = 360 - angle;
		
		float newX = r*sin;
		float newY = r*cos;
		
		this.x = newX + shapeX;
		this.y = newY + shapeY;
		
		//rotateCenter(this.angle);
	}
	
	public float distance(float x, float y){
		float distX = this.x - x;
		float distY = this.y - y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}
	
	public float distanceX(float x){
		float distX = Math.abs(this.x - x);
		return distX;
	}
	
	public float distanceY(float y){
		float distY = Math.abs(this.y - y);
		return distY;
	}
	
	public void drawShape(SpriteBatcher batcher){
		batcher.drawSprite(x, y, radius * 2, radius * 2, Assets.alwaysLoadedAssets.circle);
	}
	
	public void drawBoundingBox(SpriteBatcher batcher){
		GameObjectRectangle a = new GameObjectRectangle(x, y, radius*2, radius*2);
		a.drawBoundingBox(batcher);
	}
}
