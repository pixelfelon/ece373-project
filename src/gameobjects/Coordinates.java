package gameobjects;

import java.lang.Math; 

public class Coordinates {
	
	private double xPosition;
	private double yPosition;
	private double distance;
	
	public Coordinates(){
		xPosition = 0.0;
		yPosition = 0.0;
		distance = 0.0;
	}
	
	public double getxPosition() {
		return xPosition;
	}

	public void setxPosition(double xPosition) {
		this.xPosition = xPosition;
	}

	public double getyPosition() {
		return yPosition;
	}

	public void setyPosition(double yPosition) {
		this.yPosition = yPosition;
	}
	
	public double Distance(double x, double y) {
		
		distance = Math.sqrt(Math.pow((x - xPosition), 2) + Math.pow((y - yPosition), 2));
		
		return distance;
	}

}
