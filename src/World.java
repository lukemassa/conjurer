import java.util.ArrayList;
import java.util.PriorityQueue;

public class World
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
		StringBuilder sb = new StringBuilder();
		sb.append("\nRelations in the world:");
		String lastName = "";
		for (Relation r : allRelations)
		{
			if (r.getName() != lastName)
			{
				sb.append("\n" + r.getName() + ": ");
				lastName = r.getName();
			}
			sb.append(r + " ");
		}
		sb.append("\n");
		return sb.toString();
	}
	public static ArrayList<Relation> relationsOfAThing(Thing t)
	{
		ArrayList<Relation> toreturn = new ArrayList<Relation>();
		for (Relation r : allRelations)
		{
			if (r.contains(t))
				toreturn.add(r);
		}
			
		return toreturn;
	}
	public void apply(String command)
	{
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
			{
				System.exit(1);
			}

			a = new Action(theConjurer, verb, findThings(nouns));
			
			a.perform(allThings, allRelations);
		
			
		}
		catch (BadSentence e)
		{
			System.out.println("Sorry, " + e.getMessage() + ".");
		}
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



}
