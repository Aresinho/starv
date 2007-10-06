import java.awt.*;

import javax.swing.*;


public class MainSystem {
	
	static JFrame frame;
	static String context = "MAIN";
	public static Java3DFrame ex;
	
	public static void main(String[] args) {
	        //Schedule a job for the event-dispatching thread:
	        //creating and showing this application's GUI.
	                createAndShowGUI();
	    }
	
	
	protected static void createAndShowGUI()
	{
		frame = new JFrame("S.T.A.R.V. Main");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        JComponent newContentPane = new MainWindow();
        newContentPane.setOpaque(true); //content panes must be opaque
        frame.setContentPane(newContentPane);
      
        frame.setSize(1024, 800);
        frame.setMenuBar(new MainMenuBar());
        //Display the window.
       
        frame.pack();
        frame.setVisible(true);
		
	}	
}
