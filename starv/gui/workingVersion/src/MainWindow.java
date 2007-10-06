import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;


public class MainWindow extends Window 
{
	protected MainWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		test.setText("The world view should go here");
		
		leftScroll = new JScrollPane(test);
		leftScroll.setPreferredSize(new Dimension(800, 800));
		
		left.add(createPanelForComponent(leftScroll, "World View"));
		right.setBorder(BorderFactory.createTitledBorder("ToolBars"));
		right.add(new JLabel("Toolbars go here"));
		
		packPane();
	}
}
