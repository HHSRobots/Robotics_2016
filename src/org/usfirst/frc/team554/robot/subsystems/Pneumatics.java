package org.usfirst.frc.team554.robot.subsystems;

import org.usfirst.frc.team554.robot.Robot;

import edu.wpi.first.wpilibj.Compressor;
import edu.wpi.first.wpilibj.command.Subsystem;
//import org.usfirst.frc.team554.robot.*;
/**
 *
 */
public class Pneumatics extends Subsystem {
	Compressor compressor;


	public Pneumatics() {
		if (Robot.isReal()) {
			compressor = new Compressor();
		}

	}

	/* No default command */
	public void initDefaultCommand() {}

	/* Start the compressor going. The compressor automatically starts and stops
	    as it goes above and below maximum pressure.	 */
	public void start() {
		if (Robot.isReal()) {
			compressor.start();
		}
	}
    
}

