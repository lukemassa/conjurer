import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;

public class World implements Serializable
{
	private ArrayList<Thing> allThings;
	private PriorityQueue<Relation> allRelations;
	private Thing theConjurer;
	
	public World()
	{
		allThings = new ArrayList<Thing>();
		allRelations = new PriorityQueue<Relation>();
		try
		{
			theConjurer = new Thing("theConjurer");
			allThings.add(theConjurer);
			Relation r = new Relation("Exists", theConjurer);
			allRelations.add(r);
		} catch (DontKnowThatNoun e)
		{
			System.err.println("Fatal Error: The Conjurer not defined in things.txt");
			System.exit(0);
		}
	}
	
	public String toString()
	{
		HashMap<String,ArrayList<Thing[]>> relationTree = new HashMap<String,ArrayList<Thing[]>>();
		
		for (Relation r : allRelations)
		{
			ArrayList<Thing[]> things= relationTree.get(r.getName());
			if (things == null)
			{
				things = new ArrayList<Thing[]>();
			}
			things.add(r.getThings());
			relationTree.put(r.getName(), things);
		}
		
		StringBuilder sb = new StringBuilder();
		sb.append("\nRelations in the world:\n");

		for (String name : relationTree.keySet())
		{
			sb.append("  " + name +": ");
			for (Thing[] things : relationTree.get(name))
			{
				sb.append("(");
				for (Thing t : things)
					sb.append(t + ", ");
				
				sb.delete(sb.length()-2, sb.length());
				sb.append(") ");
			}
			sb.append("\n");
		}
		
		return sb.toString();
	}

	public void apply(String command)
	{

		if (command.equals(""))
		{
			return;
		}
		String verb = parseVerb(command);
		String[] nouns = parseNouns(command);
		
		Action a;
		try 
		{
			//Special case. Makes new thing
			if (verb.equals("conjure"))
			{
				if (nouns.length != 1)
					throw new WrongNumberOfThings(verb, nouns.length);
				Thing t = new Thing(nouns[0]);
				allThings.add(t);
				Relation r = new Relation("Exists", t);
				allRelations.add(r);
				return;
			}
			if (verb.equals("exit"))
				System.exit(1);
			

			a = new Action(theConjurer, verb, findThings(nouns));
			
			alter(a.perform(this));

		}
		catch (BadSentence e)
		{
			System.out.println("Sorry, " + e.getMessage() + ".");
		}
	}
	



	private void alter(World newWorld)
	{
		this.allThings = newWorld.allThings;
		this.allRelations = newWorld.allRelations;
		
	}

	private void remove(Relation r)
	{
		if (allRelations.contains(r))
		{
			allRelations.remove(r);
			return;
		}
		
	}

	private Thing[] findThings(String[] nouns) throws ThingsDontExist
	{
		Thing[] toReturn = new Thing[nouns.length];
		for (int i = 0; i < nouns.length; i++)
		{
			boolean found = false;
			for (int j = 0; j < allThings.size(); j++)
			{
				if (allThings.get(j).getName().equals(nouns[i]))
				{
					toReturn[i] = allThings.get(j);
					found = true;
					break;
				}
			}
			if (!found)
				throw new ThingsDontExist();
		}
		return toReturn;
	}

	private String[] parseNouns(String command)
	{
		// FIX. Right now it assumes that all NPs one word, and that everything after
		// verb is NP
		if (!command.contains(" "))
			return new String[0];
		return command.substring(command.indexOf(" ")+1).split(" ");

	}

	private String parseVerb(String command)
	{
		return command.split(" ")[0];
	}

	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}

	public void destroyThing(Thing thing)
	{
		ArrayList<Relation> deleteThese = relationsOfAThing(thing);
		for (int i = 0; i < deleteThese.size(); i++	)
			remove(deleteThese.get(i));
		remove(thing);
		
		

		
	}
	private void remove(Thing thing)
	{
		for (int i = 0; i < allThings.size(); i++)
		{
			if (thing.equals(allThings.get(i)))
			{
				allThings.remove(i);
				return;
			}
		}
		
	}

	private ArrayList<Relation> relationsOfAThing(Thing t)
	{
		ArrayList<Relation> toreturn = new ArrayList<Relation>();
		for (Relation r : allRelations)
		{
			if (r.contains(t))
				toreturn.add(r);
		}
		return toreturn;
	}

	public void deleteRelation(Relation r)
	{
		remove(r);
		
	}

	public void addRelation(Relation r)
	{
		allRelations.add(r);
		
	}

}
