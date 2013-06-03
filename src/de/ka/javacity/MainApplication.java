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
		glShadeModel(GL_SMOOTH);
		glLightModelf(GL_LIGHT_MODEL_LOCAL_VIEWER, 1.0f);//asFloatBuffer(new float[]{0.001f, 0.001f, 0.001f, 1f}));
		glLightModel(GL_LIGHT_MODEL_AMBIENT, asFloatBuffer(new float[]{0.2f, 0.2f, 0.2f, 1.0f}));
		
		glEnable(GL_LIGHT0);
		glLight(GL_LIGHT0, GL_SPECULAR, asFloatBuffer(new float[]{0.8f, 0.8f, 0.8f, 1.0f}));
		glLightf(GL_LIGHT0, GL_SPOT_CUTOFF, 45);
		glLightf(GL_LIGHT0, GL_SPOT_EXPONENT, 1.0f); 
		
		glEnable(GL_LIGHT1);
		glLight(GL_LIGHT1, GL_DIFFUSE, asFloatBuffer(new float[]{0.5f, 0.5f, 0.5f, 0f}));
	
		
		// Culling
		glEnable(GL_CULL_FACE);
		glCullFace(GL_BACK);
		
		// Material Color		
		glEnable(GL_COLOR_MATERIAL);
		glMaterialf(GL_FRONT_AND_BACK, GL_SHININESS, 128f);
		glColorMaterial(GL_FRONT_AND_BACK, GL_AMBIENT_AND_DIFFUSE);

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
		
			// camera
			glLoadIdentity();
			camera.lookThrough();
			
			
			// Translate light			
			float x = 256f*2f;
			float y = 5f;
			float z = 256f*2f;
			float size = 1f;
			
			glLight(GL_LIGHT0, GL_SPOT_DIRECTION, asFloatBuffer(new float[]{-1f, 0f, 1f, 1f}));
			glLight(GL_LIGHT0, GL_POSITION, asFloatBuffer(new float[]{ x, y, z, 1f}));
			
			glLight(GL_LIGHT1, GL_POSITION, asFloatBuffer(new float[]{ x, y, z, 0f}));
			
			// draw Lightbox
			
			glPushMatrix();
			glTranslatef(x, y, z);

			glBegin(GL_QUADS);
				// left
				glVertex3f(-size, -size, -size);
				glVertex3f(-size, -size, size);
				glVertex3f(-size, size, size);
				glVertex3f(-size, size, -size);

				// right
				glVertex3f(size, size, -size);
				glVertex3f(size, size, size);
				glVertex3f(size, -size, size);
				glVertex3f(size, -size, -size);

				// bottom
				glVertex3f(size, -size, -size);
				glVertex3f(size, -size, size);
				glVertex3f(-size, -size, size);
				glVertex3f(-size, -size, -size);

				// top
				glVertex3f(-size, size, -size);
				glVertex3f(-size, size, size);
				glVertex3f(size, size, size);
				glVertex3f(size, size, -size);

				// back
				glVertex3f(-size, -size, -size);
				glVertex3f(-size, size, -size);
				glVertex3f(size, size, -size);
				glVertex3f(size, -size, -size);

				// front
				glVertex3f(size, -size, size);
				glVertex3f(size, size, size);
				glVertex3f(-size, size, size);
				glVertex3f(-size, -size, size);

			glEnd();
			glPopMatrix();
			
	
			
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
