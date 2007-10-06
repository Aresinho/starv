
import java.awt.*;

public class FileMenu extends MenuHandler {
	
	protected FileMenu(String type)
	{
		super("File");
		boolean normal = (type == null ? true : false);
		
		
		MenuItem menu = null;
		Menu subMenu = new Menu("New");
		subMenu.addActionListener(this);
		
		if(normal){
		
			subMenu.add(menu = new MenuItem("Map..."));
			menu.addActionListener(this);
		
		
			subMenu.add(menu = new MenuItem("Project"));
			menu.addActionListener(this);
		
			subMenu.add(menu = new MenuItem("Simulation"));
			menu.addActionListener(this);
			
			add(subMenu);
		}
		else 
		{
			if(type.equalsIgnoreCase("trace")) add(menu = new MenuItem("New Trace"));
			else { add(menu = new MenuItem("New")); }
			menu.addActionListener(this);
		}
		
		
		subMenu = new Menu("Open");
		subMenu.addActionListener(this);
		
		if(normal) 
		{
			subMenu.add(menu = new MenuItem("Project"));
			menu.addActionListener(this);
		
			subMenu.add(menu = new MenuItem("Trace"));
			menu.addActionListener(this);
		
			subMenu.add(menu = new MenuItem("Multi-trace"));
			menu.addActionListener(this);
			add(subMenu);
			
			add(subMenu);
		}
		else
		{
			if(type.equalsIgnoreCase("trace")) 
			{
				add(menu = new MenuItem("Open Trace"));
				menu.addActionListener(this);
				add(menu = new MenuItem("Open Second Trace"));
				menu.addActionListener(this);
			}
			else
			{
				add(menu = new MenuItem("Open"));
				menu.addActionListener(this);
			}
		}
		
		if(normal || type.equalsIgnoreCase("editor"))
		{
			add(menu = new MenuItem("Save"));
			menu.addActionListener(this);
		
			add(menu = new MenuItem("Save As"));
			menu.addActionListener(this);
		
			add(menu = new MenuItem("Save All"));
			menu.addActionListener(this);
		
			add(menu = new MenuItem("Close"));
			menu.addActionListener(this);
			
			addSeparator();
			
			add(menu = new MenuItem("Exit"));
			menu.addActionListener(this);
		}	
	}
	
	
	protected FileMenu()
	{
		this(null);
	}
	
}