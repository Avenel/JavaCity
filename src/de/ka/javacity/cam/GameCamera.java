package de.ka.javacity.cam;

import org.lwjgl.util.vector.Vector3f;

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
	public void up(float distance);
	public void down(float distance);
	
	public void lookThrough();

	public float getRenderDistance();
	public void setRenderDistance(float distance);
	
	public int getChunkSize();
	public void setChunkSize(int chunksize);
	
	public float getBlockSize();
	public void setBlockSize(float blocksize);
	
	public Vector3f getPosition();
	public void setPosition(Vector3f position);
}
