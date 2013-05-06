// the Template object
import java.util.*;
import java.io.*;

public class Template {
	private String name;
	private String[] corePhrase;
	private LinkedList<Integer> optionSlots;
	private LinkedList<String> options;
	
	public Template()
	{
		name = "";
		corePhrase = null;
		optionSlots = new LinkedList<Integer>();
		options = new LinkedList<String>();
	}
	
	// hash helper function for setTemplates method
	private void hash(HashMap<String,LinkedList<Template>> map, String name, Template t)
	{
		LinkedList<Template> list = new LinkedList<Template>();

		if (!map.containsKey(name))
			list.add(t);			
		
		else
		{
			list = map.get(name);
			list.add(t);
			map.remove(name);
		}
		
		map.put(name,list);

	}
	
	public String toString()
	{
		String toReturn = name + ": ";
		for (int i = 0; i < corePhrase.length; i++)
			toReturn += corePhrase[i] + " ";
		
		return toReturn;
	}
	
	public LinkedList<Integer> getOptionSlots()
	{
		return optionSlots;
	}
	
	public LinkedList<String> getOptions()
	{
		return options;
	}
	
	public HashMap<String,LinkedList<Template>> setTemplates(String filename)
	{
		HashMap<String,LinkedList<Template>> map = new HashMap<String,LinkedList<Template>>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(filename));
			String nextLine = "";
			// iterate through all listed verbs
			while ((nextLine = br.readLine()) != null)
			{
				String name = nextLine;
				
				// iterate through all templates for each verb
				while (nextLine.compareTo("END") != 0)
				{
					Template t = new Template();
					
					// define the templates name and core phrase
					t.name = name;
					t.corePhrase = br.readLine().split(" ");
					
					// define the templates options
					String[] options = br.readLine().split(" ");
					for (int i = 0; i < t.options.size(); i++)
						t.options.add(options[i]);
	
					hash(map, name, t);
					
					nextLine = br.readLine();
				}
			}
		} catch (Exception e){};
		
		return map;
	}
}