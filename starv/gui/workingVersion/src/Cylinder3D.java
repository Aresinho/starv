import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.corba.se.impl.orbutil.graph.Node;
import com.sun.j3d.utils.geometry.Cylinder;


public class Cylinder3D extends Object3D {
	float radius =1;
	float length=1;
	public Cylinder3D()
	{
		super();
	}
	public TransformGroup drawMe(Vector3d newVector)
	{
		transform3d.setTranslation(newVector);
		group = new TransformGroup(transform3d);
		group.addChild(new Cylinder(radius,length));
		return group;
	}
	public void setRadius(float radius)
	{
		this.radius = radius;
	}
	public void setLength(float length)
	{
		this.length = length;
	}
	public float getRadius()
	{
		return this.radius;
	}
	//public float
}
