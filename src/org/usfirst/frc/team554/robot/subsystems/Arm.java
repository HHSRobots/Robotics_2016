package org.usfirst.frc.team554.robot.subsystems;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

public class Arm extends Subsystem {
	private SpeedController armMotorLeft; 
	private SpeedController armMotorRight;
	private Encoder armMotorLeftEncoder, armMotorRightEncoder;
	
	// Backwards is spinning toward robot while forward is spinning away from robot
	private double armOuterLimit, armInnerLimit, armShootableLimit;
	
	public Arm() {
		super();
		armMotorLeft = new Talon(7); 
		armMotorRight = new Talon(6); 
		
		// according to andymark.com, http://www.andymark.com/Gearmotor-p/am-2924.htm , because the encoder is on the back
		// 		of the motor, the encoder will return opposite what it is expected to. They recommend flipping it in hardware
		// 		which Luke (from the wiring diagram) said they are currently not, or they should be flipped in software.
		// 		so we could just switch the order that they are initialized in like new Encoder(7,6) instead.
		
		armMotorLeftEncoder = new Encoder(6,7);
		armMotorRightEncoder = new Encoder(8,9);
		
		// total circumfrance of the tip of the arm is 94.2477796 in
		//total pulses per rev is 1316
		//the arm can move about 200 deg 
		
		
		// distance per pulse could be .07161685 in.( distance the tip of the arm moves) with a max of about 52.3598776 in
		//							 or 1 pulse with a max of about 730
		// 							 or .27355623 degrees (360 degrees = 1316 pulse readout)
		
		armMotorRightEncoder.setDistancePerPulse(360./1316); 
    	armMotorLeftEncoder.setDistancePerPulse(360./1316);
		}
	
	
	public void armStop(){
    	armMotorLeft.set(0.);
    	armMotorRight.set(0.);
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
	public boolean isShootable()
	{
		return (getRightEncoderDistance() <= armShootableLimit) && (getLeftEncoderDistance() <= armShootableLimit);
	}
	
	
	public double getLeftEncoderDistance(){
		// Will return positive A and B channels reversed on inputs
		return armMotorLeftEncoder.getDistance();
	}
	public double getRightEncoderDistance(){
		// will retun positive
		return armMotorRightEncoder.getDistance();
	}
	public double getOuterLimit()
	{
		return armOuterLimit;
	}
	public double getShootableLimit()
	{
		return armShootableLimit;
	}
	public double getInnerLimit()
	{
		return armInnerLimit;
	}
	
	protected void initDefaultCommand() {
        // Set the default command for a subsystem here.
	}
	
	public double getArmAngle() {
		 return ((armMotorLeftEncoder.getDistance()+armMotorRightEncoder.getDistance())/2.);
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
	public void setShootableLimit(double limit)
	{
		armShootableLimit = limit;
	}
	
	public void log(){
		
		SmartDashboard.putNumber("Right Encoder Distance", getRightEncoderDistance());
		SmartDashboard.putNumber("Left Encoder Distance", getLeftEncoderDistance());
		SmartDashboard.putNumber("arm movement percentage", this.getArmAngle()/armOuterLimit);
		SmartDashboard.putBoolean("is it shootable?", this.isShootable());
		
	}

}
