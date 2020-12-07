package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameobjects.Entity;

public class UIEnemy extends UIEntity
{

	private static final double radius = 8.0;
	Random rn = new Random();
	int randomNum = rn.nextInt(3 - 1 + 1) + 1; //(max - min + 1) + min

	public UIEnemy(JPanel parent, Entity target) {
		super(parent, target);
		try {
			if(randomNum == 1) {
				image = ImageIO.read( new File(".\\Graphics\\enemy1.png"));
				offSetX = 15;
				offSetY = 15;
			}
			else if (randomNum == 2) {
				image = ImageIO.read( new File(".\\Graphics\\enemy2.png"));
				offSetX = 17;
				offSetY = 12;
			}
			else if (randomNum == 3) {
				image = ImageIO.read( new File(".\\Graphics\\enemy3.png"));
				offSetX = 25;
				offSetY = 21;
			}
		}
		
		catch(IOException e) {
			//Not handled
		}
	}

	@Override
	public void
	update (Graphics2D g)
	{
		Point2D center = this.convertCoords(this.target.getPosition());
//		g.setStroke(new BasicStroke(2));
//		g.setColor(Color.RED);
//		g.draw(new Line2D.Double(center.getX(), center.getY() - radius, center.getX(), center.getY() + radius));
//		g.draw(new Line2D.Double(center.getX() - radius, center.getY(), center.getX() + radius, center.getY()));
		//g.drawImage(image, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
		UIEntity.centerImg(g, center, this.image);
	}

}
