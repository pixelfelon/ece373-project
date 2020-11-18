package gameobjects;

public abstract class LivingEntity extends Entity {

	private int health;
	private double hitRadius;

	public LivingEntity() {
		this.health = 100;
		this.hitRadius = 0.001;
	}

	public int getHealth() {
		return this.health;
	}

	public void setHealth(int health) {
		this.health = health;
	}

	protected double getHitRadius() {
		return hitRadius;
	}

	protected void setHitRadius(double hitRadius) {
		this.hitRadius = hitRadius;
	}

	public void dealDamage(int damage) {
		this.setHealth(this.getHealth() - damage);
	}

	public boolean inRangeOf(LivingEntity other) {
		return this.pos.distanceTo(other.getPosition()) < (this.getHitRadius() + other.getHitRadius());
	}

}
