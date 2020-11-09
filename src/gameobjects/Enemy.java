package gameobjects;

public class Enemy extends Entity {
private int spawnPosition;

public Enemy () {}

public int getSpawnPosition() {
	return spawnPosition;
}

public void setSpawnPosition(int spawnPosition) {
	this.spawnPosition = spawnPosition;
}

}
