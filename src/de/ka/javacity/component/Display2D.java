package de.ka.javacity.component;

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
