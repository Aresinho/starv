import java.awt.*;

public class HelpMenu extends MenuHandler {

	public HelpMenu()
	{
		super("Help");
		
		MenuItem menuItem;
		
		add(menuItem = new MenuItem("Help"));
		menuItem.addActionListener(this);
		
		add(menuItem = new MenuItem("Search")); 
		menuItem.addActionListener(this); 
		
		addSeparator();
		
		add(menuItem = new MenuItem("About STARV")); 
		menuItem.addActionListener(this); 
	}
	
	
	
}