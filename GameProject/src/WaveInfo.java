import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class WaveInfo {

	private int dimension;
	public Player p;
	private ArrayList<Enemy> enemies = new ArrayList<Enemy>();
	private ArrayList<PowerUp> powerUp = new ArrayList<PowerUp>();
	private boolean spawned;
	private int tempSec;
	private int bossHealth = 50;

	public WaveInfo(int dimension, Player p) {
		this.dimension = dimension;
		this.p = p;
		spawned = false;
	}

	public ArrayList<Enemy> getWave(int wave, Graphics2D g, int gameSec) {
		
		if (enemies.size() < 3 + wave && wave <= 3) { // Waves 1 and 2
			Point2D p2 = randomEnemySpawn();
			int randX = (int) p2.getX();
			int randY = (int) p2.getY();
			enemies.add(new BasicEnemy(randX, randY, p, dimension));
		}

		else if (enemies.size() < 6 && wave == 4) { // Wave 3
			int n = 0;
			for (Enemy enemy : enemies) {
				if (enemy instanceof AdvancedEnemy) {
					n++;
				}
			}
			if (n < 2) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new AdvancedEnemy(randX, randY, p, dimension));
			} else {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new BasicEnemy(randX, randY, p, dimension));
			}
		}

		else if (enemies.size() < 1 + wave && wave <= 6 && wave > 4) { // Wave 4 and 5
			Point2D p2 = randomEnemySpawn();
			int randX = (int) p2.getX();
			int randY = (int) p2.getY();
			int randX2 = (int) p2.getX();
			int randY2 = (int) p2.getY();
			enemies.add(new BasicEnemy(randX, randY, p, dimension));
			enemies.add(new AdvancedEnemy(randX2, randY2, p, dimension));
		}

		else if (enemies.size() < 8 && wave == 7) { // Wave 6
			int t = 0;
			int b = 0;
			for (Enemy enemy : enemies) {
				if (enemy instanceof TankEnemy) {
					t++;
					b++;
				}
			}

			if (t < 2) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new TankEnemy(randX, randY, p, dimension));
			}
			if (b < 2) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new BasicEnemy(randX, randY, p, dimension));
			} else {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new AdvancedEnemy(randX, randY, p, dimension));
			}
		}

		else if (enemies.size() < 8 && wave == 8) { // Wave 7
			Point2D p2 = randomEnemySpawn();
			int randX = (int) p2.getX();
			int randY = (int) p2.getY();
			int randX2 = (int) p2.getX();
			int randY2 = (int) p2.getY();
			enemies.add(new BasicEnemy(randX, randY, p, dimension));
			enemies.add(new TankEnemy(randX2, randY2, p, dimension));
		}

		else if (enemies.size() < 8 && wave == 9) { // Wave 8
			int t = 0;
			int s = 0;
			for (Enemy enemy : enemies) {
				if (enemy instanceof TankEnemy) {
					t++;
					s++;
				}
			}

			if (t < 3) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new TankEnemy(randX, randY, p, dimension));
			}
			if (s < 1) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new SpeedEnemy(randX, randY, p, dimension));
			} else {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new BasicEnemy(randX, randY, p, dimension));
			}
		}
		
		else if (enemies.size() < 8 && wave == 10) { // Wave 9
			int t = 0;
			int s = 0;
			for (Enemy enemy : enemies) {
				if (enemy instanceof TankEnemy) {
					t++;
					s++;
				}
			}

			if (t < 3) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new TankEnemy(randX, randY, p, dimension));
			}
			if (s < 1) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new SpeedEnemy(randX, randY, p, dimension));
			} else {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new AdvancedEnemy(randX, randY, p, dimension));
			}
		}

		if (enemies.size() < 6 && wave == 11) { //Wave 10
			int b = 0;
			int basic = 0;
			int advanced = 0;
			int tank = 0;
			int speed = 0;
			for (Enemy enemy : enemies) {
				if (enemy instanceof Boss) {
					b++;
				}
				else if (enemy instanceof BasicEnemy) {
					basic++;
				}
				else if (enemy instanceof AdvancedEnemy) {
					advanced++;
				}
				else if (enemy instanceof TankEnemy) {
					tank++;
				}
				else if (enemy instanceof SpeedEnemy) {
					speed++;
				}
			}

			if (b < 1) {
				enemies.add(new Boss(Game.WIDTH / 2, 200, p, 40, gameSec, bossHealth));
			}

			if (enemies.get(0).getHealth() <= bossHealth * 0.5 && basic < 2) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new BasicEnemy(randX, randY, p, dimension));
			}
			
			if (enemies.get(0).getHealth() <= bossHealth * 0.25 && advanced < 1) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new AdvancedEnemy(randX, randY, p, dimension));
			}
			
			if (enemies.get(0).getHealth() <= bossHealth * 0.20 && tank < 1) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new TankEnemy(randX, randY, p, dimension));
			}
			
			if (enemies.get(0).getHealth() <= 0.1 && speed < 1) {
				Point2D p2 = randomEnemySpawn();
				int randX = (int) p2.getX();
				int randY = (int) p2.getY();
				enemies.add(new SpeedEnemy(randX, randY, p, dimension));
			}
		}

		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
			enemies.get(i).update();
			if (wave == 11) {
				bossLevel(gameSec, g, enemies.get(0).getHealth(), bossHealth);
			}
		}
		return enemies;
	}

	private void bossLevel(int gameSec, Graphics2D g, int currentBossHealth, int bossHealth) {
		if (currentBossHealth >= bossHealth * 0.5) {
			if (gameSec % 5 == 0 && gameSec > 0) {
				enemies.get(0).setSpeed(3);
			} else {
				enemies.get(0).setSpeed(0);
			}	
		}
		else if (currentBossHealth < bossHealth * 0.5){
			if (gameSec % 3 == 0 && gameSec > 0) {
				enemies.get(0).setSpeed(3);
			} else {
				enemies.get(0).setSpeed(0);
			}
		}
		
		g.setColor(Color.red);
		g.fillRect(15, 80, 20, enemies.get(0).getHealth() * 3);
	}
	
	public boolean bossDead() {
		return (enemies.get(0).getHealth() == 0);
	}

	public ArrayList<PowerUp> spawnPowerUp(int gameSec, int wave) {
		if (gameSec % 10 == 0 && powerUp.isEmpty() && gameSec > 0) {
			int randX = (int) (Math.random() * 850) + 50;
			int randY = (int) (Math.random() * 600) + 50;
			int randPU = (int) (Math.random() * 15) + 1;
			boolean b = false;
			
			if (wave == 11) {
				while (b == false) {
					System.out.println(randPU);
					randPU = (int) (Math.random() * 15) + 1;
					if (randPU <= 12) {
						b = true;
					}
				}
			}
			
			if (randPU >= 1 && randPU <= 4) {
				powerUp.add(new BulletPowerUp(randX, randY));
			} else if (randPU >= 5 && randPU <= 9) {
				powerUp.add(new SpeedPowerUp(randX, randY));
			} else if (randPU >= 10 && randPU <= 12) {
				powerUp.add(new HealthPowerUp(randX, randY));
			} else if (randPU == 13) {
				powerUp.add(new GodPowerUp(randX, randY));
			} else if (randPU == 14 || randPU == 15) {
				powerUp.add(new NukePowerUp(randX, randY));
			}
			tempSec = gameSec;
			spawned = true;
		}
		if (gameSec - tempSec == 5) {
			powerUp.clear();
			spawned = false;
		}
		return powerUp;
	}

	public boolean getSpawnedPowerUp() {
		return spawned;
	}

	public void setSpawnedPowerUp(boolean spawned) {
		this.spawned = spawned;
	}

	public Point2D randomEnemySpawn() {
		int randDoor = (int) (Math.random() * 4) + 1;
		Point2D p2;
		if (randDoor == 1) { // Left Entrance
			int randY = (int) (Math.random() * 100) + 300;
			p2 = new Point2D.Double(0, randY);
		} else if (randDoor == 2) { // Right Entrance
			int randY = (int) (Math.random() * 100) + 300;
			p2 = new Point2D.Double(1000 - dimension, randY);
		} else if (randDoor == 3) { // Upper Entrance
			int randX = (int) (Math.random() * 100) + 425;
			p2 = new Point2D.Double(randX, 0);
		} else { // Lower Entrance
			int randX = (int) (Math.random() * 100) + 425;
			p2 = new Point2D.Double(randX, 750 - dimension);
		}
		return p2;
	}

}
