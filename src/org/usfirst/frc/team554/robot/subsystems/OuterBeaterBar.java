package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class OuterBeaterBar extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
private SpeedController BeaterBarMotor;
	
	public OuterBeaterBar(){
		super();
		BeaterBarMotor = new Talon(5);//Talon number up for debate.
		
	}
	
	
	public void moveBeater(Joystick operator_joystick)
	{
		moveBeaterJoystick(operator_joystick);
	}
	
	public void moveBeaterJoystick(Joystick operator_joystick)
	{
		if(operator_joystick.getZ()>=0.1)
		{
			BeaterBarMotor.set(-0.5);//value should be tweaked
		}
	}
	
//	public void moveBeaterButton(Joystick operator_joystick)
//	{
//		//TODO find out how many speeds they want, the crimeny buggers
//	}
	
	public void end(){
		BeaterBarMotor.set(0);
	}
	
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

