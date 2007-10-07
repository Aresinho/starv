import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Cylinder;
import com.sun.j3d.utils.geometry.Sphere;


public class Sphere3D extends Object3D {

	public Sphere3D()
	{
		super();
	}
	public TransformGroup drawMe(Vector3d newVector)
	{
		transform3d.setTranslation(newVector);
		group = new TransformGroup(transform3d);
		group.addChild(new Sphere(1f));
		return group;
	}

}
