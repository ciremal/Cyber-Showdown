
import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

//Class for the player
public abstract class Enemy {

	// Variables for the players x, y, x-velocity and y-velocity
	protected double x, y;
	protected double speed;
	protected double velX, velY = 0;
	protected double dimension;
	protected Player p;
	protected Rectangle2D.Double enemyArea;
	protected int health;
	static BufferedImage enemy;

	// Constructor
	public Enemy(int x, int y, Player p, int dimension) {
		this.x = x;
		this.y = y;
		this.p = p;
		this.dimension = dimension;
	}

	// Gets the current x
	public double getX() {
		return x;
	}

	// Gets the current y
	public double getY() {
		return y;
	}

	public Shape getArea() {
		return enemyArea;
	}

	public double getSpeed() {
		return speed;
	}
	
	public double getDimension() {
		return dimension;
	}
	
	public int getHealth() {
		return health;
	}

	// Sets the x
	public void setX(double x) {
		this.x = x;
	}

	// Sets the y
	public void setY(double y) {
		this.y = y;
	}

	public void addX(double x) {
		this.x += x;
	}

	public void addY(double y) {
		this.y += y;
	}

	// Sets x-velocity
	public void setVelX() {
		if (p.getMidX() > this.x) {
			this.velX = speed;
		} else if (p.getMidX() < this.x) {
			this.velX = -speed;
		} else if (p.getMidX() == this.x) {
			this.velX = 0;
		}
	}

	// Sets y-velocity
	public void setVelY() {
		if (p.getMidY() > this.y) {
			this.velY = speed;
		} else if (p.getMidY() < this.y) {
			this.velY = -speed;
		} else if (p.getMidY() == this.y) {
			this.velY = 0;
		}
	}
	
	public void enemyBounds() {
		if (this.x < 425 && this.y > -dimension && this.y < 50) { // Upper door left wall
			this.addX(this.speed);
		}
		if (this.x > 575 - dimension && this.y > -dimension && this.y < 50) { // Upper door right wall
			this.addX(-this.speed);
		}

		if (this.x < 425 && this.y > 700 - dimension && this.y < 750) { // Lower door left wall
			this.addX(this.speed);
		}
		if (this.x > 575 - dimension && this.y > 700 - dimension && this.y < 750) { // Lower door right wall
			this.addX(-this.speed);
		}

		if (this.y < 300 && this.x < 50 && this.x > -dimension) { // Left door upper wall
			this.addY(this.speed);
		}
		if (this.y > 450 - dimension && this.x < 50 && this.x > -dimension) { // Left door lower wall
			this.addY(-this.speed);
		}

		if (this.y < 300 && this.x > 950 - dimension && this.x < 1000) { // Right door upper wall
			this.addY(this.speed);
		}
		if (this.y > 450 - dimension && this.x > 950 && this.x < 1000) { // Right door lower wall
			this.addY(-this.speed);
		}
	}

	// Updates the x and y variables for movement
	public void update() {}
	
	public void setHealth(int health) {}
	
	public void setSpeed(double speed) {}

	public void draw(Graphics2D g) {}
}
