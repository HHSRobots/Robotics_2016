package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	The distance we need to move the robot 133->135 inches give or take a few inches for the angle of the ramp
 */
public class AutonomousProgram004 extends CommandGroup {
     //Select equal the autonomous selector

    public  AutonomousProgram004() {
    	// Low Bar pass through, rotate, move to distance and shoot
    	addSequential(new ArmMoveToAngle(180.,0.8)); // move arm to aid going thru low bar
    	addSequential(new DriveTrain_ToDistance(180.,1.0));
     	addSequential(new DriveTrain_RotateRight(57.));
      	addSequential(new ArmMoveToAngle(120.,0.8));
      	addSequential(new DriveTrain_ToDistance(48.,1.0));
      	addSequential(new BeaterBars_Shoot());
    }
}
