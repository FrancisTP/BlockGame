package com.tp.batman.francis.blockgame.framework.math;

public class Rectangle {
	public Vector2 lowerLeft;
	public float width, height;
	public float x, y;
	
	public Rectangle(float x, float y, float width, float height){
		this.lowerLeft = new Vector2(x - width/2, y - height/2);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	public Vector2 getLowerLeft() {
		return lowerLeft;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		this.width = width;
		this.lowerLeft = new Vector2(x - width/2, y - height/2);
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
		this.lowerLeft = new Vector2(x - width/2, y - height/2);
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
}
