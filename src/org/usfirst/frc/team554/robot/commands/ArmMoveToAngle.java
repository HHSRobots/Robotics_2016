package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class ArmMoveToAngle extends Command {

	private double TargetAngle;
	private double MoveSpeed;
	private boolean movedone;
	private double MoveDir;
	private double CurrentAngle;
	private final double slope;

    public ArmMoveToAngle(double TargetAngle,double MoveSpeed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.arm);
    	this.TargetAngle = TargetAngle;
    	this.MoveSpeed = MoveSpeed;
    	this.slope = -1.0 * (( this.MoveSpeed - .4) / 30);
    	}

    // Called just before this Command runs the first time
    protected void initialize() {
    	this.movedone = false;
    	if (Robot.arm.getArmAngle() < this.TargetAngle) {
    	    this.MoveDir = -1;  // move out
            }
    	else {
    		this.MoveDir =  1.;  // move in
			}
    	}

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	double currentMoveSpeed = this.MoveSpeed;
    	this.CurrentAngle = Robot.arm.getArmAngle();
    	if ((this.CurrentAngle > (this.TargetAngle - 30)) && this.MoveDir < 0 ) {
        	currentMoveSpeed = this.slope * (this.CurrentAngle - this.TargetAngle) + .4;
        	}
    	if ( ((MoveDir == -1.) && (this.CurrentAngle < this.TargetAngle)) ||
    		 ((MoveDir == 1.) && (this.CurrentAngle > this.TargetAngle)) )	{
    		Robot.arm.moveArmAtSpeed(currentMoveSpeed * this.MoveDir);
    		}
    	else {
    		Robot.arm.armStop();
    		this.movedone = true;
    		}
    		
    	}

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.movedone;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
