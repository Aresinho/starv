import java.awt.Dimension;
import java.awt.event.*;


import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import javax.swing.Timer;


/**
 * 
 * @author IPAPI
 *
 */
public class TraceWindow extends Window implements ActionListener
{	EventHandler delegate = new EventHandler();
	static JLabel distance = new JLabel("Distance Traveled: ");
	static JLabel speed = new JLabel("Average Speed: ");
	static JLabel time = new JLabel("Elapsed Time: ");
	static JLabel goalR1 = new JLabel("Distance to Goal R1: ");
	static JLabel goalR2 = new JLabel("Distance to Goal R2: ");
	static JLabel between = new JLabel("Distance between R1 and R2: ");
	static JLabel speedR1 = new JLabel("Speed R1: ");
	static JLabel speedR2 = new JLabel("Speed R2: ");
	static Timer t;
	
	long seconds, minutes, hours;
	

	protected TraceWindow()
	{
		super();
		JTextArea test = new JTextArea(5, 30);
		test.setText("The trace goes here");
		
		leftScroll = new JScrollPane(test);
		leftScroll.setPreferredSize(new Dimension(800, 800));
		
		
		left.add(createPanelForComponent(leftScroll, "Trace"));
		
		// Start construction of right Panel
		JPanel checkBoxes = createVerticalBoxPanel();

		JCheckBox toggleTrace = new JCheckBox("Distance to Goal R1");
        toggleTrace.setActionCommand("Distance to R1");
        toggleTrace.addActionListener(this);
		
        checkBoxes.add(toggleTrace);
        
        toggleTrace = new JCheckBox("Distace to Goal R2");
        toggleTrace.setActionCommand("Distance to R2");
        toggleTrace.addActionListener(this);
        
        
        checkBoxes.add(toggleTrace);
        
        toggleTrace = new JCheckBox("Distace between R1 and R2");
        toggleTrace.setActionCommand("Distance between");
        toggleTrace.addActionListener(this);
        
        
        checkBoxes.add(toggleTrace);
        
        toggleTrace = new JCheckBox("Speed R1");
        toggleTrace.addActionListener(this);
        
        checkBoxes.add(toggleTrace);
        
        toggleTrace = new JCheckBox("Speed R2");
        toggleTrace.addActionListener(this);
        
        
        checkBoxes.add(toggleTrace);
        
        
        toggleTrace = new JCheckBox("Start");
        toggleTrace.setActionCommand("start trace");
        toggleTrace.addActionListener(this);
        
        
        checkBoxes.add(toggleTrace);
        
        right.add(createPanelForComponent(checkBoxes, "View"));
        
        checkBoxes = createVerticalBoxPanel();
        
        checkBoxes.add(distance);
        checkBoxes.add(speed);
        checkBoxes.add(time);
		
       // right.setBorder(BorderFactory.createTitledBorder("View Options"));
        right.add(createPanelForComponent(checkBoxes, "Statistics"));
        
		packPane();
		
		t = new javax.swing.Timer(1000, new ActionListener() {
	          public void actionPerformed(ActionEvent e) {
	             updateTime();
	          }
	       });
	}
	
	/**
	 * Handle CheckBox Events
	 */
	public void actionPerformed(ActionEvent item) 
	{ 
	    delegate.findAndExecute(item.getActionCommand()); 
	}
	/**
	 * 
	 */
	public void updateTime()
	{
		String min, sec, hour;
		if(++seconds == 60)
		{	
			seconds = 0;
			if(++minutes == 60)
			{
				minutes = 00;
				hours++;
			}
		}
		
		//Pretty Print
		sec = "" + seconds;
		min = "" + minutes;
		hour = "" + hours;
		
		if(seconds < 10) sec = "0" + seconds;	
		if(minutes < 10) min = "0" + minutes;
		if(hours < 10 ) hour = "0" + hours;
		
		
		time.setText("Time Elapsed: " + hour + ":" + min + ":" + sec);
	}
}
