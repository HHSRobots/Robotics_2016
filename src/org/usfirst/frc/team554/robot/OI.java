package org.usfirst.frc.team554.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import org.usfirst.frc.team554.robot.commands.ExampleCommand;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
<<<<<<< HEAD
<<<<<<< HEAD
	
	Joystick Ps4 = new Joystick(1);
	Joystick driver_Joystick = new Joystick(0);
	Button L1 = new JoystickButton(Ps4, 5);
	Button L2 = new JoystickButton(Ps4, 7);
	Button R1 = new JoystickButton(Ps4, 6);
	Button R2 = new JoystickButton(Ps4, 8);
	Button Square = new JoystickButton(Ps4, 1);
	Button Cross = new JoystickButton(Ps4, 2);
	Button Circle = new JoystickButton(Ps4, 3);
    Button Triangle = new JoystickButton(Ps4, 4);
    Button LStickButton = new JoystickButton(Ps4, 11);
    Button RStickButton = new JoystickButton(Ps4,12);
=======
=======
>>>>>>> origin/MainComputer
	//Joysticks: Xbox One Controller -> Operator, Joystick -> Driver
	Joystick Xbone = new Joystick(1);
	Button L1 = new JoystickButton(Xbone, 5);
	Button L2 = new JoystickButton(Xbone, 7);
	Button R1 = new JoystickButton(Xbone, 6);
	Button R2 = new JoystickButton(Xbone, 8);
	Button Square = new JoystickButton(Xbone, 1);
	Button Cross = new JoystickButton(Xbone, 2);
	Button Circle = new JoystickButton(Xbone, 3);
    Button Triangle = new JoystickButton(Xbone, 4);
    Button LStickButton = new JoystickButton(Xbone, 11);
    Button RStickButton = new JoystickButton(Xbone,12);
<<<<<<< HEAD
>>>>>>> master
=======
>>>>>>> origin/MainComputer
    
	public Joystick getOperator(){
		return Xbone;
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

