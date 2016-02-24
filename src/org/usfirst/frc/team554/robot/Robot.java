
package org.usfirst.frc.team554.robot;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.Preferences;

import org.usfirst.frc.team554.robot.commands.AutonomousProgram001;
import org.usfirst.frc.team554.robot.commands.AutonomousProgram002_Portcullis;
import org.usfirst.frc.team554.robot.commands.AutonomousProgram003;
import org.usfirst.frc.team554.robot.commands.AutonomousProgram004;
import org.usfirst.frc.team554.robot.commands.AutonomousProgram005_Frise;

import org.usfirst.frc.team554.robot.subsystems.*;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/*
 * FYI These are the current encoder distances (in) per pulse: 
 * 1) (7.75*pi)/360 * 18/36 = 0.03381575426
 * 2) (7.75*pi)/256 * 18/36 = 0.04755340442
 */

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	// public static final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
	public static OI oi;
	public static DriveTrain driveTrain;
	public static Arm arm;
	public static Pneumatics pneumatics;
	public static BeaterBars beaterBars;
	public static Camera camera;
	public static PDP powerDistPanel;
	public static ThumbWheel tWheel;
	public int autoProgramNumber;
	public int cameraUpdate;
	private Preferences pref;
	
	
    Command autonomousCommand;

    /**
     * This function is run when the robot is first started up and should be
     * used for any initialization code.
     */
    public void robotInit() {
		pref = Preferences.getInstance();
    	driveTrain = new DriveTrain();
    	arm = new Arm();
    	pneumatics = new Pneumatics();
    	beaterBars = new BeaterBars();
    	camera = new Camera();
    	powerDistPanel = new PDP();
    	tWheel = new ThumbWheel();
    	oi = new OI();
    	
    	arm.setInnerLimit(pref.getDouble("inner limit", 5.0));//semiknown value
    	arm.setOuterLimit(pref.getDouble("outer limit", 235.0));//semiknown value value is in degrees
    	arm.setShootableLimit(pref.getDouble("Shootable limit", 90)); //semiknown value
    	beaterBars.setCollectMotorSpeed(pref.getDouble("Collect Speed", -0.5));
    	beaterBars.setShootMotorSpeed(pref.getDouble("Shoot Speed", 1.0));
    	beaterBars.setPassMotorSpeed(pref.getDouble("Pass Speed", 0.5));
        
        arm.resetEncoder();
        driveTrain.resetEncoder();
        driveTrain.resetGyro();
        pneumatics.start();
        driveTrain.gearDown();
        camera.cameraFront();
    }
    
	/**
     * This function is called once each time the robot enters Disabled mode.
     * You can use it to reset any subsystem information you want to clear when
	 * the robot is disabled.
     */
    public void disabledInit(){

    }
	
	public void disabledPeriodic() {
		Scheduler.getInstance().run();
        camera.updateCam();
        log();
	}

	/**
	 * This autonomous (along with the chooser code above) shows how to select between different autonomous modes
	 * using the dashboard. The sendable chooser code works with the Java SmartDashboard. If you prefer the LabVIEW
	 * Dashboard, remove all of the chooser code and uncomment the getString code to get the auto name from the text box
	 * below the Gyro
	 *
	 * You can add additional auto modes by adding additional commands to the chooser code above (like the commented example)
	 * or additional comparisons to the switch structure below with additional strings & commands.
	 */
    public void autonomousInit() {
    	autoProgramNumber = tWheel.getThumbWheelval();
		switch (autoProgramNumber) {
			case 1: autonomousCommand =	new AutonomousProgram001();
				break;
			case 2: autonomousCommand = new AutonomousProgram002_Portcullis();
		        break;
			case 3: autonomousCommand = new AutonomousProgram003();
				break;
			case 4: autonomousCommand = new AutonomousProgram004();
				break;
			case 5: autonomousCommand = new AutonomousProgram005_Frise();
		default: ;//do nothing
		};
		driveTrain.resetGyro();
		driveTrain.resetEncoder();
		
    	if (autonomousCommand != null) autonomousCommand.start();
		
    }

    
    /**
     * This function is called periodically during autonomous
     */
    public void autonomousPeriodic() {
        Scheduler.getInstance().run();
        camera.updateCam();
        log();
    }

    public void teleopInit() {
		// This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to 
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null) autonomousCommand.cancel();
    	driveTrain.resetGyro();      
    }
    	

    /**
     * This function is called periodically during operator control
     */
    public void teleopPeriodic() {
        Scheduler.getInstance().run();
        camera.updateCam();
        camera.moveCamera(oi.getDriver());
        log();
    }
    
    /**
     * This function is called periodically during test mode
     */
    public void testPeriodic() {
        LiveWindow.run();
    }
    
    
    public void log(){
    	arm.log();
    	beaterBars.log();
    	driveTrain.log();
    	tWheel.log();
    	powerDistPanel.log();
    }
}
