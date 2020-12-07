package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import gameobjects.Entity;

public class UIPlanet extends UIEntity
{

	private static final double radius = 16.0;

	public UIPlanet(JPanel parent, Entity target) {
		super(parent, target);
	}

	@Override
	public void
	update (Graphics2D g)
	{
		Point2D center = this.convertCoords(this.target.getPosition());
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.RED);
		g.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2));
	}

}
