import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Boss extends Enemy{

	private Rectangle2D.Double enemyArea;
	static BufferedImage enemy;
	int health;
	
	public Boss(int x, int y, Player p, int dimension, int gameSec, int health) {
		super(x, y, p, dimension);
		
		this.health = health;
		
		try {
			enemy = ImageIO.read(this.getClass().getResource("Boss.png"));
		} catch (IOException e) {
			System.out.println("Unknown Image");
		}
		
		enemyArea = new Rectangle2D.Double(this.getX(), this.getY(), dimension, dimension);
	}
	
	public int getHealth() {
		return this.health;
	}
	
	public void setHealth(int health) {
		this.health = health;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public Shape getArea() {
		return enemyArea;
	}
	
	public void update() {
		this.setVelX();
		this.setVelY();
		x += velX;
		y += velY;		
		enemyArea.setRect(x, y, dimension, dimension);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(enemy, (int) x, (int) y, (int)dimension, (int)dimension, null);
		g.setColor(Color.white);
		//g.draw(enemyArea);
	}

}
