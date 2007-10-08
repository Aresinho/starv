import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

/**
 * Abstract Class to draw control toolbars. 
 * @author Ares
 *
 */
abstract class ControlToolbar extends JPanel implements ActionListener {
	
	/**
	 * Event handler to delegate work. 
	 */
	EventHandler handler = new EventHandler();
	
	/**
	 * Delegate work of the event to the EventHandler
	 */
	public void actionPerformed(ActionEvent event)
	{
		handler.findAndExecute(event.getActionCommand());
	}
	
	 /** 
	  * Returns an ImageIcon, or null if the path was invalid. 
	  */
    protected static ImageIcon createImageIcon(String path) {
        java.net.URL imgURL = EditToolbar.class.getResource(path);
        if (imgURL != null) {
            return new ImageIcon(imgURL);
        } else {
            System.err.println("Couldn't find file: " + path);
            return null;
        }
    }
}
