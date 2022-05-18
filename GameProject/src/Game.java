
import java.awt.*;
import java.awt.event.*;
import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.*;

class Game extends JPanel implements MouseListener, ActionListener {

	public static final int WIDTH = 1014;
	public static final int HEIGHT = 787;
	public static final int ENEMY_DIMENSION = 30;
	public static final int WAVE_TRANSITION = 3;
	public static int WAVE_LENGTH = 20;

	Timer gameTime = new Timer(1000, this);
	static int gameSec;
	static int tempSec;
	int wave = 1;

	int screen = 0;

	static BufferedImage gameScreen;
	static BufferedImage menuScreen;
	static BufferedImage howtoplayScreen;

	Player p = new Player(WIDTH / 2, HEIGHT / 2);

	WaveInfo waveInfo = new WaveInfo(ENEMY_DIMENSION, p);

	ArrayList<Enemy> enemies = new ArrayList<Enemy>();

	ArrayList<Bullet> bullets = new ArrayList<Bullet>();
	boolean shoot = false;
	boolean nukeActivated = false;
	boolean godActivated = false;
	static int bulletType = 0;
	String lastKeyPressed = "UP";

	ArrayList<PowerUp> powerUp = new ArrayList<PowerUp>();
	int perkDuration;

	static int mouseX;
	static int mouseY;

	public Game() {
		new Window(WIDTH, HEIGHT, "Game", this);
		setKeyBindings();
		this.addMouseListener(this);
	}

	public void actionPerformed(ActionEvent e) {
		requestFocus();
		gameSec += 1;
	}

	public static void images() {
		try {
			gameScreen = ImageIO.read(new File("Images/Game Screen.png"));
			menuScreen = ImageIO.read(new File("Images/Menu Screen.png"));
			howtoplayScreen = ImageIO.read(new File("Images/HowToPlayScreen.png"));
		} catch (IOException e) {
			System.out.println("Unknown Image");
		}
	}

	public void setKeyBindings() {
		ActionMap actionMap = getActionMap();
		int condition = JComponent.WHEN_IN_FOCUSED_WINDOW;
		InputMap inputMap = getInputMap(condition);

		String leftPressed = "VK_A";
		String rightPressed = "VK_D";
		String upPressed = "VK_W";
		String downPressed = "VK_S";

		String leftRelease = "VK_A_RELEASE";
		String rightRelease = "VK_D_RELEASE";
		String upRelease = "VK_W_RELEASE";
		String downRelease = "VK_S_RELEASE";

		String leftAim = "VK_LEFT";
		String rightAim = "VK_RIGHT";
		String upAim = "VK_UP";
		String downAim = "VK_DOWN";

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, false), leftPressed);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_A, 0, true), leftRelease);
		actionMap.put(leftPressed, new KeyAction(leftPressed));
		actionMap.put(leftRelease, new KeyAction(leftRelease));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, false), rightPressed);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_D, 0, true), rightRelease);
		actionMap.put(rightPressed, new KeyAction(rightPressed));
		actionMap.put(rightRelease, new KeyAction(rightRelease));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, false), upPressed);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_W, 0, true), upRelease);
		actionMap.put(upPressed, new KeyAction(upPressed));
		actionMap.put(upRelease, new KeyAction(upRelease));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, false), downPressed);
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_S, 0, true), downRelease);
		actionMap.put(downPressed, new KeyAction(downPressed));
		actionMap.put(downRelease, new KeyAction(downRelease));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_LEFT, 0, false), leftAim);
		actionMap.put(leftAim, new KeyAction(leftAim));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_RIGHT, 0, false), rightAim);
		actionMap.put(rightAim, new KeyAction(rightAim));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_UP, 0, false), upAim);
		actionMap.put(upAim, new KeyAction(upAim));

		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_DOWN, 0, false), downAim);
		actionMap.put(downAim, new KeyAction(downAim));
	}

	public class KeyAction extends AbstractAction {
		public KeyAction(String actionCommand) {
			putValue(ACTION_COMMAND_KEY, actionCommand);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			switch (e.getActionCommand()) {
			case "VK_A":
				p.setVelX(-p.getSpeed());
				break;
			case "VK_D":
				p.setVelX(p.getSpeed());
				break;
			case "VK_W":
				p.setVelY(-p.getSpeed());
				break;
			case "VK_S":
				p.setVelY(p.getSpeed());
				break;
			case "VK_A_RELEASE":
				p.setVelX(0);
				break;
			case "VK_D_RELEASE":
				p.setVelX(0);
				break;
			case "VK_W_RELEASE":
				p.setVelY(0);
				break;
			case "VK_S_RELEASE":
				p.setVelY(0);
				break;

			case "VK_LEFT":
				shoot = true;
				lastKeyPressed = "LEFT";
				if (bullets.size() <= 10) {
					addBullet();
				}
				break;
			case "VK_RIGHT":
				shoot = true;
				lastKeyPressed = "RIGHT";
				if (bullets.size() <= 10) {
					addBullet();
				}
				break;
			case "VK_UP":
				shoot = true;
				lastKeyPressed = "UP";
				if (bullets.size() <= 10) {
					addBullet();
				}
				break;
			case "VK_DOWN":
				shoot = true;
				lastKeyPressed = "DOWN";
				if (bullets.size() <= 10) {
					addBullet();
				}
				break;
			}
		}
	}

	// Main game
	public void gameLoop(Graphics2D g) {
		gameTime.start();
		g.drawImage(gameScreen, 0, 0, WIDTH - 15, HEIGHT - 36, null);

		// Only for the beginning of the game
		if (wave == 1 && gameSec <= WAVE_TRANSITION) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ariel", Font.PLAIN, 60));
			g.drawString("WAVE " + wave, WIDTH / 2 - 120, HEIGHT / 2);
			if (gameSec == 3) {
				wave++;
				gameSec = 0;
				gameTime.restart();
			}
		}

		// All game loop stuff
		if (gameSec <= WAVE_LENGTH && wave != 1) {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ariel", Font.PLAIN, 20));
			g.drawString(gameSec + "", 18, 30);
			p.draw(g);

			// Nuke power up
			if (nukeActivated) {
				if (gameSec - tempSec == perkDuration) {
					nukeActivated = false;
				}
			} else {
				spawnEnemy(g);
			}
			
			if (godActivated) {
				g.setColor(Color.magenta);
				g.fillRect(15, 80, 20, ((perkDuration - (gameSec - tempSec))) * 20);
			}

			// When a bullet has been fired
			if (shoot) {
				bulletFired(g);
			}

			// Prevents enemies from merging (Kinda)
			for (int i = 1; i < enemies.size(); i++) {
				if (testIntersection(enemies.get(i).getArea(), enemies.get(i - 1).getArea())) {
					enemies.get(i).addX(1);
					enemies.get(i - 1).addX(-1);
				}
			}

			// Tests collision with enemy and player, declares action of collision
			for (int i = 0; i < enemies.size(); i++) {
				enemies.get(i).enemyBounds();
				if (testIntersection(p.getArea(), enemies.get(i).getArea()) && godActivated) {
					Point2D p2 = waveInfo.randomEnemySpawn();
					int randX = (int) p2.getX();
					int randY = (int) p2.getY();
					enemies.get(i).setX(randX);
					enemies.get(i).setY(randY);
				}
				else if (testIntersection(p.getArea(), enemies.get(i).getArea()) && !godActivated) {
					p.setX(WIDTH / 2);
					p.setY(HEIGHT / 2);
					p.setHealth(p.getHealth() - 1);
					bulletType = 0;
					bullets.clear();
					if (wave != 11) {
						enemies.clear();
					}
					else if (wave == 11) {
						for (Enemy enemy : enemies) {
							if (enemy instanceof Boss) {
								enemy.setX(WIDTH/2);
								enemy.setY(200);
							}
							else {
								Point2D p = waveInfo.randomEnemySpawn();
								enemy.setX(p.getX());
								enemy.setY(p.getY());
							}
						}
					}
				}
			}

			// Timed power ups
			if (gameSec - tempSec == perkDuration) {
				p.setSpeed(2);
				godActivated = false;
			}

			// When player dies
			if (p.getHealth() == 0) {
				screen = 2;
				gameTime.stop();
			}
			
			if (p.getHealth() != 0 && wave == 12) {
				screen = 3;
				gameTime.stop();
			}

			spawnPowerUp(g);
			p.playerBounds();
			p.displayHealth(g, 100, 15);
			p.update();
			if (wave == 11) {
				WAVE_LENGTH = gameSec + 5;
				if (enemies.get(0).getHealth() == 0) {
					screen = 3;
				}
			}
		}

		// For transition onto the next wave
		else {
			g.setColor(Color.WHITE);
			g.setFont(new Font("Ariel", Font.PLAIN, 60));
			g.drawString("WAVE " + wave, WIDTH / 2 - 120, HEIGHT / 2);
			if (gameSec == WAVE_LENGTH + WAVE_TRANSITION) {
				wave++;
				nextLevel();
			}
		}
		repaint();
	}

	public void nextLevel() {
		enemies.clear();
		powerUp.clear();
		bullets.clear();
		waveInfo.setSpawnedPowerUp(false);
		gameSec = 0;
		nukeActivated = false;
		godActivated = false;
		p.setSpeed(2);
		p.setX(WIDTH / 2);
		p.setY(HEIGHT / 2);
		gameTime.restart();
	}

	public void spawnEnemy(Graphics2D g) {
		enemies = waveInfo.getWave(wave, g, gameSec);
	}

	public void spawnPowerUp(Graphics2D g) {
		powerUp = waveInfo.spawnPowerUp(gameSec, wave);

		if (waveInfo.getSpawnedPowerUp()) {
			if (testIntersection(powerUp.get(0).getArea(), p.getArea())) {
				if (powerUp.get(0).getType() == 1) {
					int rand = (int) (Math.random() * 2) + 1;
					bulletType = rand;
				} else if (powerUp.get(0).getType() == 2) {
					p.setSpeed(4);
				} else if (powerUp.get(0).getType() == 3) {
					nukeActivated = true;
					enemies.clear();
				} else if (powerUp.get(0).getType() == 4) {
					godActivated = true;
				}
				else if (powerUp.get(0).getType() == 5 && p.getHealth() <= 10) {
					p.setHealth(p.getHealth() + 1);
				}
				tempSec = gameSec;
				perkDuration = powerUp.get(0).getDuration();
				powerUp.remove(0);
				waveInfo.setSpawnedPowerUp(false);
			} else {
				powerUp.get(0).draw(g);
			}
		}
	}

	public void addBullet() {
		if (bulletType == 0) {
			bullets.add(new NormalBullet((int) p.getMidX(), (int) p.getMidY(), lastKeyPressed));
		} else if (bulletType == 1) {
			bullets.add(new FastBullet((int) p.getMidX(), (int) p.getMidY(), lastKeyPressed));
		} else if (bulletType == 2) {
			bullets.add(new BigBullet((int) p.getMidX(), (int) p.getMidY(), lastKeyPressed));
		}
	}

	public void bulletFired(Graphics2D g) {
		for (int i = 0; i < bullets.size(); i++) {

			bullets.get(i).draw(g);

			if (bullets.get(i).getDirection().equals("LEFT")) {
				bullets.get(i).setVelX(-bullets.get(i).getSpeed());
				bullets.get(i).setVelY(0);
			} else if (bullets.get(i).getDirection().equals("RIGHT")) {
				bullets.get(i).setVelX(bullets.get(i).getSpeed());
				bullets.get(i).setVelY(0);
			} else if (bullets.get(i).getDirection().equals("UP")) {
				bullets.get(i).setVelX(0);
				bullets.get(i).setVelY(-bullets.get(i).getSpeed());
			} else if (bullets.get(i).getDirection().equals("DOWN")) {
				bullets.get(i).setVelX(0);
				bullets.get(i).setVelY(bullets.get(i).getSpeed());
			}

			bullets.get(i).update();

			for (int n = 0; n < enemies.size(); n++) {
				if (!bullets.isEmpty()) {
					if (testIntersection(bullets.get(i).getArea(), enemies.get(n).getArea())) {
						if (enemies.get(n).getHealth() > 1) {
							enemies.get(n).setHealth(enemies.get(n).getHealth() - 1);
							bullets.get(i).setHit(true);
						} else {
							if (enemies.get(0) instanceof Boss) {
								screen = 3;
							}
							else {
								Point2D p2 = waveInfo.randomEnemySpawn();
								int randX = (int) p2.getX();
								int randY = (int) p2.getY();
								enemies.get(n).setX(randX);
								enemies.get(n).setY(randY);
								bullets.get(i).setHit(true);
								if (enemies.get(n) instanceof TankEnemy) {
									enemies.get(n).setHealth(2);
								}
							}
						}
					}
				}
			}

			if (bullets.get(i).getHit()) {
				bullets.remove(i);
			} else if (bullets.get(i).getX() <= 49 || bullets.get(i).getX() >= WIDTH - 64) {
				bullets.remove(i);
			} else if (bullets.get(i).getY() <= 49 || bullets.get(i).getY() >= HEIGHT - 89) {
				bullets.remove(i);
			}
		}
	}

	public static boolean testIntersection(Shape shapeA, Shape shapeB) {
		Area areaA = new Area(shapeA);
		areaA.intersect(new Area(shapeB));
		return !areaA.isEmpty();
	}

	// Paint component
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		Graphics2D g2d = (Graphics2D) g;
		if (screen == 0) {
			menu(g2d);
		}
		else if (screen == 1) {
			gameLoop(g2d);
		} 
		else if (screen == 2) {
			gameOverScreen(g2d);
		}
		else if (screen == 3) {
			winScreen(g2d);
		}
		else if (screen == 4) {
			howToPlayScreen(g2d);
		}
	}
	
	public void menu(Graphics2D g) {
		g.drawImage(menuScreen, 0, 0, WIDTH - 15, HEIGHT - 36, null);
		
		if (mouseX >= 371 && mouseX <= 639 && mouseY >= 232 && mouseY <= 347) {
			screen = 1;
		}
		
		else if (mouseX >= 684 && mouseX <= 955 && mouseY >= 232 && mouseY <= 347) {
			System.exit(0);
		}
		
		else if (mouseX >= 57 && mouseX <= 325 && mouseY >= 232 && mouseY <= 347) {
			screen = 4;
		}
		
		repaint();
	}

	private void gameOverScreen(Graphics2D g) {
		g.drawImage(gameScreen, 0, 0, WIDTH - 15, HEIGHT - 36, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel", Font.PLAIN, 60));
		g.drawString("GAME OVER", WIDTH / 2 - 190, HEIGHT / 2);
	}
	
	private void winScreen(Graphics2D g) {
		g.drawImage(gameScreen, 0, 0, WIDTH - 15, HEIGHT - 36, null);
		g.setColor(Color.WHITE);
		g.setFont(new Font("Ariel", Font.PLAIN, 60));
		g.drawString("CONGRATS YOU WIN", WIDTH / 2 - 300, HEIGHT / 2);
	}
	
	private void howToPlayScreen(Graphics2D g) {
		g.drawImage(howtoplayScreen, 0, 0, WIDTH - 15, HEIGHT - 36, null);
		
		if (mouseX >= 62 && mouseX <= 256 && mouseY >= 621 && mouseY <= 711) {
			screen = 0;
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent evt) {
		mouseX = evt.getX();
		mouseY = evt.getY();
		System.out.println(mouseX + " " + mouseY);
	}

	public void mouseClicked(MouseEvent e) {
	}

	public void mouseReleased(MouseEvent e) {
	}

	public void mouseEntered(MouseEvent e) {
	}

	public void mouseExited(MouseEvent e) {
	}

}