package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Position2D extends AbstractComponent {

	private float x,y;
	
	public Position2D() {
		this.type = ComponentType.POSITION2D;
		this.x = 0.0f;
		this.y = 0.0f;
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	
}
