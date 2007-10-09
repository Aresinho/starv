

import java.awt.*;

/**
 * 
 * @author IPAPI
 *
 */
public class EditMenu extends MenuHandler {

	/**
	 * Constructor using MenuHandler's constructor
	 *  and adds three menu items: Map, Simulation Parameters, and Crop Trace.
	 */
	protected EditMenu()
	{
		super("Edit");
		
		MenuItem menu;
		
		add(menu = new MenuItem("Map"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Simulation Parameters"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Crop Trace"));
		menu.addActionListener(this);
	}
	
}