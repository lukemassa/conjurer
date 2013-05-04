import java.io.Serializable;
import java.util.Arrays;

public class Relation implements Comparable, Serializable
{
	private String name;
	private Thing things[];
	
	public Relation(String name, Thing ... things)
	{
		this.name = name;
		this.things = things;
	}
	public Thing[] getThings()
	{
		return things;
	}
	public String toString()
	{
		StringBuilder sb = new StringBuilder();
		sb.append("(");
		for (Thing t : things)
			sb.append(t + ", ");
		sb.delete(sb.length()-2, sb.length());
		sb.append(")");
		return sb.toString();	
	}
	public static void main(String[] args)
	{
		// TODO Auto-generated method stub

	}
	@Override
	public boolean equals(Object o)
	{
		if (this == o)
			return true;
		if((o == null) || (o.getClass() != this.getClass()))
	        return false;
		Relation that = (Relation) o;
		return this.toString().equals(that.toString());
		
	}

	
	public boolean contains(Thing t)
	{
		for (Thing thing : things)
		{
			if (thing.equals(t))
				return true;
		}
		return false;
	}
	public boolean shouldDelete()
	{
		return this.name.equals("delete");
	}
	public Thing getThing(int i)
	{
		return things[i];
	}
	
	@Override
	public int compareTo(Object o)
	{
		if (this == o)
			return 0;
		Relation that = (Relation) o;
		return this.name.compareTo(that.name);

	}
	public String getName()
	{
		return name;
	}

}
