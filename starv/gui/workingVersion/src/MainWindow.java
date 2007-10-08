import java.awt.Color;
import java.awt.Dimension;

import javax.swing.BorderFactory;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.Border;

/**
 * Draws the Main Window of STARV. This window contains a space for a map and the different
 * pallets related to it. 
 * @author Ares
 *
 */
public class MainWindow extends Window 
{
	protected MainWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		test.setText("The world view should go here");
		test.setBackground(Color.BLACK);
		
		leftScroll = new JScrollPane(test);
		leftScroll.setPreferredSize(new Dimension(800, 800));
		
		left.add(createPanelForComponent(leftScroll, "World View"));
		
		//Create a border for the Right (Pallet) component
		right.setBorder(BorderFactory.createTitledBorder("ToolBars"));
		
		//Add the necessary toolbars
		right.add(new SimulationToolbar());
		//Add empty space at the end to keep aesthetics
		Border emptySpace = BorderFactory.createEmptyBorder(0, 0, 500, 0);
		right.setBorder(emptySpace);
		
		//Pack the Pane;
		packPane();
	}
}
