package com.tp.batman.francis.blockgame.framework.math;

public class OverlapTester {
    public static boolean overlapCircles(Circle c1, Circle c2) {
        float distance = c1.center.distSquared(c2.center);
        float radiusSum = c1.radius + c2.radius;
        return distance <= radiusSum * radiusSum;
    }
    
    public static boolean overlapRectangles(Rectangle r1, Rectangle r2) {
        if(r1.lowerLeft.x < r2.lowerLeft.x + r2.width &&
           r1.lowerLeft.x + r1.width > r2.lowerLeft.x &&
           r1.lowerLeft.y < r2.lowerLeft.y + r2.height &&
           r1.lowerLeft.y + r1.height > r2.lowerLeft.y)
            return true;
        else
            return false;
    }
    
    public static boolean overlapCircleRectangle(Circle c, Rectangle r) {
        float closestX = c.center.x;
        float closestY = c.center.y;
        
        if(c.center.x < r.lowerLeft.x) {
            closestX = r.lowerLeft.x; 
        } 
        else if(c.center.x > r.lowerLeft.x + r.width) {
            closestX = r.lowerLeft.x + r.width;
        }
          
        if(c.center.y < r.lowerLeft.y) {
            closestY = r.lowerLeft.y;
        } 
        else if(c.center.y > r.lowerLeft.y + r.height) {
            closestY = r.lowerLeft.y + r.height;
        }
        
        return c.center.distSquared(closestX, closestY) < c.radius * c.radius;           
    }
    
    public static boolean pointInCircle(Circle c, Vector2 p) {
        return c.center.distSquared(p) < c.radius * c.radius;
    }
    
    public static boolean pointInCircle(Circle c, float x, float y) {
        return c.center.distSquared(x, y) < c.radius * c.radius;
    }
    
    public static boolean pointInRectangle(Rectangle r, Vector2 p) {
        return r.lowerLeft.x <= p.x && r.lowerLeft.x + r.width >= p.x &&
               r.lowerLeft.y <= p.y && r.lowerLeft.y + r.height >= p.y;
    }
    
    public static boolean pointInRectangle(Rectangle r, float x, float y) {
        return r.lowerLeft.x <= x && r.lowerLeft.x + r.width >= x &&
               r.lowerLeft.y <= y && r.lowerLeft.y + r.height >= y;
    }
    
    
    
    public boolean overlapLineCircle(Line line, Circle circle) {
		return true;
	}

	public boolean overlapLineRectangle(Line line, Rectangle rectangle){
		float x1, y1; // top left
		float x2, y2; // top right
		float x3, y3; // bottom left
		float x4, y4; // bottom right
		
		boolean top, bottom, left, right;
		
		x1 = rectangle.x - rectangle.width/2;
		y1 = rectangle.y + rectangle.height/2;
		
		x2 = rectangle.x + rectangle.width/2;
		y2 = rectangle.y + rectangle.height/2;
				
		x3 = rectangle.x - rectangle.width/2;
		y3 = rectangle.y - rectangle.height/2;
		
		x4 = rectangle.x + rectangle.width/2;
		y4 = rectangle.y - rectangle.height/2;
		
		if(rectangle.lowerLeft.angle != 0){
			float TO_RADIANS = (1 / 180.0f) * (float) Math.PI;
			
			float rad = rectangle.lowerLeft.angle * TO_RADIANS;
			float cos = (float) Math.cos(rad);
			float sin = (float) Math.sin(rad);
			
			float newX1 = x1 * cos - y1 * sin;
			float newY1 = x1 * sin + y1 * cos;
			
			float newX2 = x2 * cos - y2 * sin;
			float newY2 = x2 * sin + y2 * cos;
			
			float newX3 = x3 * cos - y3 * sin;
			float newY3 = x3 * sin + y3 * cos;
			
			float newX4 = x4 * cos - y4 * sin;
			float newY4 = x4 * sin + y4 * cos;
			
			x1 = newX1;
			y1 = newY1;
			
			x1 = newX2;
			y1 = newY2;
			
			x1 = newX3;
			y1 = newY3;
			
			x1 = newX4;
			y1 = newY4;
		}
		
		// Top line ((x1, y1) and (x2, y2))
		top = linesIntersect(x1, y1, x2, y2, line.x1, line.y1, line.x2, line.y2);
		// Bottom line ((x3, y3) and (x4, y4))
		bottom = linesIntersect(x3, y3, x4, y4, line.x1, line.y1, line.x2, line.y2);
		// Left line ((x1, y1) and (x3, y3))
		left = linesIntersect(x1, y1, x3, y3, line.x1, line.y1, line.x2, line.y2);
		// Right line ((x2, y2) and (x4, y4))
		right = linesIntersect(x2, y2, x4, y4, line.x1, line.y1, line.x2, line.y2);
		
		return (top || bottom || left || right);
	}

	public boolean overlapLines(Line line, Line line2) {
		return linesIntersect(line.x1, line.y1, line.x2, line.y2, line2.x1, line2.y1, line2.x2, line2.y2);
	}
    
    
    
    private boolean linesIntersect(float x1, float y1, float x2, float y2,
			float x3, float y3, float x4, float y4) {
		// (x1, y1) and (x2, y2) make up the first line
		// (x3, y3) and (x4, y4) make up the second line

		return ((relativeCCW(x1, y1, x2, y2, x3, y3)
				* relativeCCW(x1, y1, x2, y2, x4, y4) <= 0) && (relativeCCW(x3,
				y3, x4, y4, x1, y1) * relativeCCW(x3, y3, x4, y4, x2, y2) <= 0));
	}
    
    private int relativeCCW(double x1, double y1, double x2, double y2,
			double px, double py) {

		x2 -= x1;
		y2 -= y1;
		px -= x1;
		py -= y1;

		double ccw = px * y2 - py * x2;
		if (ccw == 0.0) {
			
			ccw = px * x2 + py * y2;
			if (ccw > 0.0) {

				px -= x2;
				py -= y2;

				ccw = px * x2 + py * y2;

				if (ccw < 0.0) {
					ccw = 0.0;
				}
			}
		}
		return (ccw < 0.0) ? -1 : ((ccw > 0.0) ? 1 : 0);
	}
}
