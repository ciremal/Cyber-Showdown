import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class NukePowerUp extends PowerUp{

	protected static final int TYPE = 3;
	private static final int DURATION = 2;
	static BufferedImage powerUp;
	
	public NukePowerUp(int x, int y) {
		super(x, y);
		
		try {
			 powerUp = ImageIO.read(this.getClass().getResource("Nuke Perk.png"));
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
