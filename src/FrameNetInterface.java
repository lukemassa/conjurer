import de.saar.coli.salsa.reiter.framenet.*;
import java.util.*; 

public class FrameNetInterface {

	// method to identify inherited frames
	public LinkedList<ExtFrame> getRootFrame(String verb)
	{
		FrameNet net = ActionInfo.fn;
		Collection<Frame> rootFrames = net.getRootFrames();
		
		for (Frame f : rootFrames)
		{
			System.out.println(f);
		}
		return null;
	}
	
	// get next-best frame
	public Frame getFrame(FrameNet net, String verb)
	{
		
		
		return null;
	}
	
	public static void main(String[] args)
	{
		ActionInfo.loadFrameNet();
		FrameNetInterface iface = new FrameNetInterface();
		LinkedList<ExtFrame> whatever = iface.getRootFrame("blahblahblah");
		
	}
} 
