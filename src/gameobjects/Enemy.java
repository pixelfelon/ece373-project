package gameobjects;


public class Enemy extends LivingEntity {

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

	public void setSpawnPosition(double x, double y) {
		this.x = x;
		this.y = y;
		this.m = y/x;
	}

	public void setSpeed(double speed) {
		this.speed = speed;
	}

	public double getSpeed() {
		return this.speed;
	}

	public void tick(double dT) {
		
		if (x > 0) {
			x = (x - dT*speed);
		}
		
		else if (x < 0) {
			x = (x + dT*speed);
		}
		
		y = m*x;
		
		
		if ((-0.02 <= x && x <= 0.02) && (-0.02 <= y && y <= 0.02)) {
			x = 0.0;
			y = 0.0;
			this.setHealth(0);
			//this.markReadyToDelete();
		}
		
		//System.out.println("x: " + x + " y: " + y); //For Testing
		
		this.getPosition().setX(x);
		this.getPosition().setY(y);
		
	}

}
