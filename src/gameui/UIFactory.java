package gameui;

import javax.swing.JPanel;

import gameobjects.*;

public class UIFactory {

	public static UIEntity
	makeUIEntity (GamePanel parent, Entity entity)
	{
		if (Planet.class.isAssignableFrom(entity.getClass()))
		{
			return new UIPlanet(parent, (Planet)entity);
		}
		if (Star.class.isAssignableFrom(entity.getClass()))
		{
			return new UISun(parent, (Star)entity);
		}
		if (Enemy.class.isAssignableFrom(entity.getClass()))
		{
			return new UIEnemy(parent, (Enemy)entity);
		}
		else
		{
			throw new RuntimeException();
		}
	}

}
