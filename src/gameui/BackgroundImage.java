package gameui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class BackgroundImage extends JPanel{
	
	//private Image img;
	Image img = Toolkit.getDefaultToolkit().getImage(".\\Graphics\\solar_system_background.jpg");
	
	  public BackgroundImage(String img) {
	    this(new ImageIcon(img).getImage());
	  }
	  
	  public BackgroundImage(Image img) {
	    this.img = img;
	    Dimension size = new Dimension(img.getWidth(null), img.getHeight(null));
	    setPreferredSize(size);
	 //   setMinimumSize(size);
	 //   setMaximumSize(size);
	    setSize(size);
	    setLayout(null);
	  }
	  @Override
	  public void paintComponent(Graphics g) {
	    g.drawImage(img, 0, 0, null);
	  }

}
