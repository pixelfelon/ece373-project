package gameobjects;

import java.util.HashSet;
import java.util.Set;
import java.util.ArrayList;
import java.util.Collections;

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

	private static final int enemyLimit = 5;

	public Game() {
		this(5, GameDifficulty.NORMAL);
	}

	public Game(int planets, GameDifficulty difficulty) {
		this.difficulty = difficulty;
		this.viewportWidth = 500;
		this.viewportHeight = 500;

		this.entities = new HashSet<Entity>();
		this.enemies = new HashSet<Enemy>();
		this.planets = new ArrayList<Planet>();
		Set<Enemy> enemiesView = Collections.unmodifiableSet(this.enemies);

		this.sun = new Star();
		this.entities.add(this.sun);

		for (int i = 0; i < planets; i++) {
			Planet planet = new Planet(0.3 + 0.5 * ((double)i / (double)planets), enemiesView);
			Weapon newGun = null;
			switch ((int)Math.round(0.501 + Math.random() * 2.9)) {
				case 1: newGun = new Phalanx(); break;
				case 2: newGun = new RailGun(); break;
				case 3: newGun = new Missile(); break;
			}
			//planet.setWeapon(newGun); //testing weapon
			this.planets.add(planet);
			this.entities.add(planet);
		}

		this.reset();
	}


	public HashSet<Entity> getEntities() {
		return this.entities;
	}

	public HashSet<Enemy> getEnemies() {
		return this.enemies;
	}

	public ArrayList<Planet> getPlanets() {
		return this.planets;
	}

	public Star getSun() {
		return this.sun;
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
		//speed needs adjusting
		double speedMin = 0.1;
		double speedMax = 0.2;

		// starting position is at random point on a circle slightly larger than the viewport
		Vec2D startPos = new Vec2D();
		double r = this.getOuterBound() * (Math.PI / 2) * 1.05;
		double theta = Math.random() * 2 * Math.PI;
		startPos.setRTheta(r, theta);

		double speed = Math.random() * (speedMax - speedMin) + speedMin;

		if (this.enemies.size() <= enemyLimit) {
			Enemy enemy = new Enemy(startPos, this.sun);
			enemy.setSpeed(speed);
			this.entities.add(enemy);
			this.enemies.add(enemy);
			System.out.printf("Spawning enemy %h at theta=%.1fdeg\n", enemy, theta * 180.0 / Math.PI);
		}
	}

	private void pruneEntities() {
		HashSet<Entity> toDelete = new HashSet<Entity>();
		for (Entity e : this.entities) {
			if (e.isReadyToDelete()) {
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
		this.pruneEntities();
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

		if (Math.random() * this.difficulty.getEnemyRate() > 1.0) {
			this.spawnEnemy();
		}

		if (this.sun.getHealth() <= 0)
		{
			this.gameOver();
		}
	}

}
