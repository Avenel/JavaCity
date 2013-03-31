package de.ka.javacity.graphic;

import javafx.scene.canvas.GraphicsContext;

public interface IView {
	public void draw(GraphicsContext gc, double x, double y, int rotation);
}
