package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class BeaterBars_TimedShoot extends Command {

	private double timeValue = 5;
	
    public BeaterBars_TimedShoot() {
    	requires(Robot.beaterBars);
    	
    	setTimeout(this.timeValue);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.beaterBars.beaterShoot();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return isTimedOut();
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
