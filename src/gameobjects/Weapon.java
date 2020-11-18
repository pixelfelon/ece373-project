package gameobjects;

public abstract class Weapon {

	protected int damage;
	protected int attackSpeed;
	protected int reloadTimer = 0;
	protected double range;
	private Enemy target;

	public Weapon() {}

	public int getDamage() {
		return this.damage;
	}

	public void setDamage(int damage) {
		this.damage = damage;
	}

	public int getAttackSpeed() {
		return this.attackSpeed;
	}

	public void setAttackSpeed(int attackSpeed) {
		this.attackSpeed = attackSpeed;
	}

	public double getRange() {
		return this.range;
	}

	public void setRange(double range) {
		this.range = range;
	}

	public Enemy getTarget() {
		return this.target;
	}

	public void setTarget(Enemy target) {
		this.target = target;
	}

	public void attack() {
		//Enemy enemy = this.weapon.getTarget();
		if(this.reloadTimer == 0) {
		target.setHealth(target.getHealth() - this.getDamage());
		this.reloadTimer = 12;
		}
		else {
			this.reloadTimer = this.reloadTimer - this.attackSpeed;
		}
	}
	
}
