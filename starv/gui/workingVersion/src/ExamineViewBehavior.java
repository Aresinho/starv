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
class ExamineViewerBehavior extends ViewerBehavior {
  // Previous cursor location
  protected int previousX = 0;

  protected int previousY = 0;

  // Saved standard cursor
  protected Cursor savedCursor = null;

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into a
   * transform group given later with the setTransformGroup( ) method.
   */
  public ExamineViewerBehavior() {
    super();
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into a
   * transform group given later with the setTransformGroup( ) method.
   * 
   * @param parent
   *            The AWT Component that contains the area generating mouse
   *            events.
   */
  public ExamineViewerBehavior(Component parent) {
    super(parent);
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into the
   * given transform group.
   * 
   * @param transformGroup
   *            The transform group to be modified by the behavior.
   */
  public ExamineViewerBehavior(TransformGroup transformGroup) {
    super();
    subjectTransformGroup = transformGroup;
  }

  /**
   * Construct an examine behavior that listens to mouse movement and button
   * presses to generate rotation and translation transforms written into the
   * given transform group.
   * 
   * @param transformGroup
   *            The transform group to be modified by the behavior.
   * @param parent
   *            The AWT Component that contains the area generating mouse
   *            events.
   */
  public ExamineViewerBehavior(TransformGroup transformGroup, Component parent) {
    super(parent);
    subjectTransformGroup = transformGroup;
  }

  /**
   * Respond to a button1 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton1(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.HAND_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a rotation
    //
    // Compute the delta in X and Y from the previous
    // position. Use the delta to compute rotation
    // angles with the mapping:
    //
    //   positive X mouse delta --> positive Y-axis rotation
    //   positive Y mouse delta --> positive X-axis rotation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaX = x - previousX;
    int deltaY = y - previousY;

    if (deltaX > UNUSUAL_XDELTA || deltaX < -UNUSUAL_XDELTA
        || deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double xRotationAngle = deltaY * XRotationFactor;
    double yRotationAngle = deltaX * YRotationFactor;

    //
    // Build transforms
    //
    transform1.rotX(xRotationAngle);
    transform2.rotY(yRotationAngle);

    // Get and save the current transform matrix
    subjectTransformGroup.getTransform(currentTransform);
    currentTransform.get(matrix);
    translate.set(matrix.m03, matrix.m13, matrix.m23);

    // Translate to the origin, rotate, then translate back
    currentTransform.setTranslation(origin);
    currentTransform.mul(transform1, currentTransform);
    currentTransform.mul(transform2, currentTransform);
    currentTransform.setTranslation(translate);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to a button2 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton2(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.MOVE_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a translation
    //
    // Compute the delta in Y from the previous
    // position. Use the delta to compute translation
    // distances with the mapping:
    //
    //   positive Y mouse delta --> positive Y-axis translation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaY = y - previousY;

    if (deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double zTranslationDistance = deltaY * ZTranslationFactor;

    //
    // Build transforms
    //
    translate.set(0.0, 0.0, zTranslationDistance);
    transform1.set(translate);

    // Get and save the current transform
    subjectTransformGroup.getTransform(currentTransform);

    // Translate as needed
    currentTransform.mul(transform1, currentTransform);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to a button3 event (press, release, or drag).
   * 
   * @param mouseEvent
   *            A MouseEvent to respond to.
   */
  public void onButton3(MouseEvent mev) {
    if (subjectTransformGroup == null)
      return;

    int x = mev.getX();
    int y = mev.getY();

    if (mev.getID() == MouseEvent.MOUSE_PRESSED) {
      // Mouse button pressed: record position
      previousX = x;
      previousY = y;

      // Change to a "move" cursor
      if (parentComponent != null) {
        savedCursor = parentComponent.getCursor();
        parentComponent.setCursor(Cursor
            .getPredefinedCursor(Cursor.MOVE_CURSOR));
      }
      return;
    }
    if (mev.getID() == MouseEvent.MOUSE_RELEASED) {
      // Mouse button released: do nothing

      // Switch the cursor back
      if (parentComponent != null)
        parentComponent.setCursor(savedCursor);
      return;
    }

    //
    // Mouse moved while button down: create a translation
    //
    // Compute the delta in X and Y from the previous
    // position. Use the delta to compute translation
    // distances with the mapping:
    //
    //   positive X mouse delta --> positive X-axis translation
    //   positive Y mouse delta --> negative Y-axis translation
    //
    // where positive X mouse movement is to the right, and
    // positive Y mouse movement is **down** the screen.
    //
    int deltaX = x - previousX;
    int deltaY = y - previousY;

    if (deltaX > UNUSUAL_XDELTA || deltaX < -UNUSUAL_XDELTA
        || deltaY > UNUSUAL_YDELTA || deltaY < -UNUSUAL_YDELTA) {
      // Deltas are too huge to be believable. Probably a glitch.
      // Don't record the new XY location, or do anything.
      return;
    }

    double xTranslationDistance = deltaX * XTranslationFactor;
    double yTranslationDistance = -deltaY * YTranslationFactor;

    //
    // Build transforms
    //
    translate.set(xTranslationDistance, yTranslationDistance, 0.0);
    transform1.set(translate);

    // Get and save the current transform
    subjectTransformGroup.getTransform(currentTransform);

    // Translate as needed
    currentTransform.mul(transform1, currentTransform);

    // Update the transform group
    subjectTransformGroup.setTransform(currentTransform);

    previousX = x;
    previousY = y;
  }

  /**
   * Respond to an elapsed frames event (assuming subclass has set up a wakeup
   * criterion for it).
   * 
   * @param time
   *            A WakeupOnElapsedFrames criterion to respond to.
   */
  public void onElapsedFrames(WakeupOnElapsedFrames timeEvent) {
    // Can't happen
  }
}

/*
 * 
 * Copyright (c) 1998 David R. Nadeau
 *  
 */

/**
 * WalkViewerBehavior is a utility class that creates a "walking style"
 * navigation symantic.
 * 
 * The behavior wakes up on mouse button presses, releases, and mouse movements
 * and generates transforms in a "walk style" that enables the user to walk
 * through a scene, translating and turning about as if they are within the
 * scene. Such a walk style is similar to the "Walk" navigation type used by
 * VRML browsers.
 * <P>
 * The behavior maps mouse drags to different transforms depending upon the
 * mouse button held down:
 * <DL>
 * <DT>Button 1 (left)
 * <DD>Horizontal movement --> Y-axis rotation
 * <DD>Vertical movement --> Z-axis translation
 * 
 * <DT>Button 2 (middle)
 * <DD>Horizontal movement --> Y-axis rotation
 
 * <DD>Vertical movement --> X-axis rotation
 * 
 * <DT>Button 3 (right)
 * <DD>Horizontal movement --> X-axis translation
 * <DD>Vertical movement --> Y-axis translation
 * </DL>
 * 
 * To support systems with 2 or 1 mouse buttons, the following alternate
 * mappings are supported while dragging with any mouse button held down and
 * zero or more keyboard modifiers held down:
 * <UL>
 * <LI>No modifiers = Button 1
 * <LI>ALT = Button 2
 * <LI>Meta = Button 3
 * <LI>Control = Button 3
 * </UL>
 * The behavior automatically modifies a TransformGroup provided to the
 * constructor. The TransformGroup's transform can be set at any time by the
 * application or other behaviors to cause the walk rotation and translation to
 * be reset.
 * <P>
 * While a mouse button is down, the behavior automatically changes the cursor
 * in a given parent AWT Component. If no parent Component is given, no cursor
 * changes are attempted.
 * 
 * @version 1.0, 98/04/16
 * @author David R. Nadeau, San Diego Supercomputer Center
 */