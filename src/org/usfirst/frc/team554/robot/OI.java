package org.usfirst.frc.team554.robot;
import org.usfirst.frc.team554.robot.commands.*;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Joystick.RumbleType;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

//import org.usfirst.frc.team554.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
  */
public class OI {


	// Joystick: Logitech Controller( Driver)
	
    Joystick driver_Joystick = new Joystick(0);
    Button trigger = new JoystickButton(driver_Joystick,1);
    Button missleButton = new JoystickButton(driver_Joystick,2);
    Button button3 = new JoystickButton(driver_Joystick,3);
    Button button4 = new JoystickButton(driver_Joystick,4);
    Button button5 = new JoystickButton(driver_Joystick,5);
    Button button6 = new JoystickButton(driver_Joystick,6);
    Button button7 = new JoystickButton(driver_Joystick,7);
    Button button8 = new JoystickButton(driver_Joystick,8);
    Button button9 = new JoystickButton(driver_Joystick,9);
    Button button10 = new JoystickButton(driver_Joystick,10);
    
  //Joysticks: Xbox One Controller( Operator)
    //Fixed. minus the Trigger axis
    
    
	Joystick XboxOneController = new Joystick(1);
	Button A = new JoystickButton(XboxOneController, 1);
	Button B = new JoystickButton(XboxOneController, 2);
	Button X = new JoystickButton(XboxOneController, 3);
    Button Y = new JoystickButton(XboxOneController, 4);
    Button LB = new JoystickButton(XboxOneController, 5);
	Button RB = new JoystickButton(XboxOneController, 6);
	
	//Both of the triggers need to be set as their relative axis
	Button LTrigger = new JoystickButton(XboxOneController, 7);
	Button RTrigger = new JoystickButton(XboxOneController, 8);
    Button LStickButton = new JoystickButton(XboxOneController, 9);
    Button RStickButton = new JoystickButton(XboxOneController,10);
    
    
    public OI()
    {
    	trigger.whenPressed(new Camera_Toggle());
    	button7.whenPressed(new EngageHighGear());
    	button8.whenPressed(new EngageLowGear());
    	
    }
    


	
    
    
    

	public Joystick getOperator(){
		return XboxOneController;
	}
	
	public Joystick getDriver(){
		return driver_Joystick;
	}
	
	
    //// CREATING BUTTONS
    // One type of button is a joystick button which is any button on a joystick.
    // You create one by telling it which joystick it's on and which button
    // number it is.
    // Joystick stick = new Joystick(port);
    // Button button = new JoystickButton(stick, buttonNumber);
    
    // There are a few additional built in buttons you can use. Additionally,
    // by subclassing Button you can create custom triggers and bind those to
    // commands the same as any other Button.
    
    //// TRIGGERING COMMANDS WITH BUTTONS
    // Once you have a button, it's trivial to bind it to a button in one of
    // three ways:
    
    // Start the command when the button is pressed and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenPressed(new ExampleCommand());
    
    // Run the command while the button is being held down and interrupt it once
    // the button is released.
    // button.whileHeld(new ExampleCommand());
    
    // Start the command when the button is released  and let it run the command
    // until it is finished as determined by it's isFinished method.
    // button.whenReleased(new ExampleCommand());
}

