package gameui;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameobjects.Star;

public class UISun extends UIEntity
{

	private BufferedImage liveSun;
	private BufferedImage deadSun;
	
	public
	UISun (JPanel parent, Star target)
	{
		super(parent, target);
		try
		{
			liveSun = ImageIO.read( new File(".\\Graphics\\sun.png"));
			deadSun = ImageIO.read( new File(".\\Graphics\\deadsun.png"));
			offSetX = 40;
			offSetY = 40;
		}
		catch (IOException e)
		{
			System.err.printf("Could not find sun sprite!\n");
		}
	}

	@Override
	public void
	update (Graphics2D g)
	{
		Point2D center = this.convertCoords(this.target.getPosition());
		if (((Star)(this.target)).getHealth() > 0)
		{
			g.drawImage(liveSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
		}
		else
		{
			g.drawImage(deadSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
		}
		System.out.printf("SH=%d\n", ((Star)(this.target)).getHealth());
	}

}
