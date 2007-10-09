import java.util.LinkedList;

import javax.media.j3d.TransformGroup;
import javax.vecmath.Vector3d;


public class UserDefined3D extends Object3D{
	public LinkedList objects=null;
	Vector3d translation = new Vector3d();
	
	public UserDefined3D()
	{
		super();
	}
	public TransformGroup drawMe()
	{
		TransformGroup holder = new TransformGroup();
		for(int k = 0;k< objects.size();k++)
		{
			Object3D item = (Object3D)objects.get(k);
			holder.addChild(item.drawMe());
		}
		return holder;
	}

}