import java.awt.*;
import java.io.File;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.*;

//Class for the player
public class Player {
  
  //Variables for the players x, y, x-velocity and y-velocity
  private int x, y;
  private int velX = 0;
  private int velY = 0;
  
  //Image variable for the ship
  static BufferedImage spaceship;
  
  //Constructor
  public Player(int x, int y) { 
    this.x = x;
    this.y = y;
    
    try{
      spaceship = ImageIO.read(new File ("SpaceShip.png"));
    }
    catch  (IOException e){
      System.out.println("Unknown Image");
    }
    
  }
  
  //Gets the current x
  public int getX(){
    return x;
  }
  
  //Gets the current y
  public int getY(){
    return y;
  }
  
  //Sets the x
  public void setX(int x){
    this.x += x;
  }
  
  //Sets the y
  public void setY(int y){
    this.y += y;
  }
  
  //Updates the x and y variables for movement
  public void update(){
    x += velX;
    y += velY;
  }
  
  //Sets x-velocity 
  public void setVelX(int velX){
    this.velX = velX;
  }
  
  //Sets y-velocity
  public void setVelY(int velY){
    this.velY = velY;
  }
  
  //Draws the ship
  public void draw(Graphics g){
    g.drawImage(spaceship, x, y, 55, 96, null); 
  }
  
}
