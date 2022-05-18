import java.awt.Color;
import java.awt.Graphics2D;

public class FastBullet extends Bullet{
	
	protected static final double speed = 3.5; 
	protected static final Color c = Color.blue;

	public FastBullet(int x, int y, String direction) {
		super(x, y, direction);
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void draw(Graphics2D g) {
		g.setColor(c);
		g.fillRect((int) x, (int) y, 5, 5);
		g.setColor(Color.white);
		g.draw(bulletArea);
	}

	
}
