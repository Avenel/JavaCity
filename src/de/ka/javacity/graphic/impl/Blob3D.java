package de.ka.javacity.graphic.impl;

import org.lwjgl.opengl.GL11;

import de.ka.javacity.graphic.IView3D;

public class Blob3D implements IView3D {

	private float size = 1f;
	private float r, g, b;
	
	public Blob3D() {
		this.r = 0.5f;
		this.g = 0.25f;
		this.b = 0.05f;
	}

	@Override
	public void draw(float x, float y, float z, float rx, float ry, float rz) {
		// draw quad
		GL11.glColor3f(r, g, b);
		
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
	
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
		
			GL11.glBegin(GL11.GL_QUADS);
				// left
				GL11.glVertex3f(-size, -size, -size);
				GL11.glVertex3f(-size, -size, size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(-size, size, -size);
		
				// right
				GL11.glVertex3f(size, size, -size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(size, -size, -size);

				// bottom
				GL11.glVertex3f(size, -size, -size);
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(-size, -size, size);
				GL11.glVertex3f(-size, -size, -size);

				// top
				GL11.glVertex3f(-size, size, -size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(size, size, -size);
				
				// back
				GL11.glVertex3f(-size, -size, -size);
				GL11.glVertex3f(-size, size, -size);
				GL11.glVertex3f(size, size, -size);
				GL11.glVertex3f(size, -size, -size);
				
				// front
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(-size, -size, size);

			GL11.glEnd();
		GL11.glPopMatrix();
	}

	public float getSize() {
		return size;
	}

	public void setSize(float size) {
		this.size = size;
	}

	public float getR() {
		return r;
	}

	public void setR(float r) {
		this.r = r;
	}

	public float getG() {
		return g;
	}

	public void setG(float g) {
		this.g = g;
	}

	public float getB() {
		return b;
	}

	public void setB(float b) {
		this.b = b;
	}

	
}
