import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class TheConjurer
{
	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome to Conjurer!");
		

		System.setProperty("wordnet.database.dir", "/home/luke/workspace/COS401-Final/WordNet-3.0/dict");
		ThingInfo.loadNounSynsetExtensions("extensions.txt");
		ActionInfo.loadActions("actions.txt");
	

		Conjurer game = new Conjurer();
		InputStreamReader reader = new InputStreamReader(System.in); 
		game.play(new BufferedReader(reader));
}
