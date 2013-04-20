import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Queue;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class ThingInfo
{
	private static WordNetDatabase database = WordNetDatabase.getFileInstance();
	
	private static HashMap<String, ArrayList<ExtendedNounSynset>> newParents = new
			HashMap<String, ArrayList<ExtendedNounSynset>>();
	
	public static NounSynset getNounSynset(String name) throws DontKnowThatNoun
	{
	
		Synset[] nouns = database.getSynsets(name, SynsetType.NOUN);

		if (nouns.length == 0)
			throw new DontKnowThatNoun(name);
		
		//fix this later. There should be a way to choose which synset is best for this noun.
		return (NounSynset) nouns[0];
	}

	public static boolean isA(ExtendedNounSynset noun1, ExtendedNounSynset noun2)
	{
		//is sense one contained within sense2? 
		
		//eg if sense1=dog and sense2=animal, returns true
		
		//performing BFS
		
		ArrayList<ExtendedNounSynset> queue = new ArrayList<ExtendedNounSynset>();
		queue.add(noun1);
		while (!queue.isEmpty())
		{
			ExtendedNounSynset n = queue.remove(0);
			//System.out.println(n.getNounSynset());
			//System.out.println(n.getNounSynset().hashCode());
			if (n.equals(noun2))
				return true;
			queue.addAll(n.getParents());
		}
		return false;
	}
	public static void loadNounSynsetExtensions(String file)
	{
		BufferedReader br = null;
		try
		{
			String sCurrentLine;
			br = new BufferedReader(new FileReader(file));
			sCurrentLine = br.readLine();
			while (true)
			{
				//end of file
				if (sCurrentLine == null) 
					return;
				
				//ignore this line
				if (sCurrentLine.length() == 0|| sCurrentLine.charAt(0) == '#')
				{
					sCurrentLine = br.readLine();
					continue;
				}
				
				String[] words = sCurrentLine.split(" ");
				ExtendedNounSynset child = new ExtendedNounSynset(words[0]);
				ExtendedNounSynset parent = new ExtendedNounSynset(words[1]);
				
				if (!newParents.containsKey(child))	
				{
					ArrayList<ExtendedNounSynset> temp = new ArrayList<ExtendedNounSynset>();
					newParents.put(child.toString(), temp);
				}
				System.out.println("child" + child);
				System.out.println("parent" + parent);
				newParents.get(child.toString()).add(parent);
				
				sCurrentLine = br.readLine();
					
				
			}	
		} catch (IOException e)
		{
			e.printStackTrace();
	
		} finally
		{
			try
			{
				if (br != null)
					br.close();
			} catch (IOException ex)
			{
				ex.printStackTrace();
			}
		}
	}
	

	public static ArrayList<ExtendedNounSynset> getHypernyms(ExtendedNounSynset s)
	{
		if (newParents.containsKey(s.toString()))
			return newParents.get(s.toString());
		return new ArrayList<ExtendedNounSynset>();
	}
	
}
