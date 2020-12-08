package gameui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Group;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JLayeredPane;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameobjects.Game;
import gameobjects.GameDifficulty;
import gameobjects.Missile;
import gameobjects.NoWeapon;
import gameobjects.Phalanx;
import gameobjects.Planet;
import gameobjects.RailGun;

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
		highScoreScreen.setLayout(null);
		hsToMain.setBounds(350, 850, 125, 25);
		hsToMain.addActionListener(new ButtonListener());
		highScoreScreen.add(hsToMain);

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

	private class ButtonListener implements ActionListener
	{
		public void
		actionPerformed (ActionEvent e)
		{
			// Main menu screen
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

			// Difficulty screen
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
			else if(e.getSource().equals(mainMenuButton))
			{
				changePanel("MAIN");
			}

			// Weapon select screen
			else if (e.getSource().equals(planetToDifficulty))
			{
				changePanel("DIFFICULTY");
			}
			else if (e.getSource().equals(planetConfirm))
			{
				checkSelection();
				changePanel("GAME");
				gamePanel.startSimulation();
			}

			// Game screen
			else if(e.getSource().equals(pauseMenuButton))
			{
				gamePanel.stopSimulation();
				changePanel("MAIN");
			}

			// High score screen
			else if(e.getSource().equals(hsToMain))
			{
				changePanel("MAIN");
			}

			// Miscellaneous
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

}
