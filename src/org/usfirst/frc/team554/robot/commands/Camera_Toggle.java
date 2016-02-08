package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Camera_Toggle extends Command {

    public Camera_Toggle() {
    	requires(Robot.camera);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.camera.cameraToggle();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.camera.cameraToggle();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return  Robot.camera.cameraChangeComplete();
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
