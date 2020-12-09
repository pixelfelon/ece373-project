package gameobjects;

public class RailGun extends Weapon {

	public RailGun() {
		setName("RailGun");
		damage = 3;
		attackSpeed = 1;
		range = 3;
		fireTime = reloadTimer - 0.8;
	}

}
