package de.ka.javacity.component;

public abstract class AbstractComponent {

	protected ComponentType type;
	
	
	public ComponentType getType() {
		return type;
	}

	public void setType(ComponentType type) {
		this.type = type;
	}	
	
}
