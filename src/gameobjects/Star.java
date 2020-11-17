package gameobjects;

import  java.util.ArrayList;
import gameobjects.Planet; 

public class Star extends Entity {
private ArrayList<Planet> planets;

public Star() {
	planets = new ArrayList<Planet>();
	this.setHealth(20);
}

public ArrayList<Planet> getPlanets() {
	return planets;
}

public void addPlanet(Planet planet) {
	this.planets.add(planet);
}

}
