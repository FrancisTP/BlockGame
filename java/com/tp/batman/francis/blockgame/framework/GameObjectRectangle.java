package com.tp.batman.francis.blockgame.framework;

import com.tp.batman.francis.blockgame.framework.gl.SpriteBatcher;
import com.tp.batman.francis.blockgame.framework.gl.TextureRegion;
import com.tp.batman.francis.blockgame.game.Assets.Assets;

public class GameObjectRectangle {
	public static float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
	public static float TO_DEGREES = (1 / (float) Math.PI) * 180;
	
	public float lowerLeftX, lowerLeftY;
	public float lowerRightX, lowerRightY;
	public float topLeftX, topLeftY;
	public float topRightX, topRightY;
	
	
	public float startingLowerLeftX, startingLowerLeftY;
	public float startingLowerRightX, startingLowerRightY;
	public float startingTopLeftX, startingTopLeftY;
	public float startingTopRightX, startingTopRightY;
	
	
	public float x, y;
	
	public float startingX, startingY;

	public float getWidth() {
		return width;
	}

	public float getHeight() {
		return height;
	}

	public float getY() {
		return y;
	}

	public float getX() {
		return x;
	}

	public float width, height;
	
	public float angle;

	public TextureRegion textureRegion;
	
	public GameObjectRectangle(float x, float y, float width, float height){
		this.x = x;
		this.y = y;
		
		this.width = width;
		this.height = height;
		
		startingX = this.x;
		startingY = this.y;
		
		this.lowerLeftX = x - (width/2);
		this.lowerLeftY = y - (height/2);
		
		this.lowerRightX = x + (width/2);
		this.lowerRightY = y - (height/2);
		
		this.topLeftX = x - (width/2);
		this.topLeftY = y + (height/2);
		
		this.topRightX = x + (width/2);
		this.topRightY = y + (height/2);
		
		
		startingLowerLeftX = this.lowerLeftX;
		startingLowerLeftY = this.lowerLeftY;
		startingLowerRightX = this.lowerRightX;
		startingLowerRightY = this.lowerRightY;
		startingTopLeftX = this.topLeftX;
		startingTopLeftY = this.topLeftY;
		startingTopRightX = this.topRightX;
		startingTopRightY = this.topRightY;
		
		
		this.angle = 0;
	}

	public GameObjectRectangle(float x, float y, float width, float height, TextureRegion textureRegion){
		this.x = x;
		this.y = y;

		this.width = width;
		this.height = height;

		startingX = this.x;
		startingY = this.y;

		this.lowerLeftX = x - (width/2);
		this.lowerLeftY = y - (height/2);

		this.lowerRightX = x + (width/2);
		this.lowerRightY = y - (height/2);

		this.topLeftX = x - (width/2);
		this.topLeftY = y + (height/2);

		this.topRightX = x + (width/2);
		this.topRightY = y + (height/2);


		startingLowerLeftX = this.lowerLeftX;
		startingLowerLeftY = this.lowerLeftY;
		startingLowerRightX = this.lowerRightX;
		startingLowerRightY = this.lowerRightY;
		startingTopLeftX = this.topLeftX;
		startingTopLeftY = this.topLeftY;
		startingTopRightX = this.topRightX;
		startingTopRightY = this.topRightY;


		this.angle = 0;

		this.textureRegion = textureRegion;
	}
	
	public GameObjectRectangle(float x, float y, float width, float height, float angle){
		this.x = x;
		this.y = y;
		
		startingX = this.x;
		startingY = this.y;
		
		this.width = width;
		this.height = height;
		
		this.lowerLeftX = x - (width/2);
		this.lowerLeftY = y - (height/2);
		
		this.lowerRightX = x + (width/2);
		this.lowerRightY = y - (height/2);
		
		this.topLeftX = x - (width/2);
		this.topLeftY = y + (height/2);
		
		this.topRightX = x + (width/2);
		this.topRightY = y + (height/2);
		
		startingLowerLeftX = this.lowerLeftX;
		startingLowerLeftY = this.lowerLeftY;
		startingLowerRightX = this.lowerRightX;
		startingLowerRightY = this.lowerRightY;
		startingTopLeftX = this.topLeftX;
		startingTopLeftY = this.topLeftY;
		startingTopRightX = this.topRightX;
		startingTopRightY = this.topRightY;
		
		this.angle = angle;
		
		rotateCenter(this.angle);
	}
	
	public GameObjectRectangle(float x, float y, float width, float height, float rectangleX, float rectangleY, float angle){
		this.x = x;
		this.y = y;
		
		startingX = this.x;
		startingY = this.y;
		
		this.width = width;
		this.height = height;
		
		this.lowerLeftX = x - (width/2);
		this.lowerLeftY = y - (height/2);
		
		this.lowerRightX = x + (width/2);
		this.lowerRightY = y - (height/2);
		
		this.topLeftX = x - (width/2);
		this.topLeftY = y + (height/2);
		
		this.topRightX = x + (width/2);
		this.topRightY = y + (height/2);
		
		startingLowerLeftX = this.lowerLeftX;
		startingLowerLeftY = this.lowerLeftY;
		startingLowerRightX = this.lowerRightX;
		startingLowerRightY = this.lowerRightY;
		startingTopLeftX = this.topLeftX;
		startingTopLeftY = this.topLeftY;
		startingTopRightX = this.topRightX;
		startingTopRightY = this.topRightY;
		
		this.angle = angle;
		
		rotateAroundPoint(rectangleX, rectangleY, this.angle);
	}
	
	public void setPosition(float x, float y){
		float differenceX = x - this.x;
		float differenceY = y - this.y;
		
		this.x = x;
		this.y = y;
		
		this.lowerLeftX += differenceX;
		this.lowerLeftY += differenceY;
		
		this.lowerRightX += differenceX;
		this.lowerRightY += differenceY;
		
		this.topLeftX += differenceX;
		this.topLeftY += differenceY;
		
		this.topRightX += differenceX;
		this.topRightY += differenceY;
	}
	
	public void add(float x, float y){
		this.x += x;
		this.y += y;
		
		this.lowerLeftX += x;
		this.lowerLeftY += y;
		
		this.lowerRightX += x;
		this.lowerRightY += y;
		
		this.topLeftX += x;
		this.topLeftY += y;
		
		this.topRightX += x;
		this.topRightY += y;
	}
	
	public void rotateCenter(float angle){
		this.angle = angle;
		
		float rad = this.angle * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		this.lowerLeftX = (this.startingLowerLeftX * cos - this.startingLowerLeftY * sin) + x;
		this.lowerLeftY = (this.startingLowerLeftX * sin + this.startingLowerLeftY * cos) + y;
		
		this.lowerRightX = (this.startingLowerRightX * cos - this.startingLowerRightY * sin) + x;
		this.lowerRightY = (this.startingLowerRightX * sin + this.startingLowerRightY * cos) + y;
		
		this.topLeftX = (this.startingTopLeftX * cos - this.startingTopLeftY * sin) + x;
		this.topLeftY = (this.startingTopLeftX * sin + this.startingTopLeftY * cos) + y;
		
		this.topRightX = (this.startingTopRightX * cos - this.startingTopRightY * sin) + x;
		this.topRightY = (this.startingTopRightX * sin + this.startingTopRightY * cos) + y;
	}
	
	public void rotateAroundPoint(float rectangleX, float rectangleY, float angle){
		this.angle = angle;
		
		float rad = (this.angle + 180) * TO_RADIANS;
		float cos = (float) Math.cos(rad);
		float sin = (float) Math.sin(rad);
		
		this.x = (rectangleX*cos - rectangleY*sin) + this.x;
		this.y = (rectangleX*sin + rectangleY*cos) + this.y;
		
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
		
		// Top Line
		float topAng = 0;
		float topLength = (float) Math.sqrt(((topRightX - topLeftX) * (topRightX - topLeftX)) + ((topRightY - topLeftY) * (topRightY - topLeftY)));
		if(topLeftX > topRightX && topLeftY > topRightY){
			float topLengthSide = topLeftY - topRightY;
			topAng = (float)(Math.asin(topLengthSide/topLength));
			topAng = (float)(topAng * 180 / Math.PI);
				
		}
		else if(topLeftX < topRightX && topLeftY > topRightY){
			float topLengthSide = topLeftY - topRightY;
			topAng = (float)(Math.asin(topLengthSide/topLength));
			topAng = 180 - (float)(topAng * 180 / Math.PI);
				
		}
		else if(topLeftX > topRightX && topLeftY < topRightY){
			float topLengthSide = topRightY - topLeftY;
			topAng = (float)(Math.asin(topLengthSide/topLength));
			topAng = 180 - (float)(topAng * 180 / Math.PI);
				
		}
		else if(topLeftX < topRightX && topLeftY < topRightY){
			float topLengthSide = topRightY - topLeftY;
			topAng = (float)(Math.asin(topLengthSide/topLength));
			topAng = (float)(topAng * 180 / Math.PI);
			
		} 
		else if(topLeftX == topRightX){
			topAng = 90;
		}
		else if(topLeftY == topRightY){
			topAng = 0;
		}

		batcher.drawSprite((topLeftX + topRightX) / 2, (topLeftY + topRightY) / 2, topLength, 2, topAng, Assets.alwaysLoadedAssets.line);
		
		
		// BottomLine
		float botAng = 0;
		float botLength = (float) Math.sqrt(((lowerRightX - lowerLeftX) * (lowerRightX - lowerLeftX)) + ((lowerRightY - lowerLeftY) * (lowerRightY - lowerLeftY)));
		if(lowerLeftX > lowerRightX && lowerLeftY > lowerRightY){
			float topLengthSide = lowerLeftY - lowerRightY;
			 botAng= (float)(Math.asin(topLengthSide/botLength));
			botAng = (float)(botAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX < lowerRightX && lowerLeftY > lowerRightY){
			float topLengthSide = lowerLeftY - lowerRightY;
			botAng = (float)(Math.asin(topLengthSide/botLength));
			botAng = 180 - (float)(botAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX > lowerRightX && lowerLeftY < lowerRightY){
			float topLengthSide = lowerRightY - lowerLeftY;
			botAng = (float)(Math.asin(topLengthSide/botLength));
			botAng = 180 - (float)(botAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX < lowerRightX && lowerLeftY < lowerRightY){
			float topLengthSide = lowerRightY - lowerLeftY;
			botAng = (float)(Math.asin(topLengthSide/botLength));
			botAng = (float)(botAng * 180 / Math.PI);
			
		} 
		else if(lowerLeftX == lowerRightX){
			botAng = 90;
		}
		else if(lowerLeftY == lowerRightY){
			botAng = 0;
		}
		
		batcher.drawSprite((lowerLeftX + lowerRightX) / 2, (lowerLeftY + lowerRightY) / 2, botLength, 2, botAng, Assets.alwaysLoadedAssets.line);
		
		
		// Left
		float leftAng = 0;
		float leftLength = (float) Math.sqrt(((topLeftX - lowerLeftX) * (topLeftX - lowerLeftX)) + ((topLeftY - lowerLeftY) * (topLeftY - lowerLeftY)));
		if(lowerLeftX > topLeftX && lowerLeftY > topLeftY){
			float leftLengthSide = lowerLeftY - topLeftY;
			leftAng = (float)(Math.asin(leftLengthSide/leftLength));
			leftAng = (float)(leftAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX < topLeftX && lowerLeftY > topLeftY){
			float rightLengthSide = lowerLeftY - topLeftY;
			leftAng = (float)(Math.asin(rightLengthSide/leftLength));
			leftAng = 180 - (float)(leftAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX > topLeftX && lowerLeftY < topLeftY){
			float rightLengthSide = topLeftY - lowerLeftY;
			leftAng = (float)(Math.asin(rightLengthSide/leftLength));
			leftAng = 180 - (float)(leftAng * 180 / Math.PI);
				
		}
		else if(lowerLeftX < topLeftX && lowerLeftY < topLeftY){
			float rightLengthSide = topLeftY - lowerLeftY;
			leftAng = (float)(Math.asin(rightLengthSide/leftLength));
			leftAng = (float)(leftAng * 180 / Math.PI);
			
		} 
		else if(lowerLeftX == topLeftX){
			leftAng = 90;
		}
		else if(lowerLeftY == topLeftY){
			leftAng = 0;
		}

		batcher.drawSprite((lowerLeftX + topLeftX) / 2, (lowerLeftY + topLeftY) / 2, leftLength, 2, leftAng, Assets.alwaysLoadedAssets.line);

		
		// Right
		float rightAng = 0;
		float rightLength = (float) Math.sqrt(((topRightX - lowerRightX) * (topRightX - lowerRightX)) + ((topRightY - lowerRightY) * (topRightY - lowerRightY)));
		if(lowerRightX > topRightX && lowerRightY > topRightY){
			float rightLengthSide = lowerRightY - topRightY;
			rightAng = (float)(Math.asin(rightLengthSide/rightLength));
			rightAng = (float)(rightAng * 180 / Math.PI);
				
		}
		else if(lowerRightX < topRightX && lowerRightY > topRightY){
			float rightLengthSide = lowerRightY - topRightY;
			rightAng = (float)(Math.asin(rightLengthSide/rightLength));
			rightAng = 180 - (float)(rightAng * 180 / Math.PI);
				
		}
		else if(lowerRightX > topRightX && lowerRightY < topRightY){
			float rightLengthSide = topRightY - lowerRightY;
			rightAng = (float)(Math.asin(rightLengthSide/rightLength));
			rightAng = 180 - (float)(rightAng * 180 / Math.PI);
				
		}
		else if(lowerRightX < topRightX && lowerRightY < topRightY){
			float rightLengthSide = topRightY - lowerRightY;
			rightAng = (float)(Math.asin(rightLengthSide/rightLength));
			rightAng = (float)(rightAng * 180 / Math.PI);
			
		} 
		else if(lowerRightX == topRightX){
			rightAng = 90;
		}
		else if(lowerRightY == topRightY){
			rightAng = 0;
		}

		batcher.drawSprite((lowerRightX + topRightX) / 2, (lowerRightY + topRightY) / 2, rightLength, 2, rightAng, Assets.alwaysLoadedAssets.line);
		
		}
	
	public void drawBoundingBox(SpriteBatcher batcher){
		float minX, minY, maxX, maxY;
		
		if(lowerLeftX <= lowerRightX && lowerLeftX <= topLeftX && lowerLeftX <= topRightX)
			minX = lowerLeftX;
		else if(lowerRightX <= lowerLeftX && lowerRightX <= topLeftX && lowerRightX <= topRightX)
			minX = lowerRightX;
		else if(topLeftX <= lowerLeftX && topLeftX <= lowerRightX && topLeftX <= topRightX)
			minX = topLeftX;
		else
			minX = topRightX;
		
		if(lowerLeftX >= lowerRightX && lowerLeftX >= topLeftX && lowerLeftX >= topRightX)
			maxX = lowerLeftX;
		else if(lowerRightX >= lowerLeftX && lowerRightX >= topLeftX && lowerRightX >= topRightX)
			maxX = lowerRightX;
		else if(topLeftX >= lowerLeftX && topLeftX >= lowerRightX && topLeftX >= topRightX)
			maxX = topLeftX;
		else
			maxX = topRightX;
		
		if(lowerLeftY <= lowerRightY && lowerLeftY <= topLeftY && lowerLeftY <= topRightY)
			minY = lowerLeftY;
		else if(lowerRightY <= lowerLeftY && lowerRightY <= topLeftY && lowerRightY <= topRightY)
			minY = lowerRightY;
		else if(topLeftY <= lowerLeftY && topLeftY <= lowerRightY && topLeftY <= topRightY)
			minY = topLeftY;
		else
			minY = topRightY;
		
		if(lowerLeftY >= lowerRightY && lowerLeftY >= topLeftY && lowerLeftY >= topRightY)
			maxY = lowerLeftX;
		else if(lowerRightY >= lowerLeftY && lowerRightY >= topLeftY && lowerRightY >= topRightY)
			maxY = lowerRightY;
		else if(topLeftY >= lowerLeftY && topLeftY >= lowerRightY && topLeftY >= topRightY)
			maxY = topLeftY;
		else
			maxY = topRightY;
		
		float width = maxX - minX;
		float height = maxY - minY;
		
		GameObjectRectangle a = new GameObjectRectangle(x, y, width, height);
		a.drawShape(batcher);
	}
}
