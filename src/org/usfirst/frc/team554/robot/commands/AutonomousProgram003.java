package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousProgram003 extends CommandGroup {
    // move forward w/o arm
    public  AutonomousProgram003() {
        
    	addParallel(new DriveTrain_GyroReset());
    	addSequential(new DriveTrain_ToDistance(146,1.0));
    }
}
