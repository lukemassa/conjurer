import java.util.ArrayList;

import edu.smu.tspell.wordnet.NounSynset;

public class ExtendedNounSynset
{
	private NounSynset ns;
	private boolean marked;
	private String extendedns;

	public ExtendedNounSynset(String s)
	{
		try
		{
			ns = ThingInfo.getNounSynset(s);
			extendedns = null;
		} catch (DontKnowThatNoun e)
		{
			ns = null;
			extendedns = s;
		}
		finally
		{
			marked = false;	
		}		
	}
	public ExtendedNounSynset(NounSynset ns)
	{
		this.ns = ns;
		extendedns = null;
		marked = false;
	}
	public ExtendedNounSynset(ExtendedNounSynset n)
	{
		this.ns = n.ns;
		this.extendedns = n.extendedns;
		this.marked = false;
	}

	public void mark()
	{
		marked = true;
	}
	public NounSynset getNounSynset()
	{
		return ns;
	}
	public boolean isMarked()
	{
		return marked;
	}

	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if ((o == null) || (o.getClass() != this.getClass()))
			return false;
		ExtendedNounSynset that = (ExtendedNounSynset) o;
		
		//neither this nor that has null ns
		if (this.ns != null && that.ns != null)
			return this.ns.equals(that.ns);
		
		//only one of this or that has a null ns
		if (this.ns == null && that.ns != null || this.ns != null && that.ns == null)
			return false;
		
		//both this and that have null ns
		return this.extendedns.equals(that.extendedns);
	}
	@Override
	public String toString()
	{
		if (ns != null)
			return ns.toString();
		return extendedns;
	}
	public ArrayList<ExtendedNounSynset> getParents()
	{
		ArrayList<ExtendedNounSynset> toreturn = new ArrayList<ExtendedNounSynset>();
		
		if (ns != null)
		{
			for (NounSynset a : ns.getHypernyms())
			{
				ExtendedNounSynset temp = new ExtendedNounSynset(a);
				toreturn.add(temp);
			}
		}
		
		for (ExtendedNounSynset s : ThingInfo.getHypernyms(this))
		{
			ExtendedNounSynset temp = new ExtendedNounSynset(s);
			toreturn.add(temp);
		}
		
		return toreturn;

	}
}
