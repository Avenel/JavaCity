package de.ka.javacity.graphic;

import javafx.scene.canvas.Canvas;

public interface IView {
	public void draw(Canvas canvas);
	
	// 2D coordinates...
	public void setPosition2D(int x, int y);
	
	// rotation
	public void setRotation2D(int degree);
}
