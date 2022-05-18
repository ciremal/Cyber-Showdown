import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public abstract class PowerUp {
	
	protected int x, y;
	protected static final int TYPE = 0;
	protected static final int XLEN = 20;
	protected static final int YLEN = 20;
	protected Rectangle2D.Double powerUpArea;
	protected boolean spawned;
	protected int DURATION;
	static BufferedImage powerUp;
	
	public PowerUp(int x, int y) {
		this.x = x;
		this.y = y;
		spawned = false;
		
		powerUpArea = new Rectangle2D.Double(this.getX(), this.getY(), XLEN, YLEN);
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public int getDuration() {
		return DURATION;
	}
	
	public Shape getArea() {
		return powerUpArea;
	}
	
	public boolean getSpawned() {
		return spawned;
	}
	
	public int getType() {
		return TYPE;
	}
	
	public void setX(int x) {
		this.x = x;
	}
	
	public void setY(int y) {
		this.y = y;
	}
	
	public void setSpawned(boolean spawned) {
		this.spawned = spawned;
	}
	
	public void draw(Graphics2D g) {}

}
