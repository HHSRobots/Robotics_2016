package org.usfirst.frc.team554.robot.commands;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class Arm_MoveArmToDistance extends Command {

	protected double distance;
	private double speed;
    public Arm_MoveArmToDistance(double pulsesToMove) {
    	distance = pulsesToMove;
    	requires(Robot.arm);
    	speed = ((distance - Robot.arm.getRightEncoderDistance()) > 0) ? 1 : -1;
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    }
    public Arm_MoveArmToDistance(double pulsesToMove, double geschwindigkeit){
    	requires(Robot.arm);
    	distance = pulsesToMove;
    	speed = geschwindigkeit;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.arm.moveArmAtSpeed(speed);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return Robot.arm.armIsAtDistance(distance);
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
