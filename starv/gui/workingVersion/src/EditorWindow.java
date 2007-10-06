import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;


public class EditorWindow extends Window 
{
	protected EditorWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		test.setText("The map view should go here, and it is working!");
		
		MainSystem.ex = new Java3DFrame();
		JComponent map = MainSystem.ex.initialize();
		MainSystem.ex.buildUniverse();
		
		
		leftScroll = new JScrollPane(map);
		leftScroll.setPreferredSize(new Dimension(800, 800));
		
	//	map.setPreferredSize(new Dimension(790, 790));
		
		left.add(createPanelForComponent(leftScroll, "Map View"));
		//left.add(map);
		right.setBorder(BorderFactory.createTitledBorder("Editor ToolBars"));
		right.add(new JLabel("Toolbars go here"));
		
		packPane();
	}
}
