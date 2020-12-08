package gameui;

import javax.swing.JFrame;

public class MilestoneDriver
{

	public static void
	main (String[] args)
	{
		SolarDefenderGUI frame = new SolarDefenderGUI();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    frame.setVisible(true);
	}

}
