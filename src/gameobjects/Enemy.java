package gameobjects;


public class Enemy extends LivingEntity {

	private LivingEntity target;
	private double speed;

	public static final int damage = 5;

	public Enemy(Vec2D pos, LivingEntity target) {
		this.pos = pos;
		this.target = target;
		this.speed = 0.0;
	}

	public Vec2D getPosition() {
		return this.pos;
	}

	public LivingEntity getTarget() {
		return this.target;
	}

	public void setTarget(LivingEntity target) {
		this.target = target;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void setHealth(int health) {
		if (health <= 0) {
			health = 0;
			this.markReadyToDelete();
			System.out.printf("Enemy %h died!\n", this);
		}
		else {
			//System.out.printf("Enemy %h damaged, health = %d\n", this, this.getHealth());
		}
		super.setHealth(health);
	}

	protected void kamikaze() {
		if (this.getHealth() > 0) {
			this.target.dealDamage(damage);
			this.setHealth(0);
			System.out.printf("!!! Enemy %h reached the sun! Sun health = %d\n", this, this.target.getHealth());
		}
	}

	public void tick(double dT) {
		this.moveTowards(this.target, this.speed * dT);

		if (this.inRangeOf(this.target)) {
			this.kamikaze();
			this.markReadyToDelete();
		}
	}

	public void reset() {
		this.markReadyToDelete();
	}

}
