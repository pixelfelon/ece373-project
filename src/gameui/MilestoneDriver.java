package gameui;

import java.awt.Dimension;

import javax.swing.JFrame;

public class MilestoneDriver
{

	public static void
	main (String[] args)
	{
		SolarDefenderGUI frame = new SolarDefenderGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setSize(800, 800);
	    frame.setMinimumSize(new Dimension(450, 450));
	    frame.setVisible(true);
	}

}
