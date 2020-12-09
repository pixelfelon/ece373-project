package gameui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class BackgroundImage extends JPanel
{

	Image img = Toolkit.getDefaultToolkit().getImage(".\\Graphics\\solar_system_background.jpg");

	public
	BackgroundImage (String img)
	{
		this(new ImageIcon(img).getImage());
	}

	public
	BackgroundImage (Image img)
	{
		this.img = img;
	}

	@Override
	public void
	paintComponent (Graphics g)
	{
		super.paintComponent(g);
		g.drawImage(img, 0, 0, null);
	}

}
