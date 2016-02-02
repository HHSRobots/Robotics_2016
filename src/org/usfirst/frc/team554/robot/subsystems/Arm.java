package org.usfirst.frc.team554.robot.subsystems;

import org.usfirst.frc.team554.robot.commands.Arm_Move;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
	private SpeedController armMotorLeft; 
	private SpeedController armMotorRight;
	private Encoder armMotorLeftEncoder, armMotorRightEncoder;
	
	
	
	// Backwards is spinning toward robot while forward is spinning away from robot
	private double armUpperLimit, armLowerLimit;
	
	
	public Arm() {
		super();
		
		
		armMotorLeft = new Talon(0); // THIS STILL NEEDS TO BE SET
		armMotorRight = new Talon(1); // SET 
		armMotorLeftEncoder = new Encoder(7,8); // SET
		armMotorRightEncoder = new Encoder(9,10);// SET
		
		armMotorRightEncoder.setDistancePerPulse(1./256.); //SET
    	armMotorLeftEncoder.setDistancePerPulse(1./256.); //SET
		
		
		armUpperLimit = 0.0;
		armLowerLimit = 1.0; /// This still needs to be set. 
		
		
	}
	
	
	public void armStop(){
    	armMotorLeft.set(0);
    	armMotorRight.set(0);
    }
	
	
	public void armMove(Joystick stick){
		// Remember! one of these motors will be negative. This must be fixed when updated.
		if (Math.abs(stick.getZ()) > 0.1)
		{
			armMotorLeft.set(stick.getZ());//value still up for debate
			armMotorRight.set(-stick.getZ());
		}
		
		else
		{
			armMotorLeft.set(0);
			armMotorRight.set(0);
		}
		
	}
	public void resetEncoder(){
			armMotorLeftEncoder.reset();
			armMotorRightEncoder.reset();
	
    }
	
	public boolean didHitLowerLimit(){
		return armMotorLeftEncoder.getDistance() >= armLowerLimit || armMotorRightEncoder.getDistance() >= armLowerLimit;
	}
	public boolean didHitUpperLimit(){
		return armMotorLeftEncoder.getDistance() <= armUpperLimit || armMotorRightEncoder.getDistance() <= armUpperLimit;
	}
	
	
	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		
		setDefaultCommand( new Arm_Move());
	}
	
	public void log(){
		SmartDashboard.putNumber("Current Percentage of Movement", (armMotorLeftEncoder.getDistance() / armLowerLimit) * 100  );
		
	}

}
