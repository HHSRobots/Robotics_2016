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
    private double distanceToSlowDown;
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
    	distanceToSlowDown = 20.0;
    	
    	left_encoder.setDistancePerPulse(0.0486947*127/134);//correction value based on test.
    	right_encoder.setDistancePerPulse(0.0486947*127/134);
    	
    	gyro = new AnalogGyro(0);
    	wasHeld=false;
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrain_JoyStickDrive());
    }
    
    public void driveAutomaticTurn( double angle){
    	
    	// These things do the exact same thing. B is just easier to read.
    	
    	//A
    	double z = (angle > 0 ) ? 1.0 : -1.0 ;
    	
    	//B
    	if (angle > 0){
    		z = 1.0;
    	}
    	else {
    		z = -1.0;
    	}
    	
    	
    	
  
    	
    	drive.arcadeDrive(0,z);
    }

    
    public void driveAutomaticStraight(double speed, double distance)//method to be used by the robot when it is moving in a straight line in autonomous
    {//remember to put something that resets the gyro when the driving is first begun in the autonomous code list.
    	double z = 0;
    	double Kp = -0.025;
    	double angle = gyro.getAngle();
    	z = Kp * angle;
    	if ( Math.abs( speed) > 0.4 ){
    		if ( (distance - getDistance()) <= distanceToSlowDown && (distance - getDistance()) > 0 ){
    			speed = ((speed - 0.4) / 20 * (distance - getDistance()) + 0.4);
    		}
    		else if ( (getDistance()- distance ) <= distanceToSlowDown && (getDistance()- distance) > 0 ){
    			speed =  -1*((speed - 0.4) / 20 * (distance - getDistance()) + 0.4);
    		}
    		
    	}	
    	
    	drive.arcadeDrive(-speed, z);
    }
    
    public void drivemanual(double left, double right){
    	left_wheels.set(left);
    	right_wheels.set(right);
    }
    
    public void drive(Joystick joystick_driver){
    	double z=0; // amount of rotation applied to robot -128 through 128, 0 is none
    	double Kp = -0.025; // constant that gives magnitude of rotation correction (recomended is 0.03)
    	
    	if (joystick_driver.getRawButton(2) == true ){	
    		
    		double c = 0.75; // C is the center of the logistic curve
    		double k = (Math.log(100)-1) / c; // this shouldn't be changed unless my math is wrong
    		if ( Math.abs(joystick_driver.getY()) >= 0.1){
    			
	    		if (joystick_driver.getZ() > 0 ){
	    			z = 1 / ( 1 + Math.pow(Math.E, - k *( joystick_driver.getZ() - c)));
	    		}
	    		else if (joystick_driver.getZ() < 0){
	    			z = - 1 / ( 1 + Math.pow(Math.E, - k *(-c - joystick_driver.getZ())));
	    		}
	    		else {
	    			z = 0.0;
	    		}
    		}
    		else{
    			z = joystick_driver.getZ();
    		}
    		
    		
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
    
    public double getGyroAngle(){
    	return this.gyro.getAngle();
    }
    
    
    public void gearUp()
    {
    	gearShiftSolenoid.set(true);
    }
    
    public void gearDown()
    {
    	gearShiftSolenoid.set(false);
    }
    
    public Boolean getGear(){
    	return gearShiftSolenoid.get();
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
