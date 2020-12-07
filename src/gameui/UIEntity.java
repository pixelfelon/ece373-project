package gameui;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import gameobjects.Entity;
import gameobjects.Vec2D;

public abstract class UIEntity
{

	protected Entity target;
	protected JPanel parent;

	public
	UIEntity (JPanel parent, Entity target)
	{
		this.target = target;
		this.parent = parent;
	}

	public boolean isReadyToDelete() {
		return this.target.isReadyToDelete();
	}

	public abstract void update (Graphics2D g);

	protected Point2D
	convertCoords (Vec2D vector)
	{
		double vh = this.parent.getHeight();
		double vw = this.parent.getWidth();
		double vmin = Math.min(vh, vw);
		double x = vector.getX() * (vmin / 2.0) + (vw / 2.0);
		double y = -vector.getY() * (vmin / 2.0) + (vh / 2.0);
		return new Point2D.Double(x, y);
	}

}
