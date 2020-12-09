package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import gameobjects.Star;
import gameobjects.Vec2D;

public class UISun extends UIEntity
{

	private BufferedImage liveSun;
	private BufferedImage deadSun;
	private BufferedImage heart;
	private BufferedImage gameOver;
	private int score;
	//ImageIcon gameOver = new ImageIcon(".\\Graphics\\game_over.gif");
	//private JLabel	gameOverGIF = new JLabel(gameOver);
	
	public
	UISun (GamePanel parent, Star target)
	{
		super(parent, target);
		try
		{
			liveSun = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/sun.png"));
			deadSun = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/deadsun.png"));
			heart = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/heart.png"));
			gameOver = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/game_over.gif"));
			offSetX = 40;
			offSetY = 40;
			score = 0;
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
		Point2D sh = this.convertCoords(new Vec2D(-.16, .95));
		Point2D scoreCounter = this.convertCoords(new Vec2D(.16, .95));
		if (((Star)(this.target)).getHealth() > 0)
		{
			g.drawImage(liveSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
			score += (int)this.parent.getGame().getDifficulty().getEnemyRate();
			this.parent.getGame().setScore(score);
		}
		else
		{
			g.drawImage(deadSun, (int)center.getX() - (int)offSetX, (int)center.getY() - (int)offSetY, null);
			if (((System.nanoTime() / 1000000000.0) % 1.0) < 0.5) { 
				// this block only runs in the lower half of each passing second }
				g.drawImage(gameOver, (int)sh.getX() - (int)100, (int)sh.getY() + 25, null);
				
				SolarDefenderGUI.viewScores.setVisible(true);
			}
//			gameOverGIF.setBounds((int)sh.getX() - (int)100, (int)sh.getY() + 25, 275, 35);
//			parent.add(gameOverGIF);
			
		}
		g.drawImage(heart, (int)sh.getX() - 30, (int)sh.getY() - 20, null);
		Stroke stroke1 = new BasicStroke(12f);
		g.setStroke(stroke1);
		g.setColor(Color.RED);
		g.drawString("HEALTH " + ((Star)(this.target)).getHealth(), (int)sh.getX(), (int)sh.getY());
		g.setColor(Color.WHITE);
		g.drawString("SCORE: " + this.parent.getGame().getScore(), (int)scoreCounter.getX(), (int)scoreCounter.getY());
	}

}
