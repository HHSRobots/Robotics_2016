package org.usfirst.frc.team554.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;


public class ArmMoveWithJoystickTrigger extends Trigger {
	private Joystick joy;
	
	public ArmMoveWithJoystickTrigger(Joystick joy) {
		this.joy = joy;
	}	
    
    public boolean get() {
    	return( (joy.getY() <= -0.1) || (joy.getY() >= 0.1));
    }
}
