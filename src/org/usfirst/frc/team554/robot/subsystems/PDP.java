package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.PowerDistributionPanel;
import edu.wpi.first.wpilibj.command.Subsystem;
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
        SmartDashboard.putNumber("ElevMtr1",pdp.getCurrent(10));
        SmartDashboard.putNumber("ElevMtr2",pdp.getCurrent(11));
    }
}

