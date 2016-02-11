package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrain_ToDistance extends Command {
	private double MoveDistance;
	private double MoveSpeed;
	private double CurrentDistance;
	private final double initialDistance; //This is used for an eventual rework of the isFinished method

    public DriveTrain_ToDistance(double MoveDistance,double MoveSpeed) {
        // Use requires() here to declare subsystem dependencies
        requires(Robot.driveTrain);
        this.MoveDistance = MoveDistance;
        this.MoveSpeed = MoveSpeed;
        initialDistance = Robot.driveTrain.getDistance();
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.drivemanual(MoveSpeed,MoveSpeed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	CurrentDistance = Robot.driveTrain.getDistance();
        return ((MoveSpeed > 0 && CurrentDistance >= MoveDistance) ||
        	   (MoveSpeed < 0 && CurrentDistance <= MoveDistance));
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.drivemanual(0.,0.);
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
