package gameobjects;

public abstract class LivingEntity extends Entity {

	protected int health;

	public LivingEntity() {
		this.health = 100;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	public void dealDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

}
