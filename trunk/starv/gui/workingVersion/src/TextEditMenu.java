import java.awt.MenuItem;

public class TextEditMenu extends MenuHandler {

	protected TextEditMenu()
	{
		super("Edit");
		
		MenuItem menu;
		
		add(menu = new MenuItem("Undo"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Copy"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Paste"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Cut"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Delete"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Select All"));
		menu.addActionListener(this);
	}
	
	
}