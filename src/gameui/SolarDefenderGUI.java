package gameui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

import gameobjects.Game;
import gameobjects.GameDifficulty;
import gameobjects.Missile;
import gameobjects.NoWeapon;
import gameobjects.Phalanx;
import gameobjects.Planet;
import gameobjects.RailGun;
import gameobjects.Weapon;


public class SolarDefenderGUI extends JFrame
{

	private Game game = new Game();
	private CardLayout layout = new CardLayout();

	//Panels
	private Image spaceBG = new ImageIcon(".\\Graphics\\space_background.jpg").getImage();
	private Image spaceBG2 = new ImageIcon(".\\Graphics\\solar_system_background.jpg").getImage();
	private JPanel mainScreen = new BackgroundImage(spaceBG);
	private JPanel difficultyScreen = new BackgroundImage(spaceBG);
	private	JPanel planetSelectScreen = new BackgroundImage(spaceBG);
	private JLayeredPane gameScreen = new JLayeredPane();
	private JPanel highScoreScreen = new BackgroundImage(spaceBG);

	//Main Screen
	ImageIcon titleIcon = new ImageIcon(".\\Graphics\\solar_defender_title.gif");
	private JLabel	titleGIF = new JLabel(titleIcon);
	private JButton sButton = new JButton("Start Game");
	private JButton hsButton = new JButton("High Scores");
	private JButton eButton = new JButton("Exit");

	//High Scores Screen
	private JButton hsToMain = new JButton("< Back");

	//Difficulty Screen
	private JButton easyButton = new JButton("Easy");
	private JButton medButton = new JButton("Medium");
	private JButton hardButton = new JButton("Hard");

	//Planet Selection
	String[] weapons = {"No Weapon", "Phalanx", "Missile", "RailGun"};
	boolean check1 = false, check2 = false, check3 = false, check4 = false, check5 = false, check6 = false;
	private JRadioButton planet1Radio = new JRadioButton("Uleturn");
	private JRadioButton planet2Radio = new JRadioButton("Zahonus");
	private JRadioButton planet3Radio = new JRadioButton("Atis");
	private JRadioButton planet4Radio = new JRadioButton("Hipra");
	private JRadioButton planet5Radio = new JRadioButton("Galea");
	private JRadioButton planet6Radio = new JRadioButton("Voneon");
	private JComboBox<String> planet1WeaponSelect = new JComboBox<String>(weapons);
	private JComboBox<String> planet2WeaponSelect = new JComboBox<String>(weapons);
	private JComboBox<String> planet3WeaponSelect = new JComboBox<String>(weapons);
	private JComboBox<String> planet4WeaponSelect = new JComboBox<String>(weapons);
	private JComboBox<String> planet5WeaponSelect = new JComboBox<String>(weapons);
	private JComboBox<String> planet6WeaponSelect = new JComboBox<String>(weapons);
	private	JButton planetToDifficulty = new JButton("< Back");
	private	JButton planetConfirm = new JButton("Confirm");

	//Game Screen
	private JButton pauseMenuButton = new JButton("Menu");
	private GamePanel gamePanel = new GamePanel(this.game, spaceBG);

	//General Buttons
	private JButton mainMenuButton = new JButton("Main Menu");

	//Game Objects
	ImageIcon planet1Icon = new ImageIcon(".\\Graphics\\planet1.png");
	ImageIcon planet2Icon = new ImageIcon(".\\Graphics\\planet2.png");
	ImageIcon planet3Icon = new ImageIcon(".\\Graphics\\planet3.png");
	ImageIcon planet4Icon = new ImageIcon(".\\Graphics\\planet4.png");
	ImageIcon planet5Icon = new ImageIcon(".\\Graphics\\planet5.png");
	ImageIcon planet6Icon = new ImageIcon(".\\Graphics\\planet6.png");
	private JLabel planet1Label = new JLabel(planet1Icon);
	private JLabel planet2Label = new JLabel(planet2Icon);
	private JLabel planet3Label = new JLabel(planet3Icon);
	private JLabel planet4Label = new JLabel(planet4Icon);
	private JLabel planet5Label = new JLabel(planet5Icon);
	private JLabel planet6Label = new JLabel(planet6Icon);
	Planet planet1 = new Planet(0.3 + 0.5 * (0.0 / 5.0));
	Planet planet2 = new Planet(0.3 + 0.5 * (1.0 / 5.0));
	Planet planet3 = new Planet(0.3 + 0.5 * (2.0 / 5.0));
	Planet planet4 = new Planet(0.3 + 0.5 * (3.0 / 5.0));
	Planet planet5 = new Planet(0.3 + 0.5 * (4.0 / 5.0));
	Planet planet6 = new Planet(0.3 + 0.5 * (5.0 / 5.0));
	Weapon weapon1 = null;

	public
	SolarDefenderGUI ()
	{
		super("Solar Defender");
		//this.setMinimumSize(new Dimension(320, 240));
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(layout);
		this.add(mainScreen, "MAIN");
		this.add(difficultyScreen, "DIFFICULTY");
		this.add(planetSelectScreen, "PLANETS");
		this.add(gameScreen, "GAME");
		this.add(highScoreScreen, "HIGHSCORES");

		planet1.spriteIdx = 1;
		planet2.spriteIdx = 2;
		planet3.spriteIdx = 3;
		planet4.spriteIdx = 4;
		planet5.spriteIdx = 5;
		planet6.spriteIdx = 6;

		buildMainScreen();
		buildDiffScreen();
		buildPlanetSelectScreen();
		buildGameScreen();
		buildHighScoresScreen();

		this.pack();
		this.setMinimumSize(this.getSize());
		this.setSize(1000, 1000);
		changePanel("MAIN");

		System.out.printf("frame H=%d, W=%d\n", this.getHeight(), this.getWidth());
		System.out.printf("main pane H=%d, W=%d\n", this.getContentPane().getHeight(), this.getContentPane().getWidth());
		System.out.printf("difficultyScreen H=%d, W=%d\n", difficultyScreen.getHeight(), difficultyScreen.getWidth());
		System.out.printf("mainScreen H=%d, W=%d\n", mainScreen.getHeight(), mainScreen.getWidth());
	}

	private void
	buildMainScreen ()
	{
		mainScreen.setLayout(new GridBagLayout());
		JPanel content = new JPanel();
		GroupLayout layout = new GroupLayout(content);
		content.setLayout(layout);
		content.setOpaque(false);
		mainScreen.add(content);

		sButton.addActionListener(new ButtonListener());
		hsButton.addActionListener(new ButtonListener());
		eButton.addActionListener(new ButtonListener());

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.CENTER)
				.addComponent(titleGIF)
				.addGroup(layout.createSequentialGroup()
						.addComponent(sButton, GroupLayout.DEFAULT_SIZE, 125, 150)
						.addGap(25)
						.addComponent(hsButton, GroupLayout.DEFAULT_SIZE, 125, 150))
				.addComponent(eButton, GroupLayout.DEFAULT_SIZE, 125, 150)
				);
		layout.setVerticalGroup(
				layout.createSequentialGroup()
				.addComponent(titleGIF)
				.addGap(50)
				.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
						.addComponent(sButton, GroupLayout.DEFAULT_SIZE, 25, 50)
						.addComponent(hsButton, GroupLayout.DEFAULT_SIZE, 25, 50))
				.addGap(50)
				.addComponent(eButton, GroupLayout.DEFAULT_SIZE, 25, 50)
				);
	}

	private void
	buildDiffScreen ()
	{
		difficultyScreen.setLayout(new GridBagLayout());
		JPanel content = new JPanel();
		BoxLayout layout = new BoxLayout(content, BoxLayout.Y_AXIS);
		content.setLayout(layout);
		content.setOpaque(false);
		difficultyScreen.add(content);

		easyButton.setBackground(Color.GREEN);
		medButton.setBackground(Color.ORANGE);
		hardButton.setBackground(Color.RED);

		Dimension buttonSize = new Dimension(125, 30);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
		easyButton.setMaximumSize(maxSize);
		medButton.setMaximumSize(maxSize);
		hardButton.setMaximumSize(maxSize);
		mainMenuButton.setMaximumSize(maxSize);
		easyButton.setPreferredSize(buttonSize);
		medButton.setPreferredSize(buttonSize);
		hardButton.setPreferredSize(buttonSize);
		mainMenuButton.setPreferredSize(buttonSize);

		easyButton.addActionListener(new ButtonListener());
		medButton.addActionListener(new ButtonListener());
		hardButton.addActionListener(new ButtonListener());
		mainMenuButton.addActionListener(new ButtonListener());

		content.add(easyButton);
		content.add(Box.createVerticalStrut(20));
		content.add(medButton);
		content.add(Box.createVerticalStrut(20));
		content.add(hardButton);
		content.add(Box.createVerticalStrut(40));
		content.add(mainMenuButton);
	}

	private void
	buildHighScoresScreen ()
	{
		highScoreScreen.setLayout(null);
		hsToMain.setBounds(350, 850, 125, 25);
		hsToMain.addActionListener(new ButtonListener());
		highScoreScreen.add(hsToMain);

	}

	private void
	buildPlanetSelectScreen ()
	{
		planetSelectScreen.setLayout(null);

		planet1Label.setBounds(50, 50, 100, 100);
		planet2Label.setBounds(50, 150, 100, 100);
		planet3Label.setBounds(50, 250, 100, 100);
		planet4Label.setBounds(50, 350, 100, 100);
		planet5Label.setBounds(50, 450, 100, 100);
		planet6Label.setBounds(50, 550, 100, 100);

		planet1Radio.setBounds(150, 90, 100, 20);
		planet2Radio.setBounds(150, 190, 100, 20);
		planet3Radio.setBounds(150, 290, 100, 20);
		planet4Radio.setBounds(150, 390, 100, 20);
		planet5Radio.setBounds(150, 490, 100, 20);
		planet6Radio.setBounds(150, 590, 100, 20);

		planet1Radio.setForeground(Color.WHITE);
		planet2Radio.setForeground(Color.WHITE);
		planet3Radio.setForeground(Color.WHITE);
		planet4Radio.setForeground(Color.WHITE);
		planet5Radio.setForeground(Color.WHITE);
		planet6Radio.setForeground(Color.WHITE);

		planet1Radio.setBackground(Color.BLACK);
		planet2Radio.setBackground(Color.BLACK);
		planet3Radio.setBackground(Color.BLACK);
		planet4Radio.setBackground(Color.BLACK);
		planet5Radio.setBackground(Color.BLACK);
		planet6Radio.setBackground(Color.BLACK);

		planet1WeaponSelect.setBounds(250, 90, 100, 20);
		planet2WeaponSelect.setBounds(250, 190, 100, 20);
		planet3WeaponSelect.setBounds(250, 290, 100, 20);
		planet4WeaponSelect.setBounds(250, 390, 100, 20);
		planet5WeaponSelect.setBounds(250, 490, 100, 20);
		planet6WeaponSelect.setBounds(250, 590, 100, 20);

		planetToDifficulty.setBounds(350, 850, 125, 25);
		planetConfirm.setBounds(500, 850, 125, 25);

		planet1Radio.addActionListener(new ButtonListener()); 
		planet2Radio.addActionListener(new ButtonListener());
		planet3Radio.addActionListener(new ButtonListener());
		planet4Radio.addActionListener(new ButtonListener());
		planet5Radio.addActionListener(new ButtonListener());
		planet6Radio.addActionListener(new ButtonListener());

		planet1WeaponSelect.addActionListener(new ButtonListener());
		planet2WeaponSelect.addActionListener(new ButtonListener());
		planet3WeaponSelect.addActionListener(new ButtonListener());
		planet4WeaponSelect.addActionListener(new ButtonListener());
		planet5WeaponSelect.addActionListener(new ButtonListener());
		planet6WeaponSelect.addActionListener(new ButtonListener());

		planetToDifficulty.addActionListener(new ButtonListener());
		planetConfirm.addActionListener(new ButtonListener());

		planetSelectScreen.add(planet1Label);
		planetSelectScreen.add(planet2Label);
		planetSelectScreen.add(planet3Label);
		planetSelectScreen.add(planet4Label);
		planetSelectScreen.add(planet5Label);
		planetSelectScreen.add(planet6Label);

		planetSelectScreen.add(planet1Radio);
		planetSelectScreen.add(planet2Radio);
		planetSelectScreen.add(planet3Radio);
		planetSelectScreen.add(planet4Radio);
		planetSelectScreen.add(planet5Radio);
		planetSelectScreen.add(planet6Radio);

		planetSelectScreen.add(planet1WeaponSelect);
		planetSelectScreen.add(planet2WeaponSelect);
		planetSelectScreen.add(planet3WeaponSelect);
		planetSelectScreen.add(planet4WeaponSelect);
		planetSelectScreen.add(planet5WeaponSelect);
		planetSelectScreen.add(planet6WeaponSelect);

		planetSelectScreen.add(planetToDifficulty);
		planetSelectScreen.add(planetConfirm);
	}

	private void
	buildGameScreen ()
	{
		gameScreen.setLayout(new BorderLayout());
		pauseMenuButton.addActionListener(new ButtonListener());
		pauseMenuButton.setBounds(0, 0, 125, 25);
		gameScreen.add(pauseMenuButton, 0);
		gameScreen.add(gamePanel, 2);
	}

	private void
	changePanel (String panel)
	{
		this.layout.show(this.getContentPane(), panel);
		this.getContentPane().doLayout();
		this.update(this.getGraphics());
	}

	private class ButtonListener implements ActionListener {


		public void actionPerformed(ActionEvent e) {

			//JButton source = (JButton)(e.getSource());

			//Main Menu Screen
			if(e.getSource().equals(sButton))
			{
				changePanel("DIFFICULTY");
			}
			else if(e.getSource().equals(hsButton))
			{
				changePanel("HIGHSCORES");
			}
			else if(e.getSource().equals(eButton))
			{
				ImageIcon iconExit = new ImageIcon(".\\Graphics\\enemy1.jpg");
				int result = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Exit", JOptionPane.YES_NO_OPTION, 0, iconExit);
				if(result == JOptionPane.YES_OPTION) {
					dispose();
				}
			}

			//Difficulty Screen
			else if(e.getSource().equals(easyButton))
			{ 
				game.setDifficulty(GameDifficulty.EASY);
				changePanel("PLANETS");
			}
			else if(e.getSource().equals(medButton))
			{
				game.setDifficulty(GameDifficulty.NORMAL);
				changePanel("PLANETS");
			}
			else if(e.getSource().equals(hardButton))
			{
				game.setDifficulty(GameDifficulty.HARD);
				changePanel("PLANETS");
			}

			//High Score Screen
			else if(e.getSource().equals(hsToMain)) {
				changePanel("MAIN");
			}

			//Planet Select Screen
			else if(e.getSource() == planet1Radio) {
				if (planet1Radio.isSelected()) {
					game.addPlanet(planet1);
				}
				else {
					game.removePlanet(planet1);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet2Radio)) {
				if (planet2Radio.isSelected()) {
					game.addPlanet(planet2);
				}
				else {
					game.removePlanet(planet2);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet3Radio)) {
				if (planet3Radio.isSelected()) {
					game.addPlanet(planet3);
				}
				else {
					game.removePlanet(planet3);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet4Radio)) {
				if (planet4Radio.isSelected()) {
					game.addPlanet(planet4);
				}
				else {
					game.removePlanet(planet4);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet5Radio)) {
				if (planet5Radio.isSelected()) {
					game.addPlanet(planet5);
				}
				else {
					game.removePlanet(planet5);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet6Radio)) {
				if (planet6Radio.isSelected()) {
					game.addPlanet(planet6);
				}
				else {
					game.removePlanet(planet6);
				}
				System.out.println(game.getPlanets());
			}
			else if(e.getSource().equals(planet1WeaponSelect)) {
				if(planet1WeaponSelect.getSelectedItem() == "No Weapon") {
					planet1.setWeapon(new NoWeapon());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Phalanx") {
					planet1.setWeapon(new Phalanx());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Missile") {
					planet1.setWeapon(new Missile());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet1.setWeapon(new RailGun());
				}
				System.out.println(planet1WeaponSelect.getSelectedItem());
			}
			else if(e.getSource().equals(planet2WeaponSelect)) {
				if(planet2WeaponSelect.getSelectedItem() == "No Weapon") {
					planet2.setWeapon(new NoWeapon());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Phalanx") {
					planet2.setWeapon(new Phalanx());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Missile") {
					planet2.setWeapon(new Missile());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet2.setWeapon(new RailGun());
				}
				System.out.println(planet2WeaponSelect.getSelectedItem());
			}
			else if(e.getSource().equals(planet3WeaponSelect)) {
				if(planet3WeaponSelect.getSelectedItem() == "No Weapon") {
					planet3.setWeapon(new NoWeapon());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Phalanx") {
					planet3.setWeapon(new Phalanx());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Missile") {
					planet3.setWeapon(new Missile());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet3.setWeapon(new RailGun());
				}
				System.out.println(planet3WeaponSelect.getSelectedItem());
			}
			else if(e.getSource().equals(planet4WeaponSelect)) {
				if(planet4WeaponSelect.getSelectedItem() == "No Weapon") {
					planet4.setWeapon(new NoWeapon());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Phalanx") {
					planet4.setWeapon(new Phalanx());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Missile") {
					planet4.setWeapon(new Missile());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet4.setWeapon(new RailGun());
				}
				System.out.println(planet4WeaponSelect.getSelectedItem());
			}
			else if(e.getSource().equals(planet5WeaponSelect)) {
				if(planet5WeaponSelect.getSelectedItem() == "No Weapon") {
					planet5.setWeapon(new NoWeapon());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Phalanx") {
					planet5.setWeapon(new Phalanx());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Missile") {
					planet5.setWeapon(new Missile());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet5.setWeapon(new RailGun());
				}
				System.out.println(planet5WeaponSelect.getSelectedItem());
			}
			else if(e.getSource().equals(planet6WeaponSelect)) {
				if(planet6WeaponSelect.getSelectedItem() == "No Weapon") {
					planet6.setWeapon(new NoWeapon());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Phalanx") {
					planet6.setWeapon(new Phalanx());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Missile") {
					planet6.setWeapon(new Missile());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet6.setWeapon(new RailGun());
				}
				System.out.println(planet6WeaponSelect.getSelectedItem());
			}

			else if (e.getSource().equals(planetToDifficulty)) {
				changePanel("DIFFICULTY");
			}

			else if(e.getSource().equals(planetConfirm)) {
				checkSelection();
				double vh = gameScreen.getHeight();
				double vw = gameScreen.getWidth();
				System.out.printf("Screen h/w %f %f\n", vh, vw);
				System.out.println("planetConfirm");
				changePanel("GAME");
				gamePanel.startSimulation();
			}

			else if(e.getSource().equals(pauseMenuButton))
			{
				gamePanel.stopSimulation();
				changePanel("MAIN");
			}

			//General Buttons
			else if(e.getSource().equals(mainMenuButton))
			{
				changePanel("MAIN");
				if (gamePanel.isRunning())
				{
					gamePanel.stopSimulation();
				}
			}

			else
			{
				System.out.printf("UNHANDLED EVENT from %h", e.getSource());
			}

		}
		public void checkSelection() {

			if (planet1Radio.isSelected()) {
				game.addPlanet(planet1);

				if(planet1WeaponSelect.getSelectedItem() == "No Weapon") {
					planet1.setWeapon(new NoWeapon());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Phalanx") {
					planet1.setWeapon(new Phalanx());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Missile") {
					planet1.setWeapon(new Missile());
				}
				else if(planet1WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet1.setWeapon(new RailGun());
				}
			}
			else {
				game.removePlanet(planet1);
			}

			if (planet2Radio.isSelected()) {
				game.addPlanet(planet2);

				if(planet2WeaponSelect.getSelectedItem() == "No Weapon") {
					planet2.setWeapon(new NoWeapon());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Phalanx") {
					planet2.setWeapon(new Phalanx());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Missile") {
					planet2.setWeapon(new Missile());
				}
				else if(planet2WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet2.setWeapon(new RailGun());
				}
			}
			else {
				game.removePlanet(planet2);
			}

			if (planet3Radio.isSelected()) {
				game.addPlanet(planet3);

				if(planet3WeaponSelect.getSelectedItem() == "No Weapon") {
					planet3.setWeapon(new NoWeapon());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Phalanx") {
					planet3.setWeapon(new Phalanx());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Missile") {
					planet3.setWeapon(new Missile());
				}
				else if(planet3WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet3.setWeapon(new RailGun());
				}

			}
			else {
				game.removePlanet(planet3);
			}

			if (planet4Radio.isSelected()) {
				game.addPlanet(planet4);

				if(planet4WeaponSelect.getSelectedItem() == "No Weapon") {
					planet4.setWeapon(new NoWeapon());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Phalanx") {
					planet4.setWeapon(new Phalanx());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Missile") {
					planet4.setWeapon(new Missile());
				}
				else if(planet4WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet4.setWeapon(new RailGun());
				}
			}
			else {
				game.removePlanet(planet4);
			}

			if (planet5Radio.isSelected()) {
				game.addPlanet(planet5);

				if(planet5WeaponSelect.getSelectedItem() == "No Weapon") {
					planet5.setWeapon(new NoWeapon());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Phalanx") {
					planet5.setWeapon(new Phalanx());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Missile") {
					planet5.setWeapon(new Missile());
				}
				else if(planet5WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet5.setWeapon(new RailGun());
				}
			}
			else {
				game.removePlanet(planet5);
			}

			if (planet6Radio.isSelected()) {
				game.addPlanet(planet6);

				if(planet6WeaponSelect.getSelectedItem() == "No Weapon") {
					planet6.setWeapon(new NoWeapon());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Phalanx") {
					planet6.setWeapon(new Phalanx());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Missile") {
					planet6.setWeapon(new Missile());
				}
				else if(planet6WeaponSelect.getSelectedItem() == "Rail Gun") {
					planet6.setWeapon(new RailGun());
				}
				System.out.println(planet6WeaponSelect.getSelectedItem());
			}
			else {
				game.removePlanet(planet6);
			}
		}
	}

}
