package de.ka.javacity.graphic.impl;

import org.lwjgl.opengl.GL11;

import de.ka.javacity.graphic.IView3D;

public class Blob3D implements IView3D {


	public Blob3D() {
	}

	@Override
	public void draw(float x, float y, float z, float rx, float ry, float rz) {
		// draw quad
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f, 0.5f, 1.0f);

		// Front
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
			
			GL11.glBegin(GL11.GL_QUADS);
				GL11.glVertex3f(x, y, z);
			    GL11.glVertex3f(x + 10, y, z);
			    GL11.glVertex3f(x + 10, y + 10, z);
			    GL11.glVertex3f(x, y + 10, z);		    
			GL11.glEnd();
		GL11.glPopMatrix();
		
		// Back
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
			
			GL11.glBegin(GL11.GL_QUADS);
			    GL11.glVertex3f(x, y, z + 10);
			    GL11.glVertex3f(x + 10, y, z + 10);
			    GL11.glVertex3f(x + 10, y + 10, z + 10);
			    GL11.glVertex3f(x, y + 10, z + 10);		    
			GL11.glEnd();
		GL11.glPopMatrix();

		// Top
		GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
		
			GL11.glBegin(GL11.GL_QUADS);
			    GL11.glVertex3f(x, y, z);
			    GL11.glVertex3f(x + 10, y, z);
			    GL11.glVertex3f(x + 10, y, z + 10);
			    GL11.glVertex3f(x, y, z + 10);		    
		    GL11.glEnd();
	    GL11.glPopMatrix();
	    
		// Bottom
	    GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glRotatef(0.0f, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
			
			GL11.glBegin(GL11.GL_QUADS);
			    GL11.glVertex3f(x, y+ 10, z);
			    GL11.glVertex3f(x + 10, y + 10, z);
			    GL11.glVertex3f(x + 10, y + 10, z + 10);
			    GL11.glVertex3f(x, y + 10, z + 10);		    
		    GL11.glEnd();
	    GL11.glPopMatrix();
		    
		// Left
	    GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
			
			GL11.glBegin(GL11.GL_QUADS);
			    GL11.glVertex3f(x, y, z);
			    GL11.glVertex3f(x, y + 10, z);
			    GL11.glVertex3f(x, y + 10, z + 10);
			    GL11.glVertex3f(x, y, z + 10);		    
		    GL11.glEnd();	  
	    GL11.glPopMatrix();
	    
		// Right
	    GL11.glPushMatrix();
			GL11.glTranslatef(x, y, z);
			GL11.glRotatef(rx, 1.0f, 0.0f, 0.0f);
			GL11.glRotatef(ry, 0.0f, 1.0f, 0.0f);
			GL11.glRotatef(rz, 0.0f, 0.0f, 1.0f);
			GL11.glTranslatef(-x, -y, -z);
			
			GL11.glBegin(GL11.GL_QUADS);
			    GL11.glVertex3f(x + 10, y, z);
			    GL11.glVertex3f(x + 10, y + 10, z);
			    GL11.glVertex3f(x + 10, y + 10, z + 10);
			    GL11.glVertex3f(x + 10, y, z + 10);		    
		    GL11.glEnd();	    
	    GL11.glPopMatrix();
	}
	
}
