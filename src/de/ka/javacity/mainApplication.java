package de.ka.javacity;

import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.game.impl.BaseGame;

/**
 * Main for JavaCity
 */
public class MainApplication {

	private long lastFPS;
	private int fps;
	private int actualFps;
	private int maxBlobs = 10000;

	public MainApplication() {
		int blobCount = 0;

		// Create game
		AbstractGame game = new BaseGame();
		
		// Setup Display
		try {
			Display.setDisplayMode(new DisplayMode(game.getWindow_width(), game.getWindow_height()));
			Display.setTitle(game.getTitle());
			Display.setFullscreen(game.isFullscreen());
			Display.create();
		} catch (LWJGLException e) {
			e.printStackTrace();
			Display.destroy();
			System.exit(1);
		}
		
		// init OpenGl
		GL11.glMatrixMode(GL11.GL_PROJECTION);
		GL11.glLoadIdentity();
		gluPerspective(90.0f, 800f/600f, 0.001f, 100);
		GL11.glViewport(0, 0, 800, 600);
		GL11.glMatrixMode(GL11.GL_MODELVIEW);		
		
		// Culling
		GL11.glEnable(GL11.GL_CULL_FACE);
		GL11.glCullFace(GL11.GL_BACK);
		
		// start fps time
		this.lastFPS = this.getTime();
		
		// Init Game
		
		game.startUp();
			
		// ApplicationLoop
		while(!Display.isCloseRequested()) {
			// Clear the screen and depth buffer
			GL11.glClear(GL11.GL_COLOR_BUFFER_BIT | GL11.GL_DEPTH_BUFFER_BIT);	
			
			// Inputs
			
			// UpdateWorld
			// More Blobs!
			for (int i=0; i<1 && blobCount < maxBlobs ; i++) {
				game.createTestBlob(0, 0);
				blobCount++;
			}
									
			// Update everything, includes rendering objects
			game.update();
			
			// Update fps
			this.updateFPS();
			
			// Show some debug information in titlebar
			Display.setTitle(game.getTitle() + ", Blobs: " + blobCount + ", FPS: " + this.actualFps);
			
			// Update Display, sync to 60 FPS
			Display.update();
			Display.sync(60);
		}
		
		Display.destroy();
	}
	
	/**
	 * Get the accurate system time
	 * 
	 * @return The system time in milliseconds
	 */
	public long getTime() {
	    return (Sys.getTime() * 1000) / Sys.getTimerResolution();
	}
	
	/**
	 * Calculate the FPS 
	 */
	public void updateFPS() {
		if (getTime() - lastFPS > 1000) {
			actualFps = fps;
			fps = 0;
			lastFPS += 1000;
		}
		fps++;
	}
	
	/**
	 * Start the application!
	 * @param args
	 */
	public static void main(String[] args) {
		new MainApplication();
	}
	
}
