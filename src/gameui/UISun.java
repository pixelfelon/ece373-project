package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameobjects.Entity;
import gameobjects.Vec2D;

public class UISun extends UIEntity
{

	private static final double radius = 40.0;
	
	public
	UISun (JPanel parent, Entity target)
	{
		super(parent, target);
		try {
			image = ImageIO.read( new File(".\\Graphics\\sun.png"));
			offSet(image.getWidth(), image.getHeight());
			System.out.println(image.getHeight() + " " + image.getWidth());
		} catch (IOException e) {
			// TODO Auto-generated catch block
		}
	}

	@Override
	public void
	update (Graphics2D g)
	{
//		Point2D pul = this.convertCoords(new Vec2D(-1, 1));
//		Point2D pur = this.convertCoords(new Vec2D(1, 1));
//		Point2D pll = this.convertCoords(new Vec2D(-1, -1));
//		Point2D plr = this.convertCoords(new Vec2D(1, -1));
//		Point2D pcl = this.convertCoords(new Vec2D(-1, 0));
//		Point2D pcr = this.convertCoords(new Vec2D(1, 0));
//		Point2D puc = this.convertCoords(new Vec2D(0, -1));
//		Point2D plc = this.convertCoords(new Vec2D(0, 1));
//		g.setStroke(new BasicStroke(4));
//		g.setColor(Color.GREEN);
//		g.draw(new Line2D.Double(pul, pur));
//		g.draw(new Line2D.Double(pur, plr));
//		g.draw(new Line2D.Double(plr, pll));
//		g.draw(new Line2D.Double(pll, pul));
//		g.setStroke(new BasicStroke(1));
//		g.draw(new Line2D.Double(pcl, pcr));
//		g.draw(new Line2D.Double(puc, plc));
//		g.setStroke(new BasicStroke(2));
//		g.setColor(Color.YELLOW);
		Point2D center = this.convertCoords(this.target.getPosition());
//		g.draw(new Ellipse2D.Double(center.getX() - radius, center.getY() - radius, radius * 2, radius * 2));
		g.drawImage(image, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
	}

}
