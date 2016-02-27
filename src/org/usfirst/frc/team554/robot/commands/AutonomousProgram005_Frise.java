package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.CommandGroup;

/**
 * Frise Auto Program
 */
public class AutonomousProgram005_Frise extends CommandGroup {

    public  AutonomousProgram005_Frise() {
    	
    	addSequential(new DriveTrain_ToDistance(50.0,1.));//Move Robot to Frise
    	addSequential(new ArmMoveToAngle(Robot.arm.getOuterLimit(),.6));//Lower arm to lower Frise
    	addSequential(new DriveTrain_ToDistance(60.0,1.));//Drive forward to get wheels on Frise
    	addParallel(new ArmMoveToAngle(Robot.arm.getInnerLimit(), .6 ));//Raise arm to move out of way
    	addSequential(new DriveTrain_ToDistance(134,1.)); // Move Robot through defense

    }
}
