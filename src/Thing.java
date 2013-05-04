import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.impl.file.ReferenceSynset;

public class Thing implements Serializable
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
		if ((o == null) || (o.getClass() != this.getClass()))
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
