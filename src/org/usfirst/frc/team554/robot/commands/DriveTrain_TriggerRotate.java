package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTrain_TriggerRotate extends Command {

	private double moveAngle;
	private double currentAngle;
	private Boolean startingGear;
	
    public DriveTrain_TriggerRotate(double z) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	moveAngle = z;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	Robot.driveTrain.resetGyro();
    	startingGear = Robot.driveTrain.getGear();
    	Robot.driveTrain.gearUp();
    	
    	
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.driveAutomaticTurn(moveAngle);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        
    	//this.CurrentDistance = Robot.driveTrain.getDistance();
    	//return ((MoveSpeed > 0 && (this.CurrentDistance) >= this.MoveDistance) ||
    		//	(MoveSpeed < 0 && (this.CurrentDistance) <= this.MoveDistance));
    	currentAngle = Robot.driveTrain.getGyroAngle();
    	
    	return ((moveAngle > 0  && currentAngle >= moveAngle) || 
    			( moveAngle < 0 && currentAngle <= moveAngle));
    	
    	
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.resetGyro();
    	if (! startingGear){
    		Robot.driveTrain.gearDown();
    	}
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
