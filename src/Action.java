import java.util.ArrayList;
import java.util.HashMap;
import java.util.PriorityQueue;


public class Action
{
	private String verb;
	private Thing[] things;
	private String[] thetaRoles;
	
	public Action(Thing theConjurer, String verb, Thing[] nouns) throws BadSentence
	{
		this.verb = verb;
		things = new Thing[nouns.length+1];
		
		things[0] = theConjurer;
		for (int i = 0; i < nouns.length; i++)
			things[i+1]=nouns[i];
		
		thetaRoles = ActionInfo.getActionThetaRole(verb);
		

		if (things.length != thetaRoles.length)
			throw new WrongNumberOfThings(verb, things.length);
		
		ArrayList<String> whatItIs = new ArrayList<String>();
		ArrayList<String> whatItShouldBe = new ArrayList<String>();
		for (int i = 0; i < things.length; i++)
		{
			
			if(!things[i].isA(thetaRoles[i]))
			{
				whatItIs.add(things[i].toString());
				whatItShouldBe.add(thetaRoles[i]);
			}
		}
		if (whatItIs.size() != 0)
			throw new VerbDoesntTakeTheseThings(whatItIs, whatItShouldBe);
		
	}
	public World perform(World w)
	{
		
		HashMap<String,ArrayList<Integer>> results = ActionInfo.getResults(verb);
		for (String relationName : results.keySet())
		{
			ArrayList<Thing> whichThings = new ArrayList<Thing>();
			for (Integer i : results.get(relationName))
				whichThings.add(things[i]);
			
			Thing[] aWhichThings = new Thing[whichThings.size()];
			
			Relation r = new Relation(relationName, whichThings.toArray(aWhichThings));
			
			//get destroy command
			if (r.getName().equals("destroy"))
			{
				w.destroyThing(r.getThing(0));

			}
			//remove given relation
			else if (r.getName().charAt(0) == '~')
			{
				w.deleteRelation(r);
			}
			//add relation
			else 
				w.addRelation(r);
		}
		
	
		return w;
	}


	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}



}
