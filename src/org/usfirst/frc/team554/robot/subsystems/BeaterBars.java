package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Talon;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class BeaterBars extends Subsystem {
    
    // Put methods for controlling this subsystem
    // here. Call these from Commands.
    
private SpeedController OuterBeaterBarMotor;
private SpeedController InnerBeaterBarMotor;
private double collectMotorSpeed, shootMotorSpeed, passMotorSpeed;
private double beaterBarType;
private Boolean beaterBarShoot, beaterBarPass, beaterBarCollect, beaterBarStop;
	
	public BeaterBars(){
		super();
		OuterBeaterBarMotor = new Talon(5);
		InnerBeaterBarMotor = new Talon(4);
		beaterBarType = 0.0;
		beaterBarPass = false;
		beaterBarShoot = false;
		beaterBarStop = true;
		beaterBarCollect = false;
	}
	
	public void setCollectMotorSpeed(double limit){
		collectMotorSpeed = limit;
	}
	public void setShootMotorSpeed(double limit){
		shootMotorSpeed = limit;
	}
	public void setPassMotorSpeed(double limit){
		passMotorSpeed = limit;
	}
	
	
	public void beaterCollect(){
		OuterBeaterBarMotor.set(collectMotorSpeed);
		InnerBeaterBarMotor.set(collectMotorSpeed);
		beaterBarPass = false;
		beaterBarShoot = false;
		beaterBarStop = false;
		beaterBarCollect = true;
		beaterBarType = 1.0;
	}
	
	//This is for passing and shooting. We are going to be automating the arm movement (somewhat)
	//If the beater bar is too low we are going to move it up to a distance that allows the ball to be shot without having interference from the beaterbars
	
	public void beaterShoot(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(shootMotorSpeed);
		beaterBarPass = false;
		beaterBarShoot = true;
		beaterBarStop = false;
		beaterBarCollect = false;
		beaterBarType = 3.0;
	}
	public void beaterPass(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(passMotorSpeed);
		beaterBarPass = true;
		beaterBarShoot = false;
		beaterBarStop = false;
		beaterBarCollect = false;
		beaterBarType = 2.0;
	}
	public void stop(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(0);
		beaterBarPass = false;
		beaterBarShoot = false;
		beaterBarStop = true;
		beaterBarCollect = false;
		beaterBarType = 0.0;
	}
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
     }
    
    public void log(){
    	SmartDashboard.putBoolean("Beater Bar Stop", beaterBarStop);
    	SmartDashboard.putBoolean("Beater Bar Collect", beaterBarCollect);
    	SmartDashboard.putBoolean("Beater Bar Pass", beaterBarPass);
    	SmartDashboard.putBoolean("Beater Bar Shoot", beaterBarShoot);
    	SmartDashboard.putNumber("BeaterBar movement type", beaterBarType);
    }
}

