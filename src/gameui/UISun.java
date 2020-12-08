package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import gameobjects.Star;
import gameobjects.Vec2D;

public class UISun extends UIEntity
{

	private BufferedImage liveSun;
	private BufferedImage deadSun;
	private BufferedImage heart;
	private BufferedImage gameOver;
	
	public
	UISun (JPanel parent, Star target)
	{
		super(parent, target);
		try
		{
			liveSun = ImageIO.read( new File(".\\Graphics\\sun.png"));
			deadSun = ImageIO.read( new File(".\\Graphics\\deadsun.png"));
			heart = ImageIO.read( new File(".\\Graphics\\heart.png"));
			gameOver = ImageIO.read( new File(".\\Graphics\\game_over.gif"));
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
		Point2D sh = this.convertCoords(new Vec2D(-.08, .95));
		if (((Star)(this.target)).getHealth() > 0)
		{
			g.drawImage(liveSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
		}
		else
		{
			g.drawImage(deadSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
			g.drawImage(gameOver, (int)sh.getX() - (int)100, (int)sh.getY() + 25, null);
			
		}
		g.drawImage(heart, (int)sh.getX() - 30, (int)sh.getY() - 20, null);
		Stroke stroke1 = new BasicStroke(12f);
		g.setStroke(stroke1);
		g.setColor(Color.RED);
		g.drawString("HEALTH " + ((Star)(this.target)).getHealth(), (int)sh.getX(), (int)sh.getY());
		System.out.printf("health %d\n", ((Star)(this.target)).getHealth());
	}

}
