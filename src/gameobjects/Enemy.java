package gameobjects;


public class Enemy extends LivingEntity {

	private int spawnPosition;
	
	private double x;
	private double y;
	private double speed;
	private double m;

	public Enemy() {
		x = 0.0;
		y = 0.0;
		speed = 0.0;
		m = 0.0;
	}

	/*public int getSpawnPosition() {
		return this.spawnPosition;
	}*/

	public void setSpawnPosition(double x, double y) {
		this.x = x;
		this.y = y;
		m = y/x;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public double getSpeed() {
		return this.speed;
	}

	public void setHealth(int health) {
		this.health = health;
		if (this.health <= 0) {
			this.markReadyToDelete();
		}
	}

	public void tick(double dT) {
		
		x = y/m;
		y = m*x;
		
		
		if (x > 0) {
		//	System.out.print(" X postion: " + x);
			x = (x - dT*speed);
		}
		
		if (x < 0) {
		//	System.out.print(" X postion: " + x);
			x = (x + dT*speed);
		}
		
		if (y > 0) {
		//	System.out.print(" Y postion: " + y);
			y = (y - dT*speed);
		}
		
		if (y < 0) {
			//System.out.print(" Y postion: " + y);
			y = (y + dT*speed);
		}
		
		if (-0.01 <= x && x <= 0.01) {
			x = 0.0;
		}
		if (-0.01 <= y && y <= 0.01) {
			y = 0.0;
		}
		
		System.out.println("X postion: " + x + " y position: " + y);
		// System.out.println();
		
		/*x = (y - dT*speed);
		y = (x - dT*speed);*/
		
		
	}

	/*
	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	*/

}
