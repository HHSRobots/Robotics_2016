package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team554.robot.Robot;


/**
 *
 */
public class BeaterBars_Pass extends Command {

    public BeaterBars_Pass() {
    	requires(Robot.beaterBars);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.beaterBars.beaterPass();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.beaterBars.stop();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
