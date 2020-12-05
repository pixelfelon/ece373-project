package gameui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import gameobjects.Game;
import gameobjects.GameDifficulty;


public class SolorDefenderGUI extends JFrame {
	
		private Image background = Toolkit.getDefaultToolkit().getImage("solar_system_background.jpg");
		//for setMinimum parameter
		private Dimension screenSize = new Dimension(720, 542); 
		
		private JPanel mainScreen = new JPanel();
		private JPanel difficultyScreen = new JPanel();
		private JPanel highScoreScreen = new JPanel();
		
		private JButton sButton = new JButton("Start Game");
		private JButton hsButton = new JButton("High Scores");
		private JButton eButton = new JButton("Exit");
		
		private JButton easyButton = new JButton("Easy");
		private JButton medButton = new JButton("Medium");
		private JButton hardButton = new JButton("Hard");
		private JButton returnButton = new JButton("Return");
		
		//Game Objects

		public SolorDefenderGUI(){
			
			super("Solor Defenders");
		    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		    BuildMainScreen();
		    BuildDiffScreen();
		    
		    //getContentPane().setLayout(new BorderLayout());
		    changePanel(mainScreen);
		    
		}
		
		public void paintComponent(Graphics g) {
			   super.paintComponents(g);
			   g.drawImage(background, 0, 0, null);
			   
			}

			
		private void BuildMainScreen() {
			
			
			//JLabel icon = new JLabel(new ImageIcon(".\\Graphics\\solar_system_background.jpg")); 
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\solar_system_background.jpg").getImage());
			Rectangle f = this.getBounds();
			//panel.setSize(super.getWidth(), super.getHeight());
			//panel.setBounds(400, 200, 800, 500);
			
			//BackgroundImage img = new BackgroundImage(mainScreen);
			System.out.println(super.getSize() + " " + f.getHeight());
			mainScreen.setLayout(null);
			
		    //Main Screen
		    sButton.setBounds(225, 150, 125, 25);
		    hsButton.setBounds(405, 150, 125, 25);
		    eButton.setBounds(315, 225, 125, 25);
		    
		    sButton.addActionListener(new ButtonListener());
		    hsButton.addActionListener(new ButtonListener());
		    eButton.addActionListener(new ButtonListener());
		    
		    mainScreen.add(sButton);
		    mainScreen.add(hsButton);
		    mainScreen.add(eButton);
		    mainScreen.add(panel);
		   // super.add(new JLabel(new ImageIcon(".\\Graphics\\solar_system_background.jpg")));
		    
		    this.pack();
		}
		
		private void BuildDiffScreen() {
			
			BackgroundImage panel = new BackgroundImage(new ImageIcon(".\\Graphics\\solar_system_background.jpg").getImage());
			difficultyScreen.setLayout(null);
			
		    //Main Screen
		    easyButton.setBounds(315, 125, 125, 30);
		    medButton.setBounds(315, 180, 125, 30);
		    hardButton.setBounds(315, 235, 125, 30);
		    returnButton.setBounds(315, 325, 125, 30);
		    
		    easyButton.addActionListener(new ButtonListener());
		    medButton.addActionListener(new ButtonListener());
		    hardButton.addActionListener(new ButtonListener());
		    returnButton.addActionListener(new ButtonListener());
		    
		    difficultyScreen.add(easyButton);
		    difficultyScreen.add(medButton);
		    difficultyScreen.add(hardButton);
		    difficultyScreen.add(returnButton);
		    difficultyScreen.add(panel);
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
		    	
		    	JButton source = (JButton)(e.getSource());
				
				//Save Button
				if(source.equals(sButton))
				{
					changePanel(difficultyScreen);
				}
				else if(source.equals(hsButton))
				{
					
				}
				else if(source.equals(eButton))
				{
					int result = JOptionPane.showConfirmDialog(null, "Are You Sure?", "Exit", JOptionPane.YES_NO_OPTION);
					if(result == JOptionPane.YES_OPTION) {
					dispose();
					}
				}
				
				//Difficulty Screen
				else if(source.equals(easyButton))
				{
					Game game = new Game(6, GameDifficulty.valueOf("EASY")); 
				}
				else if(source.equals(medButton))
				{
					Game game = new Game(4, GameDifficulty.valueOf("NORMAL")); 
				}
				else if(source.equals(hardButton))
				{
					Game game = new Game(3, GameDifficulty.valueOf("HARD")); 
				}
				else if(source.equals(returnButton))
				{
					changePanel(mainScreen);
				}

		    }
		}

}
