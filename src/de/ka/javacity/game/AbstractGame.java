package de.ka.javacity.game;

import de.ka.javacity.cam.GameCamera;


public abstract class AbstractGame {

	protected String title;
	protected int window_width;
	protected int window_height;
	protected boolean fullscreen;

	public void startUp() {
	}

	public int getWindow_width() {
		return window_width;
	}

	public void setWindow_width(int window_width) {
		this.window_width = window_width;
	}

	public int getWindow_height() {
		return window_height;
	}

	public void setWindow_height(int window_height) {
		this.window_height = window_height;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public boolean isFullscreen() {
		return fullscreen;
	}

	public void setFullscreen(boolean fullscreen) {
		this.fullscreen = fullscreen;
	}

	public void update() {
		
	}
	
	public void createTestBlob(float x, float y, float z) {}
	
	public GameCamera getCamera(){
		return null;
	}
	
	public void setCamera(GameCamera camera){}

}
