package org.usfirst.frc.team554.robot.triggers;

import edu.wpi.first.wpilibj.buttons.Trigger;
import org.usfirst.frc.team554.robot.Robot;
import edu.wpi.first.wpilibj.Joystick;

public class BeaterBarShootTrigger extends Trigger {
    
	private Joystick joy;
	
	public BeaterBarShootTrigger(Joystick joy) {
		this.joy = joy;
	}	
    
    public boolean get() {
    	return( joy.getRawButton(6)  && Robot.arm.isShootable());
    }
}
