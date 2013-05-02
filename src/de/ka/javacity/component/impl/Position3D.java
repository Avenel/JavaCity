package de.ka.javacity.component.impl;

import org.lwjgl.util.vector.Vector3f;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Position3D extends AbstractComponent {
	Vector3f position;
	
	public Position3D(float x, float y, float z) {
		this.type = ComponentType.POSITION3D;
		this.position = new Vector3f(x, y, z); 
	}

	public Vector3f getPosition() {
		return position;
	}

	public void setPosition(Vector3f position) {
		this.position = position;
	}
	
	public void setX(float x) {
		this.position.setX(x);
	}
	
	public void setY(float y) {
		this.position.setY(y);
	}
	
	public void setZ(float z) {
		this.position.setZ(z);
	}
	
	public float getX() {
		return this.position.getX();
	}
	
	public float getY() {
		return this.position.getY();
	}
	
	public float getZ() {
		return this.position.getZ();
	}
	
}
