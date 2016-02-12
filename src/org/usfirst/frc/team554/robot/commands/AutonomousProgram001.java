package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 *	The distance we need to move the robot 133->135 inches give or take a few inches for the angle of the ramp
 */
public class AutonomousProgram001 extends CommandGroup {
     //Select equal the autonomous selector
     int select = 1;
    public  AutonomousProgram001() {
    	
    	
    	// Low Bar pass through
    	
    	addSequential(new DriveTrain_ToDistance(135,.6));//// This must be changed according to the distance one must travel to cross the low bar
    	
    	addSequential(new DriveTrain_ToDistance(8,-.6)); // This should move the robot backward ****The negative value might not be correct****
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	//This is a note for Jeremy... Pudding made of Pudding!!!!!
    }
}
