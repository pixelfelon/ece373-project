package gameui;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;


public class HighScores implements Serializable {
	
	private static final long serialVersionUID = 4482698393932138605L;
	private HashMap<Integer, String> map;
	
	public HighScores() {
		map = new HashMap<Integer, String>();
	}
	
	
	public void sortScores(int score, String name) {
		
		map.put(score, name);

	}
	
	public boolean isTopTen(int score) {
		boolean isTopTen = false;
		int i = 1;
		
		TreeMap<Integer, String> sortedScores = new TreeMap<>(Collections.reverseOrder());
		sortedScores.putAll(map);
		
		Set<Integer> currentScores = sortedScores.keySet();
        
        for(Integer key : currentScores){
        	
        	if(score > key && i <= 10) {
        	isTopTen = true;
        	}
        	
        	i++;
        }
		
		
		return isTopTen;
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
		catch (ClassNotFoundException e) 
		{
			e.printStackTrace();
		}
		catch (FileNotFoundException e)
		{
			return new HighScores();
		}
		catch(IOException i)
		{
			i.printStackTrace();
		} 
		
		return highScore;
	}
	
	public void printScores() {
			
		TreeMap<Integer, String> sortedScores = new TreeMap<>(Collections.reverseOrder());
		
		sortedScores.putAll(map);
		
		Set<Map.Entry<Integer, String>> entries = sortedScores.entrySet();
		
		int i = 1;
		 System.out.print("<html>");
		 
		//using for loop
		for(Map.Entry<Integer, String> entry : entries){
			
			if(i <= 10) {
		    System.out.println(i + " Name: " + entry.getValue() + " | Score: " + entry.getKey() + "<br>");
			}
		    i++;
		}
		
		while(i <= 10) {
			System.out.println(i + " Name: Unknown | Score: ### <br>");
			i++;
		}
		
		System.out.print("</html>");
		
		
	}

}
