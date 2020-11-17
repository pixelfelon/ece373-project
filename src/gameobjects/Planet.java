package gameobjects;

public class Planet extends Entity {

	private Weapon weapon;
	private Star star;
	private double radius;
	private double theta;

	public Planet(double radius) {
		this.radius = radius;
		this.theta = 0;
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
		if (this.weapon != null) {
			this.weapon.setTarget(this.findClosestEnemy());
		}
	}

	public void setStar(Star star) {
		this.star = star;
	}
	
	public void setRadius(int radius) {
		this.radius = radius;
	}
	
	public Enemy findClosestEnemy() {
		//TODO: Calculate closest enemy
		Enemy target = null;
		return target;
	}

}
