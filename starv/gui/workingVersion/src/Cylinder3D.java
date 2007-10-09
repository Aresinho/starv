import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.j3d.utils.geometry.Cylinder;

/**
 * 
 * @author IPAPI
 *
 */
public class Cylinder3D extends Object3D {
	float radius =1;
	float length=1;
	public static Vector3d translation;
	
	
	/**
	 * Constructor using the Object3D contructor
	 */
	public Cylinder3D()
	{
		super();
	}
	
	/**
	 *  draws a cylinder
	 *  @return group   a TransformGroup object
	 */
	public TransformGroup drawMe()
	{
		transform3d.setTranslation(translation);
		group = new TransformGroup(transform3d);
		group.addChild(new Cylinder(radius,length));
		return group;
	}
	
	/**
	 *  sets the radius of the cylinder.
	 * @param radius
	 */
	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	
	/**
	 * sets the length of the cylinder
	 * @param length
	 */
	public void setLength(float length)
	{
		this.length = length;
	}
	
	/**
	 *  returns the radius of this cylinder
	 * @return this.radius 
	 */
	public float getRadius()
	{
		return this.radius;
	}
	
	/**
	 *  sets the vector to possition the cylinder.
	 * @param vec
	 */
	public void setVector(Vector3d vec)
	{
		translation = vec;
	}
	
	/**
	 * returns the vector of the cylinder
	 * @return translation  a Vector3D object
	 */
	public Vector3d getVector()
	{
		return translation;
	}
}
