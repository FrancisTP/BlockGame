package com.tp.batman.francis.blockgame.framework.math;

// FloatMath might not be faster than Math anymore
public class Vector2 {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	public float x, y;
	public float angle;
	
	public Vector2(){
	}
	
	public Vector2(float x, float y){
		this.x = x;
		this.y = y;
		angle = 0;
	}
	
	public Vector2(float x, float y, float angle){
		this.x = x;
		this.y = y;
		this.angle = angle;
		checkAngle();
	}
	
	public Vector2(Vector2 other){
		this.x = other.x;
		this.y = other.y;
		this.angle = other.angle;
	}
	
	public Vector2 cpy(){
		return new Vector2(x, y);
	}
	
	public Vector2 set(float x, float y){
		this.x = x;
		this.y = y;
		return this;
	}
	
	public Vector2 set(Vector2 other){
		this.x = other.x;
		this.y = other.y;
		return this;
	}
	
	public Vector2 add(float x, float y){
		this.x += x;
		this.y += y;
		return this;
	}
	
	public Vector2 add(Vector2 other){
		this.x += other.x;
		this.y += other.y;
		return this;
	}
	
	public Vector2 sub(float x, float y){
		this.x -= x;
		this.y -=y;
		return this;
	}
	
	public Vector2 sub(Vector2 other){
		this.x -= other.x;
		this.y -= other.y;
		return this;
	}
	
	public Vector2 mul(float scalar){
		this.x *= scalar;
		this.y *= scalar;
		return this;
	}
	
	
	public float len(){
		return (float) Math.sqrt((x*x) + (y*y));
	}
	
	public Vector2 nor(){
		float len = len();
		if(len != 0){
			this.x /= len;
			this.y /= len;
		}
		return this;
	}
	
	public float angle(){
		float angle = (float) Math.atan2(y, x) * TO_DEGREES;
		if(angle < 0)
			angle += 360;
		return angle;
	}
	
	
	public Vector2 rotate(float angle){
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		float newX = this.x * cos - this.y * sin;
		float newY = this.x * sin + this.y * cos;
		
		this.x = newX;
		this.y = newY;
		
		this.angle = angle;
		
		checkAngle();
		
		return this;
	}
	
	
	public Vector2 rotateAroundPoint(float r, float angle){
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		float newX = r*cos;
		float newY = r*sin;
		
		this.x = newX;
		this.y = newY;
		
		this.angle = angle;
		
		checkAngle();
		
		return this;
	}
	
	
	public Vector2 rotateAroundPoint(float x, float y, float r, float angle){
		float rad = angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		float newX = r*cos;
		float newY = r*sin;
		
		this.x = newX + x;
		this.y = newY + y;
		
		this.angle = angle;
		
		checkAngle();
		
		return this;
	}
	
	public Vector2 rotateAroundPoint(float x, float y, float rotationX,  float rotationY, float angle){
		float rad = (angle+180) * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		float newX = rotationX*cos - rotationY*sin;
		float newY = rotationX*sin + rotationY*cos;
		
		this.x = newX + x;
		this.y = newY + y;
		
		this.angle = angle;
		
		checkAngle();
		
		return this;
	}
	
	
	
	public float dist(Vector2 other){
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}
	
	
	public float dist(float x, float y){
		float distX = this.x - x;
		float distY = this.y - y;
		return (float) Math.sqrt(distX * distX + distY * distY);
	}
	
	public float distSquared(Vector2 other){
		float distX = this.x - other.x;
		float distY = this.y - other.y;
		return distX * distX + distY * distY;
	}
	
	public float distSquared(float x, float y){
		float distX = this.x - x;
		float distY = this.y - y;
		return distX * distX + distY * distY;
	}
	
	private void checkAngle(){
		if(angle >= 360){
			angle -= 360;
		}
		if(angle < 0){
			angle += 360;
		}
	}

	public Vector2 scl (float scalar) {
		return this.set(this.x * scalar, this.y * scalar);
	}
}
