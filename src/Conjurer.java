import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


import edu.smu.tspell.wordnet.AdjectiveSynset;
import edu.smu.tspell.wordnet.NounSynset;
import edu.smu.tspell.wordnet.Synset;
import edu.smu.tspell.wordnet.SynsetType;
import edu.smu.tspell.wordnet.WordNetDatabase;

public class Conjurer
{

	private void play(BufferedReader in) throws IOException
	{
		World w = new World();
		
		while (true)
		{
			System.out.print("> ");
			w.apply(in.readLine());
			System.out.println(w);
		}
		
		
	}
	public static void main(String[] args) throws IOException
	{
		System.out.println("Welcome to Conjurer!");
		
	//	ThingInfo.loadProperties(args[0]);
		//ThingInfco.loadThingProperties(args[1]);
		
	//	ActionInfo.loadActions(args[2]);
		
		
		System.setProperty("wordnet.database.dir", "/home/luke/workspace/COS401-Final/WordNet-3.0/dict");
		ThingInfo.loadNounSynsetExtensions("extensions.txt");
		ActionInfo.loadActions("actions.txt");
				
//		System.out.println("***theta roles");
//		for (String s : allActionThetaRoles.keySet())conu
//		{
//			System.out.print(s+"\n\t");
//			for (String[] s2 : allActionThetaRoles.get(s))
//			{
//			
//				for (String s3 : s2)
//				{
//					System.out.print(s3+ " ");
//				}
//			}
//			System.out.println();
//		}
//		
//		System.out.println("\n***pre relations\n");
//		
//		for (String s : allPreRelations.keySet())
//		{
//			System.out.println("**" + s);
//			for (String s2 : allPreRelations.get(s).keySet())
//			{
//				System.out.print(s2 + ": ");
//				for (Integer i : allPreRelations.get(s).get(s2))
//					System.out.print(i + " ");
//				System.out.println();
//			}
//			System.out.println();
//		}
//		
//		
//		System.out.println("\n***post relations\n");
//		for (String s : allPostRelations.keySet())
//		{
//			System.out.println("**" + s);
//			for (String s2 : allPostRelations.get(s).keySet())
//			{
//				System.out.print(s2 + ": ");
//				for (Integer i : allPostRelations.get(s).get(s2))
//					System.out.print(i + " ");
//				System.out.println();
//			}
//			System.out.println();
//		}

		Conjurer game = new Conjurer();
		InputStreamReader reader = new InputStreamReader(System.in); 
		game.play(new BufferedReader(reader));
		
	}






}
