package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Arm_Move extends Command {

    public Arm_Move() {
    	requires(Robot.arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    //You will have a bad time...
    
    
    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.arm.resetEncoder();
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    		Robot.arm.armMove(Robot.oi.getOperator());
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
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
