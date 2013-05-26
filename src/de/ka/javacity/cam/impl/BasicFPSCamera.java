package de.ka.javacity.cam.impl;

import org.lwjgl.opengl.GL11;
import org.lwjgl.util.vector.Vector3f;


import de.ka.javacity.cam.GameCamera;
 
//First Person Camera Controller
public class BasicFPSCamera implements GameCamera {
    //3d vector to store the camera's position in
    private Vector3f    position    = null;
    //the rotation around the Y axis of the camera
    private float       yaw         = 0.0f;
    //the rotation around the X axis of the camera
    private float       pitch       = 0.0f;
    
    private float renderDistance;
	private int chunksize;
	private float blocksize;
	
    
    //Constructor that takes the starting x, y, z location of the camera
    public BasicFPSCamera(float x, float y, float z)
    {
        //instantiate position Vector3f to the x y z params.
        position = new Vector3f(x, y, z);
        
        this.renderDistance = 200f;
    }
    public BasicFPSCamera() { // This is made so the line FPCameraController app = new FPCameraController(); will work
                // TODO Auto-generated constructor stub
        }
        //increment the camera's current yaw rotation
    public void yaw(float amount)
    {
        //increment the yaw by the amount param
        yaw += amount;
    }
 
    //increment the camera's current yaw rotation
    public void pitch(float amount)
    {
        //increment the pitch by the amount param
        pitch += amount;
    }
    //moves the camera forward relitive to its current rotation (yaw)
    public void walkForward(float distance)
    {
                position.x += distance * (float)Math.sin(Math.toRadians(yaw));
                position.z -= distance * (float)Math.cos(Math.toRadians(yaw));
    }
 
    //moves the camera backward relitive to its current rotation (yaw)
    public void walkBackwards(float distance)
    {          
                position.x -= distance * (float)Math.sin(Math.toRadians(yaw));
                position.z += distance * (float)Math.cos(Math.toRadians(yaw));
    }
 
    //strafes the camera left relitive to its current rotation (yaw)
    public void strafeLeft(float distance)
    {
                position.x += distance * (float)Math.sin(Math.toRadians(yaw-90));
                position.z -= distance * (float)Math.cos(Math.toRadians(yaw-90));
    }
 
    //strafes the camera right relitive to its current rotation (yaw)
    public void strafeRight(float distance)
    {
                position.x += distance * (float)Math.sin(Math.toRadians(yaw+90));
                position.z -= distance * (float)Math.cos(Math.toRadians(yaw+90));
    }
 
    @Override
    /**
     * Fly up
     */
	public void up(float distance) {
		position.y -= distance;		
	}
    
	@Override
	/**
	 * Fly down
	 */
	public void down(float distance) {
		position.y += distance;
	}
    
    //translates and rotate the matrix so that it looks through the camera
    //this dose basic what gluLookAt() does
    public void lookThrough()
    {
        //roatate the pitch around the X axis
        GL11.glRotatef(pitch, 1.0f, 0.0f, 0.0f);
        //roatate the yaw around the Y axis
        GL11.glRotatef(yaw, 0.0f, 1.0f, 0.0f);
        //translate to the position vector's location
        GL11.glTranslatef(-position.x, position.y, -position.z);
    }
	public Vector3f getPosition() {
		return position;
	}
	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	
	@Override
	public float getRenderDistance() {
		return this.renderDistance;
	}
	@Override
	public void setRenderDistance(float distance) {
		this.renderDistance = distance;
	}
	@Override
	public int getChunkSize() {
		return this.chunksize;
	}
	@Override
	public void setChunkSize(int chunksize) {
		this.chunksize = chunksize;
	}
	
	@Override
	public float getBlockSize() {
		return this.blocksize;
	}
	
	@Override
	public void setBlockSize(float blocksize) {
		this.blocksize = blocksize;
	}
	
    
}