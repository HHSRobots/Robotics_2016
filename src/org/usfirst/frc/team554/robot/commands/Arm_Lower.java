package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

import org.usfirst.frc.team554.robot.Robot;

/**
 *
 */
public class Arm_Lower extends Command {

    public Arm_Lower() {
    	requires(Robot.arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	// In the resetEncoder method, it will only reset once, not once per command
    	Robot.arm.resetEncoder();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	//assuming that the right motor is moving clockwise:
    	Robot.arm.armMove(1);
    	
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.didHitLowerLimit();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.arm.armStop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
