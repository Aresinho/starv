import java.awt.*;
import javax.swing.*;
/**
 * Super class for all windows in the STARV interface. It knows how to draw a window
 * with different panels, working area and BarMenu. This is an abstract class since
 * some of the components are missing and they have to be provided by the subclasses.
 * Also this class serves as a server for methods to draw and pack Windows (Panes)
 * @author Ares
 * @version 1.0;
 */
abstract class Window extends JPanel {

	/**
	 * Class Variable Frame. The application shows everything on one window therefore
	 * we only need just one frame. This JFrame is the one that has to be repainted every
	 * time that the MainSystem or EventHandler Requests it. 
	 */
	static JFrame frame;
	
	
	/**
	 * Left side of the Window. It is enclosed in a Scroll Pane. This is where the World
	 * View, the Simulation and main features go.
	 */
	
	static JPanel left;
	static JScrollPane  leftScroll;
	
	
	/**
	 * Right side of the Window. Toolbars and other user interface commands also go here
	 */
	
	JPanel right;
	
	
	/**
	 * Splits the Window into two and only two sections
	 */
	JSplitPane pane;
	
	/**
	 * Creates a new empty window with two panels
	 */
	public Window()
	{	super(new BorderLayout());
		left = createVerticalBoxPanel();
		right = createVerticalBoxPanel();
	}
	
	/**
	 * Creates a new Panel with a Vertical BoxLayout and centered
	 * @return the new Panel
	 */
	public JPanel createVerticalBoxPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return p;
    }
	
	/**
	 * Builds the window by adding both panels. Setting the Expandable icon
	 * and creating an empty border around the frame;
	 */
	public void packPane()
	{
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                left, right);
		
		pane.setOneTouchExpandable(true);
		
		add(pane, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}
	
	/**
	 * Creates a panel with a label for the components.
	 * @param comp the desired component to encapsulate
	 * @param title title of the panel
	 * @return a BorderLayou Panel
	 */
	public static JPanel createPanelForComponent(JComponent comp,
            String title) 
	{
			JPanel panel = new JPanel(new BorderLayout());
			panel.add(comp, BorderLayout.CENTER);
			if (title != null) {
				panel.setBorder(BorderFactory.createTitledBorder(title));
			}
			return panel;
	}

}
