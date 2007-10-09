
import java.awt.*;
/**
 * 
 * @author IPAPI
 *
 */
public class EditorMenuBar extends MenuBar {

	/**
	 * Constructor
	 * creates and add a menu item to the menu bar in the enviroment editor
	 */
	protected EditorMenuBar()
	{
		add(new FileMenu("editor"));
		add(new TextEditMenu());
		add(new OptionsMenu());
		add(new HelpMenu());
	}
	
}
