package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;

public class Arm extends Subsystem {
	private SpeedController armMotorLeft; 
	private SpeedController armMotorRight;
	private Encoder armMotorLeftEncoder, armMotorRightEncoder;
	
	
	
	// Backwards is spinning toward robot while forward is spinning away from robot
	private double armBackwardLimit, armForwardLimit;
	
	
	public Arm() {
		super();
		
		
		armMotorLeft = new Talon(1); // THIS STILL NEEDS TO BE SET
		armMotorRight = new Talon(2); // SET 
		armMotorLeftEncoder = new Encoder(7,8); // SET
		armMotorRightEncoder = new Encoder(9,10);// SET
		
		armMotorRightEncoder.setDistancePerPulse(1./256.); //SET
    	armMotorLeftEncoder.setDistancePerPulse(1./256.); //SET
		
		
		armBackwardLimit = 0.0;
		armForwardLimit = 1.0; /// This still needs to be set. 
		
	}
	
	
	public void armStop(){
    	armMotorLeft.set(0);
    	armMotorRight.set(0);
    }
	
	
	public void armMove(double speed){
		// Remember! one of these motors will be negative. This must be fixed when updated.
		boolean ifweHaventconfigedyet = true;
		if( ifweHaventconfigedyet)
			return;
		armMotorLeft.set(speed);
		armMotorRight.set(speed);
		
	}
	public void resetEncoder(){
		armMotorLeftEncoder.reset();
		armMotorRightEncoder.reset();
    }
	
	public boolean didHitForwardLimit(){
		return armMotorLeftEncoder.getDistance() >= armForwardLimit || armMotorRightEncoder.getDistance() >= armForwardLimit;
	}
	public boolean didHitBackwardLimit(){
		return armMotorLeftEncoder.getDistance() <= armBackwardLimit || armMotorRightEncoder.getDistance() <= armBackwardLimit;
	}
	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		
	}
	
	public void log(){
		
	}

}
