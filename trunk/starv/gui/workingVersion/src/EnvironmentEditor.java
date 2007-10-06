import java.awt.FileDialog;
import java.awt.Frame;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.LinkedList;
import java.util.Stack;


public class EnvironmentEditor {
	public Stack undo;
	public Stack undoObject;
	public Stack undoOther;
	public Environment environment=null;
	private boolean savedOnce = false;
	public Object3D activeObject=null;
	public LinkedList activeObjectList=null;
	public Object3D copiedObject=null;
	public LinkedList copiedObjectList=null;
	FileOutputStream fos;
	String filePath;
	
	private static final int OBJECT_CHANGE =0;
	private static final int PASTE =1;
	private static final int CUT=2;

	public EnvironmentEditor()
	{
		undo = new Stack();
		undoObject = new Stack();
		undoOther = new Stack();
	}
	public void createNew(double length, double width, String name)
	{
		environment = new Environment(length, width, name);
	}
	public void saveEnvironment()
	{
		if( savedOnce == false)
			saveAs();
		else
		{
			try {
				fos = new FileOutputStream( filePath );
				ObjectOutputStream outStream = new ObjectOutputStream( fos );

			      outStream.writeObject( environment );
			      outStream.flush();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
				
	}//end saveEnvironment
	
	public void saveAs()
	{
			//make sure to save it as the environment name
	      FileDialog fd = new FileDialog( new Frame(), 
	        "Save As...", FileDialog.SAVE );
	      fd.show();
	      filePath = new String( fd.getDirectory() + environment.getName() );

	      
		try {
			fos = new FileOutputStream( filePath );
			ObjectOutputStream outStream = new ObjectOutputStream( fos );

		      outStream.writeObject( environment );
		      outStream.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}
	      
	}//end saveAs

	public void openEnvironment()
	{
		FileDialog fd = new FileDialog( new Frame(), "Open...",FileDialog.LOAD );
		fd.show();
		filePath = new String( fd.getDirectory() + fd.getFile() );
		//  Create a stream for reading.
		try{
			FileInputStream fis = new FileInputStream( filePath );
			//  Next, create an object that can read from that file.
			ObjectInputStream inStream = new ObjectInputStream( fis );
			// Retrieve the Serializable object.
			environment = ( Environment )inStream.readObject();
		}
		catch(Exception e)
		{
			
		}
		// display the map
		//activate the ViewControlToolbar
	}//end openEnvironment
	public void closeMap()
	{
		environment =null;
	}
	public void undo()
	{
		int typeOfUndo = Integer.parseInt((String) undo.pop());
		switch (typeOfUndo){
			case OBJECT_CHANGE:
			{// undo changes for a single object ( sizing, location)
				Object3D lastChange = (Object3D)undoObject.pop();
				environment.replace(lastChange);
				break;
			}
			case PASTE:
			{//undo when a paste has occured
				LinkedList lastChange = (LinkedList)undoObject.pop();
				environment.setObjects(lastChange);
				break;
			}
			case CUT:
			{//undo when a cut has occured
				LinkedList lastChange = (LinkedList)undoObject.pop();
				environment.setObjects(lastChange);
				break;
			}
			
		
	}
	}
	
	
	public void setActiveObject(LinkedList listOfObjects)
	{// when the user selects 1 to many items, they are sent in a list, and then copied to a local list
		activeObjectList = listOfObjects;
	}
	public LinkedList getActivObject()
	{// will this work as an overloader?
		return activeObjectList;
	}
	public void paste(/* will need some sort of location point, mouse coordinates maybe. translated into trasnform*/)
	{
		if( copiedObjectList != null)
		{
			undoObject.push(environment.getObjects());
			undo.push(PASTE);
			for(int k=0; k<copiedObjectList.size(); k++)
				environment.addObject3D((Object3D)copiedObjectList.get(k));
			// might need to change the postion of the object, right now being pasted over its copy
		}
	}// end paste
	public void copy()
	{// saves active object to copy "buffer"
		if(activeObjectList != null)
		{
			copiedObjectList = activeObjectList;
		}
	}//end copy
	public void cut()
	{// saves active object to copy "buffer" then removes the object from the environment
		if(activeObjectList != null)
		{
			undoObject.push(environment.getObjects());
			undo.push(CUT);
			copiedObjectList = activeObjectList;
			for(int k=0; k<copiedObjectList.size();k++)
				environment.removeObject3D((Object3D)copiedObjectList.get(k));
			copiedObject = null;
		}
	}//end cut
	public void delete()
	{// remove selected item(s) from environment
		if(activeObjectList != null)
		{
			undoObject.push(environment.getObjects());
			undo.push(CUT);
			for(int k=0; k<copiedObjectList.size();k++)
				environment.removeObject3D((Object3D)copiedObjectList.get(k));
		}
	}//end delete
	public void selectAll()
	{
		activeObjectList = environment.getObjects();
	}
	public void setLength(double newLength)
	{
		if(environment != null)
			environment.setLength(newLength);
		
	}
	public void setWidth(double width)
	{
		if(environment != null)
			environment.setWidth(width);
	}
	public void setLongitude(String [] newLong)
	{
		if(environment != null)
			environment.setLongitude(newLong[0], newLong[1], newLong[2]);
	}
	public void setLattitude(String[] newLatt)
	{
		if(environment != null)
			environment.setLatitude(newLatt[0], newLatt[1], newLatt[2]);
	}
	public double getLength()
	{
		if(environment != null)
			return environment.getLength();
		else 
			return 0.0;
	}
	public double getWidth()
	{
		if(environment != null)
			return environment.getWidth();
		else 
			return 0.0;
	}
	public String[] getLongitude()
	{
		if(environment != null)
			return environment.getLongitude();
		else 
			return null;
	}
	public String[] getLatitude()
	{
		if(environment != null)
			return environment.getLatitude();
		else 
			return null;
	}
	public void resetDefaults()
	{
		// TO-DO
	}
	public void createNewObject3D(LinkedList listOfObjects)
	{// dont know how to do this yet.
		
		/*
		 * problem:  	the enivroment object holds a linked list of 3D objects.
		 * 				When a new 3D object is created it is a conntatenation of 2 or more objects.
		 * 				How does this get saved in the linked list of objects?  
		 * 
		 * possible solution:		The " user defined shape" might be constructed so that it draws more than one shape at a time.
		 * 							In that case. This class will create a new "user defined" object, by taking all the shapes that it
		 * 							needs to draw and adding it to the attributes of the newly created "user defined" shape.
		 * 								** this could get tricky if one of the items on the list is a user defined shape.
		 */
		
	}
}
