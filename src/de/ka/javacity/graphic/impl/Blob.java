package de.ka.javacity.graphic.impl;

import de.ka.javacity.graphic.IView;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Blob implements IView {

	private int x, y;
	private int rotation;
	
	public Blob() {
		this.x = 0;
		this.y = 0;
		this.rotation = 0;
	}
	
	
	@Override
	public void draw(Canvas canvas) {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		gc.setFill(Color.GREEN);
		gc.setStroke(Color.BLUE);
		gc.fillOval(x, y, 30, 30);
	}

	@Override
	public void setPosition2D(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void setRotation2D(int degree) {
		this.rotation = degree;
	}

}
