package de.ka.javacity.entity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;
import de.ka.javacity.system.FamilyName;

public abstract class AbstractEntity {
	
	// uuid vernünftig? oder doch eher long?
	private UUID id;
	private Map<ComponentType, AbstractComponent> components;
	private Map<String, FamilyName> memberships;
	

	public AbstractEntity () {
		id = UUID.randomUUID();
		this.components = new HashMap<ComponentType, AbstractComponent>();
		this.memberships = new HashMap<String, FamilyName>();
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
	
	public Map<String, FamilyName> getMemberships() {
		return memberships;
	}
	
	public void addMembership(FamilyName family) {
		this.memberships.put(family.toString(), family);
	}
	
	public void removeMembership(FamilyName membership) {
		this.memberships.remove(membership.toString());
	}
	
}
