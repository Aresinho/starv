import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;


public class EditorWindow extends Window 
{
	public static EnvironmentEditor editor = new EnvironmentEditor();
	protected EditorWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		
		MainSystem.ex = new Java3DFrame();
		JComponent map = MainSystem.ex.initialize();
		MainSystem.ex.buildUniverse();
		
		
		leftScroll = new JScrollPane(map);
		leftScroll.setPreferredSize(new Dimension(800, 800));
		
		left.add(createPanelForComponent(leftScroll, "Map View"));
		
		right.setBorder(BorderFactory.createTitledBorder("Editor ToolBars"));
		right.add(new JLabel("Toolbars go here"));
		
		packPane();
		editor = new EnvironmentEditor();// added making the environment editor.
	}
}
