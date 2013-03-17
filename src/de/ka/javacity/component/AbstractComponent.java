package de.ka.javacity.component;

public abstract class AbstractComponent {

	private ComponentType type;
	
	
	public ComponentType getType() {
		return type;
	}

	public void setType(ComponentType type) {
		this.type = type;
	}	
	
}
