import java.awt.*;
import java.io.File;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.*;

//Class for the bullet
public class Bullet {
  
  //Variables for bullet x, y, velocity, and bufferediamge
  private double x, y;
  private double vel = 2;
  static BufferedImage bullet;
  
  //Constructor
  public Bullet(int x, int y) { 
    this.x = x;
    this.y = y;
    
    try{
      bullet = ImageIO.read(new File ("Bullet.png"));
    }
    catch  (IOException e){
      System.out.println("Unknown Image");
    }
  }
  
  //Updates the y coordinate constantly 
  public void update(){
    y -= vel;
  }
  
  //Gets the current y coordinate
  public double getY(){
    return y;
  }
  
  //Gets the current x coordinate
  public double getX(){
    return x;
  }
  
  //Gets the current velocity 
  public double getVel(){
    return vel;
  }
  
  //Draws the image
  public void draw(Graphics g){
    g.drawImage(bullet, (int)x, (int)y, 30, 60, null);
  }
  
}
