package de.ka.javacity.graphic.impl;

import org.lwjgl.opengl.GL11;

import de.ka.javacity.graphic.IView3D;

public class Blob3D implements IView3D {

	private float size = 1;

	public Blob3D() {
	}

	@Override
	public void draw(float x, float y, float z, float rx, float ry, float rz) {
		// draw quad

		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
	
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
		
			GL11.glBegin(GL11.GL_QUADS);
				// left
				GL11.glColor3f(1, 0, 0);
				GL11.glVertex3f(-size, -size, -size);
				GL11.glVertex3f(-size, -size, size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(-size, size, -size);
		
				// right
				GL11.glColor3f(0, 1, 0);
				GL11.glVertex3f(size, size, -size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(size, -size, -size);

				// bottom
				GL11.glColor3f(0, 0, 1);
				GL11.glVertex3f(size, -size, -size);
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(-size, -size, size);
				GL11.glVertex3f(-size, -size, -size);

				// top
				GL11.glColor3f(1, 1, 0);
				GL11.glVertex3f(-size, size, -size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(size, size, -size);
		
				// back
				GL11.glColor3f(1, 1, 1);
				GL11.glVertex3f(-size, -size, -size);
				GL11.glVertex3f(-size, size, -size);
				GL11.glVertex3f(size, size, -size);
				GL11.glVertex3f(size, -size, -size);
				
				// front
				GL11.glColor3f(1, 0, 1);
				GL11.glVertex3f(size, -size, size);
				GL11.glVertex3f(size, size, size);
				GL11.glVertex3f(-size, size, size);
				GL11.glVertex3f(-size, -size, size);

			GL11.glEnd();
		GL11.glPopMatrix();
	}

}
