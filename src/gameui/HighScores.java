package gameui;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;


public class HighScores implements Serializable {
	
	//private ArrayList<Integer> score;
	//private ArrayList<String> name;
	private HashMap<Integer, String> map;
	//TreeMap<Integer, String> sortedScores;
	
	public HighScores() {
	//	score = new ArrayList<Integer>();
	//	name = new ArrayList<String>();
		map = new HashMap<Integer, String>();
		//sortedScores = new TreeMap<>(sortedScores);
	}
	
/*	public void setScore(int score) {
		this.score.add(score);
	}
	
	public void setName(String name) {
		this.name.add(name);
	}
	*/
	public void sortScores(int score, String name) {
		
		map.put(score, name);

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
		
		/*System.out.print("<html>");
		
		for(int i = 0; i < score.size(); i++) {
			if(i <= 10) {
			System.out.print((i + 1) + " Name: " + name.get(i) + " | Score: " + score.get(i) + "<br>");
			}
		}
		
		System.out.print("</html>");*/
		
		TreeMap<Integer, String> sortedScores = new TreeMap<>(map);
		
		if(sortedScores.size() != 0) {
		System.out.println(sortedScores);
		}
		
	}

}
