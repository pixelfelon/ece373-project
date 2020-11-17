package gameobjects;

<<<<<<< HEAD
public class Enemy extends Entity {
private int spawnPosition;
private int score = 1; 
=======
public class Enemy extends LivingEntity {
>>>>>>> origin/master

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

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

}
