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
	private double armOuterLimit, armInnerLimit;
	
	
	public Arm() {
		super();
		
		
		armMotorLeft = new Talon(7); 
		armMotorRight = new Talon(6); 
		armMotorLeftEncoder = new Encoder(6,7);
		armMotorRightEncoder = new Encoder(8,9);
		
		armMotorRightEncoder.setDistancePerPulse(1./256.); 
    	armMotorLeftEncoder.setDistancePerPulse(1./256.); 
		
		
		armOuterLimit = 0.0;
		armInnerLimit = 1.0; /// This still needs to be set. 
		
		
	}
	
	
	public void armStop(){
    	armMotorLeft.set(0);
    	armMotorRight.set(0);
    }
	
	
	public void armMove(Joystick stick){
		// Remember! one of these motors will be negative. This must be fixed when updated.
		//SmartDashboard.putNumber("Throttle Value", stick.getThrottle());
    	
    	if (didHitInnerLimit() && stick.getThrottle() > 0.1 ){
    		armMotorLeft.set(-stick.getThrottle());
    		armMotorRight.set(stick.getThrottle());	
    	}
    	else if (didHitOuterLimit() && stick.getThrottle() < -0.1 ){
    		armMotorLeft.set(-stick.getThrottle());
    		armMotorRight.set(stick.getThrottle());
    	}
    	else {
    		armStop();
    	}
		
	}
	public void resetEncoder(){
			armMotorLeftEncoder.reset();
			armMotorRightEncoder.reset();
	
    }
	
	public boolean didHitInnerLimit(){
		return armMotorLeftEncoder.getDistance() <= armInnerLimit || armMotorRightEncoder.getDistance() <= armInnerLimit;
	}
	public boolean didHitOuterLimit(){
		return armMotorLeftEncoder.getDistance() >= armOuterLimit || armMotorRightEncoder.getDistance() >= armOuterLimit;
	}
	
	
	public double getLeftEncoderDistance(){
		// Will return negative
		return armMotorLeftEncoder.getDistance();
	}
	public double getRightEncoderDistance(){
		// will retun positive
		return armMotorRightEncoder.getDistance();
	}
	
	
	
	protected void initDefaultCommand() {
		// TODO Auto-generated method stub
		setDefaultCommand( new Arm_Move());
		
	}
	
	public boolean armIsAtDistance(double distance){
		return getRightEncoderDistance() == distance;
	}
	
	
	public void moveArmToDistance(double distance){
		double speed;
		if (getRightEncoderDistance() < distance){
			speed = 1;
		}
		else if (getRightEncoderDistance() > distance){
			speed = -1;
		}
		else{
			speed = 0;
		}
		
		moveArmAtSpeed(speed);
	}
	
	
	public void moveArmAtSpeed(double speed){
		armMotorLeft.set(-speed);
		armMotorRight.set(speed);
	}
	
	public void setOuterLimit(double limit){
		armOuterLimit = limit;
	}
	public void setInnerLimit(double limit){
		armInnerLimit = limit;
	}
	
	public void log(){
		
		SmartDashboard.putNumber("Current Percentage of Movement", (armMotorLeftEncoder.getDistance() / armInnerLimit) * 100  );
		SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
		SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
		
	}

}
