package gameui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.io.Serializable;
import java.util.HashMap;

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
import javax.swing.JTextField;

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

	// Game Objects
		private Game game = new Game();
		String[] weapons = {"No Weapon", "Phalanx", "Missile", "Rail Gun"};
		private final int maxPlanets = 6;
		private final String[] planetIcons = {
			".\\Graphics\\planet1.png",
			".\\Graphics\\planet2.png",
			".\\Graphics\\planet3.png",
			".\\Graphics\\planet4.png",
			".\\Graphics\\planet5.png",
			".\\Graphics\\planet6.png"
		};
		private final String[] planetNames = {
			"Uleturn",
			"Zahonus",
			"Atis",
			"Hipra",
			"Galea",
			"Voneon"
		};
		private Planet[] planets = new Planet[maxPlanets];
		private WeaponSelector[] planetUI = new WeaponSelector[maxPlanets];

		// Panels
		private CardLayout layout = new CardLayout();
		private Image spaceBG = new ImageIcon(".\\Graphics\\space_background.jpg").getImage();
		private JPanel mainScreen = new BackgroundImage(spaceBG);
		private JPanel difficultyScreen = new BackgroundImage(spaceBG);
		private	JPanel planetSelectScreen = new BackgroundImage(spaceBG);
		private JLayeredPane gameScreen = new JLayeredPane();
		private JPanel highScoreScreen = new BackgroundImage(spaceBG);

		// Main Screen
		ImageIcon titleIcon = new ImageIcon(".\\Graphics\\solar_defender_title.gif");
		private JLabel	titleGIF = new JLabel(titleIcon);
		private JButton sButton = new JButton("Start Game");
		private JButton hsButton = new JButton("High Scores");
		private JButton eButton = new JButton("Exit");

		// High Scores Screen
		private JButton hsToMain = new JButton("< Back");

		// Difficulty Screen
		private JButton easyButton = new JButton("Easy");
		private JButton medButton = new JButton("Medium");
		private JButton hardButton = new JButton("Hard");
		private JButton mainMenuButton = new JButton("Main Menu");

		// Planet Selection
		private	JButton planetToDifficulty = new JButton("< Back");
		private	JButton planetConfirm = new JButton("Confirm");

		// Game Screen
		private JButton pauseMenuButton = new JButton("Menu");
		private GamePanel gamePanel = new GamePanel(this.game, spaceBG);
	
	
	private HighScores highScore = new HighScores();
	static JButton viewScores = new JButton("Did you get a high Score?");
	
	public
	SolarDefenderGUI ()
	{
		super("Solar Defender");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setLayout(layout);
		this.add(mainScreen, "MAIN");
		this.add(difficultyScreen, "DIFFICULTY");
		this.add(planetSelectScreen, "PLANETS");
		this.add(gameScreen, "GAME");
		this.add(highScoreScreen, "HIGHSCORES");

		for (int i = 0; i < maxPlanets; i++)
		{
			planets[i] = new Planet(0.3 + 0.5 * ((double)i / (double)(maxPlanets - 1)));
			planets[i].spriteIdx = i + 1;
		}

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

		titleGIF.setOpaque(false);
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
		
		highScoreScreen.setLayout(new GridBagLayout());
		
		JPanel content = new JPanel();
		GridLayout layout = new GridLayout(2, 1);
		content.setLayout(layout);
		content.setOpaque(false);
		highScoreScreen.add(content);
		
		JPanel scoreScreen = new JPanel();
		JPanel backButton = new JPanel();
		JLabel scores = new JLabel();
		
		scoreScreen.setOpaque(false);
		backButton.setOpaque(false);
		
	/*	ByteArrayOutputStream stream = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(stream);

        PrintStream originalPrintStream = System.out;

        System.setOut(ps);*/
        
        highScore = HighScores.loadData();
		
        if(highScore != null) {
		highScore.printScores();
        }

        //set it back
       /* System.setOut(originalPrintStream);
        
        String output = new String(stream.toByteArray());*/
		
		//System.out.println(output);
		
	/*	scores.setText(output);
		scores.setForeground(Color.GREEN);
		scores.setFont(new Font("Serif", Font.PLAIN, 25));
		
		scoreScreen.add(scores);*/
		
		
		Dimension buttonSize = new Dimension(115, 30);
		Dimension maxSize = new Dimension(Short.MAX_VALUE, Short.MAX_VALUE);
		
		hsToMain.setMaximumSize(maxSize);
		hsToMain.setPreferredSize(buttonSize);
		
		hsToMain.addActionListener(new ButtonListener());
		
		backButton.add(hsToMain);
		
		//content.add(scoreScreen);
		content.add(backButton);

	}

	private void
	buildPlanetSelectScreen ()
	{
		planetSelectScreen.setLayout(new GridBagLayout());
		JPanel content = new JPanel();
		GroupLayout layout = new GroupLayout(content);
		content.setLayout(layout);
		content.setOpaque(false);
		planetSelectScreen.add(content);

		planetToDifficulty.addActionListener(new ButtonListener());
		planetConfirm.addActionListener(new ButtonListener());

		Group horGroup = layout.createParallelGroup(GroupLayout.Alignment.CENTER);
		Group verGroup = layout.createSequentialGroup();

		for (int i = 0; i < maxPlanets; i++)
		{
			WeaponSelector ui = new WeaponSelector(planetIcons[i], planetNames[i], weapons);
			horGroup.addComponent(ui);
			verGroup.addComponent(ui);
			planetUI[i] = ui;
		}

		horGroup.addGroup(layout.createSequentialGroup()
			.addComponent(planetToDifficulty, GroupLayout.DEFAULT_SIZE, 125, 150)
			.addGap(20)
			.addComponent(planetConfirm, GroupLayout.DEFAULT_SIZE, 125, 150)
		);
		verGroup.addGap(30);
		verGroup.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
			.addComponent(planetToDifficulty,  GroupLayout.DEFAULT_SIZE, 25, 50)
			.addComponent(planetConfirm, GroupLayout.DEFAULT_SIZE, 25, 50)
		);

		layout.setHorizontalGroup(horGroup);
		layout.setVerticalGroup(verGroup);
	}

	
	public void 
	Scores() {
		
	
		JTextField newName = new JTextField(10);
		
		JPanel myPanel = new JPanel(new GridLayout(2, 1));
	    myPanel.add(new JLabel("Enter Name"));
	    myPanel.add(newName);
	    
	    int result = JOptionPane.showConfirmDialog(null, myPanel, "NEW HIGH SCORE!", JOptionPane.OK_OPTION);
	    
	    	if(result == JOptionPane.OK_OPTION && newName.getText() != null) {
			
			//highScore.setScore(game.getScore());
			//highScore.setName(newName.getText());
			highScore.sortScores(game.getScore(), newName.getText());
	    	}
		
		HighScores.saveData(highScore);
		
		buildHighScoresScreen();
		
		changePanel("HIGHSCORES");
	
	}
	
	
	private void
	buildGameScreen ()
	{

		gameScreen.setLayout(new BorderLayout());
		pauseMenuButton.addActionListener(new ButtonListener());
		viewScores.addActionListener(new ButtonListener());
		pauseMenuButton.setBounds(0, 0, 175, 25);
		
		gameScreen.add(pauseMenuButton, 0);
		gameScreen.add(viewScores, BorderLayout.SOUTH);
		gameScreen.add(gamePanel, 2);

		
		viewScores.setVisible(false);

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
			
			else if(e.getSource().equals(viewScores))
			{
				Scores();
				//changePanel("HIGHSCORES");
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
		private void
		checkSelection ()
		{
			for (int i = 0; i < maxPlanets; i++)
			{
				if (planetUI[i].isSelected())
				{
					game.addPlanet(planets[i]);

					switch (planetUI[i].getSelectedWeapon())
					{
					case "Phalanx":
						planets[i].setWeapon(new Phalanx());
						break;
					case "Missile":
						planets[i].setWeapon(new Missile());
						break;
					case "Rail Gun":
						planets[i].setWeapon(new RailGun());
						break;
					default:
						System.out.printf("UNHANDLED WEAPON %s", planetUI[i].getSelectedWeapon());
					case "No Weapon":
						planets[i].setWeapon(new NoWeapon());
						break;
					}
				}
				else
				{
					game.removePlanet(planets[i]);
				}
		}
	}

}
