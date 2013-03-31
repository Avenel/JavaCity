package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;
import de.ka.javacity.graphic.IView;

public class Display2D extends AbstractComponent {
	
	private IView view;

	public Display2D() {
		this.type = ComponentType.DISPLAY;
	}
	
	public IView getView() {
		return view;
	}

	public void setView(IView view) {
		this.view = view;
	}
	
}
