package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
//                   												Portcullis

public class AutonomousProgram002 extends CommandGroup {
	
    public  AutonomousProgram002() {
    	
    	addParallel(new ArmMoveToAngle(Robot.arm.getOuterLimit(),.6)); // move arm to floor
    	addSequential(new DriveTrain_ToDistance(42.,.6));// drive to place arm under portcullis
    	addSequential(new TimeDelay(2.));
    	addParallel(new ArmMoveToAngle(180., .4 )); // raise arm to life portcullis gate
    	addSequential(new DriveTrain_ToDistance(134,1.)); // start moving robot forward
    	
    	
    	//
    	
        // Add Commands here:
        // e.g. addSequential(new Command1());
        //      addSequential(new Command2());
        // these will run in order.

        // To run multiple commands at the same time,
        // use addParallel()
        // e.g. addParallel(new Command1());
        //      addSequential(new Command2());
        // Command1 and Command2 will run in parallel.

        // A command group will require all of the subsystems that each member
        // would require.
        // e.g. if Command1 requires chassis, and Command2 requires arm,
        // a CommandGroup containing them would require both the chassis and the
        // arm.
    	
    }
}
