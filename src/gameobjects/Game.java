package gameobjects;

import java.util.HashSet;
import java.util.ArrayList;

public class Game {

	private HashSet<Entity> entities;
	private HashSet<Enemy> enemies;
	private ArrayList<Planet> planets;
	private Star sun;

	private int viewportWidth;
	private int viewportHeight;

	private GameDifficulty difficulty;
	private long lastNanos;
	private boolean gameActive;

	private static final int enemyLimit = 100;

	public Game() {
		this(5, GameDifficulty.NORMAL);
	}

	public Game(int planets, GameDifficulty difficulty) {
		this.difficulty = difficulty;

		this.entities = new HashSet<Entity>();
		this.enemies = new HashSet<Enemy>();
		this.planets = new ArrayList<Planet>();

		this.sun = new Star();
		this.entities.add(this.sun);

		for (int i = 0; i < planets; i++) {
			Planet planet = new Planet(0.3 + 0.5 * ((double)i / (double)planets));
			this.planets.add(planet);
			this.entities.add(planet);
		}

		this.reset();
	}


	public HashSet<Entity> getEntities() {
		return this.entities;
	}

	public GameDifficulty getDifficulty() {
		return this.difficulty;
	}

	public void setDifficulty(GameDifficulty difficulty) {
		this.difficulty = difficulty;
	}

	public boolean isGameActive() {
		return this.gameActive;
	}

	public double getCoordScale() {
		return (double)Math.min(this.viewportHeight, this.viewportWidth);
	}

	public double getOuterBound() {
		return ((double)Math.max(this.viewportHeight, this.viewportWidth)) / this.getCoordScale();
	}


	private void spawnEnemy() {
		
		int posMin = -1;
		int posMax = 1;
		double speedMin = 0.03;
		double speedMax = 0.08;
		
		double posX = Math.random() * (posMax - posMin + 1) + posMin;
		double posY = Math.random() * (posMax - posMin + 1) + posMin;
		double speed = Math.random() * (speedMax - speedMin + 1) + speedMin;
		
		if (this.enemies.size() <= enemyLimit) {
			Enemy enemy = new Enemy();
			enemy.setSpawnPosition(posX, posY);
			enemy.setSpeed(speed);
			this.entities.add(enemy);
			this.enemies.add(enemy);
		}
	}

	private void pruneEntities() {
		HashSet<Entity> toDelete = new HashSet<Entity>();
		for (Entity e : this.entities) {
			if (e.getReadyToDelete()) {
				toDelete.add(e);
			}
		}
		for (Entity e : toDelete) {
			this.entities.remove(e);
			this.enemies.remove(e); // only has an effect if e is an Enemy
		}
	}

	public void reset() {
		for (Enemy e : this.enemies) {
			e.markReadyToDelete();
		}
		pruneEntities();
		this.sun.setHealth(100);
		this.gameActive = true;
	}

	public void gameOver() {
		this.gameActive = false;
	}

	public void tick() {
		this.pruneEntities();

		long curNanos = System.nanoTime();
		double dT = (double)(curNanos - this.lastNanos) / 1000000000.0;
		this.lastNanos = curNanos;

		for (Entity e : this.entities) {
			e.tick(dT);
		}

		for (int i = 0; i < this.difficulty.getEnemyRate(); i++) {
			this.spawnEnemy();
		}

		if (this.sun.getHealth() <= 0)
		{
			this.gameOver();
		}
	}

}
