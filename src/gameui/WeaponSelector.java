package gameui;

import java.awt.Color;
import java.awt.Dimension;
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

	private static final int minHeight = 50;

	public
	WeaponSelector (String iconPath, String name, String[] weapons)
	{
		this.icon = new ImageIcon(iconPath);
		this.iconLabel = new JLabel(this.icon);
		this.checkbox = new JCheckBox(name);
		this.selector = new JComboBox<String>(weapons);

		this.checkbox.setForeground(Color.WHITE);
		this.checkbox.setOpaque(false);
		Dimension selectorDimension = this.selector.getPreferredSize();
		this.iconLabel.setPreferredSize(new Dimension(100, minHeight));
		this.checkbox.setPreferredSize(new Dimension(100, minHeight));
		this.selector.setPreferredSize(new Dimension(100, (int) selectorDimension.getHeight()));

		this.setLayout(new FlowLayout());
		this.setOpaque(false);
		this.add(this.iconLabel);
		this.add(this.checkbox);
		this.add(this.selector);
	}

	public boolean
	isSelected ()
	{
		return this.checkbox.isSelected();
	}

	public String
	getSelectedWeapon ()
	{
		return (String)this.selector.getSelectedItem();
	}

}
