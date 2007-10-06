import java.awt.MenuItem;
import java.awt.Menu;

public class OptionsMenu extends MenuHandler {

	protected OptionsMenu()
	{
		super("Options");
		
		MenuItem menu;
		Menu subMenu = new Menu("Set World Parameters");
		
		subMenu.add(menu = new MenuItem("Set Size"));
		menu.addActionListener(this);
		
		
		subMenu.add(menu = new MenuItem("Set Units"));
		menu.addActionListener(this);
		
		subMenu.add(menu = new MenuItem("Set"));
		menu.addActionListener(this);
		
		add(subMenu);
		
		add(menu = new MenuItem("Longitude/Langitude"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Create New 3D Object"));
		menu.addActionListener(this);
	}
	
	
}