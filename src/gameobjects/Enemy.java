package gameobjects;

public class Enemy extends Entity {
private int spawnPosition;
private int score = 1; 

public Enemy () {}

public int getSpawnPosition() {
	return spawnPosition;
}

public void setSpawnPosition(int spawnPosition) {
	this.spawnPosition = spawnPosition;
}

public int getScore() {
	return score;
}

public void setScore(int score) {
	this.score = score;
}

}
