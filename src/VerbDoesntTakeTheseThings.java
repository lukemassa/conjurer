import java.util.ArrayList;

public class VerbDoesntTakeTheseThings extends BadSentence
{
	public VerbDoesntTakeTheseThings(ArrayList<String> whatItIs, ArrayList<String> whatItShouldBe)
	{
		super(constructMessage(whatItIs,whatItShouldBe));
	}
	private static String constructMessage(ArrayList<String> whatItIs, ArrayList<String> whatItShouldBe)
	{
		StringBuilder sb = new StringBuilder();
		for (int i = 0; i < whatItIs.size(); i++)
			sb.append(whatItIs.get(i) + " is not a " + whatItShouldBe.get(i) + ", ");
		sb.delete(sb.length()-2, sb.length());
		return sb.toString();
	}
}
