package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Joystick;


/**
 *
 */
public class InnerBeaterBar extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
	private SpeedController BeaterBarMotor;
	
	public InnerBeaterBar(){
		super();
		BeaterBarMotor = new Talon(4);//Talon number up for debate.
		
	}
	
	public void moveBeater(Joystick operator_joystick)
	{
		moveBeaterButtons(operator_joystick);
	}
	
	public void moveBeaterButtons(Joystick operator_joystick)//If you are wondering why this is the way it is, This is the way Luke set it up. It should be by triggers and have three settings
	{
		//left trigger is fast mode for arm beater
		//left shouldery button is slow mode
		//right shouldery button is reverse (slow?)
		//X should cancel
		if(operator_joystick.getZ()<=-0.1)//should work with the trigger? Hopefully?
		{
			BeaterBarMotor.set(1);//value up for change
		}
		else if(operator_joystick.getRawButton(5))//slow forwards, should be shoulder left
		{
			BeaterBarMotor.set(0.5);//value still up for debate
		}
		else if(operator_joystick.getRawButton(6))//slow backwards, should be shoulder right
		{
			BeaterBarMotor.set(-0.5);
		}
		else
		{
			BeaterBarMotor.set(0);
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

