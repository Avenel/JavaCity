package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Position2D extends AbstractComponent {

	private double x,y;
	
	public Position2D() {
		this.type = ComponentType.POSITION2D;
		this.x = 0.0;
		this.y = 0.0;
	}

	public double getX() {
		return x;
	}

	public void setX(double x) {
		this.x = x;
	}

	public double getY() {
		return y;
	}

	public void setY(double y) {
		this.y = y;
	}
	
	
}
