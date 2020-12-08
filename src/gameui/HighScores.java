package gameui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;


public class HighScores implements Serializable {
	
	private ArrayList<Integer> score;
	private ArrayList<String> name;
	
	public HighScores() {
		score = new ArrayList<Integer>();
		name = new ArrayList<String>();
	}
	
	public void setScore(int score) {
		this.score.add(score);
	}
	
	public void setName(String name) {
		this.name.add(name);
	}
	
	public static void saveData(HighScores highScore) {
		
		FileOutputStream fileOut = null;
		ObjectOutputStream objOut= null;

		try 
		{
			fileOut = new FileOutputStream("highScores.ser");
			objOut = new ObjectOutputStream(fileOut);
			objOut.writeObject(highScore);
			objOut.close();
			fileOut.close();
	     }	
		
		catch(IOException i)
	    {
			i.printStackTrace();
	    }	
		
	}
	
	public static HighScores loadData() {
		
		FileInputStream fileIn = null;
		ObjectInputStream objIn = null;
		HighScores highScore = null;
			
		try
		{
			fileIn = new FileInputStream("highScores.ser");
			objIn = new ObjectInputStream(fileIn);
			highScore = (HighScores) objIn.readObject();
			objIn.close();
			fileIn.close();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}  
		
		return highScore;
	}
	
	public void printScores() {
		
		for(int i = 0; i < score.size(); i++) {
			System.out.println("Score: " + score.get(i) + " Name: " + name.get(i));
		}
		
	}

}
