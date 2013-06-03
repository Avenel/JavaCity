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
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.game.impl.BaseGame;

/**
 * Main for JavaCity
 */
public class MainApplication {

	private long lastFPS;
	private int fps;
	private int actualFps;
	private float mouseSensitivity = 0.15f;
	private float movementSpeed  = 50.0f;
	

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
		gluPerspective(90.0f, game.getWindow_width()/game.getWindow_height(), 0.001f, 1000);
		glViewport(0, 0, game.getWindow_width(), game.getWindow_height());
		glMatrixMode(GL_MODELVIEW);		
		
		// init simple ambientlight
		glEnable(GL_LIGHTING);
		glLightModelf(GL_LIGHT_MODEL_LOCAL_VIEWER, 1.0f);//asFloatBuffer(new float[]{0.001f, 0.001f, 0.001f, 1f}));
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.0f, 0.0f, 0.0f, 1.0f}));
		
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_SPECULAR, asFloatBuffer(new float[]{0.5f, 0.5f, 0.5f, 1f}));
		glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{ 200*2f, 0f, 30f, 1f}));
		glLight(GL_LIGHT0, GL_SPOT_DIRECTION, asFloatBuffer(new float[]{0f, 1f, 0f, 1f}));
		
		//glEnable(GL_LIGHT1);
		glLight(GL_LIGHT1, GL_SPECULAR, asFloatBuffer(new float[]{0.5f, 0.5f, 0.5f, 0f}));
		glLight(GL_LIGHT1, GL_SPOT_DIRECTION, asFloatBuffer(new float[]{0f, 0f, -1f, 0f}));
		
		// Culling
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		// Material Color
		glShadeModel(GL_SMOOTH);
		glEnable(GL_COLOR_MATERIAL);
		
		glMaterialf(GL_FRONT, GL_SHININESS, 128f);
		
		glColorMaterial(GL_FRONT, GL_DIFFUSE);
        glColor3f(0.4f, 0.27f, 0.17f);

        glEnable(GL_DEPTH_TEST); 

//		glPolygonMode(GL_FRONT_AND_BACK, GL_LINE);
		
		// start fps time
		this.lastFPS = this.getTime();

		// Create FPS Camera 
		GameCamera camera = new BasicFPSCamera(0, -180, 0);		
		camera.setRenderDistance(20);
		
		Mouse.setGrabbed(true);
		game.setCamera(camera);

		// Init Game
		game.startUp();
		
		// Generate World
		game.createTestBlob(0, 0, 0 );

		glClearColor(0.1f, 0.6f, 0.8f, 1.0f);

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
			if (Keyboard.isKeyDown(Keyboard.KEY_Q)) {
				camera.down(movementSpeed * 0.05f);
			}
			if (Keyboard.isKeyDown(Keyboard.KEY_E)) {
				camera.up(movementSpeed * 0.05f);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_LEFT)) {
				camera.yaw(-movementSpeed * mouseSensitivity);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RIGHT)) {
				camera.yaw(movementSpeed * mouseSensitivity);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_UP)) {
				camera.pitch(-movementSpeed * mouseSensitivity);
			}
			
			if (Keyboard.isKeyDown(Keyboard.KEY_DOWN)) {
				camera.pitch(movementSpeed * mouseSensitivity);
			}
			
					
			// UpdateWorld
			
			glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{ 100*2f, 10f, 100f*2f, 1f}));
			glLight(GL_LIGHT0, GL_SPOT_DIRECTION, asFloatBuffer(new float[]{0f, 1f, 1f, 1f}));
			
			// camera
			glLoadIdentity();
			camera.lookThrough();
			
			
			// Translate light			
			//glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{camera.getPosition().x, camera.getPosition().y, camera.getPosition().z, 0f}));//
			
			
//			glLight(GL_LIGHT1, GL_POSITION, asFloatBuffer(new float[]{ 128f, 0.0f, 128f, 0f}));
//			glLight(GL_LIGHT1, GL_SPOT_DIRECTION, asFloatBuffer(new float[]{0f, -1f, -1f, 0f}));
			//glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{camera.getPosition().x, camera.getPosition().y, camera.getPosition().z, 0f}));//new float[]{ 128f*3, -30.0f*3, 128f*3, 0.5f}));
					
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
