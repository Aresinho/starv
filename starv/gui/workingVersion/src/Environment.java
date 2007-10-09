

import java.io.Serializable;
import
java.util.*;
/**
 * 
 * @author IPAPI
 *
 */
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
	
	
	/**
	 * Constructor
	 * creates an environment with a fixed  dimension
	 */
	public Environment()
	{
		objects = new LinkedList();
		length = 100;
		width = 100;
		name = "";
		units = UNIT_METERS;
	}
	
	
	/**
	 * creates an environment with desire dimension an a given name.
	 * @param l the desire length
	 * @param w the desire width
	 * @param n the desire name
	 */
	public Environment(double l, double w, String n)
	{
		length = l;
		width = w;
		name = n;
		objects = new LinkedList();
		units = UNIT_METERS;
	}
	
	
	/**
	 *  sets a desire length
	 * @param l
	 */
	public void setLength(double l)
	{
		length = l;
	}
	
	/**
	 *  sets a desire width
	 * @param w
	 */
	public void setWidth(double w)
	{
		width = w;
	}
	
	
	/**
	 *  sets the desire  longitude
	 * @param deg    degrees
	 * @param min    minutes
	 * @param dir    direction
	 */
	public void setLongitude(String deg, String min, String dir)
	{
		longitude[0] = deg;
		longitude[1] = min;
		longitude[2] = dir;
	}
	
	
	/**
	 *  sets the desire latitud
	 * @param deg   degrees
	 * @param min   minutes
	 * @param dir   direction
	 */
	public void setLatitude(String deg, String min, String dir)
	{
		latitude[0] = deg;
		latitude[1] = min;
		latitude[2] = dir;
	}
	
	/**
	 *  sets the desire name
	 * @param n  name
	 */
	public void setName(String n)
	{
		name = n;
	}
	
	/**
	 *  
	 * @return the length of the environment
	 */
	public double getLength()
	{
		return length;
	}
	
	/**
	 * 
	 * @return the width of the environment
	 */
	public double getWidth()
	{
		return width;
	}
	
	
	/**
	 * 
	 * @return the longitude
	 */
	public String[] getLongitude()
	{
		return longitude;
	}
	
	
	/**
	 * 
	 * @return the latitude of the environment
	 */
	public String[] getLatitude()
	{
		return latitude;
	}
	
	
	/**
	 * 
	 * @return the name of the environment
	 */
	public String getName()
	{
		return name;
	}
	
	/**
	 * 
	 * @param replacer
	 */
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
	
	
	/**
	 * 
	 * @param newUnits
	 */
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
	
	
	/**
	 * 
	 * @return
	 */
	public int getUnits()
	{
		return units;
	}
	
	/**
	 * 
	 * @param newObject3D
	 */
	public void addObject3D(Object3D newObject3D)
	{
		objects.add(newObject3D);
	}
	
	/**
	 * 
	 * @param removeMe
	 */
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
	
	/**
	 * 
	 * @return
	 */
	public LinkedList getObjects()
	{
		return objects;
	}
	
	/**
	 * 
	 * @param newObjects
	 */
	public void setObjects(LinkedList newObjects)
	{
		this.objects = newObjects;
	}

}
