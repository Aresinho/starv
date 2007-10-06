import java.awt.*;

public class RunMenu extends MenuHandler {

	protected RunMenu()
	{
		super("Run");
		
		MenuItem menu = new MenuItem("Aria");
		add(menu);
		menu.addActionListener(this);
		
		add(menu = new MenuItem("MatLab"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("MobileSim"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("TraceAnalysis"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Simulation"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Collect Trace from Robot"));
		menu.addActionListener(this);
	}
	
	
}