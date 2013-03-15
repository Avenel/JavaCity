package de.ka.javacity.component;

public interface LocalComponent {

	public enum ComponentType {
		// Abstract GameObject types, e.g. House, Street ...
		BUILDING,
		STREET;
	}
	
	public ComponentType getType();
	public void setType(ComponentType type);
}
