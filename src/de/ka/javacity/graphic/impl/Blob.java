package de.ka.javacity.graphic.impl;

import org.lwjgl.opengl.GL11;

import de.ka.javacity.graphic.IView;

public class Blob implements IView {

	@Override
	public void draw(float x, float y, int rotation) {
		// draw quad
		// set the color of the quad (R,G,B,A)
		GL11.glColor3f(0.5f,0.5f,1.0f);
		GL11.glBegin(GL11.GL_QUADS);
		    GL11.glVertex2f(x, y);
		    GL11.glVertex2f(x + 10, y);
		    GL11.glVertex2f(x + 10, y + 10);
		    GL11.glVertex2f(x, y + 10);
		GL11.glEnd();
	}

}
