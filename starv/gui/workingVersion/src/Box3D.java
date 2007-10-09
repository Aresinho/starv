import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;

import com.sun.j3d.utils.geometry.Box;
import com.sun.j3d.utils.geometry.ColorCube;
import com.sun.j3d.utils.geometry.Cylinder;


public class Box3D extends Object3D {

	Vector3d translation = new Vector3d();
	
	public Box3D()
	{
		super();
	}
	
	public TransformGroup drawMe()
	{
		transform3d.setTranslation(translation);
		group = new TransformGroup(transform3d);
		group.addChild(new ColorCube(1.0f));
		return group;
	}

}
