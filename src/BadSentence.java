public class BadSentence extends Exception
{
	public BadSentence()
	{
		super("Unknown error");
	}
	public BadSentence(String s)
	{
		super(s);
	}
}
