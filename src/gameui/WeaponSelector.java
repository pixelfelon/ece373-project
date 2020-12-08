package gameui;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class WeaponSelector extends JPanel {

	private ImageIcon icon;
	private JLabel iconLabel;
	private JCheckBox checkbox;
	private JComboBox<String> selector;

	public
	WeaponSelector (String iconPath, String name, String[] weapons)
	{
		this.icon = new ImageIcon(iconPath);
		this.iconLabel = new JLabel(this.icon);
		this.checkbox = new JCheckBox(name);
		this.selector = new JComboBox<String>(weapons);

		this.setLayout(new FlowLayout());
		this.add(this.iconLabel);
		this.add(this.checkbox);
		this.add(this.selector);
	}

}
