package gameui;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

import javax.imageio.ImageIO;

import gameobjects.Enemy;

public class UIEnemy extends UIEntity
{

	public
	UIEnemy (GamePanel parent, Enemy target)
	{
		super(parent, target);
		int randomNum = ThreadLocalRandom.current().nextInt(3) + 1;
		try
		{
			switch (randomNum)
			{
			case 1:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/enemy1.png"));
				break;
			case 2:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/enemy2.png"));
				break;
			case 3:
				image = ImageIO.read(this.getClass().getClassLoader().getResource("Graphics/enemy3.png"));
				break;
			}
		}
		catch (IOException e)
		{
			System.err.printf("Could not find enemy sprite for choice %d!\n", randomNum);
		}
	}

	@Override
	public void
	update (Graphics2D g)
	{
		this.centerImg(g, this.target.getPosition(), this.image);
	}
	
}
