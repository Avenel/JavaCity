package de.ka.javacity;

import org.lwjgl.*;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;

/**
 * Main for JavaCity
 */
public class MainApplication {

	public MainApplication() {
		try {
			Display.setDisplayMode(new DisplayMode(800, 600));
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			System.exit(1);
		}
		
		// init OpenGl
		
		// ApplicationLoop
		
		while(!Display.isCloseRequested()) {
			// Inputs
			
			// UpdateWorld
			
			// render OpenGL
			Display.update();
		}
		
		Display.destroy();
	}
	
	
	public static void main(String[] args) {
		new MainApplication();
	}
}
