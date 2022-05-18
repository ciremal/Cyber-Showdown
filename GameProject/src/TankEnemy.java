import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class TankEnemy extends Enemy{

	private Rectangle2D.Double enemyArea;
	static BufferedImage enemy;
	
	public TankEnemy(int x, int y, Player p, int dimension) {
		super(x, y, p, dimension);
		
		health = 2;
		speed = 0.6;
		
		try {
			enemy = ImageIO.read(this.getClass().getResource("Tank Enemy.png"));
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
	
	public Shape getArea() {
		return enemyArea;
	}
	
	public void update() {
		double xDist = Math.abs(p.getMidX() - this.x);
		double yDist = Math.abs(p.getMidY() - this.y);

		if (xDist >= yDist && xDist > 100) {
			this.setVelX();
			x += velX;
		} else if (xDist < yDist && yDist > 100) {
			this.setVelY();
			y += velY;
		} else if (xDist < 200 && yDist < 200) {
			this.setVelX();
			this.setVelY();
			x += velX;
			y += velY;
		}

		enemyArea.setRect(x, y, dimension, dimension);
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(enemy, (int) x, (int) y, (int)dimension, (int)dimension, null);
		g.setColor(Color.white);
		//g.draw(enemyArea);
	}
	

}
