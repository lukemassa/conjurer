import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class ActionInfo
{
	private static HashMap<String,String[]> allActionThetaRoles = new
			HashMap<String,String[]>();
			
	private static HashMap<String, HashMap<String,ArrayList<Integer>>> allPostRelations = new
			HashMap<String, HashMap<String,ArrayList<Integer>>>();
	private static HashMap<String, HashMap<String,ArrayList<Integer>>> allPreRelations = new
			HashMap<String, HashMap<String,ArrayList<Integer>>>();
	
	
	public static void loadActions(String file)
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
				
				String[] firstline = sCurrentLine.split(" ");
				String verb = firstline[0];
				
				
				//theta roles are all the words after the verb without the verb, plus the person (the conjurer)
				String[] thetaRoles = firstline;
				thetaRoles[0] = "person";
				int nNouns = thetaRoles.length;
				
				ArrayList<String> preRelations = new ArrayList<String>();
				ArrayList<String> postRelations = new ArrayList<String>();
				
				while (true)
				{
					sCurrentLine = br.readLine();
					//end of file
					if (sCurrentLine == null) 
						break;
					
					//ignore this line
					if (sCurrentLine.length() == 0|| sCurrentLine.charAt(0) == '#')
						continue;
					
					if (sCurrentLine.charAt(0) != '\t')
					{
						break;
					}
					
					if (sCurrentLine.charAt(1) == 'B')
						preRelations.add(sCurrentLine);
					else if (sCurrentLine.charAt(1) == 'A')
						postRelations.add(sCurrentLine);
					else {
						System.out.println(sCurrentLine);
						System.err.println("First character in line no good");
						throw new IOException();
						
					}
						
				}
				oneVerb(verb, nNouns, thetaRoles, preRelations, postRelations);
				
				
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
	
	
	
	private static void oneVerb(String verb, int nNouns, String[] thetaRoles, ArrayList<String> sPreRelations, ArrayList<String> sPostRelations)
	{
		
		//Extract theta roles
		for (int i = 0; i < thetaRoles.length; i++)
			thetaRoles[i] = thetaRoles[i].replace("_", " ");
		allActionThetaRoles.put(verb,thetaRoles);
		
		//Extract pre-relations
		HashMap<String,ArrayList<Integer>> preRelationsForThisAction = new
				HashMap<String,ArrayList<Integer>>();
		
		for (String line : sPreRelations)
		{
			System.out.println(line);
			String[] words = line.substring(3).split(" ");
			String relationName = words[0];
			
			ArrayList<Integer> nounsAffected = new ArrayList<Integer>();
				
			for (int j = 1; j < words.length; j++)
				nounsAffected.add(Integer.parseInt(words[j]));
			preRelationsForThisAction.put(relationName, nounsAffected);
			
			allPreRelations.put(verb, preRelationsForThisAction);
		}
		
		
		//Extract post-relations				
		HashMap<String,ArrayList<Integer>> postRelationsForThisAction = new
				HashMap<String,ArrayList<Integer>>();
		
		for (String line : sPostRelations)
		{
			String[] words = line.substring(3).split(" ");
			String relationName = words[0];
			
			ArrayList<Integer> nounsAffected = new ArrayList<Integer>();
				
			for (int j = 1; j < words.length; j++)
				nounsAffected.add(Integer.parseInt(words[j]));
			postRelationsForThisAction.put(relationName, nounsAffected);
			
			allPostRelations.put(verb, postRelationsForThisAction);
		}
	}
	public static String[] getActionThetaRole(String verb) throws DontKnowThatVerb
	{
		if (allActionThetaRoles.get(verb)==null)
			throw new DontKnowThatVerb(verb);	
		return allActionThetaRoles.get(verb);
	}
	public static HashMap<String,ArrayList<Integer>> getResults(String verb)
	{
		return allPostRelations.get(verb);
	}
}
