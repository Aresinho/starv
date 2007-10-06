import java.awt.*;
import javax.swing.*;

import javax.swing.JComponent;

public class EventHandler {
	
	JComponent newContentPane;
	JFrame popUp = new JFrame("Set Size");
	
	public void findAndExecute(String event)
	{
		if(event.equalsIgnoreCase("exit"))
		{
			System.exit(0);
		}
		else if(event.equalsIgnoreCase("exit") && MainSystem.context.equalsIgnoreCase("editor"))
		{
			
		}
		
		if(event.equalsIgnoreCase("Map..."))
		{
			redraw(Context.EDITOR);
		}
		if(event.equalsIgnoreCase("trace"))
		{
			redraw(Context.TRACE);
		}
		if(event.equalsIgnoreCase("start trace"))
		{
			TraceWindow.t.start();
		}
		if(event.equalsIgnoreCase("set size"))
		{
			popUp.pack();
			popUp.setVisible(true);
		}
		System.out.print(event);
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
					MainSystem.frame.setMenuBar(new MainMenuBar());
				    break;
		case EDITOR:
					newContentPane = new EditorWindow();
					MainSystem.context = context.toString();
					MainSystem.frame.setMenuBar(new EditorMenuBar());
					break;
		case TRACE: 
					newContentPane = new TraceWindow();
					MainSystem.frame.setMenuBar(new TraceMenuBar());
					break;
		}
		
		MainSystem.context = context.toString();
		MainSystem.frame.setContentPane(newContentPane);
		MainSystem.frame.pack();
	}
	
	public enum Context {
	    MAIN, EDITOR, TRACE 
	}
}
