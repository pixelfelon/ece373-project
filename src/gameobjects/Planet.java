package gameobjects;

import java.util.Set;

public class Planet extends Entity {

	private Weapon weapon;
	private Star star;
	private double radius;
	private double theta;
	private double speed;
	private Set<Enemy> enemies;

	public Planet(double radius, Set<Enemy> enemies) {
		this.radius = radius;
		this.theta = 0;
		this.speed = Math.pow(Math.E, radius) * 0.1;
		this.enemies = enemies;
		System.out.printf("New planet %h created, r=%.2f, omega=%.3f\n", this, this.radius, this.speed);
	}

	public Weapon getWeapon() {
		return this.weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
		System.out.printf("Planet %h equipped with weapon type %s.\n", this, weapon.getClass().getName());
	}

	public void tick(double dT) {
		
		this.theta += this.speed;
		this.getPosition().setX(radius * Math.cos((theta * Math.PI)/180));
		this.getPosition().setY(radius * Math.sin((theta * Math.PI)/180));
		if (this.weapon != null) {
			Enemy newEnemy = this.findClosestEnemy();
			Enemy curEnemy = this.weapon.getTarget();
			if (newEnemy != null) {
				if (curEnemy == null) {
					this.weapon.setTarget(newEnemy);
					System.out.printf("Planet %h targeting enemy %h at range %.3f.\n", this, newEnemy, this.distanceTo(newEnemy));
				}
				else if ((this.distanceTo(curEnemy) - this.distanceTo(newEnemy)) > 0.1) {
					// some hysteresis for target switching
					this.weapon.setTarget(newEnemy);
					System.out.printf("Planet %h now targeting enemy %h at range %.3f.\n", this, newEnemy, this.distanceTo(newEnemy));
				}
			}
			this.weapon.attack();
		}
	}

	public void setStar(Star star) {
		this.star = star;
	}

	public Set<Enemy> getEnemies() {
		return enemies;
	}

	public void setEnemies(Set<Enemy> enemies) {
		this.enemies = enemies;
	}

	public void setRadius(int radius) {
		this.radius = radius;
	}

	public Enemy findClosestEnemy() {

		Enemy target = null;

		double dist = 0.0;
		double closestEnemy = 10.0;

		for (Enemy e : this.enemies) {

			dist = this.pos.distanceTo(e.getPosition());

			if(dist < closestEnemy && dist < this.weapon.getRange()) {
				target = e;
				closestEnemy = dist;
				//System.out.println("closest enemy dist: " + closestEnemy);
			}
		}
		
		// If target is found, deal damage (used for testing)
		/*if(target != null) {
		target.dealDamage(5);
		}*/

		return target;
	}

}
