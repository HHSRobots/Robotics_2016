package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;

/**
 *
 */
public class Camera extends Subsystem {
	private int camFront;
    private int camBack;
    private int curCam;
    private double currentAngle;
    private final double conversionRate;
    private Image frame;
    private Servo camControl;
    private CameraServer server;
    private boolean changeDone = false;
    private int cameraMode=1;
    
    
    public Camera(){
    	super();
    	
    	//TODO Set camera ids 'cam0', found on roborio web interface
    	
    	camFront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        //camBack = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camFront;
        
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
        //This will be the servo for controlling the camera movement
        camControl = new Servo(1);
        
        
        //I  don't have the conversion rate on the two angles yet.
    	conversionRate = 0.0; //Conversion rate between the servo angles and the throttle setting
    }
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
	/**
	 * Stop aka close camera stream
	 */
	public void end()
	{
		NIVision.IMAQdxStopAcquisition(curCam);
	}
	
	public void cameraToggle()
	{//if problems, maybe try to keep cameras from switching when changeDone is false;
		if(cameraMode==1)
		{
			cameraBack();
			cameraMode= cameraMode * -1;
		}
		else
		{
			cameraFront();
			cameraMode= cameraMode * -1;
		}
	}
 
	
	public void cameraFront(){
		changeDone = false;
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(camFront);
    	NIVision.IMAQdxStartAcquisition(camFront);
    	curCam = camFront;
    	updateCam();
    	changeDone = true;
	}
	
	
	public void cameraBack(){
		changeDone = false;
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(camBack);
    	NIVision.IMAQdxStartAcquisition(camBack);
    	curCam = camBack;
    	updateCam();
    	changeDone = true;
	}
	
	/**
	 * Get the img from current camera and give it to the server
	 */
    public void updateCam()
    {
        //NIVision.Rect rect = new NIVision.Rect(0, 320, 480, 1);
    	NIVision.IMAQdxGrab(curCam, frame, 1);
    	//NIVision.imaqDrawShapeOnImage(frame, frame, rect,
        //        DrawMode.DRAW_VALUE, ShapeMode.SHAPE_RECT, 0.0f);
        server.setImage(frame);
    }
    
    public boolean cameraChangeComplete(){
    	return changeDone;
    }
    
    public void log()
    {
    	SmartDashboard.putNumber("Current Camera", cameraMode);
    }
    
    public void moveCamera(Joystick driver)
    {
    	//This may need to flipped (+/-). Luke wants 100% throttle to be when the camera is facing down the most
    	currentAngle = driver.getThrottle(); //This what the driver's joystick throttle is set at
    	
    	camControl.setAngle((currentAngle*conversionRate)); //sets the servo angle with respect to the throttle
    }
   
}
