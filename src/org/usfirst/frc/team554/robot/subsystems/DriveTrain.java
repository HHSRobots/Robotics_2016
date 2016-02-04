package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.AnalogGyro;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;

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
    
    public DriveTrain(){
    	super();
    	left_wheels = new Talon(9);
    	right_wheels = new Talon(8);
    	drive = new RobotDrive(left_wheels, right_wheels);
    	left_encoder = new Encoder(2,3);
    	right_encoder = new Encoder(4,5);
    	gearShiftSolenoid = new Solenoid(0);//is this where it will go? who knows!!!!!!!!
    	
    	left_encoder.setDistancePerPulse(0.01745);
    	right_encoder.setDistancePerPulse(0.01745);
    	
    	gyro = new AnalogGyro(0);
    }
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
    	setDefaultCommand(new DriveTrain_JoyStickDrive());
    }
    
    public void drivemanual(double left, double right){
    	drive.tankDrive(left,right);
    }
    
    public void drive(Joystick joystick_driver){
    	double turn = -joystick_driver.getZ()*3/4;
    	if (!joystick_driver.getRawButton(1))
    	{
    		turn=gyro.getRate();//instead of turn being dictated by the person, the turn should be the opposite how it's turning.
    		//make sure that this actually ensures that it will turn the opposite way it's moving. It may spin uncontrollably without proper set up.
    	}
    	drive.arcadeDrive(-joystick_driver.getY(),turn);
    }
    
    
    public void reset(){
    	left_encoder.reset();
    	right_encoder.reset();
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
    }
}
