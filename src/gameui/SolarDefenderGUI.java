package gameui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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


public class SolarDefenderGUI extends JFrame {
	
		private Game game = new Game();
		private CardLayout layout = new CardLayout();
		
		private Image background = Toolkit.getDefaultToolkit().getImage("solar_system_background.jpg");
		//for setMinimum parameter
		private Dimension screenSize = new Dimension(720, 542); 
		String[] weapons = {"No Weapon", "Phalanx", "Missile", "RailGun"};
		
		//Panels
		private JPanel mainScreen = new JPanel();
		private JPanel difficultyScreen = new JPanel();
		private	JPanel planetSelectScreen = new JPanel();
		private JLayeredPane gameScreen = new JLayeredPane();
		private JPanel highScoreScreen = new JPanel();
		
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
		private GamePanel gamePanel = new GamePanel(this.game);
	
		//General Buttons
		private JButton mainMenuButton = new JButton("Main Menu");
		
		//Game Objects
		ImageIcon sunIcon = new ImageIcon();
		ImageIcon planet1Icon = new ImageIcon(".\\Graphics\\planet1.png");
		ImageIcon planet2Icon = new ImageIcon(".\\Graphics\\planet2.png");
		ImageIcon planet3Icon = new ImageIcon(".\\Graphics\\planet3.png");
		ImageIcon planet4Icon = new ImageIcon(".\\Graphics\\planet4.png");
		ImageIcon planet5Icon = new ImageIcon(".\\Graphics\\planet5.png");
		ImageIcon planet6Icon = new ImageIcon(".\\Graphics\\planet6.png");
		private JLabel sunLabel = new JLabel(sunIcon);
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
		
		public SolarDefenderGUI() {
			super("Solar Defenders");
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

		    BuildMainScreen();
		    BuildDiffScreen();
		    BuildPlanetSelectScreen();
		    BuildGameScreen();
		    BuildHighScoresScreen();
		    //getContentPane().setLayout(new BorderLayout());
		    changePanel("MAIN");
		}
		
		public void paintComponent(Graphics g) {
			   super.paintComponents(g);
			   g.drawImage(background, 0, 0, null);
			   
			}

			
		private void BuildMainScreen() {
			
			
			//JLabel icon = new JLabel(new ImageIcon(".\\Graphics\\solar_system_background.jpg")); 
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\space_background.jpg").getImage());
			Rectangle f = this.getBounds();
			//panel.setSize(super.getWidth(), super.getHeight());
			//panel.setBounds(400, 200, 800, 500);
			
			//BackgroundImage img = new BackgroundImage(mainScreen);
			System.out.println(super.getSize() + " " + f.getHeight());
			mainScreen.setLayout(null);
			
		    //Main Screen
			titleGIF.setBounds(290,150,400, 75);
		    sButton.setBounds(350, 300, 125, 25);
		    hsButton.setBounds(500, 300, 125, 25);
		    eButton.setBounds(425, 375, 125, 25);
		    
		    sButton.addActionListener(new ButtonListener());
		    hsButton.addActionListener(new ButtonListener());
		    eButton.addActionListener(new ButtonListener());
		    
		    mainScreen.add(titleGIF);
		    mainScreen.add(sButton);
		    mainScreen.add(hsButton);
		    mainScreen.add(eButton);
		    mainScreen.add(panel);
		   // super.add(new JLabel(new ImageIcon(".\\Graphics\\solar_system_background.jpg")));
		    
		    this.pack();
		}
		
		private void BuildDiffScreen() {
			
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\space_background.jpg").getImage());
			difficultyScreen.setLayout(null);
			
		    easyButton.setBounds(425, 200, 125, 30);
		    easyButton.setBackground(Color.GREEN);
		    medButton.setBounds(425, 255, 125, 30);
		    medButton.setBackground(Color.ORANGE);
		    hardButton.setBounds(425, 310, 125, 30);
		    hardButton.setBackground(Color.RED);
		    mainMenuButton.setBounds(425, 400, 125, 30);
		    
		    easyButton.addActionListener(new ButtonListener());
		    medButton.addActionListener(new ButtonListener());
		    hardButton.addActionListener(new ButtonListener());
		    mainMenuButton.addActionListener(new ButtonListener());
		    
		    difficultyScreen.add(easyButton);
		    difficultyScreen.add(medButton);
		    difficultyScreen.add(hardButton);
		    difficultyScreen.add(mainMenuButton);
		    difficultyScreen.add(panel);
		    this.pack();
		}
		
		private void BuildHighScoresScreen() {
			
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\space_background.jpg").getImage());
			highScoreScreen.setLayout(null);
			
			hsToMain.setBounds(350, 850, 125, 25);
			
			hsToMain.addActionListener(new ButtonListener());
			
			highScoreScreen.add(hsToMain);
			difficultyScreen.add(panel);
			this.pack();
			
		}
		
		private void BuildPlanetSelectScreen() {
				
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\space_background.jpg").getImage());
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
			planetSelectScreen.add(panel);
			this.pack();
		}

		private void BuildGameScreen(){
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\space_background.jpg").getImage());
			//gameScreen.setBounds(this.getBounds());
			gameScreen.setLayout(new BorderLayout());
			pauseMenuButton.addActionListener(new ButtonListener());
			
			sunLabel.setBounds(450, 450, 50, 50);
			pauseMenuButton.setBounds(0, 0, 125, 25);
			//gamePanel.setBounds(0, 0, 800, 800);

			gameScreen.add(pauseMenuButton, 0);
			gameScreen.add(sunLabel, 1);
			gameScreen.add(panel, 3);
			gameScreen.add(gamePanel, 2);
			this.pack();
		}
		
		private void changePanel(String panel) {
			this.layout.show(this.getContentPane(), panel);
		    this.getContentPane().doLayout();
		    this.update(this.getGraphics());
		}
		
		public Dimension getScreenSize() {
			return screenSize;
		}

		public void setScreenSize(Dimension screenSize) {
			this.screenSize = screenSize;
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
