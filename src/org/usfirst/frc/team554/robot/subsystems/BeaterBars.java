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
	
	public BeaterBars(){
		super();
		OuterBeaterBarMotor = new Talon(5);
		InnerBeaterBarMotor = new Talon(4);
	}
	
	
	
	
	public void moveBeaterJoystick(Joystick operator_joystick)
	{
		if(operator_joystick.getZ() >= 0.1 )
		{
			beaterCollect();
			
		}
		else if ( operator_joystick.getZ() <= -0.1 ){
			beaterShoot();
		}
		
		else {
			stop();
		}
	}
	
	public void beaterCollect(){
		
		OuterBeaterBarMotor.set(-0.5);
		InnerBeaterBarMotor.set(-0.5);
	}
	
	public void beaterShoot(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(1);
	}
	public void beaterPass(){
		OuterBeaterBarMotor.set(0);
		InnerBeaterBarMotor.set(0.5);
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

