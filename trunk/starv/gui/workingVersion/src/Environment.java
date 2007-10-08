

import java.io.Serializable;
import
java.util.*;

public class Environment implements Serializable {

	private LinkedList objects;
	private double length =11;
	private double width =1;
	private String [] longitude = new String[3];
	private String [] latitude = new String[3];
	private String name;
	private int units;
	
	private static final int UNIT_METERS = 0;
	private static final int UNIT_KILOMETERS = 1;
	private static final int UNIT_FEET = 2;
	private static final int UNIT_MILES = 3;
	
	public Environment()
	{
		objects = new LinkedList();
		length = 100;
		width = 100;
		name = "";
		units = UNIT_METERS;
	}
	
	public Environment(double l, double w, String n)
	{
		length = l;
		width = w;
		name = n;
		objects = new LinkedList();
		units = UNIT_METERS;
	}
	
	public void setLength(double l)
	{
		length = l;
	}
	
	public void setWidth(double w)
	{
		width = w;
	}
	
	public void setLongitude(String deg, String min, String dir)
	{
		longitude[0] = deg;
		longitude[1] = min;
		longitude[2] = dir;
	}
	
	public void setLatitude(String deg, String min, String dir)
	{
		latitude[0] = deg;
		latitude[1] = min;
		latitude[2] = dir;
	}
	
	public void setName(String n)
	{
		name = n;
	}
	
	public double getLength()
	{
		return length;
	}
	
	public double getWidth()
	{
		return width;
	}
	
	public String[] getLongitude()
	{
		return longitude;
	}
	
	public String[] getLatitude()
	{
		return latitude;
	}
	
	public String getName()
	{
		return name;
	}
	public void replace(Object3D replacer)
	{
		for(int k =0; k < objects.size(); k++)
		{
			Object3D replacee = (Object3D) objects.get(k);
		
			if( replacee.getIdentifier().equals(replacer.getIdentifier()) )
			{//replace the old with the new.
				objects.remove(k);
				objects.add(replacer);
				break;
			}
				
		}
	}// end replace
	public void setUnits(int newUnits)
	{
		switch(newUnits){
		case UNIT_METERS:
			units = UNIT_METERS;
			break;
		case UNIT_KILOMETERS:
			units = UNIT_KILOMETERS;
			break;
		case UNIT_FEET:
			units = UNIT_FEET;
			break;
		case UNIT_MILES:
			units = UNIT_MILES;
			break;
			default:
				units = UNIT_METERS;
			break;
		}
	}
	public int getUnits()
	{
		return units;
	}
	public void addObject3D(Object3D newObject3D)
	{
		objects.add(newObject3D);
	}
	public void removeObject3D(Object3D removeMe)
	{
		for(int k=0; k< objects.size();k++)
		{
			if(((Object3D)objects.get(k)).getIdentifier().equals(removeMe.getIdentifier()))
			{
				objects.remove(k);
			}
		}
	}//end removeObject3D
	public LinkedList getObjects()
	{
		return objects;
	}
	public void setObjects(LinkedList newObjects)
	{
		this.objects = newObjects;
	}

}
