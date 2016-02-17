package org.usfirst.frc.team554.robot.triggers;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Trigger;

public class BeaterBarCollectTrigger extends Trigger {
    
	private Joystick joy;
	
	public BeaterBarCollectTrigger(Joystick joy) {
		this.joy = joy;
	}	
    
    public boolean get() {
    	return( joy.getZ() >= 0.1);
    }
}
