import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InvalidObjectException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectStreamException;
import java.io.Serializable;
import java.util.ArrayList;

import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.impl.file.ReferenceSynset;
import edu.smu.tspell.wordnet.impl.file.RelationshipPointers;
import edu.smu.tspell.wordnet.impl.file.SenseKey;
import edu.smu.tspell.wordnet.impl.file.SynsetPointer;
import edu.smu.tspell.wordnet.impl.file.synset.NounReferenceSynset;

public class ExtendedNounSynset implements Serializable
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

		} finally
		{
			marked = false;
		}
	}
	public ExtendedNounSynset()
	{
		ns = null;
		marked = false;
		extendedns = null;
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

		// neither this nor that has null ns
		if (this.ns != null && that.ns != null)
			return this.ns.equals(that.ns);

		// only one of this or that has a null ns
		if (this.ns == null && that.ns != null || this.ns != null
				&& that.ns == null)
			return false;

		// both this and that have null ns
		return this.extendedns.equals(that.extendedns);
	}

	@Override
	public String toString()
	{
		if (ns != null)
			return "NounSynset: " + ns.toString();
		return "String: " + extendedns;
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

	private void writeObject(ObjectOutputStream o) throws IOException
	{
		if (this.ns == null)
		{
			o.writeObject(extendedns);
			return;
		}
		NounReferenceSynset boxNRS = (NounReferenceSynset) this.ns;

		int offset = boxNRS.getOffset();

		o.writeObject(offset);

	}

	private void readObject(ObjectInputStream in) throws IOException,
			ClassNotFoundException
	{

		Object o = in.readObject();
	
		try
		{
			// if the object is an integer, the original object was an ns
			Integer offset = (Integer) o;
			SynsetPointer pointer = new SynsetPointer(SynsetType.NOUN, offset);

			ns = ThingInfo.getNounSynset(pointer);
			extendedns = null;

			// if the object is a string, the original object was a string
		} catch (ClassCastException e)
		{
			extendedns = (String) o;
			ns = null;
		}

	}

	public static void main(String args[])
	{
		System.setProperty("wordnet.database.dir",
				"/home/luke/workspace/COS401-Final/WordNet-3.0/dict");
		ThingInfo.loadNounSynsetExtensions("extensions.txt");
		try
		{
			ExtendedNounSynset box = new ExtendedNounSynset("box");
			System.out.println(box);

			FileOutputStream fileOut = new FileOutputStream("stuff");
			ObjectOutputStream out = new ObjectOutputStream(fileOut);
			out.writeObject(box);
			out.close();
			fileOut.close();

			FileInputStream fileIn = new FileInputStream("stuff");
			ObjectInputStream in = new ObjectInputStream(fileIn);
			ExtendedNounSynset w = (ExtendedNounSynset) in.readObject();
			in.close();
			fileIn.close();

			System.out.println(w);
			// ReferenceSynset rf = (ReferenceSynset) box.sense.getNounSynset();
			// int lexicalFile = rf.getLexicalFileNumber();
			// int offset = rf.getOffset();

			// serialize

			// System.out.println(w);

		} catch (FileNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
