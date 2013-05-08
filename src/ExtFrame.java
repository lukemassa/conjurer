// the Frame object
import java.util.*;
import java.io.*;

public class ExtFrame {
	
	private String name;
	private LinkedList<Template> templates;
	
	public ExtFrame(String s, HashMap<String,LinkedList<Template>> templates)
	{
		name = s;
		this.templates = templates.get(s);
		
	}
	
	public LinkedList<Template> getTemplates()
	{
		return templates;
	}
	
	public String toString()
	{
		return name;
	}

	public Template getTemplate(HashMap<String,LinkedList<Template>> map, String verb, String[] sentence)
	{
		Template toReturn = new Template();
		LinkedList<Template> templates = map.get(verb);
		
		for (Template current : templates)
		{
			toReturn = current;
			String[] core = current.toString().split(" ");
			// check if the template fits the Things in t
		}
		
		return toReturn;
	}
}
