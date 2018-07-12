package com.tp.batman.francis.blockgame.framework;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

;


//import com.androidgames.framework.math.Vector2;

public class GameObjectShape {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	
	public float x, y;
	public float width, height;
	public float angle;
	public GameObjectLine[] lines;
	
	public GameObjectShape(float x, float y, float width, float height, GameObjectLine[] lines){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lines = lines;
		this.angle = 0;
	}
	
	public GameObjectShape(float x, float y, float width, float height, GameObjectLine[] lines, float angle){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lines = lines;
		this.angle = angle;
		
		rotateCenter(this.angle);
	}
	
	public GameObjectShape(float x, float y, float width, float height, GameObjectLine[] lines, float shapeX, float shapeY, float angle){
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.lines = lines;
		this.angle = angle;
		
		rotateAroundPoint(shapeX, shapeY, this.angle);
	}
	
	public void setPosition(float x, float y){
		float differenceX = x - this.x;
		float differenceY = y - this.y;
		
		this.x = x;
		this.y = y;
		
		for(int i=0; i<this.lines.length; i++)
			lines[i].add(differenceX, differenceY);
	}
	
	public void add(float x, float y){
		this.x += x;
		this.y += y;
		
		for(int i=0; i<this.lines.length; i++)
			lines[i].add(x, y);
	}
	
	public void rotateCenter(float angle){
		this.angle = angle;
		
		for(int i=0; i<this.lines.length; i++)
			lines[i].rotateCenter(this.angle);
	}
	
	public void rotateAroundPoint(float shapeX, float shapeY, float angle){
		this.angle = angle;
		
		float rad = (this.angle + 180) * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		//******************************************
		// New option
		float r = (float) Math.sqrt(((float)(Math.pow((this.x - shapeX), 2))) + ((float)(Math.pow((this.y - shapeY), 2))));
		
		float newX = r*cos;
		float newY = r*sin;
		
		this.x = newX + shapeX;
		this.y = newY + shapeY;
		
		//******************************************
		
		//this.x = (shapeX*cos - shapeY*sin) + this.x;
		//this.y = (shapeX*sin + shapeY*cos) + this.y;
		
		rotateCenter(this.angle);
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
		
		rotateCenter(this.angle);
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
		float ang = 0;
		for(int i=0; i < lines.length; i++){ 
			float length = (float) Math.sqrt(((lines[i].x2 - lines[i].x1) * (lines[i].x2 - lines[i].x1)) + ((lines[i].y2 - lines[i].y1) * (lines[i].y2 - lines[i].y1)));;
			if(lines[i].x1 > lines[i].x2 && lines[i].y1 > lines[i].y2){
				float lengthSide = lines[i].y1 - lines[i].y2;
				ang = (float)(Math.asin(lengthSide/length));
				ang = (float)(ang * 180 / Math.PI);
				
			}
			else if(lines[i].x1 < lines[i].x2 && lines[i].y1 > lines[i].y2){
				float lengthSide = lines[i].y1 - lines[i].y2;
				ang = (float)(Math.asin(lengthSide/length));
				ang = 180 - (float)(ang * 180 / Math.PI);
				
			}
			else if(lines[i].x1 > lines[i].x2 && lines[i].y1 < lines[i].y2){
				float lengthSide = lines[i].y2 - lines[i].y1;
				ang = (float)(Math.asin(lengthSide/length));
				ang = 180 - (float)(ang * 180 / Math.PI);
				
			}
			else if(lines[i].x1 < lines[i].x2 && lines[i].y1 < lines[i].y2){
				float lengthSide = lines[i].y2 - lines[i].y1;
				ang = (float)(Math.asin(lengthSide/length));
				ang = (float)(ang * 180 / Math.PI);
				
			} 
			else if(lines[i].x1 == lines[i].x2){
				ang = 90;
			}
			else if( lines[i].y1 == lines[i].y2){
				ang = 0;
			}
		
			batcher.drawSprite(((lines[i].x1 + lines[i].x2) / 2) + x, ((lines[i].y1 + lines[i].y2) / 2) + y, length, 2, ang, Assets.alwaysLoadedAssets.line);
		}
	}
	
	public void drawBoundingBox(SpriteBatcher batcher){
		float widthOne, heightOne; 
		
		float minXOne, minYOne, maxXOne, maxYOne;
		
		minXOne = lines[0].x1;
		maxXOne = lines[0].x1;
		minYOne = lines[0].y1;
		maxYOne = lines[0].y1;
		
		for(int i=0; i<lines.length; i++){
			if(lines[i].x1 < minXOne)
				minXOne = lines[i].x1;
			if(lines[i].x2 < minXOne)
				minXOne = lines[i].x2;
			
			if(lines[i].x1 > maxXOne)
				maxXOne = lines[i].x1;
			if(lines[i].x2 > maxXOne)
				maxXOne = lines[i].x2;
			
			if(lines[i].y1 < minYOne)
				minYOne = lines[i].y1;
			if(lines[i].y2 < minYOne)
				minYOne = lines[i].y2;
			
			if(lines[i].y1 > maxYOne)
				maxYOne = lines[i].y1;
			if(lines[i].y2 > maxYOne)
				maxYOne = lines[i].y2;
		}
		
		widthOne = maxXOne - minXOne;
		heightOne = maxYOne - minYOne;
		
		GameObjectRectangle a = new GameObjectRectangle(x, y, widthOne, heightOne);
		a.drawBoundingBox(batcher);
	}
}
