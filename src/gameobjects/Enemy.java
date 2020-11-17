package gameobjects;

public class Enemy extends LivingEntity {

	private int spawnPosition;

	public Enemy () {}

	public int getSpawnPosition() {
		return this.spawnPosition;
	}

	public void setSpawnPosition(int spawnPosition) {
		this.spawnPosition = spawnPosition;
	}

	public void tick(double dT) {
		//hello
	}

}
