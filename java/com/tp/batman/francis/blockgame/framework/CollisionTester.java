package com.tp.batman.francis.blockgame.framework;

//import com.tp.batman.francis.blockgame.framework.math.Circle;
//import com.tp.batman.francis.blockgame.game.Astronaut.Astronaut;

import com.tp.batman.francis.blockgame.framework.math.Circle;

public class CollisionTester {
/*
	public static boolean DeepCollisionTest(Astronaut astro, GameObjectRectangle rect) {



		return true;
	}

	public static boolean DeepCollisionTest(GameObjectRectangle rect, Astronaut astro) {
		return DeepCollisionTest(astro, rect);
	}
	
	////////////////////////////////////////////////////////////////////////////////////////
	
	public static boolean CollisionTest(Astronaut astro, GameObjectRectangle rect){
		
        if(CollisionTest(astro.head, rect) || CollisionTester.CollisionTest(astro.body, rect) || CollisionTest(astro.jetPack, rect) ||
        		CollisionTester.CollisionTest(astro.leftLeg, rect) || CollisionTester.CollisionTest(astro.rightLeg, rect))
        	return true;
        else
        	return false;
       
	}
	public static boolean CollisionTest(GameObjectRectangle rect, Astronaut astro){
		
		return CollisionTest(astro, rect);
	}
	
	public static boolean CollisionTest(Astronaut astro, GameObjectCircle circ){
		
        if(CollisionTest(astro.head, circ) || CollisionTester.CollisionTest(astro.body, circ) || CollisionTest(astro.jetPack, circ) ||
        		CollisionTester.CollisionTest(astro.leftLeg, circ) || CollisionTester.CollisionTest(astro.rightLeg, circ))
        	return true;
        else
        	return false;
       
	}
	public static boolean CollisionTest(GameObjectCircle circ, Astronaut astro){
		
		return CollisionTest(astro, circ);
	}
	
	public static boolean CollisionTest(Astronaut astro, GameObjectShape shape){
		
        if(CollisionTest(astro.head, shape) || CollisionTester.CollisionTest(astro.body, shape) || CollisionTest(astro.jetPack, shape) ||
        		CollisionTester.CollisionTest(astro.leftLeg, shape) || CollisionTester.CollisionTest(astro.rightLeg, shape))
        	return true;
        else
        	return false;
       
	}
	public static boolean CollisionTest(GameObjectShape shape, Astronaut astro){
		
		return CollisionTest(astro, shape);
	}
	
	
	
	
	*/
	
	
	////////////////////////////////////////////////////////////////////////////////////////
	private static int sgn(float x){
		
		if(x<0)
			return -1;
		else
			return 0;
	}
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////



	// New line intersecting algorithm 
	
	private static boolean LineTest(GameObjectLine line1, GameObjectLine line2){
		
		return LinesIntersect(line1.x1, line1.y1, line1.x2, line1.y2,
							  line2.x1, line2.y1, line2.x2, line2.y2);
	}
	
	private static boolean LineTest(float ax, float ay, float bx, float by, float cx, float cy, float dx, float dy){
		
		return LinesIntersect(ax, ay, bx, by, cx, cy, dx, dy);
	}
	
	private static boolean LinesIntersect(float x1, float y1,
										  float x2, float y2,
										  float x3, float y3,
										  float x4, float y4){
		
		return ((relativeCCW(x1, y1, x2, y2, x3, y3) *
				 relativeCCW(x1, y1, x2, y2, x4, y4) <= 0)
			 && (relativeCCW(x3, y3, x4, y4, x1, y1) *
				 relativeCCW(x3, y3, x4, y4, x2, y2) <= 0));
	}
	
	private static int relativeCCW(float x1, float y1,
			                                   float x2, float y2,
			                                   float px, float py)
			     {
			         x2 -= x1;
			         y2 -= y1;
			         px -= x1;
			         py -= y1;

			         float ccw = px * y2 - py * x2;

			         if (ccw < 0.01 && ccw > -0.01) {

			             // The point is colinear, classify based on which side of
			             // the segment the point falls on.  We can calculate a
			             // relative value using the projection of px,py onto the
			             // segment - a negative value indicates the point projects
			             // outside of the segment in the direction of the particular
			             // endpoint used as the origin for the projection.

			             ccw = px * x2 + py * y2;

			             if (ccw > 0.0f) {

			                 // Reverse the projection to be relative to the original x2,y2
			                 // x2 and y2 are simply negated.
			                 // px and py need to have (x2 - x1) or (y2 - y1) subtracted
			                 //    from them (based on the original values)
			                 // Since we really want to get a positive answer when the
			                 //    point is "beyond (x2,y2)", then we want to calculate
			                 //    the inverse anyway - thus we leave x2 & y2 negated.

			                 px -= x2;
			                 py -= y2;

			                 ccw = px * x2 + py * y2;
			                 
			                 if (ccw < 0.0f) {
			                     ccw = 0.0f;
			                 }
			             }
			         }
			         return (ccw < 0.0f) ? -1 : ((ccw > 0.0f) ? 1 : 0);
			     }
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	public static boolean rectangleCollision(GameObjectRectangle rect1, GameObjectRectangle rect2) {
		boolean collision = false;
		if (rect1.getX() - (rect1.getWidth()/2) < rect2.getX() + (rect2.getWidth()/2) &&  rect1.getX() + (rect1.getWidth()/2) > rect2.getX() - (rect2.getWidth()/2) &&
				rect1.getY() + (rect1.getHeight()/2) > rect2.getY() - (rect2.getHeight()/2) && rect1.getY() - (rect1.getHeight()/2) < rect2.getY() + (rect2.getHeight()/2)) {
			collision = true;
		}
		return collision;
	}
	

	public static boolean CollisionTest(GameObjectRectangle rect1, GameObjectRectangle rect2){
		
		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the first rectangle occupies
		float widthTwo, heightTwo; // height and width that the second rectangle occupies
		
		float minXOne, minYOne, maxXOne, maxYOne;
		
		if(rect1.lowerLeftX <= rect1.lowerRightX && rect1.lowerLeftX <= rect1.topLeftX && rect1.lowerLeftX <= rect1.topRightX)
			minXOne = rect1.lowerLeftX;
		else if(rect1.lowerRightX <= rect1.lowerLeftX && rect1.lowerRightX <= rect1.topLeftX && rect1.lowerRightX <= rect1.topRightX)
			minXOne = rect1.lowerRightX;
		else if(rect1.topLeftX <= rect1.lowerLeftX && rect1.topLeftX <= rect1.lowerRightX && rect1.topLeftX <= rect1.topRightX)
			minXOne = rect1.topLeftX;
		else
			minXOne = rect1.topRightX;
		
		if(rect1.lowerLeftX >= rect1.lowerRightX && rect1.lowerLeftX >= rect1.topLeftX && rect1.lowerLeftX >= rect1.topRightX)
			maxXOne = rect1.lowerLeftX;
		else if(rect1.lowerRightX >= rect1.lowerLeftX && rect1.lowerRightX >= rect1.topLeftX && rect1.lowerRightX >= rect1.topRightX)
			maxXOne = rect1.lowerRightX;
		else if(rect1.topLeftX >= rect1.lowerLeftX && rect1.topLeftX >= rect1.lowerRightX && rect1.topLeftX >= rect1.topRightX)
			maxXOne = rect1.topLeftX;
		else
			maxXOne = rect1.topRightX;
		
		if(rect1.lowerLeftY <= rect1.lowerRightY && rect1.lowerLeftY <= rect1.topLeftY && rect1.lowerLeftY <= rect1.topRightY)
			minYOne = rect1.lowerLeftY;
		else if(rect1.lowerRightY <= rect1.lowerLeftY && rect1.lowerRightY <= rect1.topLeftY && rect1.lowerRightY <= rect1.topRightY)
			minYOne = rect1.lowerRightY;
		else if(rect1.topLeftY <= rect1.lowerLeftY && rect1.topLeftY <= rect1.lowerRightY && rect1.topLeftY <= rect1.topRightY)
			minYOne = rect1.topLeftY;
		else
			minYOne = rect1.topRightY;
		
		if(rect1.lowerLeftY >= rect1.lowerRightY && rect1.lowerLeftY >= rect1.topLeftY && rect1.lowerLeftY >= rect1.topRightY)
			maxYOne = rect1.lowerLeftX;
		else if(rect1.lowerRightY >= rect1.lowerLeftY && rect1.lowerRightY >= rect1.topLeftY && rect1.lowerRightY >= rect1.topRightY)
			maxYOne = rect1.lowerRightY;
		else if(rect1.topLeftY >= rect1.lowerLeftY && rect1.topLeftY >= rect1.lowerRightY && rect1.topLeftY >= rect1.topRightY)
			maxYOne = rect1.topLeftY;
		else
			maxYOne = rect1.topRightY;
		
		widthOne = maxXOne - minXOne;
		heightOne = maxYOne - minYOne;
		
		
		
		float minXTwo, minYTwo, maxXTwo, maxYTwo;
		
		if(rect2.lowerLeftX <= rect2.lowerRightX && rect2.lowerLeftX <= rect2.topLeftX && rect2.lowerLeftX <= rect2.topRightX)
			minXTwo = rect2.lowerLeftX;
		else if(rect2.lowerRightX <= rect2.lowerLeftX && rect2.lowerRightX <= rect2.topLeftX && rect2.lowerRightX <= rect2.topRightX)
			minXTwo = rect2.lowerRightX;
		else if(rect2.topLeftX <= rect2.lowerLeftX && rect2.topLeftX <= rect2.lowerRightX && rect2.topLeftX <= rect2.topRightX)
			minXTwo = rect2.topLeftX;
		else
			minXTwo = rect2.topRightX;
		
		if(rect2.lowerLeftX >= rect2.lowerRightX && rect2.lowerLeftX >= rect2.topLeftX && rect2.lowerLeftX >= rect2.topRightX)
			maxXTwo = rect2.lowerLeftX;
		else if(rect2.lowerRightX >= rect2.lowerLeftX && rect2.lowerRightX >= rect2.topLeftX && rect2.lowerRightX >= rect2.topRightX)
			maxXTwo = rect2.lowerRightX;
		else if(rect2.topLeftX >= rect2.lowerLeftX && rect2.topLeftX >= rect2.lowerRightX && rect2.topLeftX >= rect2.topRightX)
			maxXTwo = rect2.topLeftX;
		else
			maxXTwo = rect2.topRightX;
		
		if(rect2.lowerLeftY <= rect2.lowerRightY && rect2.lowerLeftY <= rect2.topLeftY && rect2.lowerLeftY <= rect2.topRightY)
			minYTwo = rect2.lowerLeftY;
		else if(rect2.lowerRightY <= rect2.lowerLeftY && rect2.lowerRightY <= rect2.topLeftY && rect2.lowerRightY <= rect2.topRightY)
			minYTwo = rect2.lowerRightY;
		else if(rect2.topLeftY <= rect2.lowerLeftY && rect2.topLeftY <= rect2.lowerRightY && rect2.topLeftY <= rect2.topRightY)
			minYTwo = rect2.topLeftY;
		else
			minYTwo = rect2.topRightY;
		
		if(rect2.lowerLeftY >= rect2.lowerRightY && rect2.lowerLeftY >= rect2.topLeftY && rect2.lowerLeftY >= rect2.topRightY)
			maxYTwo = rect2.lowerLeftX;
		else if(rect2.lowerRightY >= rect2.lowerLeftY && rect2.lowerRightY >= rect2.topLeftY && rect2.lowerRightY >= rect2.topRightY)
			maxYTwo = rect2.lowerRightY;
		else if(rect2.topLeftY >= rect2.lowerLeftY && rect2.topLeftY >= rect2.lowerRightY && rect2.topLeftY >= rect2.topRightY)
			maxYTwo = rect2.topLeftY;
		else
			maxYTwo = rect2.topRightY;
		
		widthTwo = maxXTwo - minXTwo;
		heightTwo = maxYTwo - minYTwo;
		
		if(rect1.x + (widthOne/2) > rect2.x - (widthTwo/2) && rect1.x - (widthOne/2) < rect2.x + (widthTwo/2) &&
				rect1.y + (heightOne/2) > rect2.y - (heightTwo/2) && rect1.y - (heightOne/2) < rect2.y + (heightTwo/2)){
			
			// left
			if(LineTest(rect1.lowerLeftX, rect1.lowerLeftY, rect1.topLeftX, rect1.topLeftY, rect2.lowerLeftX, rect2.lowerLeftY, rect2.topLeftX, rect2.topLeftY))
				collision = true;
			
			if(!collision){
				if(LineTest(rect1.lowerLeftX, rect1.lowerLeftY, rect1.topLeftX, rect1.topLeftY, rect2.topLeftX, rect2.topLeftY, rect2.topRightX, rect2.topRightY))
					collision = true;
			}
						
			if(!collision){
				if(LineTest(rect1.lowerLeftX, rect1.lowerLeftY, rect1.topLeftX, rect1.topLeftY, rect2.topRightX, rect2.topRightY, rect2.lowerRightX, rect2.lowerRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.lowerLeftX, rect1.lowerLeftY, rect1.topLeftX, rect1.topLeftY, rect2.lowerRightX, rect2.lowerRightY, rect2.lowerLeftX, rect2.lowerLeftY))
					collision = true;
			}
			
			// top
			if(!collision){
				if(LineTest(rect1.topLeftX, rect1.topLeftY, rect1.topRightX, rect1.topRightY, rect2.lowerLeftX, rect2.lowerLeftY, rect2.topLeftX, rect2.topLeftY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topLeftX, rect1.topLeftY, rect1.topRightX, rect1.topRightY, rect2.topLeftX, rect2.topLeftY, rect2.topRightX, rect2.topRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topLeftX, rect1.topLeftY, rect1.topRightX, rect1.topRightY, rect2.topRightX, rect2.topRightY, rect2.lowerRightX, rect2.lowerRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topLeftX, rect1.topLeftY, rect1.topRightX, rect1.topRightY, rect2.lowerRightX, rect2.lowerRightY, rect2.lowerLeftX, rect2.lowerLeftY))
					collision = true;
			}
			
			// right
			if(!collision){
				if(LineTest(rect1.topRightX, rect1.topRightY, rect1.lowerRightX, rect1.lowerRightY, rect2.lowerLeftX, rect2.lowerLeftY, rect2.topLeftX, rect2.topLeftY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topRightX, rect1.topRightY, rect1.lowerRightX, rect1.lowerRightY, rect2.topLeftX, rect2.topLeftY, rect2.topRightX, rect2.topRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topRightX, rect1.topRightY, rect1.lowerRightX, rect1.lowerRightY, rect2.topRightX, rect2.topRightY, rect2.lowerRightX, rect2.lowerRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.topRightX, rect1.topRightY, rect1.lowerRightX, rect1.lowerRightY, rect2.lowerRightX, rect2.lowerRightY, rect2.lowerLeftX, rect2.lowerLeftY))
					collision = true;
			}
			
			// bottom
			if(!collision){
				if(LineTest(rect1.lowerRightX, rect1.lowerRightY, rect1.lowerLeftX, rect1.lowerLeftY, rect2.lowerLeftX, rect2.lowerLeftY, rect2.topLeftX, rect2.topLeftY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.lowerRightX, rect1.lowerRightY, rect1.lowerLeftX, rect1.lowerLeftY, rect2.topLeftX, rect2.topLeftY, rect2.topRightX, rect2.topRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.lowerRightX, rect1.lowerRightY, rect1.lowerLeftX, rect1.lowerLeftY, rect2.topRightX, rect2.topRightY, rect2.lowerRightX, rect2.lowerRightY))
					collision = true;
			}
			
			if(!collision){
				if(LineTest(rect1.lowerRightX, rect1.lowerRightY, rect1.lowerLeftX, rect1.lowerLeftY, rect2.lowerRightX, rect2.lowerRightY, rect2.lowerLeftX, rect2.lowerLeftY))
					collision = true;
			}
			
		}

		return collision; 
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	

	public static boolean CollisionTest(GameObjectCircle circ1, GameObjectCircle circ2){
		
		boolean collision = false;
		
		float distance = (float) Math.sqrt(  ((circ2.x - circ1.x)*(circ2.x - circ1.x)) + ((circ2.y - circ1.y)*(circ2.y - circ1.y)) );
		
		if(distance < circ1.radius + circ2.radius)
			collision = true;
		
		return collision; 
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static boolean CollisionTest(GameObjectShape shape1, GameObjectShape shape2){
		
		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the first shape occupies
		float widthTwo, heightTwo; // height and width that the second shape occupies
		
		float minXOne, minYOne, maxXOne, maxYOne;
		float minXTwo, minYTwo, maxXTwo, maxYTwo;
		
		minXOne = shape1.lines[0].x1;
		maxXOne = shape1.lines[0].x1;
		minYOne = shape1.lines[0].y1;
		maxYOne = shape1.lines[0].y1;
		
		for(int i=0; i<shape1.lines.length; i++){
			if(shape1.lines[i].x1 < minXOne)
				minXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 < minXOne)
				minXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].x1 > maxXOne)
				maxXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 > maxXOne)
				maxXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].y1 < minYOne)
				minYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 < minYOne)
				minYOne = shape1.lines[i].y2;
			
			if(shape1.lines[i].y1 > maxYOne)
				maxYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 > maxYOne)
				maxYOne = shape1.lines[i].y2;
		}
		
		widthOne = maxXOne - minXOne;
		heightOne = maxYOne - minYOne;
		
		
		minXTwo = shape2.lines[0].x1;
		maxXTwo = shape2.lines[0].x1;
		minYTwo = shape2.lines[0].y1;
		maxYTwo = shape2.lines[0].y1;
		
		for(int i=0; i<shape2.lines.length; i++){
			if(shape2.lines[i].x1 < minXTwo)
				minXTwo = shape2.lines[i].x1;
			if(shape2.lines[i].x2 < minXTwo)
				minXTwo = shape2.lines[i].x2;
			
			if(shape2.lines[i].x1 > maxXTwo)
				maxXTwo = shape2.lines[i].x1;
			if(shape2.lines[i].x2 > maxXTwo)
				maxXTwo = shape2.lines[i].x2;
			
			if(shape2.lines[i].y1 < minYTwo)
				minYTwo = shape2.lines[i].y1;
			if(shape2.lines[i].y2 < minYTwo)
				minYTwo = shape2.lines[i].y2;
			
			if(shape2.lines[i].y1 > maxYTwo)
				maxYTwo = shape2.lines[i].y1;
			if(shape2.lines[i].y2 > maxYTwo)
				maxYTwo = shape2.lines[i].y2;
		}
		
		widthTwo = maxXTwo - minXTwo;
		heightTwo = maxYTwo - minYTwo;
		
		if(shape1.x + (widthOne/2) > shape2.x - (widthTwo/2) && shape1.x - (widthOne/2) < shape2.x + (widthTwo/2) &&
				shape1.y + (heightOne/2) > shape2.y - (heightTwo/2) && shape1.y - (heightOne/2) < shape2.y + (heightTwo/2)){
		
			for(int i=0; i< shape1.lines.length && !collision; i++){
				for(int j=0; j< shape2.lines.length && !collision; j++){
					if(LineTest((shape1.lines[i].x1  + shape1.x), (shape1.lines[i].y1  + shape1.y), (shape1.lines[i].x2  + shape1.x), (shape1.lines[i].y2  + shape1.y), (shape2.lines[i].x1  + shape2.x), (shape2.lines[i].y1  + shape2.y), (shape2.lines[i].x2  + shape2.x), (shape2.lines[i].y2  + shape2.y)));
						collision = true;
				}
			}
		}
		
		return collision;
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static boolean CollisionTestOld(GameObjectRectangle rect1, GameObjectCircle circ1){
		// google to see how it works
		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the rectangle occupies
		float widthTwo, heightTwo; // height and width that the circle occupies
		
		float dxLeft, dyLeft, drLeft, dLeft; // Left side of rectangle
		@SuppressWarnings("unused")
		float dxRight, dyRight, drRight, dRight; // Right side of rectangle
		float dxTop, dyTop, drTop, dTop; // Top side of rectangle
		@SuppressWarnings("unused")
		float dxLower, dyLower, drLower, dLower; // Bottom of rectangle\
		float delta = -1;
		
		widthOne = Math.abs(rect1.width * (float) Math.sin(rect1.angle)) + Math.abs(rect1.height * (float) Math.cos(rect1.angle));
		heightOne = Math.abs(rect1.width * (float) Math.cos(rect1.angle)) + Math.abs(rect1.height * (float) Math.sin(rect1.angle));
		
		widthTwo = circ1.radius*2;
		heightTwo = circ1.radius*2;
		
		
		if(rect1.x + (widthOne/2) > circ1.x - (widthTwo/2) && rect1.x - (widthOne/2) < circ1.x + (widthTwo/2) &&
				rect1.y + (heightOne/2) > circ1.y - (heightTwo/2) && rect1.y - (heightOne/2) < circ1.y + (heightTwo/2)){
			
			dxLeft = rect1.topLeftX - rect1.lowerLeftX;
			dyLeft = rect1.topLeftY - rect1.lowerLeftY;
			drLeft = (float)(Math.sqrt(Math.pow(dxLeft, 2) + Math.pow(dyLeft, 2)));
			dLeft = (rect1.lowerLeftX*rect1.topLeftY) - (rect1.topLeftX*rect1.lowerLeftY);
			delta = (float)((Math.pow(circ1.radius, 2)* Math.pow(drLeft, 2)) - Math.pow(dLeft, 2));
			
			if(delta < 0){	
				dxRight = dxLeft;
				dyRight = dyLeft;
				drRight = drLeft;
				dRight = (rect1.lowerRightX*rect1.topRightY) - (rect1.topRightX*rect1.lowerRightY);
				delta = (float)((Math.pow(circ1.radius, 2)* Math.pow(drRight, 2)) - Math.pow(dRight, 2));
				
				if(delta < 0){
					dxTop = rect1.topRightX - rect1.topLeftX;
					dyTop = rect1.topRightY - rect1.topLeftY;
					drTop = (float)(Math.sqrt(Math.pow(dxTop,2) + Math.pow(dyTop, 2)));
					dTop = (rect1.topLeftX*rect1.topRightY) - (rect1.topRightX*rect1.topLeftY);
					delta = (float)((Math.pow(circ1.radius, 2)* Math.pow(drTop, 2)) - Math.pow(dTop, 2));
					
					if(delta < 0){
						dxLower = dxTop;
						dyLower = dyTop;
						drLower = drTop;
						dLower = (rect1.lowerLeftX*rect1.lowerRightY) - (rect1.lowerRightX*rect1.lowerLeftY);
						delta = (float)((Math.pow(circ1.radius, 2)* Math.pow(drLower, 2)) - Math.pow(dLower, 2));
					}
				}
			}
		}
		
		if(delta >= 0)
			collision = true;
		
		return collision;
	}
	
	public static boolean CollisionTest(GameObjectRectangle rect1, GameObjectCircle circ1){

		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the rectangle occupies
		float widthTwo, heightTwo; // height and width that the circle occupies
		
		@SuppressWarnings("unused")
		float dxRight, dyRight, drRight, dRight; // Right side of rectangle
		@SuppressWarnings("unused")
		float dxLower, dyLower, drLower, dLower; // Bottom of rectangle\
		
		//widthOne = (float)(rect1.width * Math.cos(rect1.angle) + rect1.height*Math.sin(rect1.angle));
		//heightOne = (float)(rect1.width*Math.sin(rect1.angle) + rect1.height*Math.cos(rect1.angle));
		
		float minX, minY, maxX, maxY;
		
		if(rect1.lowerLeftX <= rect1.lowerRightX && rect1.lowerLeftX <= rect1.topLeftX && rect1.lowerLeftX <= rect1.topRightX)
			minX = rect1.lowerLeftX;
		else if(rect1.lowerRightX <= rect1.lowerLeftX && rect1.lowerRightX <= rect1.topLeftX && rect1.lowerRightX <= rect1.topRightX)
			minX = rect1.lowerRightX;
		else if(rect1.topLeftX <= rect1.lowerLeftX && rect1.topLeftX <= rect1.lowerRightX && rect1.topLeftX <= rect1.topRightX)
			minX = rect1.topLeftX;
		else
			minX = rect1.topRightX;
		
		if(rect1.lowerLeftX >= rect1.lowerRightX && rect1.lowerLeftX >= rect1.topLeftX && rect1.lowerLeftX >= rect1.topRightX)
			maxX = rect1.lowerLeftX;
		else if(rect1.lowerRightX >= rect1.lowerLeftX && rect1.lowerRightX >= rect1.topLeftX && rect1.lowerRightX >= rect1.topRightX)
			maxX = rect1.lowerRightX;
		else if(rect1.topLeftX >= rect1.lowerLeftX && rect1.topLeftX >= rect1.lowerRightX && rect1.topLeftX >= rect1.topRightX)
			maxX = rect1.topLeftX;
		else
			maxX = rect1.topRightX;
		
		if(rect1.lowerLeftY <= rect1.lowerRightY && rect1.lowerLeftY <= rect1.topLeftY && rect1.lowerLeftY <= rect1.topRightY)
			minY = rect1.lowerLeftY;
		else if(rect1.lowerRightY <= rect1.lowerLeftY && rect1.lowerRightY <= rect1.topLeftY && rect1.lowerRightY <= rect1.topRightY)
			minY = rect1.lowerRightY;
		else if(rect1.topLeftY <= rect1.lowerLeftY && rect1.topLeftY <= rect1.lowerRightY && rect1.topLeftY <= rect1.topRightY)
			minY = rect1.topLeftY;
		else
			minY = rect1.topRightY;
		
		if(rect1.lowerLeftY >= rect1.lowerRightY && rect1.lowerLeftY >= rect1.topLeftY && rect1.lowerLeftY >= rect1.topRightY)
			maxY = rect1.lowerLeftX;
		else if(rect1.lowerRightY >= rect1.lowerLeftY && rect1.lowerRightY >= rect1.topLeftY && rect1.lowerRightY >= rect1.topRightY)
			maxY = rect1.lowerRightY;
		else if(rect1.topLeftY >= rect1.lowerLeftY && rect1.topLeftY >= rect1.lowerRightY && rect1.topLeftY >= rect1.topRightY)
			maxY = rect1.topLeftY;
		else
			maxY = rect1.topRightY;
		
		widthOne = maxX - minX;
		heightOne = maxY - minY;
		
		//widthOne = Math.abs(rect1.width * (float)Math.sin(rect1.angle)) + Math.abs(rect1.height * (float)Math.cos(rect1.angle));
		//heightOne = Math.abs(rect1.width * (float)Math.cos(rect1.angle)) + Math.abs(rect1.height * (float)Math.sin(rect1.angle));
		
		
		widthTwo = circ1.radius*2;
		heightTwo = circ1.radius*2;
		
		if(rect1.x + (widthOne/2) > circ1.x - (widthTwo/2) && rect1.x - (widthOne/2) < circ1.x + (widthTwo/2) &&
				rect1.y + (heightOne/2) > circ1.y - (heightTwo/2) && rect1.y - (heightOne/2) < circ1.y + (heightTwo/2)){
			
			
			float x1, x2, y1, y2, xPlus, yPlus, xMoin, yMoin;
			float dx, dy, dr, d, delta;
			float dxc, dyc, dxl, dyl, cross, m;
			
			// Bottom 
			if(rect1.lowerLeftY >= rect1.lowerRightY){
				x1 = rect1.lowerLeftX - circ1.x;
				x2 = rect1.lowerRightX - circ1.x;
				y1 = rect1.lowerLeftY - circ1.y;
				y2 = rect1.lowerRightY - circ1.y;
			} else {
				x1 = rect1.lowerRightX - circ1.x;
				x2 = rect1.lowerLeftX - circ1.x;
				y1 = rect1.lowerRightY - circ1.y;
				y2 = rect1.lowerLeftY - circ1.y;
			}
			
			dx = x2 - x1;
			dy = y2 - y1;
			dr = (float) Math.sqrt(dx*dx + dy*dy);
			d = x1*y2 - x2*y1;
			delta = circ1.radius*circ1.radius*dr*dr - d*d;
			
			xPlus = (d*dy + sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yPlus = ((-d)*dx + (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			xMoin = (d*dy - sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yMoin = ((-d)*dx - (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			if(delta > 0){
				dxc = xPlus - x1;
				dyc = yPlus - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = (dxc*dyl) - (dyc*dxl);
				
				if(cross >= -0.01 && cross <= 0.01){
					
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yPlus > y1 && yPlus < y2)
								collision = true;
						} else {
							if(yPlus < y1 && yPlus > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xPlus > x1 && xPlus < x2)
								collision = true;
						} else {
							if(xPlus < x1 && xPlus > x2)
								collision = true;
						}
					}
				}
				
				dxc = xMoin - x1;
				dyc = yMoin - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
		
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yMoin > y1 && yMoin < y2)
								collision = true;
						} else {
							if(yMoin < y1 && yMoin > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xMoin > x1 && xMoin < x2)
								collision = true;
						} else {
							if(xMoin < x1 && xMoin > x2)
								collision = true;
						}
					}
				}
			}
			
			
			// Top
			if(rect1.topLeftY >= rect1.topRightY){
				x1 = rect1.topLeftX - circ1.x;
				x2 = rect1.topRightX - circ1.x;
				y1 = rect1.topLeftY - circ1.y;
				y2 = rect1.topRightY - circ1.y;
			} else {
				x1 = rect1.topRightX - circ1.x;
				x2 = rect1.topLeftX - circ1.x;
				y1 = rect1.topRightY - circ1.y;
				y2 = rect1.topLeftY - circ1.y;
			}
			
			dx = x2 - x1;
			dy = y2 - y1;
			dr = (float) Math.sqrt(dx*dx + dy*dy);
			d = x1*y2 - x2*y1;
			delta = circ1.radius*circ1.radius*dr*dr - d*d;
			
			xPlus = (d*dy + sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yPlus = ((-d)*dx + (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			xMoin = (d*dy - sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yMoin = ((-d)*dx - (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			if(delta > 0){
				dxc = xPlus - x1;
				dyc = yPlus - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
					
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yPlus > y1 && yPlus < y2)
								collision = true;
						} else {
							if(yPlus < y1 && yPlus > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xPlus > x1 && xPlus < x2)
								collision = true;
						} else {
							if(xPlus < x1 && xPlus > x2)
								collision = true;
						}
					}
				}
				
				dxc = xMoin - x1;
				dyc = yMoin - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
		
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yMoin > y1 && yMoin < y2)
								collision = true;
						} else {
							if(yMoin < y1 && yMoin > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xMoin > x1 && xMoin < x2)
								collision = true;
						} else {
							if(xMoin < x1 && xMoin > x2)
								collision = true;
						}
					}
				}
			}
			
			// Left
			if(rect1.lowerLeftY >= rect1.topLeftY){
				x1 = rect1.lowerLeftX - circ1.x;
				x2 = rect1.topLeftX - circ1.x;
				y1 = rect1.lowerLeftY - circ1.y;
				y2 = rect1.topLeftY - circ1.y;
			} else {
				x1 = rect1.topLeftX - circ1.x;
				x2 = rect1.lowerLeftX - circ1.x;
				y1 = rect1.topLeftY - circ1.y;
				y2 = rect1.lowerLeftY - circ1.y;
			}
						
			dx = x2 - x1;
			dy = y2 - y1;
			dr = (float) Math.sqrt(dx*dx + dy*dy);
			d = x1*y2 - x2*y1;
			delta = circ1.radius*circ1.radius*dr*dr - d*d;
						
			xPlus = (d*dy + sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yPlus = ((-d)*dx + (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			xMoin = (d*dy - sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yMoin = ((-d)*dx - (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			if(delta > 0){
				dxc = xPlus - x1;
				dyc = yPlus - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
					
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yPlus > y1 && yPlus < y2)
								collision = true;
						} else {
							if(yPlus < y1 && yPlus > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xPlus > x1 && xPlus < x2)
								collision = true;
						} else {
							if(xPlus < x1 && xPlus > x2)
								collision = true;
						}
					}
				}
				
				dxc = xMoin - x1;
				dyc = yMoin - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
		
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yMoin > y1 && yMoin < y2)
								collision = true;
						} else {
							if(yMoin < y1 && yMoin > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xMoin > x1 && xMoin < x2)
								collision = true;
						} else {
							if(xMoin < x1 && xMoin > x2)
								collision = true;
						}
					}
				}
			}
						
			// Right
			if(rect1.lowerRightY >= rect1.topRightY){
				x1 = rect1.lowerRightX - circ1.x;
				x2 = rect1.topRightX - circ1.x;
				y1 = rect1.lowerRightY - circ1.y;
				y2 = rect1.topRightY - circ1.y;
			} else {
				x1 = rect1.topRightX - circ1.x;
				x2 = rect1.lowerRightX - circ1.x;
				y1 = rect1.topRightY - circ1.y;
				y2 = rect1.lowerRightY - circ1.y;
			}
						
			dx = x2 - x1;
			dy = y2 - y1;
			dr = (float) Math.sqrt(dx*dx + dy*dy);
			d = x1*y2 - x2*y1;
			delta = circ1.radius*circ1.radius*dr*dr - d*d;
						
			xPlus = (d*dy + sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yPlus = ((-d)*dx + (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			xMoin = (d*dy - sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			yMoin = ((-d)*dx - (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
			
			if(delta > 0){
				dxc = xPlus - x1;
				dyc = yPlus - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
					
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yPlus > y1 && yPlus < y2)
								collision = true;
						} else {
							if(yPlus < y1 && yPlus > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xPlus > x1 && xPlus < x2)
								collision = true;
						} else {
							if(xPlus < x1 && xPlus > x2)
								collision = true;
						}
					}
				}
				
				dxc = xMoin - x1;
				dyc = yMoin - y1;
				
				dxl = x2 - x1;
				dyl = y2 - y1;
				
				cross = dxc*dyl - dyc*dxl;
				
				if(cross >= -0.01 && cross <= 0.01){
		
					m = (y2-y1)/(x2-x1);
					
					if(m > 1 || m < -1){
						if(y1 < y2){
							if(yMoin > y1 && yMoin < y2)
								collision = true;
						} else {
							if(yMoin < y1 && yMoin > y2)
								collision = true;
						}
					} else {
						if(x1 < x2){
							if(xMoin > x1 && xMoin < x2)
								collision = true;
						} else {
							if(xMoin < x1 && xMoin > x2)
								collision = true;
						}
					}
				}
			}
		}
		
		return collision;
	}
	
	public static boolean CollisionTest(GameObjectCircle circ1, GameObjectRectangle rect1){
		return CollisionTest(rect1, circ1);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static boolean CollisionTest(GameObjectRectangle rect1, GameObjectShape shape1){
		
		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the shape occupies
		float widthTwo, heightTwo; // height and width that the rectangle occupies
		
		float minXOne, minYOne, maxXOne, maxYOne;
		float minXTwo, minYTwo, maxXTwo, maxYTwo;
		
		minXOne = shape1.lines[0].x1;
		maxXOne = shape1.lines[0].x1;
		minYOne = shape1.lines[0].y1;
		maxYOne = shape1.lines[0].y1;
		
		for(int i=0; i<shape1.lines.length; i++){
			if(shape1.lines[i].x1 < minXOne)
				minXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 < minXOne)
				minXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].x1 > maxXOne)
				maxXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 > maxXOne)
				maxXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].y1 < minYOne)
				minYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 < minYOne)
				minYOne = shape1.lines[i].y2;
			
			if(shape1.lines[i].y1 > maxYOne)
				maxYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 > maxYOne)
				maxYOne = shape1.lines[i].y2;
		}
		
		widthOne = maxXOne - minXOne;
		heightOne = maxYOne - minYOne;
		
		
		if(rect1.lowerLeftX <= rect1.lowerRightX && rect1.lowerLeftX <= rect1.topLeftX && rect1.lowerLeftX <= rect1.topRightX)
			minXTwo = rect1.lowerLeftX;
		else if(rect1.lowerRightX <= rect1.lowerLeftX && rect1.lowerRightX <= rect1.topLeftX && rect1.lowerRightX <= rect1.topRightX)
			minXTwo = rect1.lowerRightX;
		else if(rect1.topLeftX <= rect1.lowerLeftX && rect1.topLeftX <= rect1.lowerRightX && rect1.topLeftX <= rect1.topRightX)
			minXTwo = rect1.topLeftX;
		else
			minXTwo = rect1.topRightX;
		
		if(rect1.lowerLeftX >= rect1.lowerRightX && rect1.lowerLeftX >= rect1.topLeftX && rect1.lowerLeftX >= rect1.topRightX)
			maxXTwo = rect1.lowerLeftX;
		else if(rect1.lowerRightX >= rect1.lowerLeftX && rect1.lowerRightX >= rect1.topLeftX && rect1.lowerRightX >= rect1.topRightX)
			maxXTwo = rect1.lowerRightX;
		else if(rect1.topLeftX >= rect1.lowerLeftX && rect1.topLeftX >= rect1.lowerRightX && rect1.topLeftX >= rect1.topRightX)
			maxXTwo = rect1.topLeftX;
		else
			maxXTwo = rect1.topRightX;
		
		if(rect1.lowerLeftY <= rect1.lowerRightY && rect1.lowerLeftY <= rect1.topLeftY && rect1.lowerLeftY <= rect1.topRightY)
			minYTwo = rect1.lowerLeftY;
		else if(rect1.lowerRightY <= rect1.lowerLeftY && rect1.lowerRightY <= rect1.topLeftY && rect1.lowerRightY <= rect1.topRightY)
			minYTwo = rect1.lowerRightY;
		else if(rect1.topLeftY <= rect1.lowerLeftY && rect1.topLeftY <= rect1.lowerRightY && rect1.topLeftY <= rect1.topRightY)
			minYTwo = rect1.topLeftY;
		else
			minYTwo = rect1.topRightY;
		
		if(rect1.lowerLeftY >= rect1.lowerRightY && rect1.lowerLeftY >= rect1.topLeftY && rect1.lowerLeftY >= rect1.topRightY)
			maxYTwo = rect1.lowerLeftX;
		else if(rect1.lowerRightY >= rect1.lowerLeftY && rect1.lowerRightY >= rect1.topLeftY && rect1.lowerRightY >= rect1.topRightY)
			maxYTwo = rect1.lowerRightY;
		else if(rect1.topLeftY >= rect1.lowerLeftY && rect1.topLeftY >= rect1.lowerRightY && rect1.topLeftY >= rect1.topRightY)
			maxYTwo = rect1.topLeftY;
		else
			maxYTwo = rect1.topRightY;
		
		widthTwo = maxXTwo - minXTwo;
		heightTwo = maxYTwo - minYTwo;
		
		if(shape1.x + (widthOne/2) > rect1.x - (widthTwo/2) && shape1.x - (widthOne/2) < rect1.x + (widthTwo/2) &&
				shape1.y + (heightOne/2) > rect1.y - (heightTwo/2) && shape1.y - (heightOne/2) < rect1.y + (heightTwo/2)){
			
			
			// left
			for(int i=0; i< shape1.lines.length && !collision; i++){
				if(LineTest(rect1.lowerLeftX, rect1.lowerLeftY, rect1.topLeftX, rect1.topLeftY, (shape1.lines[i].x1 + shape1.x), (shape1.lines[i].y1 + shape1.y), (shape1.lines[i].x2 + shape1.x), (shape1.lines[i].y2 + shape1.y)))
					collision = true;
			}
			
			// right
			for(int i=0; i< shape1.lines.length && !collision; i++){
				if(LineTest(rect1.topRightX, rect1.topRightY, rect1.lowerRightX, rect1.lowerRightY, (shape1.lines[i].x1 + shape1.x), (shape1.lines[i].y1 + shape1.y), (shape1.lines[i].x2 + shape1.x), (shape1.lines[i].y2 + shape1.y)))
					collision = true;
			}
			
			// top
			for(int i=0; i< shape1.lines.length && !collision; i++){
				if(LineTest(rect1.topLeftX, rect1.topLeftY, rect1.topRightX, rect1.topRightY, (shape1.lines[i].x1 + shape1.x), (shape1.lines[i].y1 + shape1.y), (shape1.lines[i].x2 + shape1.x), (shape1.lines[i].y2 + shape1.y)))
					collision = true;
			}
			
			// bottom
			for(int i=0; i< shape1.lines.length && !collision; i++){
				if(LineTest(rect1.lowerRightX, rect1.lowerRightY, rect1.lowerLeftX, rect1.lowerLeftY, (shape1.lines[i].x1 + shape1.x), (shape1.lines[i].y1 + shape1.y), (shape1.lines[i].x2 + shape1.x), (shape1.lines[i].y2 + shape1.y)))
					collision = true;
			}
			
		}
		
		return collision;
	}
	
	public static boolean CollisionTest(GameObjectShape shape1, GameObjectRectangle rect1){
		return CollisionTest(rect1, shape1);
	}
	
	
	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	public static boolean CollisionTest(GameObjectCircle circ1, GameObjectShape shape1){
		
		boolean collision = false;
		
		float widthOne, heightOne; // height and width that the shape occupies
		float widthTwo, heightTwo; // height and width that the circle occupies
		
		float minXOne, minYOne, maxXOne, maxYOne;

		
		minXOne = shape1.lines[0].x1;
		maxXOne = shape1.lines[0].x1;
		minYOne = shape1.lines[0].y1;
		maxYOne = shape1.lines[0].y1;
		
		for(int i=0; i<shape1.lines.length; i++){
			if(shape1.lines[i].x1 < minXOne)
				minXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 < minXOne)
				minXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].x1 > maxXOne)
				maxXOne = shape1.lines[i].x1;
			if(shape1.lines[i].x2 > maxXOne)
				maxXOne = shape1.lines[i].x2;
			
			if(shape1.lines[i].y1 < minYOne)
				minYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 < minYOne)
				minYOne = shape1.lines[i].y2;
			
			if(shape1.lines[i].y1 > maxYOne)
				maxYOne = shape1.lines[i].y1;
			if(shape1.lines[i].y2 > maxYOne)
				maxYOne = shape1.lines[i].y2;
		}
		
		widthOne = maxXOne - minXOne;
		heightOne = maxYOne - minYOne;
		
		widthTwo = circ1.radius*2;
		heightTwo = circ1.radius*2;
		
		if(circ1.x + (widthOne/2) > shape1.x - (widthTwo/2) && circ1.x - (widthOne/2) < shape1.x + (widthTwo/2) &&
				circ1.y + (heightOne/2) > shape1.y - (heightTwo/2) && circ1.y - (heightOne/2) < shape1.y + (heightTwo/2)){

			
			for(int i=0; i< shape1.lines.length && !collision; i++){

				float x1, x2, y1, y2, xPlus, yPlus, xMoin, yMoin;
				float dx, dy, dr, d, delta;
				float dxc, dyc, dxl, dyl, cross, m;
				
				if(shape1.lines[i].x1 != shape1.lines[i].x2)
					m = (shape1.lines[i].y2 - shape1.lines[i].y1) / (shape1.lines[i].x2 - shape1.lines[i].x1);
				else
					m = 0;
				
				
				if(m >= -1 && m <= 1){
					// horizontal
					if(shape1.lines[i].y1 >= shape1.lines[i].y2){
						x1 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
						x2 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
						y1 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
						y2 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
					} else {
						x1 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
						x2 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
						y1 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
						y2 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
					}
				} else {
					//vertical
					if((shape1.lines[i].x1 <= shape1.lines[i].x2) && (shape1.lines[i].y1 >= shape1.lines[i].y2)){
						x1 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
						x2 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
						y1 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
						y2 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
					} else {
						x1 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
						x2 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
						y1 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
						y2 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
					}
				}
				
					
					if(shape1.lines[i].y1 >= shape1.lines[i].y2){
							x1 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
							x2 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
							y1 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
							y2 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
					} else {
						x1 = (shape1.lines[i].x2 + shape1.x) - circ1.x;
						x2 = (shape1.lines[i].x1 + shape1.x) - circ1.x;
						y1 = (shape1.lines[i].y2 + shape1.y) - circ1.y;
						y2 = (shape1.lines[i].y1 + shape1.y) - circ1.y;
					}
				
				
				dx = x2 - x1;
				dy = y2 - y1;
				dr = (float) Math.sqrt(dx*dx + dy*dy);
				d = x1*y2 - x2*y1;
				delta = circ1.radius*circ1.radius*dr*dr - d*d;
				
				xPlus = (d*dy + sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
				yPlus = ((-d)*dx + (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
				
				xMoin = (d*dy - sgn(dy)*dx*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
				yMoin = ((-d)*dx - (float) Math.abs(dy)*(float) Math.sqrt((circ1.radius*circ1.radius*dr*dr) - (d*d))) / (dr*dr);
				
				if(delta > 0){
					dxc = xPlus - x1;
					dyc = yPlus - y1;
					
					dxl = x2 - x1;
					dyl = y2 - y1;
					
					cross = (dxc*dyl) - (dyc*dxl);
					
					if(cross >= -0.01 && cross <= 0.01){
						
						m = (y2-y1)/(x2-x1);
						
						if(m > 1 || m < -1){
							if(y1 < y2){
								if(yPlus > y1 && yPlus < y2)
									collision = true;
							} else {
								if(yPlus < y1 && yPlus > y2)
									collision = true;
							}
						} else {
							if(x1 < x2){
								if(xPlus > x1 && xPlus < x2)
									collision = true;
							} else {
								if(xPlus < x1 && xPlus > x2)
									collision = true;
							}
						}
					}
					
					dxc = xMoin - x1;
					dyc = yMoin - y1;
					
					dxl = x2 - x1;
					dyl = y2 - y1;
					
					cross = dxc*dyl - dyc*dxl;
					
					if(cross >= -0.01 && cross <= 0.01){
			
						m = (y2-y1)/(x2-x1);
						
						if(m > 1 || m < -1){
							if(y1 < y2){
								if(yMoin > y1 && yMoin < y2)
									collision = true;
							} else {
								if(yMoin < y1 && yMoin > y2)
									collision = true;
							}
						} else {
							if(x1 < x2){
								if(xMoin > x1 && xMoin < x2)
									collision = true;
							} else {
								if(xMoin < x1 && xMoin > x2)
									collision = true;
							}
						}
					}
				}
				
			}
		}
		
		return collision;
	}
	
	public static boolean CollisionTest(GameObjectShape shape1, GameObjectCircle circ1){
		return CollisionTest(circ1, shape1);
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////////////

	public static boolean CollisionTest(float x, float y, Circle circ){
		float dist = (float)(Math.sqrt(Math.pow((x - circ.x), 2) + Math.pow((y - circ.y), 2)));

		if (dist < circ.radius)
			return true;
		else
			return false;
	}

	public static boolean CollisionTest(Circle circ, float x, float y){
		return CollisionTest(x, y, circ);
	}

}
