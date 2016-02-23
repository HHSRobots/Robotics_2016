package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrain_RotateRight extends Command {
    double TargetAngle;
    double CurrentAngle;
    
    public DriveTrain_RotateRight(double target_angle) {
    	this.TargetAngle = target_angle;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.CurrentAngle = Robot.driveTrain.getGyroAngle();
    	Robot.driveTrain.drivemanual(-1., .5);		
    			
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (this.TargetAngle < this.CurrentAngle);
       }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drivemanual(0, 0);
    	Robot.driveTrain.resetGyro();
    	Robot.driveTrain.resetEncoder();
   }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
