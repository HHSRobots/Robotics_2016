package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
//                   												Portcullis

public class AutonomousProgram002 extends CommandGroup {
	
	int select = 1;
    public  AutonomousProgram002() {
    	
    	addParallel(new Arm_MoveArmToDistance(Robot.arm.getOuterLimit())); // move arm to floor
    	addSequential(new DriveTrain_ToDistance(20,.6));// drive to place arm under portcullis
    	addParallel(new DriveTrain_ToDistance(5,.6)); // start moving robot forward
    	addSequential(new Arm_MoveArmToDistance(545, .6 )); // raise arm to life portcullis gate
    	addSequential(new DriveTrain_ToDistance(20,1));// robot books it and makes it through portcullis
    	
    	
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
