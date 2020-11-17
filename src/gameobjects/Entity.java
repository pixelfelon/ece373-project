package gameobjects;

public abstract class Entity {

	private Coordinates position;
	private boolean readyToDelete;
	
	public Entity() {
		this.readyToDelete = false;
		this.position = new Coordinates();
	}

	public Coordinates getPosition() {
		return this.position;
	}

	public boolean getReadyToDelete() {
		return this.readyToDelete;
	}

	protected void markReadyToDelete() {
		this.readyToDelete = true;
	}

	public abstract void tick(double dT);

}
