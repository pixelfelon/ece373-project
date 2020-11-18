package gameobjects;

import java.lang.Math; 

public class Vec2D extends Coordinates {

	private double x;
	private double y;

	public Vec2D() {
		this(0.0, 0.0);
	}

	public Vec2D(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public Vec2D(Coordinates other) {
		this.x = other.getX();
		this.y = other.getY();
	}

	public void setXY(double x, double y) {
		this.x = x;
		this.y = y;
	}

	public void setRTheta(double r, double theta) {
		this.x = r * Math.cos(theta);
		this.y = r * Math.sin(theta);
	}

	public double getX() {
		return this.x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public void addX(double x) {
		this.x += x;
	}

	public boolean xInRange(double a, double b) {
		return a <= this.x && this.x <= b;
	}

	public boolean xInRangeExcl(double a, double b) {
		return a < this.x && this.x < b;
	}

	public double getY() {
		return this.y;
	}

	public void setY(double y) {
		this.y = y;
	}

	public void addY(double y) {
		this.y += y;
	}

	public boolean yInRange(double a, double b) {
		return a <= this.y && this.y <= b;
	}

	public boolean yInRangeExcl(double a, double b) {
		return a < this.y && this.y < b;
	}

	public boolean inRect(Coordinates v1, Coordinates v2) {
		double x1 = Math.min(v1.getX(), v2.getX());
		double x2 = Math.max(v1.getX(), v2.getX());
		double y1 = Math.min(v1.getY(), v2.getY());
		double y2 = Math.max(v1.getY(), v2.getY());
		return x1 <= this.x && this.x <= x2 && y1 <= this.y && this.y <= y2;
	}

	public double getLength() {
		return Math.sqrt(Math.pow(this.x, 2) + Math.pow(this.y, 2));
	}

	public double getSlope() {
		return this.x / this.y;
	}

	public double getAngle() {
		return Math.atan2(this.y, this.x);
	}

	public double distanceTo(Coordinates other) {
		double distance = Math.sqrt(Math.pow((other.getX() - this.x), 2) + Math.pow((other.getY() - this.y), 2));

		return distance;
	}

	public void add(Coordinates other) {
		this.x += other.getX();
		this.y += other.getY();
	}

	public void subtract(Coordinates other) {
		this.x += other.getX();
		this.y += other.getY();
	}

	public void scale(double factor) {
		this.x *= factor;
		this.y *= factor;
	}

	public void normalize() {
		double length = this.getLength();
		this.x /= length;
		this.y /= length;
	}

	public static Vec2D add(Coordinates v1, Coordinates v2) {
		Vec2D out = new Vec2D(v1);
		out.add(v2);
		return out;
	}

	public static Vec2D subtract(Coordinates v1, Coordinates v2) {
		Vec2D out = new Vec2D(v1);
		out.subtract(v2);
		return out;
	}

	public static Vec2D scale(Coordinates v1, double factor) {
		Vec2D out = new Vec2D(v1);
		out.scale(factor);
		return out;
	}

	public static Vec2D normalize(Coordinates v1) {
		Vec2D out = new Vec2D(v1);
		out.normalize();
		return out;
	}

}
