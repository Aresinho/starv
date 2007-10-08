import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Toolbar for a Simulation. It is divided in three sections
 * Control (Pause, Stop, Play) Steps (Step Up, Down, Forward, Backward ) and
 * Pan (Pan Up, Down, Left, Right). It relies on EventHandler to add functionality
 * @author Ares
 * @version 1
 */
public class SimulationToolbar extends ControlToolbar {
	
	public SimulationToolbar()
	{
		//Button Images
		ImageIcon playImage = createImageIcon("Icons/play16.gif");
		ImageIcon stopImage = createImageIcon("Icons/stop16.gif");
		ImageIcon pauseImage = createImageIcon("Icons/pause16.gif");
		ImageIcon skipStartImage = createImageIcon("Icons/stepBack16.gif");
		ImageIcon skipEndImage = createImageIcon("Icons/stepForward16.gif");
		ImageIcon stepForwardImage = createImageIcon("Icons/FastForward16.gif");
		ImageIcon stepReverseImage = createImageIcon("Icons/Rewind16.gif");
		
		/////////// CONTROL SECTION ///////////////
		
		//Play Button
		JButton play = new JButton("Play", playImage);
		play.addActionListener(this);
		
		//Pause button
		JButton pause = new JButton("Pause", pauseImage);
		pause.addActionListener(this);
		
		//Stop Button
		JButton stop = new JButton("Stop", stopImage);
		stop.addActionListener(this);

		
		//Panel for control section
		JPanel control = new JPanel();
		control.setLayout(new BoxLayout(control, BoxLayout.X_AXIS));
		//Section Construction
		control.add(play);
		control.add(pause);
		control.add(stop);
		
		//////////////// STEPS SECTION ////////////////////////
		/*
		 * Due to space. this section is divided into two
		 * so it would like better on screen.
		 */
		
		//Upper Section Construction
		JButton skipStart = new JButton("Skip to Start", skipStartImage);
		skipStart.setHorizontalTextPosition(AbstractButton.CENTER);
		skipStart.setVerticalTextPosition(AbstractButton.BOTTOM);
		stop.addActionListener(this);
		
		JButton skipEnd = new JButton("Skip to End", skipEndImage);
		skipEnd.setHorizontalTextPosition(AbstractButton.CENTER);
		skipEnd.setVerticalTextPosition(AbstractButton.BOTTOM);
		skipEnd.addActionListener(this);
		
		// Upper Steps Panel creation and construction
		JPanel stepsUp = new JPanel();
		stepsUp.setLayout(new BoxLayout(stepsUp, BoxLayout.X_AXIS));
		stepsUp.add(skipStart);
		stepsUp.add(skipEnd);
		
		
		//Lower Section
		JButton stepReverse = new JButton("Step Reverse", stepReverseImage);
		stepReverse.setHorizontalTextPosition(AbstractButton.CENTER);
		stepReverse.setVerticalTextPosition(AbstractButton.BOTTOM);
		stepReverse.addActionListener(this);
		
		JButton stepForward = new JButton("Step Forward", stepForwardImage);
		stepForward.setHorizontalTextPosition(AbstractButton.CENTER);
		stepForward.setVerticalTextPosition(AbstractButton.BOTTOM);
		stepForward.addActionListener(this);
		
		
		// Lower Steps Panel creation and construction
		JPanel stepsDown = new JPanel();
		stepsDown.setLayout(new BoxLayout(stepsDown, BoxLayout.X_AXIS));
		stepsDown.add(stepReverse);
		stepsDown.add(stepForward);
		
		// Steps Creation and construction
		JPanel steps = new JPanel();
		steps.setLayout(new BoxLayout(steps, BoxLayout.PAGE_AXIS));
		steps.add(stepsUp);
		steps.add(stepsDown);
		
		//Build the Toolbar
		setLayout(new BorderLayout());
		add(Window.createPanelForComponent(control, "Controls"), BorderLayout.NORTH);
		add(Window.createPanelForComponent(steps, "Steps"), BorderLayout.CENTER);
		add(Window.createPanelForComponent(new EditToolbar(), "Panning"), BorderLayout.SOUTH);
	}

}