package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Motion3D extends AbstractComponent  {
	// Basic Velocity
	private float vx, vy, vz;

	// friction
	private float damping;
	
	// rotate
	private float rx, ry, rz;
	
	public Motion3D() {
		this.type = ComponentType.MOTION3D;
	}
	
	
	public Motion3D(float vx, float vy, float vz, float damping, float rx, float ry, float rz) {
		this.type = ComponentType.MOTION3D;
		
		this.vx = vx;
		this.vy = vy;
		this.vz = vz;
		this.damping = damping;
		
		this.rx = rx;
		this.ry = ry;
		this.rz = rz;
	}

	public float getVx() {
		return vx;
	}

	public void setVx(float vx) {
		this.vx = vx;
	}

	public float getVy() {
		return vy;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getVz() {
		return vz;
	}

	public void setVz(float vz) {
		this.vz = vz;
	}

	public float getDamping() {
		return damping;
	}

	public void setDamping(float damping) {
		this.damping = damping;
	}

	public float getRx() {
		return rx;
	}

	public void setRx(float rx) {
		this.rx = rx;
	}

	public float getRy() {
		return ry;
	}

	public void setRy(float ry) {
		this.ry = ry;
	}

	public float getRz() {
		return rz;
	}

	public void setRz(float rz) {
		this.rz = rz;
	}
	
}
