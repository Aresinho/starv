/** Super class for all windows in the STARV interface. It knows how to draw a window
 * with different panels, and a BarMenu.
 * @author Ares
 *
 */
import java.awt.*;
import javax.swing.*;



abstract class Window extends JPanel {

	static JFrame frame;
	JPanel left;
	JPanel right;
	JSplitPane pane;
	JScrollPane leftScroll;
	
	protected Window()
	{	super(new BorderLayout());
		left = createVerticalBoxPanel();
		right = createVerticalBoxPanel();
	}
	
	protected JPanel createVerticalBoxPanel() {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.PAGE_AXIS));
        p.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        return p;
    }
	
	
	protected void packPane()
	{
		pane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                left, right);
		
		pane.setOneTouchExpandable(true);
		
		add(pane, BorderLayout.CENTER);
		setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
	}
	
	
	public JPanel createPanelForComponent(JComponent comp,
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
