package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;


/**
 *
 */
public class BeaterBar extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController BeaterBarMotor;
	
	public BeaterBar(){
		super();
		BeaterBarMotor = new Talon(3);//Talon number up for debate.
		
	}
	
	
	public void moveBeater(Joystick operator_joystick)
	{
		moveBeaterJoystick(operator_joystick);
	}
	
	public void moveBeaterJoystick(Joystick operator_joystick)
	{
		if (Math.abs(operator_joystick.getY()) > 0.1)
		{
			BeaterBarMotor.set(operator_joystick.getY());//value still up for debate
		}
		else
		{
			BeaterBarMotor.set(0);
		}
	}
	
	public void moveBeaterButton(Joystick operator_joystick)
	{
		//TODO find out how many speeds they want, the crimeny buggers
	}
	
	public void end(){
		// BeaterBar_Rotate.end();
	}
	
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

