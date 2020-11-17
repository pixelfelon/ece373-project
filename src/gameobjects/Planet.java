package gameobjects;

public class Planet extends Entity {

	private Weapon weapon;

	public Planet() {
		
	}

	public Weapon getWeapon() {
		return weapon;
	}

	public void setWeapon(Weapon weapon) {
		this.weapon = weapon;
	}

	public void tick(double dT) {
		// stuff
	}

}
