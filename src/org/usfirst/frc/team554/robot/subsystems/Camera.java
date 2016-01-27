package org.usfirst.frc.team554.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

import com.ni.vision.NIVision;
//import com.ni.vision.NIVision.DrawMode;
import com.ni.vision.NIVision.Image;
//import com.ni.vision.NIVision.ShapeMode;

import edu.wpi.first.wpilibj.CameraServer;

/**
 *
 */
public class Camera extends Subsystem {
	private int camFront;
    private int camBack;
    private int curCam;
    private Image frame;
    private CameraServer server;
    private boolean changeDone = false;
    
    
    public Camera(){
    	super();
    	
    	//TODO Set camera ids 'cam0', found on roborio web interface
    	
    	camFront = NIVision.IMAQdxOpenCamera("cam0", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        camBack = NIVision.IMAQdxOpenCamera("cam1", NIVision.IMAQdxCameraControlMode.CameraControlModeController);
        curCam = camFront;
        
        // Img that will contain camera img
        frame = NIVision.imaqCreateImage(NIVision.ImageType.IMAGE_RGB, 0);
        // Server that we'll give the img to
        server = CameraServer.getInstance();
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
	
 
	
	public void cameraFront(){
		changeDone = false;
		NIVision.IMAQdxStopAcquisition(curCam);
    	NIVision.IMAQdxConfigureGrab(camFront);
    	NIVision.IMAQdxStartAcquisition(camFront);
    	curCam = camFront;
    	updateCam();
    	changeDone = true;
	}
	
	
	public void cameraSide(){
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
   
}
