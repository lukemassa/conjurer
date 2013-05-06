// the Frame object
import java.util.*;
import java.io.*;

public class Frame {
	
	private String name;
	private LinkedList<Template> templates;
	
	public Frame(String s, HashMap<String,LinkedList<Template>> templates)
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
	
}
