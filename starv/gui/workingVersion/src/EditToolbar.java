import java.awt.BorderLayout;
import java.awt.*;

import javax.swing.*;

/**
 * Control Toolbar for the EnvironmentEditor Window
 * @author IPAPI
 *
 */
public class EditToolbar extends ControlToolbar
{
	/**
	 * Constructor
	 * creates a panel, buttons are added with their respective image
	 * and then place in a desire position
	 */
	public EditToolbar()
	{
		
		ImageIcon panupImage = createImageIcon("Icons/up16.gif");
		ImageIcon panDownImage = createImageIcon("Icons/down16.gif");
		ImageIcon panRightImage = createImageIcon("Icons/Forward16.gif");
		ImageIcon panLeftImage = createImageIcon("Icons/Back16.gif");
		
	
		JPanel panUp = new JPanel();
		panUp.setLayout(new BoxLayout(panUp, BoxLayout.X_AXIS));
		JPanel panDown = new JPanel();
		panDown.setLayout(new BoxLayout(panDown, BoxLayout.X_AXIS));
		
		
		JButton panLeft = new JButton("Pan Left", panLeftImage);
		panLeft.addActionListener(this);
		
		JButton panRight = new JButton("Pan Right", panRightImage);
		panRight.addActionListener(this);
		
		JButton panUper = new JButton("Pan Up", panupImage);
		panUper.addActionListener(this);
		
		JButton panDowner = new JButton("Pan Down", panDownImage);
		panDowner.addActionListener(this);
		
		// the buttons are added to the panell.
		panUp.add(panLeft);
		panUp.add(panRight);
		panDown.add(panUper);
		panDown.add(panDowner);
		
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
		add(panDown);
		add(panUp);
		
		
	}
}
