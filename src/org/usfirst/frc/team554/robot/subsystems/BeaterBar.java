package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;


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
	
	
	public void end(){
		// BeaterBar_Rotate.end();
	}
	
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
}

