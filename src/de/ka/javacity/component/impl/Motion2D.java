package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Motion2D extends AbstractComponent {
	// Basic Velocity
	private float velocity;
	private float vx, vy;

	// friction
	private float damping;
	
	public Motion2D() {
		this.type = ComponentType.MOTION2D;
		this.velocity = 0.0f;
		this.vx = 0.0f;
		this.vy = 0.0f;
		this.damping = 0.0f;
	}
	
	public float getVelocity() {
		return velocity;
	}

	public void setVelocity(float velocity) {
		this.velocity = velocity;
	}

	public float getVx() {
		return this.vx;
	}

	public float getVy() {
		return this.vy;
	}
	
	public void setVx(float vx) {
		this.vx = vx;
	}

	public void setVy(float vy) {
		this.vy = vy;
	}

	public float getDamping() {
		return damping;
	}

	public void setDamping(float damping) {
		this.damping = damping;
	}

}
