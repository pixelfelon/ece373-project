package gameobjects;

public class Phalanx extends Weapon{

	public Phalanx() {
		setName("Phalanx");
		damage = 1;
		attackSpeed = 3;
		range = 1;
		fireTime = reloadTimer - 0.1;
	}

}
