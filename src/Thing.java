import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.smu.tspell.wordnet.NounSynset;

public class Thing
{
	private String name;
	private ExtendedNounSynset sense;
	

	public Thing(String name) throws DontKnowThatNoun
	{
		this.name = name;
		sense = new ExtendedNounSynset(name);
	}
	@Override
	public String toString()
	{
		return name;		
	}
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if((o == null) || (o.getClass() != this.getClass()))
	        return false;
		Thing that = (Thing) o;
		return this.name.equals(that.name);
		
	}
	public boolean isA(String s)
	{
		return ThingInfo.isA(sense, new ExtendedNounSynset(s));
	}

	public String getName()
	{
		return name;
	}

}
