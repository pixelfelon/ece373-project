package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;

import javax.swing.JPanel;

import gameobjects.Entity;

public class UIEnemy extends UIEntity
{

	private static final double radius = 8.0;

	public UIEnemy(JPanel parent, Entity target) {
		super(parent, target);
	}

	@Override
	public void
	update (Graphics2D g)
	{
		Point2D center = this.convertCoords(this.target.getPosition());
		g.setStroke(new BasicStroke(2));
		g.setColor(Color.RED);
		g.draw(new Line2D.Double(center.getX(), center.getY() - radius, center.getX(), center.getY() + radius));
		g.draw(new Line2D.Double(center.getX() - radius, center.getY(), center.getX() + radius, center.getY()));
	}

}
