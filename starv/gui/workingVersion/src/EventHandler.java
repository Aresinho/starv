import java.awt.*;
import java.util.LinkedList;

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
		
		if(event.equals("Map..."))
		{
			redraw(Context.EDITOR);
			System.out.println( " woop ?");
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
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("New"))
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
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Cut"))
		{
			EditorWindow.editor.cut();
		}
		if( (Context.EDITOR.toString()).equals(MainSystem.context) && event.equalsIgnoreCase("Delete"))
		{
			EditorWindow.editor.delete();
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
