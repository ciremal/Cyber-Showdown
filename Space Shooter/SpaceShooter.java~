/* Eric Lam
 * 6/12/2020
 * The game that has been created is called Asteroid Shooter. It is a space shooting game where you, the player, plays
 * as a spaceship, and has to survive against asteroids falling down the screen. The spaceship is able to shoot the
 * asteroids with bullets that come out of the spaceship. The objective of the game is to survive for 120 seconds 
 * without destroying the spaceship. The spaceship has 3 lives, and there are perks that will drop from the screen,
 * allowing for upgrades to the spaceships bullets. The game contains various screens like the menu, controls, and 
 * how to play screen. 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.*; 
import java.io.File;
import javax.imageio.*;
import java.io.IOException;
import java.awt.image.*;

class SpaceShooter extends JPanel implements KeyListener, ActionListener, MouseListener{
  
  //Final variables for screen width and height
  public static final int WIDTH = 1000;
  public static final int HEIGHT = 950;
  
  //Variables to track the mouses x and y coordinates
  static int mouseX;
  static int mouseY;
  
  //Variables for images 
  static BufferedImage gameScreen, spaceship, menu, options, controls, howToPlay1, howToPlay2,
    threeLives, twoLives, oneLives, winScreen, loseScreen;
  
  //Timer variables
  Timer time = new Timer (1000, this);;
  static int sec; //Variable for seconds 
  
  //Player and bullet objects 
  Player p = new Player (WIDTH/2, 800);
  Bullet b;
  LongBullet longB;
  FastBullet fastB;
  BigBullet bigB;
  
  //Variables used for the bullets
  double bulletVel;
  double bulletY;
  
  //Perk x and y coordinates
  int perkX = (int)(Math.random() * 900);
  int perkY = (int)(Math.random() * - 1000) - 10;
  
  //Variable for screen type, bullet type, lives and number of asteroids hit
  int screen = 0;
  int bulletType = 0;
  int lives = 2;
  int asteroidsHit = 0;
  
  //Boolean variable for shooting
  boolean shoot = false;
  
  //Array to store asteroids and rectangle variables 
  Shape asteroids[];
  Rectangle2D.Double bulletArea, shipArea, perk;
  
  //Constructor: adds keylistener, mouselistener, initializes array of asteroids and seconds
  public SpaceShooter() {
    setFocusable(true);
    addKeyListener(this);
    this.addMouseListener(this);
    asteroids = generateAsteroids();
    sec = 0;
  }
  
  //Action performed method: Requests focus to keys and increases seconds
  public void actionPerformed (ActionEvent e){
    requestFocus();
    sec += 1;
  }
  
  //Method to load images
  public static void images(){
    try{
      gameScreen = ImageIO.read(new File ("GameScreen.png"));
      spaceship = ImageIO.read(new File ("SpaceShip.png"));
      menu = ImageIO.read(new File ("MainMenu.png"));
      options = ImageIO.read(new File ("Options.png"));
      controls = ImageIO.read(new File ("ControlScreen.png"));
      threeLives = ImageIO.read(new File("Hearts(three).png"));
      twoLives = ImageIO.read(new File("Hearts(two).png"));
      oneLives = ImageIO.read(new File("Hearts(one).png"));
      howToPlay1 = ImageIO.read(new File("HowToPlay1.png"));
      howToPlay2 = ImageIO.read(new File("HowToPlay2.png"));
      winScreen = ImageIO.read(new File("WinScreen.png"));
      loseScreen = ImageIO.read(new File("LoseScreen.png"));
    }
    catch  (IOException e){
      System.out.println("Unknown Image");
    }
  }
  
  //Main game
  public void gameLoop(Graphics2D g){
    time.start(); //Starts timer
    
    //Draws perks, background, spaceship, and invisible area around spaceship
    g.setColor(Color.BLACK);
    perk = new Rectangle2D.Double(perkX, perkY, 30, 30);
    g.drawImage(gameScreen, 0, 0, WIDTH, HEIGHT, null);
    shipArea = new Rectangle2D.Double(p.getX(), p.getY(), 55, 96);
    p.draw(g); 
    g.draw(shipArea);
    
    //Calling various methods
    p.update();
    hearts(g);
    shipBounds();
    statDisplay(g);
    
    //If player has shot a bullet, call the appropriate bullet method
    if (shoot == true){
      if (bulletType == 0){
        bullet(g);
      }
      else if (bulletType == 1){
        longBullet(g);
      }
      else if (bulletType == 2){
        fastBullet(g);
      }
      else if (bulletType == 3){
        bigBullet(g);
      }
    }
    
    //After 20 seconds, calls method to load perks
    if (sec >= 20){
      loadPerk(g);
    }
    
    //For loop which tests collision between spaceship, bullets, and asteroids
    for (int i = 0; i < asteroids.length; i++){
      Ellipse2D.Double temp = (Ellipse2D.Double)asteroids[i];
      //If the ship and asteroid collide and the ship still has lives, relocate asteroid that hit ship and
      //decrease lives by 1
      if (temp.y >= shipArea.y && testIntersection(temp, shipArea) == true){
        if (lives != 0){
          temp.x = (int)(Math.random() * 900);
          temp.y = (int)(Math.random() * - 1000) - 10;
          lives--;
        }
        //If the ship and asteroid collide on ships last live, stops timer and ends game
        else{
          mouseX = 0;
          mouseY = 0;
          time.stop();
          //Goes to screen 6 (win screen) if game ends over 120 seconds
          if (sec >= 120){
            screen = 6;
          }
          //Goes to screen 7 (lose screen) if game ends under 120 seconds
          else if (sec < 120){
            screen = 7;
          }
        }
      }
      //If an asteroid reaches the buttom of the screen, relocate the asteroid back to the top
      if (temp.y > HEIGHT){
        temp.x = (int)(Math.random() * 900);
        temp.y = (int)(Math.random() * - 1000) - 10;
      }
      //If asteroid and bullet collisde, erase bullet (unless bullet type number 1) and relocate asteroid that was hit.
      //increase the number of asteroids hit by 1
      if (shoot == true){
        if (temp.y >= bulletY && testIntersection(temp, bulletArea) == true){
          temp.x = (int)(Math.random() * 900);
          temp.y = (int)(Math.random() * - 1000) - 10;
          asteroidsHit++;
          if (bulletType != 1){
            shoot = false;
          }
        }
      }
      //Increase asteroids y coordinate by 2 pixels
      temp.y = temp.y + 2;  
    }
    //Repaints the screen
    repaint();
  }
  
  //Method for the menu screen
  public void menuScreen(Graphics2D g){
    g.drawImage(menu, 0, 0, WIDTH, HEIGHT, null); //draws menu screen
    
    //Calls screen 1 (game screen) if mouse is clicked in given area, resets mouse coordinates
    if (mouseX > 302 && mouseX < 697 && mouseY > 370 && mouseY < 497){
      mouseX = 0;
      mouseY = 0;
      screen = 1;
    }
    //Calls screen 2 (options screen) if mouse is clicked in given area, resets mouse coordinates
    else if (mouseX > 302 && mouseX < 697 && mouseY > 564 && mouseY < 692){
      mouseX = 0;
      mouseY = 0;
      screen = 2;
    }
    
    //Exits game if mouse is clicked in given area
    else if (mouseX > 302 && mouseX < 698 && mouseY > 758 && mouseY < 887){
      System.exit(0);
    }
  }
  
  //Method for options screen
  public void optionScreen(Graphics2D g){
    g.drawImage(options, 0, 0, WIDTH, HEIGHT, null); //draws options screen
    
    //Calls screen 3 (first how to play screen) if mouse is clicked in given area, resets mouse coordinates
    if (mouseX > 301 && mouseX < 697 && mouseY > 301 && mouseY < 428){
      mouseX = 0;
      mouseY = 0;
      screen = 3;
    }
    //Calls screen 5 (controls screen) if mouse is clicked in given area, resets mouse coordinates
    else if (mouseX > 301 && mouseX < 697 && mouseY > 520 && mouseY < 648){
      mouseX = 0;
      mouseY = 0;
      screen = 5;
    }
    //Calls screen 0 (menu screen) if mouse is clicked in given area, resets mouse coordinates
    else if (mouseX > 30 && mouseX < 266 && mouseY > 765 && mouseY < 883){
      mouseX = 0;
      mouseY = 0;
      screen = 0;
    }
  }
  
  //Method for first how to play screen
  public void firstHowtoplayScreen(Graphics2D g){
    g.drawImage(howToPlay1, 0, 0, WIDTH, HEIGHT, null); //draws screen
    
    //Calls screen 4 (second how to play screen) if mouse is clicked in given area, resets mouse coordinates
    if (mouseX > 727 && mouseX < 965 && mouseY > 781 && mouseY < 898){
      screen = 4;
    }
    //Calls screen 2 (options screen) if mouse is clicked in given area, resets mouse coordinates
    else if (mouseX > 30 && mouseX < 266 && mouseY > 781 && mouseY < 898){
      mouseX = 0;
      mouseY = 0;
      screen = 2;
    }
  }
  
  //Method for second how to play screen 
  public void secondHowtoplayScreen(Graphics2D g){
    g.drawImage(howToPlay2, 0, 0, WIDTH, HEIGHT, null); //draws screen
    
    //Calls screen 3 (first how to play screen) if mouse is clicked in given area, resets mouse coordinates
    if (mouseX > 30 && mouseX < 266 && mouseY > 781 && mouseY < 898){
      mouseX = 0;
      mouseY = 0;
      screen = 3;
    }
  }
  
  //Method for controls screen
  public void controlScreen (Graphics2D g){
    g.drawImage(controls, 0, 0, WIDTH, HEIGHT, null); //draws screen
    
    //Calls screen 2 (options screen) if mouse is clicked in given area, resets mouse coordinates
    if (mouseX > 28 && mouseX < 267 && mouseY > 781 && mouseY < 897){
      mouseX = 0;
      mouseY = 0;
      screen = 2;
    }
  }
  
  //Method for winner screen
  public void winnerScreen (Graphics2D g){
    //Draws screen, strings, displays time survived and number of asteroids destroyed
    g.drawImage(winScreen, 0, 0, WIDTH, HEIGHT, null); 
    g.setColor(Color.WHITE);
    g.setFont(new Font("Ariel", Font.PLAIN, 60));
    g.drawString(sec + "", WIDTH/2 - 200 , HEIGHT/2 - 50); 
    g.drawString(asteroidsHit + "", WIDTH/2 + 178, HEIGHT/2 + 5);
    
    //Calls screen 1 (game screen) if mouse is clicked in given area
    if (mouseX > 88 && mouseX < 464 && mouseY > 590 && mouseX < 854){
      resetGame();
    }
    //Exits game if mouse is clicked in given area
    else if (mouseX > 535 && mouseX < 913 && mouseY > 590 && mouseX < 854){
      System.exit(0);
    }
  }
  
  //Method for losing screen
  public void loserScreen (Graphics2D g){
    //Draws screen, strings, displays time survived and number of asteroids destroyed
    g.drawImage(loseScreen, 0, 0, WIDTH, HEIGHT, null);
    g.setColor(Color.WHITE);
    g.setFont(new Font("Ariel", Font.PLAIN, 60));
    g.drawString(sec + "", WIDTH/2 - 200 , HEIGHT/2 - 50); 
    g.drawString(asteroidsHit + "", WIDTH/2 + 178, HEIGHT/2 + 5);
    
        //Calls screen 1 (game screen) if mouse is clicked in given area
    if (mouseX > 88 && mouseX < 464 && mouseY > 590 && mouseX < 854){
      resetGame();
    }
        //Exits game if mouse is clicked in given area
    else if (mouseX > 535 && mouseX < 913 && mouseY > 590 && mouseX < 854){
      System.exit(0);
    }
  }
  
  //Method to generate asteroids
  public Shape[] generateAsteroids(){
    //Array of size 10 to store asteroids
    Shape[] asteroids = new Shape[10];
    //Variables for asteroids x and y coordinates
    int asteroidX, asteroidY;
    
    //Assigns random x and y coordinates for each asteroids, adds it to array
    for (int i=0; i < asteroids.length; i++){
      asteroidX = (int)(Math.random() * 900);
      asteroidY = (int)(Math.random() * - 1000) - 10;
      asteroids[i]= new Ellipse2D.Double(asteroidX, asteroidY, 50, 50);            
    }
    return asteroids;        
  }
  
  //Method to test collision 
  public static boolean testIntersection(Shape shapeA, Shape shapeB) {
    Area areaA = new Area(shapeA);
    areaA.intersect(new Area(shapeB));
    return !areaA.isEmpty();
  }
  
  //Method to set boundaries so ship does not go off screen 
  public void shipBounds(){
    if (p.getX() < 0){
      p.setX(1);
    }
    if (p.getX() + 60 > WIDTH){
      p.setX(-1);
    }
    if (p.getY() < 0){
      p.setY(1);
    }
    if (p.getY() + 130 > HEIGHT){
      p.setY(-1);
    }
  }
  
  //Method to displays the time and number of asteroids hit
  public void statDisplay(Graphics2D g){
    g.setColor(Color.WHITE);
    g.setFont(new Font("Ariel", Font.PLAIN, 40));
    g.drawString("Time: " + sec + "", WIDTH - 230 , 50); 
    g.drawString("Asteroids Hit: " + asteroidsHit + "", WIDTH - 300 , 100); 
  }
  
  //Method to load perks
  public void loadPerk(Graphics2D g){
    //Draws perk
    g.setColor(Color.GREEN);
    g.fill(perk);
    //Increases perk y coordinate by 1 pixel
    perkY += 1;
    
    //If perk reaches the bottom of screen, relocate perk
    if (perk.y > HEIGHT){
      perkX = (int)(Math.random() * 900);
      perkY = -4000;
    }
    //If perk and ship collide, change bullet type and relocate perk
    if (perk.y >= shipArea.y && testIntersection(perk, shipArea) == true){
      shoot = false;
      bulletType = (int)(Math.random() * 3) + 1;
      perkX = (int)(Math.random() * 900);
      perkY = -4000;
    }
  }
  
  //Method to display the number of lives the ship has
  public void hearts(Graphics2D g){
    //Draws the number of hearts depending on the number of lives
    if (lives == 2){
      g.drawImage(threeLives, 0, 5, 200, 50, null);
    }
    else if (lives == 1){
      g.drawImage(twoLives, 0, 5, 200, 50, null);
    }
    else if (lives == 0){
      g.drawImage(oneLives, 0, 5, 200, 50, null);
    }
  }
  
  //Method to reset the game to initial state
  public void resetGame(){
    mouseX = 0;
    mouseY = 0;
    asteroids = generateAsteroids();
    p.setX(-p.getX() + WIDTH/2 - 50);
    p.setY(-p.getY() + 800);
    perkY = -10;
    shoot = false;
    bulletType = 0;
    lives = 2;
    sec = 0;
    asteroidsHit = 0;
    screen = 1;
    time.restart();
  }
  
  //Method that draws and updates the bullet
  public void bullet(Graphics2D g){
    g.setColor(Color.BLACK);
    b.draw(g);
    g.draw(bulletArea);
    b.update();
    bulletVel = b.getVel();
    bulletArea.y -= bulletVel;
    bulletY = bulletArea.y;
    if (b.getY() < -50){ //If bullet goes off screen, erase bullet
      shoot = false;
    }
  }
  
  //Method that draws and updates the Long bullet
  public void longBullet(Graphics2D g){
    g.setColor(Color.BLACK);
    longB.draw(g);
    g.draw(bulletArea);
    longB.update();
    bulletArea.y -= 2;
    bulletY = bulletArea.y;
    if (longB.getY() < -50){ //If bullet goes off screen, erase bullet
      shoot = false;
    }
  }
  
  //Method that draws and updates the Fast bullet
  public void fastBullet(Graphics2D g){
    g.setColor(Color.BLACK);
    fastB.draw(g);
    g.draw(bulletArea);
    fastB.update();
    bulletArea.y -= 4;
    bulletY = bulletArea.y;
    if (fastB.getY() < -50){ //If bullet goes off screen, erase bullet
      shoot = false;
    }
  }
  
  //Method that draws and updates the Big bullet
  public void bigBullet(Graphics2D g){
    g.setColor(Color.BLACK);
    bigB.draw(g);
    g.draw(bulletArea);
    bigB.update();
    bulletArea.y -= 2;
    bulletY = bulletArea.y;
    if (bigB.getY() < -50){ //If bullet goes off screen, erase bullet
      shoot = false;
    }
  }
  
  //Paint component
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2d = (Graphics2D) g;
    
    //Draws all the screens
    if (screen == 0){
      menuScreen(g2d);
    }
    else if (screen == 1){
      gameLoop(g2d);
      g2d.setPaint(Color.GRAY); 
      for(int i = 0; i < asteroids.length; i++){
        Ellipse2D.Double temp = (Ellipse2D.Double)asteroids[i];                    
        g2d.fill(temp);  
      }
    }
    else if (screen == 2){
      optionScreen(g2d);
    }
    else if (screen == 3){
      firstHowtoplayScreen(g2d);
    }
    else if (screen == 4){
      secondHowtoplayScreen(g2d);
    }
    else if (screen == 5){
      controlScreen(g2d);
    }
    else if (screen == 6){
      winnerScreen(g2d);
    }
    else if (screen == 7){
      loserScreen(g2d);
    }
    repaint();
  }
  
  //Key pressed method
  public void keyPressed(KeyEvent evt){
    switch(evt.getKeyCode()) {
      //Key movements for the spaceship
      case KeyEvent.VK_UP: 
        p.setVelY(-1);
        break;
      case KeyEvent.VK_DOWN:
        p.setVelY(1); 
        break;
      case KeyEvent.VK_LEFT:
        p.setVelX(-1);
        break;
      case KeyEvent.VK_RIGHT:
        p.setVelX(1);
        break;
      case KeyEvent.VK_SPACE: //Fires a bullet of space is pressed
        if (shoot != true){
        shoot = true;
        //Draws the appropriate bullet 
        if (bulletType == 0){
          b = new Bullet(p.getX() + 16, p.getY() - 50);
          bulletArea = new Rectangle2D.Double(b.getX(), b.getY(), 30, 60);
        }
        else if (bulletType == 1){
          longB = new LongBullet(p.getX() + 16, p.getY() - 50);
          bulletArea = new Rectangle2D.Double(longB.getX(), longB.getY(), 30, 60);
        }
        else if (bulletType == 2){
          fastB = new FastBullet(p.getX() + 16, p.getY() - 50);
          bulletArea = new Rectangle2D.Double(fastB.getX(), fastB.getY(), 30, 60);
        }
        else if (bulletType == 3){
          bigB = new BigBullet(p.getX() + 4, p.getY() - 50);
          bulletArea = new Rectangle2D.Double(bigB.getX(), bigB.getY(), 50, 80);
        }
      }
    }
  }
  
  //Key released method
  public void keyReleased(KeyEvent evt) {
    switch(evt.getKeyCode()) {
      case KeyEvent.VK_UP: 
        p.setVelY(0);
        break;
      case KeyEvent.VK_DOWN:
        p.setVelY(0); 
        break;
      case KeyEvent.VK_LEFT:
        p.setVelX(0);
        break;
      case KeyEvent.VK_RIGHT:
        p.setVelX(0);
        break;
    }
  }
  
  //Mouse pressed method, used to track where the mouse is pressed
  public void mousePressed(MouseEvent evt) {
    mouseX = evt.getX();
    mouseY = evt.getY();
    System.out.println (mouseX + " " + mouseY);
  }
  
  //Unused methods
  public void keyTyped(KeyEvent evt) {}
  public void mouseEntered(MouseEvent evt) {}
  public void mouseReleased(MouseEvent evt) {}
  public void mouseClicked(MouseEvent evt) {}
  public void mouseExited(MouseEvent evt) {}
  
  //Main method
  public static void main(String[] args) { 
    SpaceShooter game = new SpaceShooter(); //Constructs game
    
    //JFrame
    JFrame frame = new JFrame("Space Shooter");
    frame.setLayout(new BorderLayout());
    frame.setPreferredSize(new Dimension(WIDTH, HEIGHT));
    frame.add(game, BorderLayout.CENTER);
    frame.pack();
    frame.setResizable(false);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setVisible(true);
    //Loads images
    images();
  }
  
}