

import java.awt.*;

public class EditMenu extends MenuHandler {

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