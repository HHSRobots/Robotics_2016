package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	The distance we need to move the robot 133->135 inches give or take a few inches for the angle of the ramp
 */
public class AutonomousProgram001 extends CommandGroup {
     //Select equal the autonomous selector

    public  AutonomousProgram001() {
    	// Low Bar pass through
    	addSequential(new ArmMoveToAngle(180.,0.8)); // move arm to aid going thru low bar
    	addParallel(new DriveTrain_GyroReset());
    	addSequential(new DriveTrain_ToDistance(134,1.0));// This must be changed according to the distance one must travel to cross the low bar
    	//addSequential(new DriveTrain_ToDistance(124,.6)); // This should move the robot backward ****The negative value might not be correct****
    	
    	//This is a note for Jeremy... Pudding made of Pudding!!!!!
    	//I'll pudd you in your place, Kandaharrrrrrrrrrr
    	//sorry
    	//*you're
    }
}
