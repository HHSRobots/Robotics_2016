package org.usfirst.frc.team554.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc.team554.robot.*;

/**
 *
 */
public class DriveTrain_JoyStickDrive extends Command {

    public DriveTrain_JoyStickDrive() {
    	requires(Robot.driveTrain);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drive(Robot.oi.getDriver()); // aww
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drivemanual(0.0,0.0);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
