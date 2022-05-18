import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class AdvancedEnemy extends Enemy{

	private Rectangle2D.Double enemyArea;
	static BufferedImage enemy;
	
	public AdvancedEnemy(int x, int y, Player p, int dimension) {
		super(x, y, p, dimension);
		
		health = 1;
		speed = 0.8;
		
		try {
			enemy = ImageIO.read(this.getClass().getResource("Advanced Enemy.png"));
		} catch (IOException e) {
			System.out.println("Unknown Image");
		}
		
		enemyArea = new Rectangle2D.Double(this.getX(), this.getY(), dimension, dimension);
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
