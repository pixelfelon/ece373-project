package gameobjects;

import  java.util.ArrayList;


public class Star extends LivingEntity {

	private ArrayList<Planet> planets;

	public Star() {
		this.planets = new ArrayList<Planet>();
		this.setHitRadius(0.1);
	}

	public ArrayList<Planet> getPlanets() {
		return this.planets;
	}

	public void addPlanet(Planet planet) {
		this.planets.add(planet);
		planet.setRadius(planets.size());
		planet.setStar(this);
	}

	public void tick(double dT) {
		// glow
	}

}
