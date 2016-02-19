package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

public class ArmMoveWithJoystick extends Command {

	private double YValue;
	private boolean WeAreDone;
	
    public ArmMoveWithJoystick() {
    	requires(Robot.arm);
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    
    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	this.WeAreDone = false;
        this.YValue = Robot.oi.getOperator().getY();
        	if ((Robot.arm.didHitInnerLimit()==false) && (this.YValue > 0.1) ){  // move arm in
        		Robot.arm.moveArmAtSpeed(this.YValue);
         	}
        	else if ((Robot.arm.didHitOuterLimit()==false) && (this.YValue < -0.1) ){  //move arm out
        		Robot.arm.moveArmAtSpeed(this.YValue);
        	}
        	else {
        		this.WeAreDone = true;
        	}
     }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return this.WeAreDone;
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
