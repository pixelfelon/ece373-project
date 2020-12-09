package gameobjects;

public abstract class Weapon {
	
	String name = "NoWeapon";
	protected int damage;
	protected int attackSpeed;
	protected int reloadTimer = 0;
	protected double range;
	private Enemy target;
	boolean firing;
	protected double fireTime;
	
	public Weapon() {}

	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.getName();
	}
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
			if (this.target != null) {
				if (this.target.isReadyToDelete()) {
					this.target = null;
				}
				else {
					target.dealDamage(this.getDamage());
					firing = true;
				}
			}
			this.reloadTimer = 12;
		}
		else {
			this.reloadTimer = this.reloadTimer - this.attackSpeed;
			if(this.reloadTimer > this.fireTime) {
				firing = true;
			}
			else {
				firing = false;
			}
		}
	}
	public boolean getFiring() {
		return firing;
	}
	
}
