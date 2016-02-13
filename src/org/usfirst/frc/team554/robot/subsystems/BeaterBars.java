package org.usfirst.frc.team554.robot.subsystems;

import org.usfirst.frc.team554.robot.commands.BeaterBars_CollectAndShoot;
import edu.wpi.first.wpilibj.Joystick;
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
	
	public BeaterBars(){
		super();
		OuterBeaterBarMotor = new Talon(5);
		InnerBeaterBarMotor = new Talon(4);
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
	
	public void moveBeaterJoystick(Joystick operator_joystick)
	{
		if(operator_joystick.getZ() >= 0.1 )
		{
			beaterCollect();
			
		}
		else if ( operator_joystick.getZ() <= -0.1){
			beaterShoot();
			
			//TODO Check code/ limits for arm.isShootable before implementing.
			/*
			if(Robot.arm.isShootable())
			{
				beaterShoot();
			}
			else
			{
				Robot.arm.moveArmToDistance(Robot.arm.getShootableLimit());//maybe this will work? Crossed fingers I guess
				stop();
			}
			*/
		}
		
		else {
			stop();
		}
	}
	
	public void beaterCollect(){
		
		OuterBeaterBarMotor.set(collectMotorSpeed);
		InnerBeaterBarMotor.set(collectMotorSpeed);
	}
	
	
	//This is for passing and shooting. We are going to be automating the arm movement (somewhat)
	//If the beater bar is too low we are going to move it up to a distance that allows the ball to be shot without having interference from the beaterbars
	
	public void beaterShoot(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(shootMotorSpeed);
	}
	public void beaterPass(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(passMotorSpeed);
		//TODO This will not work as written below. fix before running
		/*
		if(Robot.arm.isShootable())
		{
			InnerBeaterBarMotor.set(0.5);
		}
		else
		{
			
			Robot.arm.moveArmAtSpeed(1);
		}
		*/
		
	}
	
	

	public void stop(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(0);
	}
	
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BeaterBars_CollectAndShoot());
    }
    
    public void log(){
    	SmartDashboard.putNumber("InnerBeaterBarMotor",InnerBeaterBarMotor.get());
    	SmartDashboard.putNumber("OuterBeaterBarMotor",OuterBeaterBarMotor.get());
    }
}

