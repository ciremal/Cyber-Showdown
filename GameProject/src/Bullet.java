import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class Bullet {

	protected double x, y;
	protected double velX = 0;
	protected double velY = -2;
	protected static final Color c = Color.orange;
	protected String direction;
	protected static final double speed = 1.5; 
	protected boolean hit;
	protected Rectangle2D.Double bulletArea;

	public Bullet(int x, int y, String direction) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.hit = false;
		bulletArea = new Rectangle2D.Double(this.getX(), this.getY(), 5, 5);
	}

	public void update() {
		x += velX;
		y += velY;
		bulletArea.setRect(x, y, 5, 5);
	}

	public double getX() {
		return x;
	}
	
	public double getY() {
		return y;
	}
	
	public Shape getArea() {
		return bulletArea;
	}

	public double getVelX() {
		return velX;
	}

	public double getVelY() {
		return velY;
	}

	public String getDirection() {
		return direction;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public boolean getHit() {
		return hit;
	}
	
	public void setVelX(double velX) {
		this.velX = velX;
	}

	public void setVelY(double velY) {
		this.velY = velY;
	}

	public void setDirection(String direction) {
		this.direction = direction;
	}
	
	public void setHit(boolean hit) {
		this.hit = hit;
	}

	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, 5, 5);
		g.setColor(Color.white);
		//g.draw(bulletArea);
	}

}
