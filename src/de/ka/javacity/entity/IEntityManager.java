package de.ka.javacity.entity;

import java.util.UUID;

import de.ka.javacity.component.AbstractComponent;

public interface IEntityManager {
	public AbstractEntity createEntity(AbstractComponent[] components);
	public void removeEntity(UUID id);		
}
