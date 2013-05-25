package de.ka.javacity;

import java.nio.FloatBuffer;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;

import de.ka.javacity.cam.GameCamera;
import de.ka.javacity.cam.impl.BasicFPSCamera;
import de.ka.javacity.component.impl.Chunk.BoxType;
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.game.impl.BaseGame;
import de.ka.javacity.helper.HeightMapGenerator;
import de.ka.javacity.helper.WorldGenerator;

/**
 * Main for JavaCity
 */
public class MainApplication {

	private long lastFPS;
	private int fps;
	private int actualFps;
	private float mouseSensitivity = 0.15f;
	private float movementSpeed  = 10.0f;
	

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
		glMatrixMode(GL_PROJECTION);
		glLoadIdentity();
		gluPerspective(90.0f, 800f/600f, 0.001f, 2000);
		glViewport(0, 0, 800, 600);
		glMatrixMode(GL_MODELVIEW);		
		
		// init simple ambientlight
		glEnable(GL_LIGHTING);
		glLightModel(GL_AMBIENT, asFloatBuffer(new float[]{0.05f, 0.05f, 0.05f, 1f}));
		
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_DIFFUSE, asFloatBuffer(new float[]{1.3f, 1.3f, 1.3f, 1f}));
		
		glEnable(GL_LIGHT1);
		glLight(GL_LIGHT1, GL_DIFFUSE, asFloatBuffer(new float[]{1.3f, 1.3f, 1.3f, 1f}));
		
		// Culling
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		// Material Color
		glEnable(GL_COLOR_MATERIAL);
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
		
//		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		glEnable(GL_DEPTH_TEST); 

		
		// start fps time
		this.lastFPS = this.getTime();
		
		// Init Game
		game.startUp();
		
		// Create FPS Camera 
		GameCamera camera = new BasicFPSCamera(0, -25, 0);
		
		Mouse.setGrabbed(true);
		
		// Generate World
		game.createTestBlob(0, 0, 0 );


		
		// ApplicationLoop
		while(!Display.isCloseRequested()) {
			// Clear the screen and depth buffer
			glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);	
			
			// Inputs
			// Look around
			float dx = Mouse.getDX();
			float dy = Mouse.getDY();
			
			camera.yaw(dx * mouseSensitivity);
			camera.pitch(-dy * mouseSensitivity);
			
			// Go for a walk
			if (Keyboard.isKeyDown(Keyboard.KEY_W)) {
				camera.walkForward(movementSpeed * 0.05f);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_S)) {
				camera.walkBackwards(movementSpeed * 0.05f);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_A)) {
				camera.strafeLeft(movementSpeed * 0.05f);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_D)) {
				camera.strafeRight(movementSpeed * 0.05f);
			}
					
			// UpdateWorld
			
			// Translate light
			glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{ 80f, -20.0f, 80f, 1f}));
			glLight(GL_LIGHT1, GL_POSITION, asFloatBuffer(new float[]{ 150f, -20.0f, 150f, 1f}));
			
			// camera
			glLoadIdentity();
			camera.lookThrough();
			
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
	
	
	private FloatBuffer asFloatBuffer(float[] values) {
		FloatBuffer buffer = BufferUtils.createFloatBuffer(values.length);
		buffer.put(values);
		buffer.flip();
		return buffer;
	}
	
	/**
	 * Start the application!
	 * @param args
	 */
	public static void main(String[] args) {
		new MainApplication();
	}
	
}
