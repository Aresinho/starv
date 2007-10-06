import java.applet.Applet;
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.CheckboxMenuItem;
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Frame;
import java.awt.Menu;
import java.awt.MenuBar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.util.Enumeration;
import java.util.EventListener;

import javax.media.j3d.Behavior;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Light;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.media.j3d.WakeupCriterion;
import javax.media.j3d.WakeupOnAWTEvent;
import javax.media.j3d.WakeupOnElapsedFrames;
import javax.media.j3d.WakeupOr;
import javax.vecmath.Color3f;
import javax.vecmath.Matrix4d;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f;

import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;
class CheckboxMenu extends Menu implements ItemListener {
  // State
  protected CheckboxMenuItem[] checks = null;

  protected int current = 0;

  protected CheckboxMenuListener listener = null;

  //  Construct
  public CheckboxMenu(String name, NameValue[] items,
      CheckboxMenuListener listen) {
    this(name, items, 0, listen);
  }

  public CheckboxMenu(String name, NameValue[] items, int cur,
      CheckboxMenuListener listen) {
    super(name);
    current = cur;
    listener = listen;

    if (items == null)
      return;

    checks = new CheckboxMenuItem[items.length];
    for (int i = 0; i < items.length; i++) {
      checks[i] = new CheckboxMenuItem(items[i].name, false);
      checks[i].addItemListener(this);
      add(checks[i]);
    }
    checks[cur].setState(true);
  }

  //  Handle checkbox changed events
  public void itemStateChanged(ItemEvent event) {
    Object src = event.getSource();

    for (int i = 0; i < checks.length; i++) {
      if (src == checks[i]) {
        // Update the checkboxes
        checks[current].setState(false);
        current = i;
        checks[current].setState(true);

        if (listener != null)
          listener.checkboxChanged(this, i);
        return;
      }
    }
  }

  // Methods to get and set state
  public int getCurrent() {
    return current;
  }

  public void setCurrent(int cur) {
    if (cur < 0 || cur >= checks.length)
      return; // ignore out of range choices
    if (checks == null)
      return;
    checks[current].setState(false);
    current = cur;
    checks[current].setState(true);
  }

  public CheckboxMenuItem getSelectedCheckbox() {
    if (checks == null)
      return null;
    return checks[current];
  }

  public void setSelectedCheckbox(CheckboxMenuItem item) {
    if (checks == null)
      return;
    for (int i = 0; i < checks.length; i++) {
      if (item == checks[i]) {
        checks[i].setState(false);
        current = i;
        checks[i].setState(true);
      }
    }
  }
}
