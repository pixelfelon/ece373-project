package gameui;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;

import javax.swing.JPanel;

import gameobjects.Entity;
import gameobjects.Game;
import gameobjects.Planet;
import gameobjects.Vec2D;

public abstract class UIEntity
{

	protected Entity target;
	protected GamePanel parent;
	protected BufferedImage image;
	double offSetX, offSetY;

	public
	UIEntity (GamePanel parent, Entity target)
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

	protected double
	scaleCoord (Double coord)
	{
		double vh = this.parent.getHeight();
		double vw = this.parent.getWidth();
		double vmin = Math.min(vh, vw);
		return coord * (vmin / 2.0);
	}

	protected void
	offSet (int width, int height)
	{
		offSetX = width / 2;
		offSetY = height / 2;
	}

	protected void
	centerImg (Graphics2D g, Vec2D v, BufferedImage img)
	{
		Point2D p = this.convertCoords(v);
		AffineTransform old = g.getTransform();
		g.translate(
			p.getX() - ((double)img.getWidth() / 2.0),
			p.getY() - ((double)img.getHeight() / 2.0)
		);
		g.drawImage(img, 0, 0, null);
		g.setTransform(old);
	}

	protected void
	centerCircle (Graphics2D g, Vec2D v, double radius)
	{
		Point2D p = this.convertCoords(v);
		radius = this.scaleCoord(radius);
		g.draw(new Ellipse2D.Double(p.getX() - radius, p.getY() - radius, radius * 2.0, radius * 2.0));
	}
	
	protected void
	drawLine (Graphics2D g, Vec2D v, Vec2D ve) {
		Point2D p = this.convertCoords(v);
		Point2D e = this.convertCoords(ve);
		g.draw(new Line2D.Double(p.getX(), p.getY(), e.getX(), e.getY()));
	}
	
}
