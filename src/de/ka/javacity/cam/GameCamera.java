package de.ka.javacity.cam;
/**
    Represents a camera movement like in an ego perspective shooter.
**/
public interface GameCamera {
	public void walkForward(float distance);
	public void walkBackwards(float distance);
	public void strafeLeft(float distance);
	public void strafeRight(float distance);
	public void yaw(float distance);
	public void pitch(float distance);
	
	public void lookThrough();
}
