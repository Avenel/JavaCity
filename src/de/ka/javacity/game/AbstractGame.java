package de.ka.javacity.game;

public abstract class AbstractGame {

	protected String title;
	protected double window_width;
	protected double window_height;
	protected boolean fullscreen;

	public void startUp() {
	}

	public double getWindow_width() {
		return window_width;
	}

	public void setWindow_width(double window_width) {
		this.window_width = window_width;
	}

	public double getWindow_height() {
		return window_height;
	}

	public void setWindow_height(double window_height) {
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

}
