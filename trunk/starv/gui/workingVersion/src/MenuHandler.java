
import java.awt.*;
import java.awt.event.*;

abstract class MenuHandler extends Menu implements ActionListener 
{
	public static EventHandler delegate = new EventHandler();
	
	public MenuHandler(String title)
	{
		super(title);
	}
	
	
	/**
	 * Handle Menu Events
	 */
	public void actionPerformed(ActionEvent item) 
	{ 
	    delegate.findAndExecute(item.getActionCommand()); 
	}
	
}
