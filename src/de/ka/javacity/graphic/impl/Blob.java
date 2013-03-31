package de.ka.javacity.graphic.impl;

import de.ka.javacity.graphic.IView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Blob implements IView {
	
	@Override
	public void draw(GraphicsContext gc, double x, double y, int rotation) {
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.setGlobalAlpha(0.1);
		gc.fillOval(x, y, 30, 30);
	}

}
