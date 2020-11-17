package gameobjects;


public class Enemy extends LivingEntity {
private int spawnPosition;
private int score = 1; 

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

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

}
