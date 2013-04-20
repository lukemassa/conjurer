public class WrongNumberOfThings extends BadSentence
{
	public WrongNumberOfThings(String s, int i)
	{
		super(s + " doesn't take " + i + plurSing(i));
	}
	private static String plurSing(int i)
	{
		if (i == 1)
			return " argument";
		return " arguments";
	}
}
