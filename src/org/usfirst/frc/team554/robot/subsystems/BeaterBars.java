package org.usfirst.frc.team554.robot.subsystems;

import org.usfirst.frc.team554.robot.Robot;
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
private Robot robot;
	
	public BeaterBars(Robot r){
		super();
		OuterBeaterBarMotor = new Talon(5);
		InnerBeaterBarMotor = new Talon(4);
		robot=r;
	}
	
	
	
	
	public void moveBeaterJoystick(Joystick operator_joystick)
	{
		if(operator_joystick.getZ() >= 0.1 )
		{
			beaterCollect();
			
		}
		else if ( operator_joystick.getZ() <= -0.1){
			if(robot.getArm().isShootable())
			{
				beaterShoot();
			}
			else
			{
				robot.getArm().moveArmToDistance(robot.getArm().getShootableLimit());//maybe this will work? Crossed fingers I guess
				stop();
			}
		}
		
		else {
			stop();
		}
	}
	
	public void beaterCollect(){
		
		OuterBeaterBarMotor.set(-0.5);
		InnerBeaterBarMotor.set(-0.5);
	}
	
	
	//This is for passing and shooting. We are going to be automating the arm movement (somewhat)
	//If the beater bar is too low we are going to move it up to a distance that allows the ball to be shot without having interference from the beaterbars
	
	public void beaterShoot(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(1);
	}
	public void beaterPass(){
		OuterBeaterBarMotor.set(0);
		if(robot.getArm().isShootable())
		{
			InnerBeaterBarMotor.set(0.5);
		}
		else
		{
			robot.getArm().moveArmAtSpeed(1);
		}
		
	}
	
	
//	public void moveBeaterButton(Joystick operator_joystick)
//	{
//		//TODO find out how many speeds they want, the crimeny buggers
//	}
	
	public void stop(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(0);
	}
	
	
	
	
	
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        setDefaultCommand(new BeaterBars_CollectAndShoot());
    }
    
    public void log(){
    	SmartDashboard.putNumber("Speed",10000);
    }
}

