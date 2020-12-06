package gameui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
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
		int difficulty;
		int planets = 6;
		private ArrayList<Planet> planetsArray;
		
		private Image background = Toolkit.getDefaultToolkit().getImage("solar_system_background.jpg");
		//for setMinimum parameter
		private Dimension screenSize = new Dimension(720, 542); 
		String[] weapons = {"No Weapon", "Phalanx", "Missile", "RailGun"};
		
		//Panels
		private JPanel mainScreen = new JPanel();
		private JPanel difficultyScreen = new JPanel();
		private	JPanel planetSelectScreen = new JPanel();
		private JPanel gameScreen = new JPanel();
		private JPanel highScoreScreen = new JPanel();
		
		//Main Screen
		ImageIcon titleIcon = new ImageIcon(".\\Graphics\\solar_defender_title.gif");
		private JLabel	titleGIF = new JLabel(titleIcon);
		private JButton sButton = new JButton("Start Game");
		private JButton hsButton = new JButton("High Scores");
		private JButton eButton = new JButton("Exit");
		
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
		private JComboBox	planet1WeaponSelect = new JComboBox(weapons);
		private JComboBox	planet2WeaponSelect = new JComboBox(weapons);
		private JComboBox	planet3WeaponSelect = new JComboBox(weapons);
		private JComboBox	planet4WeaponSelect = new JComboBox(weapons);
		private JComboBox	planet5WeaponSelect = new JComboBox(weapons);
		private JComboBox	planet6WeaponSelect = new JComboBox(weapons);
		private	JButton planetToDifficulty = new JButton("< Back");
		private	JButton planetConfirm = new JButton("Confirm");
		
		//Game Screen
		private JButton pauseMenuButton = new JButton("Menu");
	
		//General Buttons
		private JButton mainMenuButton = new JButton("Main Menu");
		
		//Game Objects
		ImageIcon sunIcon = new ImageIcon(".\\Graphics\\sun.png");
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
		Planet planet1 = new Planet(0.3 + 0.5 * ((double)0 / (double)planets));
		Planet planet2 = new Planet(0.3 + 0.5 * ((double)1 / (double)planets));
		Planet planet3 = new Planet(0.3 + 0.5 * ((double)2 / (double)planets));
		Planet planet4 = new Planet(0.3 + 0.5 * ((double)3 / (double)planets));
		Planet planet5 = new Planet(0.3 + 0.5 * ((double)4 / (double)planets));
		Planet planet6 = new Planet(0.3 + 0.5 * ((double)5 / (double)planets));
		Weapon weapon1 = null;
		
		public SolarDefenderGUI(){
			
			super("Solar Defenders");
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		   
		    planets = 0;
		    planetsArray = new ArrayList<Planet>();
		    BuildMainScreen();
		    BuildDiffScreen();
		    BuildPlanetSelectScreen();
		    BuildGameScreen();
		    //getContentPane().setLayout(new BorderLayout());
		    changePanel(mainScreen);
		    
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
			gameScreen.setLayout(null);
			
			sunLabel.setBounds(450, 450, 50, 50);
			pauseMenuButton.setBounds(0, 0, 125, 25);
			
			gameScreen.add(sunLabel);
			gameScreen.add(pauseMenuButton);
			gameScreen.add(panel);
			this.pack();
		}
		
		private void changePanel(JPanel panel) {
		    getContentPane().removeAll();
		    getContentPane().add(panel, BorderLayout.CENTER);
		    getContentPane().doLayout();
		    update(getGraphics());
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
					changePanel(difficultyScreen);
				}
				else if(e.getSource().equals(hsButton))
				{
					
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
					difficulty = 1;
					Game intitializeGame = new Game(GameDifficulty.valueOf("EASY")); 
					game = intitializeGame;
					changePanel(planetSelectScreen);
				}
				else if(e.getSource().equals(medButton))
				{
					difficulty = 2;
					Game intitializeGame = new Game(GameDifficulty.valueOf("NORMAL")); 
					game = intitializeGame;
					changePanel(planetSelectScreen);
				}
				else if(e.getSource().equals(hardButton))
				{
					difficulty = 3;
					Game intitializeGame = new Game(GameDifficulty.valueOf("HARD")); 
					game = intitializeGame;
					changePanel(planetSelectScreen);
				}
				//Planet Select Screen
				else if(e.getSource() == planet1Radio) {
					if(check1 == false) {
						planets++;
						//planetsArray.add(0, planet1);
						check1 = true;
					}
					else if(check1 == true){
						planets--;
						//planetsArray.remove(0);
						check1 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
					
				}
				else if(e.getSource().equals(planet2Radio)) {
					if(check2 == false) {
						planets++;
						//planetsArray.add(1, planet2);
						check2 = true;
					}
					else if(check2 == true){
						planets--;
						//planetsArray.remove(1);
						check2 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
				}
				else if(e.getSource().equals(planet3Radio)) {
					if(check3 == false) {
						planets++;
						//planetsArray.add(2, planet3);
						check3 = true;
					}
					else if(check3 == true){
						planets--;
						//planetsArray.remove(2);
						check3 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
				}
				else if(e.getSource().equals(planet4Radio)) {
					if(check4 == false) {
						planets++;
						//planetsArray.add(3, planet4);
						check4 = true;
					}
					else if(check4 == true){
						planets--;
						//planetsArray.remove(3);
						check4 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
				}
				else if(e.getSource().equals(planet5Radio)) {
					if(check5 == false) {
						planets++;
						//planetsArray.add(4, planet5);
						check5 = true;
					}
					else if(check5 == true){
						planets--;
						//planetsArray.remove(4);
						check5 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
				}
				else if(e.getSource().equals(planet6Radio)) {
					if(check6 == false) {
						planets++;
						//planetsArray.add(5, planet6);
						check6 = true;
					}
					else if(check6 == true){
						planets--;
						//planetsArray.remove(5);
						check6 = false;
					}
					System.out.println(planets);
					//System.out.println(planetsArray.size());
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
					System.out.println(planet1WeaponSelect.getSelectedItem());
				}
				else if(e.getSource().equals(planet3WeaponSelect)) {
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
					System.out.println(planet1WeaponSelect.getSelectedItem());
				}
				else if(e.getSource().equals(planet4WeaponSelect)) {
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
					System.out.println(planet1WeaponSelect.getSelectedItem());
				}
				else if(e.getSource().equals(planet5WeaponSelect)) {
					if(planet3WeaponSelect.getSelectedItem() == "No Weapon") {
						planet3.setWeapon(new NoWeapon());
					}
					else if(planet2WeaponSelect.getSelectedItem() == "Phalanx") {
						planet3.setWeapon(new Phalanx());
					}
					else if(planet3WeaponSelect.getSelectedItem() == "Missile") {
						planet2.setWeapon(new Missile());
					}
					else if(planet3WeaponSelect.getSelectedItem() == "Rail Gun") {
						planet3.setWeapon(new RailGun());
					}
					System.out.println(planet1WeaponSelect.getSelectedItem());
				}
				else if(e.getSource().equals(planet6WeaponSelect)) {
					if(planet4WeaponSelect.getSelectedItem() == "No Weapon") {
						planet4.setWeapon(new NoWeapon());
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
					System.out.println(planet1WeaponSelect.getSelectedItem());
				}
				
				else if (e.getSource().equals(planetToDifficulty)) {
					changePanel(difficultyScreen);
				}
				
				else if(e.getSource().equals(planetConfirm)) {
					System.out.println("planetConfirm");
					changePanel(gameScreen);
				}
				
				//General Buttons
				else if(e.getSource().equals(mainMenuButton))
				{
					changePanel(mainScreen);
				}

		    }
		}

}
