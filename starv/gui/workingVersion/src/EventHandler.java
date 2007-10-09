import java.awt.*;
import java.util.LinkedList;
import java.awt.event.*;

import javax.swing.*;

import javax.swing.JComponent;
import javax.vecmath.Vector3d;

public class EventHandler implements ActionListener {
	
	
	
	/**
	 * Internal Frame for pop up and get feedback/input from user
	 * @author Ares
	 */
	JFrame popUp = new JFrame("Set Size");
	JComponent newContentPane;
	JTextField reader = new JTextField(30);
	JTextField reader2 = new JTextField(30);
	
	public void findAndExecute(String event)
	{
		if(event.equalsIgnoreCase("exit"))
		{
			System.exit(0);
		}
		else if(event.equalsIgnoreCase("exit") && MainSystem.context.equalsIgnoreCase("editor"))
		{
			
		}
		
		if("Map...".equals(event))
		{
			redraw(Context.EDITOR);
		}
		if("trace".equalsIgnoreCase(event))
		{
			redraw(Context.TRACE);
		}
		if("start trace".equalsIgnoreCase(event))
		{
			TraceWindow.t.start();
		}
		if("set size".equalsIgnoreCase(event))
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			
			JPanel messagex = new JPanel();
			messagex.setLayout(new BoxLayout(messagex,BoxLayout.X_AXIS));
			panel.setBorder(BorderFactory.createEmptyBorder(30,15,30,15));
		
			messagex.add(new JLabel("Enter desired X-Size: "));
			reader.setPreferredSize(new Dimension(50,25));
			messagex.add(reader);
			
			JPanel messageY = new JPanel();
			messageY.setLayout(new BoxLayout(messageY,BoxLayout.X_AXIS));
			messageY.add(new JLabel("Enter desired Y-Size: "));
			
			messageY.add(reader2);
			
			panel.setPreferredSize(new Dimension(260, 120));
			
			JButton ok = new JButton("Ok");
			ok.setActionCommand("setSize");
			JButton cancel = new JButton("Cancel");
			
			ok.addActionListener(this);
			cancel.setActionCommand("cancelSize");
			cancel.addActionListener(this);
			
			
			JPanel decision = new JPanel();
			decision.setLayout(new BoxLayout(decision, BoxLayout.X_AXIS));
			decision.add(cancel);
			decision.add(ok);
			
			panel.add(messagex);
			panel.add(messageY);
			panel.add(decision);
			
			Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit ();
			Dimension screensize = toolkit.getScreenSize ();
			
			popUp = new JFrame("Set Size");
			popUp.setContentPane(panel);
			popUp.setLocation(screensize.width / 2 - 130, screensize.height / 2 - 60);
			popUp.pack();
			popUp.setVisible(true);
		}
		if("Longitude/Lattitude".equalsIgnoreCase(event))
		{
			JPanel panel = new JPanel();
			panel.setLayout(new BoxLayout(panel,BoxLayout.PAGE_AXIS));
			
			JPanel messagex = new JPanel();
			messagex.setLayout(new BoxLayout(messagex,BoxLayout.X_AXIS));
			panel.setBorder(BorderFactory.createEmptyBorder(30,15,30,15));
		
			messagex.add(new JLabel("Enter desired Longitude (01-23-34): "));
			reader.setPreferredSize(new Dimension(50,25));
			messagex.add(reader);
				
			JPanel messageY = new JPanel();
			messageY.setLayout(new BoxLayout(messageY,BoxLayout.X_AXIS));
			messageY.add(new JLabel("Enter desired Lattitude (01-23-45):  "));
			
			messageY.add(reader2);
			
			panel.setPreferredSize(new Dimension(350, 120));
			
			JButton ok = new JButton("Ok");
			ok.setActionCommand("setSize");
			JButton cancel = new JButton("Cancel");
			
			ok.addActionListener(this);
			cancel.setActionCommand("cancelSize");
			cancel.addActionListener(this);
			
			
			JPanel decision = new JPanel();
			decision.setLayout(new BoxLayout(decision, BoxLayout.X_AXIS));
			decision.add(cancel);
			decision.add(ok);
			
			panel.add(messagex);
			panel.add(messageY);
			panel.add(decision);
			
			Toolkit toolkit = java.awt.Toolkit.getDefaultToolkit ();
			Dimension screensize = toolkit.getScreenSize ();
			
			popUp = new JFrame("Set Size");
			popUp.setContentPane(panel);
			popUp.setLocation(screensize.width / 2 - 130, screensize.height / 2 - 60);
			popUp.pack();
			popUp.setVisible(true);
		}
		
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && "New".equalsIgnoreCase(event))
		{// create a new environment
			EditorWindow.editor.createNew();
			System.out.println("done creating a new environment");
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Open"))
		{
			EditorWindow.editor.openEnvironment();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Save"))
		{
			EditorWindow.editor.saveEnvironment();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Save As"))
		{
			EditorWindow.editor.saveAs();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Save All"))
		{
			//not sure whats to be done here
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Close"))
		{
			redraw(Context.MAIN);
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Exit"))
			System.exit(0);
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Undo"))
		{
			EditorWindow.editor.undo();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Copy"))
		{
			EditorWindow.editor.copy();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Paste"))
		{
			EditorWindow.editor.paste();
			MainSystem.ex.reDraw();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Cut"))
		{
			EditorWindow.editor.cut();
			MainSystem.ex.reDraw();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Delete"))
		{
			EditorWindow.editor.delete();
			MainSystem.ex.reDraw();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Select All"))
		{
			EditorWindow.editor.selectAll();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Set World Parameters"))
		{
			
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Longitude/Lattitude"))
		{
			
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Create new 3D Object"))
		{
			EditorWindow.editor.createNewObject3D( new LinkedList()/* Must recieve a linked list of all currently selected objects */);
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Help"))
		{
			//MainSystem.ex.topDown();
			Sphere3D mySphere = new Sphere3D();
			mySphere.setVector(new Vector3d(2.0,1.0,1.0));
			EditorWindow.editor.environment.addObject3D(mySphere);
			MainSystem.ex.reDraw();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("About STARV"))
		{
		
			Sphere3D mySphere = new Sphere3D();
			mySphere.setVector(new Vector3d(-2.0,1.0,1.0));
			EditorWindow.editor.environment.addObject3D(mySphere);
			MainSystem.ex.reDraw();

				
		}
	}
	
	/**
	 * 
	 * @param context The context to draw 0 = Main 1= Editor 2 = Trace;
	 */
	private void redraw(Context context)
	{
		MainSystem.frame.invalidate();
		
		switch(context)
		{
		case MAIN: 
					newContentPane = new MainWindow();
					MainSystem.frame.setTitle("S.T.A.R.V. Main");
					MainSystem.frame.setMenuBar(new MainMenuBar());
				    break;
		case EDITOR:
					newContentPane = new EditorWindow();
					MainSystem.context = context.toString();
					MainSystem.frame.setTitle("S.T.A.R.V. Map Editor");
					MainSystem.frame.setMenuBar(new EditorMenuBar());
					break;
		case TRACE: 
					newContentPane = new TraceWindow();
					MainSystem.frame.setTitle("S.T.A.R.V. Trace");
					MainSystem.frame.setMenuBar(new TraceMenuBar());
					break;
		}
		
		MainSystem.context = context.toString();
		MainSystem.frame.setContentPane(newContentPane);
		MainSystem.frame.pack();
	}
	
	
	public void actionPerformed(ActionEvent item) 
	{ 
	    if("setsize".equalsIgnoreCase(item.getActionCommand()))
	    {
	    	EditorWindow.editor.environment.setLength(Integer.parseInt(reader.getText()));
	    	EditorWindow.editor.environment.setWidth(Integer.parseInt((reader2.getText())));
	    	EditorWindow.editor.environment.getObjects().remove(0);
	    	MainSystem.ex = new Java3DFrame();
	    	MainSystem.ex.initialize();
	    	MainSystem.ex.buildUniverse();
	    	EditorWindow.left.add(((Window) newContentPane).createPanelForComponent(EditorWindow.leftScroll, "Map View"));
	    	popUp.dispose();
	    }
	    if("cancelSize".equalsIgnoreCase(item.getActionCommand()))
	    {
	    	popUp.dispose();
	    }
	}
	
	/**
	 * Enumerable class to keep track of the different context of the program such as
	 * MAIN, EDITO, TRACE - if more contexts are added to application then they should be 
	 * added in here. 
	 * @author Ares
	 * 
	 */
	public enum Context {
	    MAIN, EDITOR, TRACE 
	}
}
