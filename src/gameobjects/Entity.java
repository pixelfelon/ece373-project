package gameobjects;

public abstract class Entity {

	protected Vec2D pos;

	private boolean readyToDelete;

	public Entity() {
		this.readyToDelete = false;
		this.pos = new Vec2D();
	}

	public Vec2D getPosition() {
		return this.pos;
	}

	public boolean isReadyToDelete() {
		return this.readyToDelete;
	}

	protected void markReadyToDelete() {
		this.readyToDelete = true;
	}

	public abstract void tick(double dT);

	protected void moveTowards(Entity other, double speed) {
		Vec2D direction = Vec2D.subtract(other.getPosition(), this.pos);
		direction.normalize();
		direction.scale(speed);
		this.pos.add(direction);
	}

	protected double distanceTo(Entity other) {
		return this.pos.distanceTo(other.getPosition());
	}

}
