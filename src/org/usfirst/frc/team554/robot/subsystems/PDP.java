package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

//
public class PDP extends Subsystem {
    
    private PowerDistributionPanel pdp;
    
    public PDP(){
    	super();
    	pdp = new PowerDistributionPanel();
    	
    }

    double GetCurrent(int channel){
    	return pdp.getCurrent(channel);
    }
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    public void log() {
    	SmartDashboard.putNumber("Lefts wheels power output",pdp.getCurrent(8));
        SmartDashboard.putNumber("Right Wheels power output",pdp.getCurrent(9));
    }
}

