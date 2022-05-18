import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;

public class BigBullet extends Bullet{
	
	protected Rectangle2D.Double bulletArea;
	protected static final Color c = Color.red;

	public BigBullet(int x, int y, String direction) {
		super(x, y, direction);
		bulletArea = new Rectangle2D.Double(this.getX(), this.getY(), 30, 30);
	}
	
	public void update() {
		x += velX;
		y += velY;
		bulletArea.setRect(x, y, 30, 30);
	}
	
	public Shape getArea() {
		return bulletArea;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, 30, 30);
		g.setColor(Color.white);
		g.draw(bulletArea);
	}
	

}
