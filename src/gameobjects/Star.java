package gameobjects;

import  java.util.ArrayList;

<<<<<<< HEAD
public class Star extends Entity {
private ArrayList<Planet> planets;

public Star() {
	planets = new ArrayList<Planet>();
	this.setHealth(20);
}
=======
public class Star extends LivingEntity {

	private ArrayList<Planet> planets;

	public Star() {
		planets = new ArrayList<Planet>();
	}
>>>>>>> origin/master

	public ArrayList<Planet> getPlanets() {
		return this.planets;
	}

	public void addPlanet(Planet planet) {
		this.planets.add(planet);
	}

	public void tick(double dT) {
		// glow
	}

}
