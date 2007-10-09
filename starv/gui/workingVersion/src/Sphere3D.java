import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;


public class Sphere3D extends Object3D {
	public Vector3d translation;
	
	public Sphere3D()
	{
		super();
	}
	public TransformGroup drawMe()
	{
		transform3d.setTranslation(translation);
		group = new TransformGroup(transform3d);
		group.addChild(new Sphere(1f));
		return group;
	}
	public void setVector(Vector3d vec)
	{/*
		Probally pass the postion of the mouse, then figure out some algortim to translate that into valeus of vector3d
	*/translation = vec;
		
	}
	public Vector3d getVector()
	{
		return translation;
	}

}
