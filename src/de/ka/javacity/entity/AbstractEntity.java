package de.ka.javacity.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public abstract class AbstractEntity {
	
	// uuid vernünftig? oder doch eher long?
	private UUID id;
	private Map<ComponentType, AbstractComponent> components;
	
	public AbstractEntity () {
		id = UUID.randomUUID();
		this.components = new HashMap<ComponentType, AbstractComponent>();
	}
	
	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public Map<ComponentType, AbstractComponent> getComponents() {
		return components;
	}

	public void setComponents(Map<ComponentType, AbstractComponent> components) {
		this.components = components;
	}
	
	public boolean addComponent(AbstractComponent component) {
		if (!this.components.isEmpty()) {
			this.components.put(component.getType(), component);
			return true;
		}
		
		return false;
	}
	
	public boolean removeComponent(ComponentType type) {
		if (!this.components.isEmpty()) {
			this.components.remove(type);
			return true;
		}
		
		return false;		
	}
	
}
