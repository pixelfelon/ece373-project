package gameobjects;

import java.util.ArrayList;

public class Planet extends Entity {

	private Weapon weapon;
	private Star star;
	private double radius;
	private double theta;
	private ArrayList<Enemy> enemy;

	public Planet(double radius) {
		this.radius = radius;
		this.theta = 0;
		enemy = new ArrayList<Enemy>();
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void tick(double dT) {
		
		theta = theta + 1;
		this.getPosition().setX(radius * Math.cos((theta * Math.PI)/180));
		this.getPosition().setY(radius * Math.sin((theta * Math.PI)/180));
		Enemy en = this.findClosestEnemy();
		if (this.weapon != null && en != null) {
			this.weapon.setTarget(this.findClosestEnemy());
			this.weapon.attack();
		}
	}

	public void setStar(Star star) {
		this.star = star;
	}
	
	public ArrayList<Enemy> getEnemy() {
		return enemy;
	}

	public void setEnemy(Enemy enemy) {
		this.enemy.add(enemy);
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Enemy findClosestEnemy() {
		
		Enemy target = null;
		
		double x = 0.0;
		double y = 0.0;
		double dist = 0.0;
		double closestEnemy = 10.0;
		
		for (Enemy e : enemy) {
			
		x = e.getPosition().getX();
		y = e.getPosition().getY();
		dist = this.getPosition().distance(x, y);

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
