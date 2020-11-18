package gameobjects;


public class Enemy extends LivingEntity {

	private Vec2D pos;
	private LivingEntity target;
	private double speed;
	private double m;

	public static final int damage = 5;

	public Enemy(Vec2D pos, LivingEntity target) {
		this.pos = pos;
		this.target = target;
		this.speed = 0.0;
		this.m = this.pos.getSlope();
	}

	public Coordinates getPosition() {
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

	public void kamikaze() {
		if (this.getHealth() > 0) {
			this.target.dealDamage(damage);
			this.setHealth(0);
		}
	}

	public void tick(double dT) {

		if (this.pos.getX() > 0) {
			this.pos.addX(- dT * this.speed);
		}

		else if (this.pos.getX() < 0) {
			this.pos.addX(+ dT * this.speed);
		}

		this.pos.setY(this.m * this.pos.getX());

		Vec2D targetPos = new Vec2D(this.target.getPosition());
		if (this.pos.distanceTo(targetPos) < (this.getHitRadius() + this.target.getHitRadius())) {
			this.kamikaze();
			this.markReadyToDelete();
		}

		//System.out.println("x: " + x + " y: " + y); //For Testing
	}

}
