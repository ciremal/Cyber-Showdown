import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class SpeedPowerUp extends PowerUp{

	protected static final int TYPE = 2;
	private static final int DURATION = 3;
	static BufferedImage powerUp;
	
	public SpeedPowerUp(int x, int y) {
		super(x, y);
		
		try {
			 powerUp = ImageIO.read(this.getClass().getResource("Speed Perk.png"));
		} catch (IOException e) {
			System.out.println("Unknown Image");
		}
		
		powerUpArea = new Rectangle2D.Double(this.getX(), this.getY(), XLEN, YLEN);
		
	}
	
	public Shape getArea() {
		return powerUpArea;
	}
	
	public int getType() {
		return TYPE;
	}
	
	public int getDuration() {
		return DURATION;
	}
	
	public void draw(Graphics2D g) {
		g.drawImage(powerUp, x, y, XLEN, YLEN, null);
		g.setColor(Color.white);
		//g.draw(powerUpArea);
	}

}
