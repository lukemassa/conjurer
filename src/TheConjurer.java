import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;

import com.google.gson.Gson;


public class TheConjurer
{
	public static void main(String[] args) throws IOException
	{
		System.setProperty("wordnet.database.dir", "/home/luke/workspace/COS401-Final/WordNet-3.0/dict");
		ThingInfo.loadNounSynsetExtensions("extensions.txt");
		//ActionInfo.loadActions("actions.txt");
		ActionInfo.loadFrameNet();
		
		TheConjurer game = new TheConjurer();
		
		String file = args[0] + ".obj";
		
		//converting args into sentence
		String[] words =Arrays.copyOfRange(args, 1, args.length);
		StringBuilder sentence = new StringBuilder();
		for (String word : words)
			sentence.append(word + " ");
		if (sentence.length() > 0)
			sentence.deleteCharAt(sentence.length() - 1);
		
		World w = game.getWorld(file);
		w.apply(sentence.toString());
		System.out.println(w);
		game.save(file, w);
	}

	private void save(String file, World w)
	{
	      try
	      {
	    	  File f = new File(file);
	          FileOutputStream fileOut = new FileOutputStream(f);
	          ObjectOutputStream out = new ObjectOutputStream(fileOut);
	          out.writeObject(w);
	          out.close();
			  fileOut.close();
	    	  
	      }catch(IOException i)
	      {
	          i.printStackTrace();
	      }
	 
		
	}

	private World getWorld(String filename)
	{
		
	    try
	    {
	        
	    	
	    	//test to see if this person already played
	    	File f = new File(filename);
	    	if (!f.exists())
	    		return new World();
	    	else
	    	{
	    	
	    		FileInputStream fileIn = new FileInputStream(filename);
	    		ObjectInputStream in = new ObjectInputStream(fileIn);
	    		World w = (World) in.readObject();
	    		in.close();
	    		fileIn.close();
	    		return w;
			  //Close the output stream
	    	}  
	    	
	    } catch (Exception e)
	    {
			  System.err.println("Error: " + e.getMessage());
			  
	    }
		return null;
	}
}
