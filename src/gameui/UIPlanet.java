package gameui;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import gameobjects.Planet;
import gameobjects.Vec2D;

public class UIPlanet extends UIEntity
{

	private double fireTime;
	private double attackSpeed;
	double randomNum = ThreadLocalRandom.current().nextDouble(0.5) + 0.1;

	public
	UIPlanet (GamePanel parent, Planet target)
	{
		super(parent, target);

		try
		{
			switch (target.spriteIdx)
			{
			case 1:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet1.png"));
				break;
			case 2:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet2.png"));
				break;
			case 3:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet3.png"));
				break;
			case 4:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet4.png"));
				break;
			case 5:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet5.png"));
				break;
			case 6:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/planet6.png"));
				break;
			default:
				System.err.printf("Could not find planet sprite for choice %d!\n", target.spriteIdx);
				break;
			}
		}
		catch (IOException e)
		{
			System.err.printf("Could not load planet sprite for choice %d!\n", target.spriteIdx);
		}
	}

	@Override
	public void
	update (Graphics2D g)
	{
		g.setStroke(new BasicStroke(1));
		g.setColor(Color.LIGHT_GRAY);
		this.centerCircle(g, new Vec2D(0.0, 0.0), ((Planet)(this.target)).getRadius());
		if (this.image == null)
		{
			g.setStroke(new BasicStroke(2));
			g.setColor(Color.BLUE);
			this.centerCircle(g, this.target.getPosition(), 0.03);
		}
		else
		{
			this.centerImg(g, this.target.getPosition(), this.image);
			g.setStroke(new BasicStroke(1));
			g.setColor(Color.PINK);
			
			if(((Planet)(this.target)).getWeapon() != null) {
				if(((Planet)(this.target)).getWeapon().getRange() == 0) {g.setColor(Color.WHITE);}
				else if(((Planet)(this.target)).getWeapon().getRange() == 1) {
					g.setColor(Color.YELLOW);
					attackSpeed = 0.2;
					fireTime = 0.1;
					}
				else if(((Planet)(this.target)).getWeapon().getRange() == 2) {
					g.setColor(Color.RED);
					attackSpeed = 2;
					fireTime = 2;
					}
				else if(((Planet)(this.target)).getWeapon().getRange() == 3) {
					g.setColor(Color.CYAN);
					g.setStroke(new BasicStroke(5));
					attackSpeed = 3 - randomNum;
					fireTime = 0.8 + randomNum;
					
						}
					}
			}
			
		if(((Planet)this.target).getWeapon().getFiring()){
			if(((Planet)(this.target)).findClosestEnemy() != null) {
				if ((((System.nanoTime() / 1000000000.0) % attackSpeed) < fireTime)) {
			this.drawLine(g, this.target.getPosition(), ((Planet)(this.target)).findClosestEnemy().getPosition());
				}
			}
		}
	}

}
