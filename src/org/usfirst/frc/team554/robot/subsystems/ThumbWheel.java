package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class ThumbWheel extends Subsystem {
	private DigitalInput bit0, bit1, bit2, bit3;
	
	public ThumbWheel() {
		super();
		
		bit0 = new DigitalInput(12);
		bit1 = new DigitalInput(13);
		bit2 = new DigitalInput(14);
		bit3 = new DigitalInput(15);
	}

	public int getThumbWheelval() {
		int twvalue;
		
		twvalue = 0;
		if(bit0.get()==false) twvalue += 1;
		if(bit1.get()==false) twvalue += 2;
		if(bit2.get()==false) twvalue += 4;
		if(bit3.get()==false) twvalue += 8;
		return twvalue;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void log(){
    	SmartDashboard.putNumber("ThumbWheel",  getThumbWheelval());
    }
}


