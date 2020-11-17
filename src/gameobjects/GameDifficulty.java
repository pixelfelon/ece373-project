package gameobjects;

public enum GameDifficulty {

	EASY(1),
	NORMAL(2),
	HARD(3);

	private final int enemyRate;

	private GameDifficulty(int enemyRate) {
    	this.enemyRate = enemyRate;
    }

    public int getEnemyRate () {
    	return this.enemyRate;
    }

}
