import java.awt.Component;
import java.awt.Dimension;
import java.awt.ScrollPane;

import javax.swing.*;

/**
 * 
 * @author IPAPI
 *
 */
public class EditorWindow extends Window 
{
	public static EnvironmentEditor editor = new EnvironmentEditor();
	
	/**
	 *  Constructor
	 *   Creates a Java3D frame, its added to the main system
	 *   in a fixed position.
	 */
	protected EditorWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		
		MainSystem.ex = new Java3DFrame();
		Component map = MainSystem.ex.initialize();
		MainSystem.ex.buildUniverse();
		
		left.add(map, "Map View");
		
		
		right.setBorder(BorderFactory.createTitledBorder("Editor Controls"));
		right.setPreferredSize(new Dimension(180,800));
		right.add(new EditToolbar());
		
		packPane();
	}
}
