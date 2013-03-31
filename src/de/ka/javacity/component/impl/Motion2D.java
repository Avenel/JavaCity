package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Motion2D extends AbstractComponent {
	// Basic Velocity
	private double velocity;
	private double vx, vy;

	// friction
	private double damping;
	
	public Motion2D() {
		this.type = ComponentType.MOTION2D;
		this.velocity = 0.0;
		this.vx = 0.0;
		this.vy = 0.0;
		this.damping = 0.0;
	}
	
	public double getVelocity() {
		return velocity;
	}

	public void setVelocity(double velocity) {
		this.velocity = velocity;
	}

	public double getVx() {
		return this.vx;
	}

	public double getVy() {
		return this.vy;
	}
	
	public void setVx(double vx) {
		this.vx = vx;
	}

	public void setVy(double vy) {
		this.vy = vy;
	}

	public double getDamping() {
		return damping;
	}

	public void setDamping(double damping) {
		this.damping = damping;
	}

}
