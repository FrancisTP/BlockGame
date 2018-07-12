package com.tp.batman.francis.blockgame.framework;

public class GameObjectLine {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	
	public float x1, y1; // X and Y are relative to the center of the GameObjectShape
	public float x2, y2;
	public float startingX1, startingY1;
	public float startingX2, startingY2;
	
	float angle;
	
	public GameObjectLine(float x1, float y1, float x2, float y2){
		this.x1 = x1;
		this.y1 = y1 - 25;
		
		this.x2 = x2;
		this.y2 = y2 - 25;
		
		startingX1 = this.x1;
		startingX2 = this.x2;
		startingY1 = this.y1;
		startingY2 = this.y2;
		
		this.angle = 0;
	}
	
	public GameObjectLine(float x1, float y1, float x2, float y2, float angle){
		this.x1 = x1;
		this.y1 = y1 - 25;
		
		this.x2 = x2;
		this.y2 = y2 - 25;
		
		startingX1 = this.x1;
		startingX2 = this.x2;
		startingY1 = this.y1;
		startingY2 = this.y2;
		
		this.angle = angle;
	}
	
	public void setPosition(float x1, float y1, float x2, float y2){
		this.x1 = x1;
		this.y1 = y1;
		
		this.x2 = x2;
		this.y2 = y2;
	}
	
	public void add(float x, float y){
		this.x1 += x;
		this.y1 += y;
		
		this.x2 += x;
		this.y2 += y;
	}
	
	public void rotateCenter(float angle){
		this.angle = angle;
		
		float rad = this.angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		this.x1 = startingX1 * cos - startingY1 * sin;
		this.y1 = startingX1 * sin + startingY1 * cos;
		
		this.x2 = startingX2 * cos - startingY2 * sin;
		this.y2 = startingX2 * sin + startingY2 * cos;
	}
	
	// Not going to be used, pretty sure it doesn't even work
	public void rotateAroundPoint(float lineX, float lineY, float angle){
		this.angle = angle;
		
		float rad = (this.angle + 180) * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		this.x1 = (lineX*cos - lineY*sin) + this.x1;
		this.y1 = (lineX*sin + lineY*cos) + this.y1;
		
		this.x2 = (lineX*cos - lineY*sin) + this.x2;
		this.y2 = (lineX*sin + lineY*cos) + this.y2;
		
		rotateCenter(this.angle);
	}
	
	// returns distance from middle point
	public float distance(float x, float y){
		float middleX = (this.x1 + this.x2)/2;
		float middleY = (this.y1 + this.y1)/2;
		
		float distX = middleX - x;
		float distY = middleY - y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}
	
	public float distanceX(float x){
		float middleX = (this.x1 + this.x2)/2;
		float distX = Math.abs(middleX - x);
		return distX;
	}
	
	public float distanceY(float y){
		float middleY = (this.y1 + this.y1)/2;
		float distY = Math.abs(middleY - y);
		return distY;
	}
}
