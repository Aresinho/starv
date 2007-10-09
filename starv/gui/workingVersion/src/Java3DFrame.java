import java.applet.Applet;
import java.awt.CheckboxMenuItem;
import java.awt.Component;
import java.awt.Dimension;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.LinkedList;
import javax.media.j3d.Appearance;
import javax.media.j3d.BoundingSphere;
import javax.media.j3d.BranchGroup;
import javax.media.j3d.Canvas3D;
import javax.media.j3d.DirectionalLight;
import javax.media.j3d.Group;
import javax.media.j3d.Light;
import javax.media.j3d.Material;
import javax.media.j3d.Shape3D;
import javax.media.j3d.Texture;
import javax.media.j3d.TextureAttributes;
import javax.media.j3d.Transform3D;
import javax.media.j3d.TransformGroup;
import javax.vecmath.Color3f;
import javax.vecmath.Point3d;
import javax.vecmath.Point3f;
import javax.vecmath.Vector3d;
import javax.vecmath.Vector3f; 

import com.sun.j3d.utils.behaviors.mouse.MouseRotate;
import com.sun.j3d.utils.geometry.Primitive;
import com.sun.j3d.utils.image.TextureLoader;
import com.sun.j3d.utils.picking.PickCanvas;
import com.sun.j3d.utils.picking.PickResult;
import com.sun.j3d.utils.universe.PlatformGeometry;
import com.sun.j3d.utils.universe.SimpleUniverse;
import com.sun.j3d.utils.universe.Viewer;
import com.sun.j3d.utils.universe.ViewingPlatform;


public class Java3DFrame extends Applet implements MouseListener {
  //  Navigation types
  public final static int Walk = 0;

  public final static int Examine = 1;

  //  Should the scene be compiled?
  private boolean shouldCompile = true;

  //  GUI objects for our subclasses
  protected Java3DFrame example = null;

  protected Canvas3D exampleCanvas = null;

  protected TransformGroup exampleViewTransform = null;

  private TransformGroup holderTransform = null;
  protected TransformGroup exampleSceneTransform = null;

  protected boolean debug = false;

  //  Private GUI objects and state
  private boolean headlightOnOff = true;

  private int navigationType = Examine;

  private CheckboxMenuItem headlightMenuItem = null;

  private CheckboxMenuItem walkMenuItem = null;

  private CheckboxMenuItem examineMenuItem = null;

  private DirectionalLight headlight = null;

  private ExamineViewerBehavior examineBehavior = null;

  PickCanvas pickCanvas;
  
  private WalkViewerBehavior walkBehavior = null;
  BranchGroup sceneRoot = new BranchGroup();
  private int cycle =0;

  //--------------------------------------------------------------
  //  ADMINISTRATION
  //--------------------------------------------------------------

  /**
   * Constructs a new Example object.
   * 
   * @return a new Example that draws no 3D content
   */
  public Java3DFrame() {
    // Do nothing
  }

  /**
   * Initializes the application when invoked as an applet.
   */
  public void init() {
    // Collect properties into String array
    String[] args = new String[2];
    // NOTE: to be done still...

    this.initialize();
    this.buildUniverse();

    // NOTE: add something to the browser page?
  }

  /**
   * Initializes the Example by parsing command-line arguments, building an
   * AWT Frame, constructing a menubar, and creating the 3D canvas.
   * 
   * @param args
   *            a String array of command-line arguments
   */
  protected Component initialize() {
    
    exampleCanvas = new Canvas3D(SimpleUniverse.getPreferredConfiguration());
   
    
    exampleCanvas.setPreferredSize(new Dimension(800,800));
 //   ScrollPane scroll = new ScrollPane();  
    
  //  scroll.add(exampleCanvas);
  //  scroll.setPreferredSize(new Dimension(600, 600));
    
    
    
    return exampleCanvas;
    
  }

  /**
   * Parses incoming command-line arguments. Applications that subclass this
   * class may override this method to support their own command-line
   * arguments.
   * 
   * @param args
   *            a String array of command-line arguments
   */
  protected void parseArgs(String[] args) {
    for (int i = 0; i < args.length; i++) {
      if (args[i].equals("-d"))
        debug = true;
    }
  }

  //--------------------------------------------------------------
  //  SCENE CONTENT
  //--------------------------------------------------------------

  /**
   * Builds the 3D universe by constructing a virtual universe (via
   * SimpleUniverse), a view platform (via SimpleUniverse), and a view (via
   * SimpleUniverse). A headlight is added and a set of behaviors initialized
   * to handle navigation types.
   */
  protected void buildUniverse() {
    //
    //  Create a SimpleUniverse object, which builds:
    //
    //    - a Locale using the given hi-res coordinate origin
    //
    //    - a ViewingPlatform which in turn builds:
    //          - a MultiTransformGroup with which to move the
    //            the ViewPlatform about
    //
    //          - a ViewPlatform to hold the view
    //
    //          - a BranchGroup to hold avatar geometry (if any)
    //
    //          - a BranchGroup to hold view platform
    //            geometry (if any)
    //
    //    - a Viewer which in turn builds:
    //          - a PhysicalBody which characterizes the user's
    //            viewing preferences and abilities
    //
    //          - a PhysicalEnvironment which characterizes the
    //            user's rendering hardware and software
    //
    //          - a JavaSoundMixer which initializes sound
    //            support within the 3D environment
    //
    //          - a View which renders the scene into a Canvas3D
    //
    //  All of these actions could be done explicitly, but
    //  using the SimpleUniverse utilities simplifies the code.
    //
    if (debug)
      System.err.println("Building scene graph...");
    SimpleUniverse universe = new SimpleUniverse(null, // Hi-res coordinate
        // for the origin -
        // use default
        1, // Number of transforms in MultiTransformGroup
        exampleCanvas, // Canvas3D into which to draw
        null); // URL for user configuration file - use defaults

    //
    //  Get the viewer and create an audio device so that
    //  sound will be enabled in this content.
    //
    Viewer viewer = universe.getViewer();
    viewer.createAudioDevice();

    //
    //  Get the viewing platform created by SimpleUniverse.
    //  From that platform, get the inner-most TransformGroup
    //  in the MultiTransformGroup. That inner-most group
    //  contains the ViewPlatform. It is this inner-most
    //  TransformGroup we need in order to:
    //
    //    - add a "headlight" that always aims forward from
    //       the viewer
    //
    //    - change the viewing direction in a "walk" style
    //
    //  The inner-most TransformGroup's transform will be
    //  changed by the walk behavior (when enabled).
    //
    
    
    ViewingPlatform viewingPlatform = universe.getViewingPlatform();
    exampleViewTransform = viewingPlatform.getViewPlatformTransform();

    //
    //  Create a "headlight" as a forward-facing directional light.
    //  Set the light's bounds to huge. Since we want the light
    //  on the viewer's "head", we need the light within the
    //  TransformGroup containing the ViewPlatform. The
    //  ViewingPlatform class creates a handy hook to do this
    //  called "platform geometry". The PlatformGeometry class is
    //  subclassed off of BranchGroup, and is intended to contain
    //  a description of the 3D platform itself... PLUS a headlight!
    //  So, to add the headlight, create a new PlatformGeometry group,
    //  add the light to it, then add that platform geometry to the
    //  ViewingPlatform.
    //
    BoundingSphere allBounds = new BoundingSphere(
        new Point3d(0.0, 0.0, 0.0), 100000.0);

    PlatformGeometry pg = new PlatformGeometry();
    headlight = new DirectionalLight();
    headlight.setColor(White);
    headlight.setDirection(new Vector3f(0.0f, 0.0f, -1.0f));
    headlight.setInfluencingBounds(allBounds);
    headlight.setCapability(Light.ALLOW_STATE_WRITE);
    pg.addChild(headlight);
    viewingPlatform.setPlatformGeometry(pg);

    //
    //  Create the 3D content BranchGroup, containing:
    //
    //    - a TransformGroup who's transform the examine behavior
    //      will change (when enabled).
    //
    //    - 3D geometry to view
    //
    // Build the scene root
    

    // Build a transform that we can modify
    exampleSceneTransform = new TransformGroup();
    exampleSceneTransform
        .setCapability(TransformGroup.ALLOW_TRANSFORM_READ);
    exampleSceneTransform
        .setCapability(TransformGroup.ALLOW_TRANSFORM_WRITE);
    //exampleSceneTransform.setCapability(Group.ALLOW_CHILDREN_EXTEND);


    // Create a simple Shape3D node; add it to the scene graph.
    EditorWindow.editor.environment = new Environment();
    Cylinder3D poop = new Cylinder3D();
    poop.setVector(new Vector3d(1,1,1));
    EditorWindow.editor.environment.addObject3D(poop);
    //EditorWindow.editor.environment.addObject3D(new Box3D());
    Sphere3D woop = new Sphere3D();
    woop.setVector(new Vector3d(-1,-1,-1));
    EditorWindow.editor.environment.addObject3D(woop);

    sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
    sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
    sceneRoot.setCapability(BranchGroup.ALLOW_DETACH);
    sceneRoot.setCapability(BranchGroup.ALLOW_CHILDREN_EXTEND);
    
    LinkedList tester = new LinkedList();
    LinkedList objects = EditorWindow.editor.environment.getObjects();
    tester.add((Object3D)objects.get(0));
    
    EditorWindow.editor.setActiveObject(tester);
    System.out.println(" after for loop");
    

    
    Transform3D rot = new Transform3D();
    
    TransformGroup obby = new TransformGroup(rot);
    
    TextureLoader texLoader = new TextureLoader("flooring.jpg", this);
    Texture groundTex = texLoader.getTexture();
    groundTex.setBoundaryModeS(Texture.WRAP);
    groundTex.setBoundaryModeT(Texture.WRAP);
    groundTex.setMinFilter(Texture.NICEST);
    groundTex.setMagFilter(Texture.NICEST);
    groundTex.setMipMapMode(Texture.BASE_LEVEL);
    groundTex.setEnable(true); 

    Appearance groundApp = new Appearance();

    Material groundMat = new Material();
    groundMat.setAmbientColor(0.6f, 0.6f, 0.6f);
    groundMat.setDiffuseColor(1.0f, 1.0f, 1.0f);
    groundMat.setSpecularColor(0.0f, 0.0f, 0.0f);
    groundApp.setMaterial(groundMat);

    rot = new Transform3D();
    rot.setScale(new Vector3d(4.0, 4.0, 1.0));

    TextureAttributes groundTexAtt = new TextureAttributes();
    groundTexAtt.setTextureMode(TextureAttributes.MODULATE);
    groundTexAtt.setPerspectiveCorrectionMode(TextureAttributes.NICEST);
    groundTexAtt.setTextureTransform(rot);
    groundApp.setTextureAttributes(groundTexAtt);

    if (groundTex != null)
      groundApp.setTexture(groundTex); 

    ElevationGrid ground = new ElevationGrid((int)EditorWindow.editor.environment.getLength(), // X dimension
    		(int)EditorWindow.editor.environment.getWidth(), // Z dimension
        2.0f, // X spacing
        2.0f, // Z spacing
        // Automatically use zero heights
        groundApp); // Appearance
    TransformGroup tg;
    Vector3f trans = new Vector3f();
    trans.set(0.0f, -1.0f, 0.0f);
    rot.set(trans);
    obby = new TransformGroup(rot);
    obby.addChild(ground);
    sceneRoot.addChild(obby);
    BoundingSphere worldBounds = new BoundingSphere(new Point3d(0.0, 0.0,
            0.0), // Center
            1000.0); // Extent

        DirectionalLight light = new DirectionalLight();
        light.setEnable(true);
        light.setColor(new Color3f(1.0f, 1.0f, 1.0f));
        light.setDirection(new Vector3f(0.5f, -1.0f, -0.5f));
        light.setInfluencingBounds(worldBounds);
        sceneRoot.addChild(light);

    
    //
    //  Build the scene, add it to the transform, and add
    //  the transform to the scene root
    //
    if (debug)
      System.err.println("  scene...");
    Group scene = this.buildScene();
    exampleSceneTransform.addChild(scene);
    sceneRoot.addChild(exampleSceneTransform);

    examineBehavior = new ExamineViewerBehavior(exampleSceneTransform);
    examineBehavior.setSchedulingBounds(allBounds);
    sceneRoot.addChild(examineBehavior);

    walkBehavior = new WalkViewerBehavior(exampleViewTransform);
    walkBehavior.setSchedulingBounds(allBounds);
    sceneRoot.addChild(walkBehavior);

    if (navigationType == Walk) {
      examineBehavior.setEnable(false);
      walkBehavior.setEnable(true);
    } else {
      examineBehavior.setEnable(true);
      walkBehavior.setEnable(false);
    }

    //
    //  Compile the scene branch group and add it to the
    //  SimpleUniverse.
    //
    reDraw();
    
    
    if (shouldCompile)
      sceneRoot.compile();
    universe.addBranchGraph(sceneRoot);

    reset();
    
    
    pickCanvas = new PickCanvas(exampleCanvas, sceneRoot);

	pickCanvas.setMode(PickCanvas.BOUNDS);

	exampleCanvas.addMouseListener(this);
    
  }

  /**
   * Builds the scene. Example application subclasses should replace this
   * method with their own method to build 3D content.
   * 
   * @return a Group containing 3D content to display
   */
  public Group buildScene() {
    // Build the scene group containing nothing
    Group scene = new Group();
    return scene;
  }

  //--------------------------------------------------------------
  //  SET/GET METHODS
  //--------------------------------------------------------------

  /**
   * Sets the headlight on/off state. The headlight faces forward in the
   * direction the viewer is facing. Example applications that add their own
   * lights will typically turn the headlight off. A standard menu item
   * enables the headlight to be turned on and off via user control.
   * 
   * @param onOff
   *            a boolean turning the light on (true) or off (false)
   */
  public void setHeadlightEnable(boolean onOff) {
    headlightOnOff = onOff;
    if (headlight != null)
      headlight.setEnable(headlightOnOff);
    if (headlightMenuItem != null)
      headlightMenuItem.setState(headlightOnOff);
  }

  /**
   * Gets the headlight on/off state.
   * 
   * @return a boolean indicating if the headlight is on or off
   */
  public boolean getHeadlightEnable() {
    return headlightOnOff;
  }

  /**
   * Sets the navigation type to be either Examine or Walk. The Examine
   * navigation type sets up behaviors that use mouse drags to rotate and
   * translate scene content as if it is an object held at arm's length and
   * under examination. The Walk navigation type uses mouse drags to rotate
   * and translate the viewer as if they are walking through the content. The
   * Examine type is the default.
   * 
   * @param nav
   *            either Walk or Examine
   */
  public void setNavigationType(int nav) {
    if (nav == Walk) {
      navigationType = Walk;
      if (walkMenuItem != null)
        walkMenuItem.setState(true);
      if (examineMenuItem != null)
        examineMenuItem.setState(false);
      if (walkBehavior != null)
        walkBehavior.setEnable(true);
      if (examineBehavior != null)
        examineBehavior.setEnable(false);
    } else {
      navigationType = Examine;
      if (walkMenuItem != null)
        walkMenuItem.setState(false);
      if (examineMenuItem != null)
        examineMenuItem.setState(true);
      if (walkBehavior != null)
        walkBehavior.setEnable(false);
      if (examineBehavior != null)
        examineBehavior.setEnable(true);
    }
  }

  /**
   * Gets the current navigation type, returning either Walk or Examine.
   * 
   * @return either Walk or Examine
   */
  public int getNavigationType() {
    return navigationType;
  }

  /**
   * Sets whether the scene graph should be compiled or not. Normally this is
   * always a good idea. For some example applications that use this Example
   * framework, it is useful to disable compilation - particularly when nodes
   * and node components will need to be made un-live in order to make
   * changes. Once compiled, such components can be made un-live, but they are
   * still unchangable unless appropriate capabilities have been set.
   * 
   * @param onOff
   *            a boolean turning compilation on (true) or off (false)
   */
  public void setCompilable(boolean onOff) {
    shouldCompile = onOff;
  }

  /**
   * Gets whether the scene graph will be compiled or not.
   * 
   * @return a boolean indicating if scene graph compilation is on or off
   */
  public boolean getCompilable() {
    return shouldCompile;
  }

  //These methods will be replaced
  //  Set the view position and direction
  public void setViewpoint(Point3f position, Vector3f direction) {
    Transform3D t = new Transform3D();
    t.set(new Vector3f(position));
    exampleViewTransform.setTransform(t);
    // how to set direction?
  }
  public void topDown()
  {
	  //exampleViewTransform
	  System.out.println(" mod syas " + cycle%2);
	  if(cycle%2 == 0)
	  {
		  Transform3D trans = new Transform3D();
		  Transform3D trans2 = new Transform3D();
		  trans.rotX(5.0);
		  trans.setTranslation(new Vector3f(0.0f, 50.0f, 10.75f));
		  trans.mul(trans2, trans);
		  exampleViewTransform.setTransform(trans);
		  setNavigationType(navigationType);
		  
	  }
	  else
		  reset();
	  cycle++;
  }
  //  Reset transforms
  public void reset() {
	    Transform3D trans = new Transform3D();
	    exampleSceneTransform.setTransform(trans);
	    trans.set(new Vector3f(0.0f, 0.0f, 10.0f));
	    exampleViewTransform.setTransform(trans);
	    setNavigationType(Walk);
	  }

  //
  //  Gets the URL (with file: prepended) for the current directory.
  //  This is a terrible hack needed in the Alpha release of Java3D
  //  in order to build a full path URL for loading sounds with
  //  MediaContainer. When MediaContainer is fully implemented,
  //  it should handle relative path names, but not yet.
  //
  public String getCurrentDirectory() {
    // Create a bogus file so that we can query it's path
    File dummy = new File("dummy.tmp");
    String dummyPath = dummy.getAbsolutePath();

    // strip "/dummy.tmp" from end of dummyPath and put into 'path'
    if (dummyPath.endsWith(File.separator + "dummy.tmp")) {
      int index = dummyPath.lastIndexOf(File.separator + "dummy.tmp");
      if (index >= 0) {
        int pathLength = index + 5; // pre-pend 'file:'
        char[] charPath = new char[pathLength];
        dummyPath.getChars(0, index, charPath, 5);
        String path = new String(charPath, 0, pathLength);
        path = "file:" + path.substring(5, pathLength);
        return path + File.separator;
      }
    }
    return dummyPath + File.separator;
  }

  //--------------------------------------------------------------
  //  USER INTERFACE
  //--------------------------------------------------------------
  
  public void reDraw()
  { 
	    LinkedList objects = new LinkedList();
	    if( EditorWindow.editor.environment != null){
	    	if(sceneRoot.numChildren() >5)
	    		{
	    		for(int m=5; m<sceneRoot.numChildren();m++){
	    			BranchGroup temp = (BranchGroup)sceneRoot.getChild(m);
	    			temp.detach();
	    			sceneRoot.removeChild(m);
	    		}
	    		}
	    	objects =  EditorWindow.editor.environment.getObjects();
	    	for(int k =0; k< objects.size();k++)
	    	{
	    		BranchGroup objectBranchWrapper = new BranchGroup();
	    		objectBranchWrapper.setCapability(BranchGroup.ALLOW_CHILDREN_READ);
	    		objectBranchWrapper.setCapability(BranchGroup.ALLOW_CHILDREN_WRITE);
	    		objectBranchWrapper.setCapability(BranchGroup.ALLOW_DETACH);
	    		
	    		Object3D currShape = (Object3D)objects.get(k);
	    		System.out.println(currShape.toString());
	    		TransformGroup objectGroupWrap = currShape.drawMe();
	    		  MouseRotate behavior = new MouseRotate();
	    		  behavior.setTransformGroup(objectGroupWrap);
	    		  objectGroupWrap.addChild(behavior);
	    		  //behavior.setSchedulingBounds(bounds);
	    		objectBranchWrapper.addChild(objectGroupWrap);
	    		sceneRoot.addChild(objectBranchWrapper);
	    		objectBranchWrapper = null;
	    	}

	    
	    }
  }
  
  
  /** 
   * Enables Object Selection. Creates a new Canvas that is able to select objects
   * It throws a ray on the x and y coordinates and "captures" the object it hits
   */
  public void mouseClicked(MouseEvent e)

  {
	  //Identify the source of the the click
      pickCanvas.setShapeLocation(e);
      
      //Get the object/node that it picked
      PickResult result = pickCanvas.pickClosest();

      
      if (result == null) { // Something was selected

         System.out.println("Nothing picked"); // for testing

      } else {  //Something was picked

         Primitive p = (Primitive)result.getNode(PickResult.PRIMITIVE);

         Shape3D s = (Shape3D)result.getNode(PickResult.SHAPE3D); //Get the shape object

         if (p != null) {  //Line Highlighting actions go here

            System.out.println(p.getClass().getName());

         } else if (s != null) { //Object Highlighting actions go here

               System.out.println(s.getClass().getName());

         } else{ //Upss Something is wrong, there is always something selected

            System.out.println("null");

         }

      }

  }
  
  
  /**
   * Action to be taken with the mouse is pressed
   */
  public void mousePressed(MouseEvent e) {
      saySomething("Mouse pressed; # of clicks: "
                   + e.getClickCount(), e);
   }

  /**
   * Action to be taken when the mouse i released
   */
   public void mouseReleased(MouseEvent e) {
      saySomething("Mouse released; # of clicks: "
                   + e.getClickCount(), e);
   }

   
   /**
    * Action to be taken when mouse enters our canvas
    */
   public void mouseEntered(MouseEvent e) {
      saySomething("Mouse entered", e);
   }

   /**
    * Action to be taken when mouse exits canvas
    */
   public void mouseExited(MouseEvent e) {
      saySomething("Mouse exited", e);
   }

   /**
    * Testing purposes
    * @param eventDescription the event description
    * @param e and event
    */
   void saySomething(String eventDescription, MouseEvent e) {
	   System.out.print(eventDescription);
   }
 
   
   
   
  //  Well known colors, positions, and directions
  public final static Color3f White = new Color3f(1.0f, 1.0f, 1.0f);

  public final static Color3f Gray = new Color3f(0.7f, 0.7f, 0.7f);

  public final static Color3f DarkGray = new Color3f(0.2f, 0.2f, 0.2f);

  public final static Color3f Black = new Color3f(0.0f, 0.0f, 0.0f);

  public final static Color3f Red = new Color3f(1.0f, 0.0f, 0.0f);

  public final static Color3f DarkRed = new Color3f(0.3f, 0.0f, 0.0f);

  public final static Color3f Yellow = new Color3f(1.0f, 1.0f, 0.0f);

  public final static Color3f DarkYellow = new Color3f(0.3f, 0.3f, 0.0f);

  public final static Color3f Green = new Color3f(0.0f, 1.0f, 0.0f);

  public final static Color3f DarkGreen = new Color3f(0.0f, 0.3f, 0.0f);

  public final static Color3f Cyan = new Color3f(0.0f, 1.0f, 1.0f);

  public final static Color3f Blue = new Color3f(0.0f, 0.0f, 1.0f);

  public final static Color3f DarkBlue = new Color3f(0.0f, 0.0f, 0.3f);

  public final static Color3f Magenta = new Color3f(1.0f, 0.0f, 1.0f);

  public final static Vector3f PosX = new Vector3f(1.0f, 0.0f, 0.0f);

  public final static Vector3f NegX = new Vector3f(-1.0f, 0.0f, 0.0f);

  public final static Vector3f PosY = new Vector3f(0.0f, 1.0f, 0.0f);

  public final static Vector3f NegY = new Vector3f(0.0f, -1.0f, 0.0f);

  public final static Vector3f PosZ = new Vector3f(0.0f, 0.0f, 1.0f);

  public final static Vector3f NegZ = new Vector3f(0.0f, 0.0f, -1.0f);

  public final static Point3f Origin = new Point3f(0.0f, 0.0f, 0.0f);

  public final static Point3f PlusX = new Point3f(0.75f, 0.0f, 0.0f);

  public final static Point3f MinusX = new Point3f(-0.75f, 0.0f, 0.0f);

  public final static Point3f PlusY = new Point3f(0.0f, 0.75f, 0.0f);

  public final static Point3f MinusY = new Point3f(0.0f, -0.75f, 0.0f);

  public final static Point3f PlusZ = new Point3f(0.0f, 0.0f, 0.75f);

  public final static Point3f MinusZ = new Point3f(0.0f, 0.0f, -0.75f);
}