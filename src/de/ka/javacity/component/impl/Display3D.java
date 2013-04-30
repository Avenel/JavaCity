package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;
import de.ka.javacity.graphic.IView3D;

public class Display3D extends AbstractComponent {
	
	private IView3D view;

	public Display3D() {
		this.type = ComponentType.DISPLAY3D;
	}
	
	public IView3D getView() {
		return view;
	}

	public void setView(IView3D view) {
		this.view = view;
	}
	
}
