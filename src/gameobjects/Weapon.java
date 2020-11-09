package gameobjects;

public abstract class Weapon {
protected int damage;
protected int attackSpeed;
protected int range;
private Enemy target;

public Weapon() {}

public int getDamage() {
	return damage;
}

public void setDamage(int damage) {
	this.damage = damage;
}

public int getAttackSpeed() {
	return attackSpeed;
}

public void setAttackSpeed(int attackSpeed) {
	this.attackSpeed = attackSpeed;
}

public int getRange() {
	return range;
}

public void setRange(int range) {
	this.range = range;
}

public Enemy getTarget() {
	return target;
}

public void setTarget(Enemy target) {
	this.target = target;
}

}
