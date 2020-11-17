package gameui;

import gameobjects.Star;

public class GameOverlay {
private int score;
private int starHealth;

public GameOverlay() {
	
}

public int getScore() {
	return score;
}
public void setScore(int score) {
	this.score = score;
}

public int getStarHealth() {
	return starHealth;
}

public void setStarHealth(Star star) {
	this.starHealth = star.getHealth();
}

}
