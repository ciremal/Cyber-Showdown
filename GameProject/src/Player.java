import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

//Class for the player
public class Player {

	// Variables for the players x, y, x-velocity and y-velocity
	private double x, y;
	private double velX, velY = 0;
	static BufferedImage player;
	static BufferedImage heart;
	private static final int XLEN = 42;
	private static final int YLEN = 49;
	private double speed = 2;
	private int health = 3;
	private Rectangle2D.Double playerArea;

	public Player(int x, int y) {
		this.x = x;
		this.y = y;

		try {
			player = ImageIO.read(this.getClass().getResource("Player.png"));
			heart = ImageIO.read(this.getClass().getResource("Heart.png"));
		} catch (IOException e) {
			System.out.println("Unknown Image");
		}

		playerArea = new Rectangle2D.Double(this.getX(), this.getY(), XLEN, YLEN);
	}

	// Gets the current x
	public double getX() {
		return x;
	}

	// Gets the current y
	public double getY() {
		return y;
	}

	public double getMidX() {
		double midX = ((this.getX() + 25) + this.getX()) / 2;
		return midX;
	}

	public double getMidY() {
		double midY = ((this.getY() + 25) + this.getY()) / 2;
		return midY;
	}

	public Shape getArea() {
		return playerArea;
	}
	
	public double getVelX() {
		return velX;
	}
	
	public double getVelY() {
		return velY;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public int getHealth() {
		return health;
	}

	public void addX(double x) {
		this.x += x;
	}

	public void addY(double y) {
		this.y += y;
	}

	// Sets the x
	public void setX(double x) {
		this.x = x;
	}

	// Sets the y
	public void setY(double y) {
		this.y = y;
	}

	// Sets x-velocity
	public void setVelX(double velX) {
		this.velX = velX;
	}

	// Sets y-velocity
	public void setVelY(double velY) {
		this.velY = velY;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void playerBounds() {
		if (this.getX() < 50) {
			this.addX(this.speed);
		}
		if (this.getX() + 106 > Game.WIDTH) {
			this.addX(-this.speed);
		}
		if (this.getY() < 50) {
			this.addY(this.speed);
		}
		if (this.getY() + 135 > Game.HEIGHT) {
			this.addY(-this.speed);
		}
	}

	// Updates the x and y variables for movement
	public void update() {
		x += velX;
		y += velY;
		playerArea.setRect(x, y, XLEN, YLEN);	
	}
	
	public void displayHealth(Graphics2D g, int x, int y) {
		for (int i = 1; i <= this.health; i++) {
			g.drawImage(heart, x + 25*i, y, 22, 19, null);
		}
	}

	public void draw(Graphics2D g) {
		g.drawImage(player, (int) x, (int) y, XLEN, YLEN, null);
		g.setColor(Color.white);
		//g.draw(playerArea);
	}
}
