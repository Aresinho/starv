import java.awt.MenuItem;
import java.awt.Menu;
import javax.swing.*;
import java.awt.*;
import java.awt.MenuItem.*;

public class OptionsMenu extends MenuHandler {

	protected OptionsMenu()
	{
		super("Options");
		
		MenuItem menu;
		Menu subMenu = new Menu("Set World Parameters");
		
		subMenu.add(menu = new MenuItem("Set Size"));
		menu.addActionListener(this);
		
		Menu submenu2 = new Menu("Set Units");
		
		ButtonGroup group = new ButtonGroup();
		CheckboxMenuItem radioMenu = new CheckboxMenuItem("Meters");
		radioMenu.addActionListener(this);
		radioMenu.setState(true);
		
		submenu2.add(radioMenu);
		
		radioMenu = new CheckboxMenuItem("Kilometers");
		radioMenu.addActionListener(this);
		
		submenu2.add(radioMenu);
		
		submenu2.addSeparator();
		

		radioMenu = new CheckboxMenuItem("Feet");
		radioMenu.addActionListener(this);

		
		submenu2.add(radioMenu);
		
		radioMenu = new CheckboxMenuItem("Miles");
		radioMenu.addActionListener(this);

		
		
		
		radioMenu = new CheckboxMenuItem("Imperial");
		radioMenu.addActionListener(this);
		submenu2.add(radioMenu);
		
		subMenu.add(submenu2);
		
		add(subMenu);
		
		add(menu = new MenuItem("Longitude/Lattitude"));
		menu.addActionListener(this);
		
		add(menu = new MenuItem("Create New 3D Object"));
		menu.addActionListener(this);
	}
	
	
}