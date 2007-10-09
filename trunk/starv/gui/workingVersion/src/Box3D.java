import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;


public class Box3D extends Object3D {

	public Box3D()
	{
		super();
	}
	
	public TransformGroup drawMe(Vector3d newVector)
	{
		transform3d.setTranslation(newVector);
		group = new TransformGroup(transform3d);
		group.addChild(new ColorCube(1.0f));
		return group;
	}

}
