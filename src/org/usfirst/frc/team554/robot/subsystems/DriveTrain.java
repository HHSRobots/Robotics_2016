package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.VictorSP;

import org.usfirst.frc.team554.robot.commands.DriveTrain_JoyStickDrive;
import edu.wpi.first.wpilibj.Solenoid;

/**
 *
 */
public class DriveTrain extends Subsystem {
    private SpeedController left_wheels, right_wheels;
    private RobotDrive drive;
    private Encoder left_encoder, right_encoder;
    private AnalogGyro gyro;
    private Solenoid gearShiftSolenoid;
    private boolean wasHeld;
    
    public DriveTrain(){
    	super();
    	left_wheels = new VictorSP(9);
    	right_wheels = new VictorSP(8);
    	drive = new RobotDrive(left_wheels, right_wheels);
    	left_encoder = new Encoder(2,3);
    	right_encoder = new Encoder(4,5);
    	gearShiftSolenoid = new Solenoid(0);
    	
    	drive.setSafetyEnabled(false);
    	
    	left_encoder.setDistancePerPulse(0.01745);
    	right_encoder.setDistancePerPulse(0.01745);
    	
    	gyro = new AnalogGyro(0);
    	wasHeld=false;
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrain_JoyStickDrive());
    }
    
    public void drivemanual(double left, double right){
    	left_wheels.set(left);
    	right_wheels.set(right);
    }
    
    public void drive(Joystick joystick_driver){
    	double z=0; // amount of rotation applied to robot -128 through 128, 0 is none
    	double Kp = -0.025; // constant that gives magnitude of rotation correction (recomended is 0.03)
    	
    	if (joystick_driver.getRawButton(2) == true ){	
    		z = joystick_driver.getZ();
    		wasHeld = true;
    	} else if (wasHeld && Math.abs(gyro.getRate()) <= 10.0 && !joystick_driver.getRawButton(2)){
    		gyro.reset();
    		wasHeld = false;
    		double angle = gyro.getAngle();
    		z = Kp * angle;
    	} else if(!wasHeld) {
    	// inset gyro balancing code here to compensate for skew
    		double angle = gyro.getAngle();
    		z = Kp * angle;
    	}
    	
    	drive.arcadeDrive(joystick_driver.getY(),z);
    }
    
    
    public void resetEncoder(){
    	left_encoder.reset();
    	right_encoder.reset();
    }
    public void resetGyro(){
    	gyro.reset();
    }
    
    public double getDistance(){
    	
    	return (left_encoder.getDistance() + right_encoder.getDistance())/2;
    }
    
    public AnalogGyro getGyro(){
    	return this.gyro;
    }
    
    public void gearUp()
    {
    	gearShiftSolenoid.set(true);
    }
    
    public void gearDown()
    {
    	gearShiftSolenoid.set(false);
    }
    
    public void log() {
        SmartDashboard.putNumber("drive leftmove", left_encoder.getDistance());
        SmartDashboard.putNumber("drive rightmove", right_encoder.getDistance());
        SmartDashboard.putNumber("drive distance", this.getDistance());
        SmartDashboard.putBoolean("High Gear", gearShiftSolenoid.get());
        SmartDashboard.putBoolean("Low Gear", !gearShiftSolenoid.get());
        SmartDashboard.putNumber("Gyro Rate", gyro.getRate());
        
    }
}
