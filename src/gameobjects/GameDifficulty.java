package gameobjects;

public enum GameDifficulty {

	EASY(1.5),
	NORMAL(2.5),
	HARD(3.5);

	private final double enemyRate;

	private GameDifficulty(double enemyRate) {
    	this.enemyRate = enemyRate;
    }

    public double getEnemyRate () {
    	return this.enemyRate;
    }

}
