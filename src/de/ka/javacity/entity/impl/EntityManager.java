package de.ka.javacity.entity.impl;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.system.IFamilyManager;

public class EntityManager implements IEntityManager {

	private Map<UUID, AbstractEntity> entities;
	private IFamilyManager familyManager;
	
	public EntityManager(IFamilyManager familyManager) {
		this.familyManager = familyManager;
		this.entities = new HashMap<UUID, AbstractEntity>();
	}
	
	@Override
	public AbstractEntity createEntity(AbstractComponent[] components) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void removeEntity(UUID id) {
		this.familyManager.deRegisterEntityFromFamilies(this.entities.get(id));
		this.entities.remove(id);
	}

}
