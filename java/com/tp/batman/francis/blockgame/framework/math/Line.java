package com.tp.batman.francis.blockgame.framework.math;

public class Line {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;

	public float angle;

	public float x1, x2;
	public float y1, y2;

	public Line(float x1, float y1, float x2, float y2) {
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}
	
	public void updateCoordinate(float x1, float y1, float x2, float y2){
		this.x1 = x1;
		this.x2 = x2;
		this.y1 = y1;
		this.y2 = y2;
	}

	public void rotate(float angle) {
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);

		float newX1 = this.x1 * cos - this.y1 * sin;
		float newX2 = this.x2 * cos - this.y2 * sin;

		float newY1 = this.x1 * sin + this.y1 * cos;
		float newY2 = this.x2 * sin + this.y2 * cos;

		this.x1 = newX1;
		this.x2 = newX2;
		this.y1 = newY1;
		this.y2 = newY2;
	}
}