package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *
 */
public class AutonomousProgram001 extends CommandGroup {
     //Select equal the autonomous selector
     int select = 1;
    public  AutonomousProgram001() {
    	
    	
    	// Low Bar pass through
    	
    	addSequential(new DriveTrain_ToDistance(20,.6));//// This must be changed according to the distance one must travel to cross the low bar
    	
    	addSequential(new DriveTrain_ToDistance(-1,-.6)); // This moves the robot back ****The negative value might not be correct****
    	//This is a note for Jeremy... Pudding
    }
}
