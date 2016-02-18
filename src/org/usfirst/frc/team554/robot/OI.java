package org.usfirst.frc.team554.robot;

import org.usfirst.frc.team554.robot.commands.*;
import org.usfirst.frc.team554.robot.triggers.*;

import edu.wpi.first.wpilibj.Joystick;
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
    BeaterBarShootTrigger BBShootButton = new BeaterBarShootTrigger(XboxOneController);
    BeaterBarCollectTrigger BBCollectButton = new BeaterBarCollectTrigger(XboxOneController);
    ArmMoveWithJoystickTrigger MoveArmWithJoystickButton = new ArmMoveWithJoystickTrigger(XboxOneController);
    

	//Both of the triggers need to be set as their relative axis
	Button LTrigger = new JoystickButton(XboxOneController, 7);
	Button RTrigger = new JoystickButton(XboxOneController, 8);
    Button LStickButton = new JoystickButton(XboxOneController, 9);
    Button RStickButton = new JoystickButton(XboxOneController,10);
    
    
    public OI()
    {
    	//trigger.whenPressed(new Camera_Toggle());
    	button7.whenPressed(new DriveTrain_EngageHighGear());
    	button8.whenPressed(new DriveTrain_EngageLowGear());
    	LB.whenPressed(new BeaterBars_Pass());
    	A.whenPressed(new BeaterBars_Collect());
    	B.whenPressed(new BeaterBars_Stop());
    	BBShootButton.whenActive(new BeaterBars_Shoot());
    	//BBCollectButton.whenActive(new BeaterBars_Collect());
    	//BBCollectButton.whenInactive(new BeaterBars_Stop());
    	MoveArmWithJoystickButton.whileActive(new ArmMoveWithJoystick());
    }
    
	public Joystick getOperator(){
		return XboxOneController;
	}
	
	public Joystick getDriver(){
		return driver_Joystick;
	}
	

}

