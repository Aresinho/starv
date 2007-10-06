
import java.awt.*;

public class MainMenuBar extends MenuBar {

	protected MainMenuBar()
	{
		add(new FileMenu());
		add(new EditMenu());
		add(new RunMenu());
		add(new HelpMenu());
	}
	
}
