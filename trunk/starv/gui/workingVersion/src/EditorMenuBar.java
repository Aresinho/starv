
import java.awt.*;

public class EditorMenuBar extends MenuBar {

	protected EditorMenuBar()
	{
		add(new FileMenu("editor"));
		add(new TextEditMenu());
		add(new OptionsMenu());
		add(new HelpMenu());
	}
	
}
