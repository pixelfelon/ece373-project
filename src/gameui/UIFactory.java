package gameui;

import javax.swing.JPanel;

import gameobjects.*;

public class UIFactory {

	public static UIEntity
	makeUIEntity (JPanel parent, Entity entity)
	{
		if (Planet.class.isAssignableFrom(entity.getClass()))
		{
			return new UIPlanet(parent, entity);
		}
		if (Star.class.isAssignableFrom(entity.getClass()))
		{
			return new UISun(parent, entity);
		}
		if (Enemy.class.isAssignableFrom(entity.getClass()))
		{
			return new UIEnemy(parent, entity);
		}
		else
		{
			return new UIDefault(parent, entity);
			//throw new RuntimeException();
		}
	}

}
