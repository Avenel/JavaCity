package de.ka.javacity.node;

import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.system.FamilyName;

/**
 * Eine Node beinhaltet ein Mitglied der zugehörigen Familie.
 * 
 * @author Avenel
 * 
 */
public abstract class AbstractNode {
	protected FamilyName name;
	protected AbstractEntity entity;
	
	public abstract void addEntity(AbstractEntity entity);
	
	public AbstractEntity removeEntity(){
		AbstractEntity toRemovedEntity = this.entity;
		this.entity = null;
		return toRemovedEntity;
	}

	/**
	 * Beschreibt die notwendigen Komponenten die eine Entität besitzen muss, um
	 * ein Mitglied dieser Familie zu sein.
	 * 
	 * @param entity
	 * @return true/false
	 */
	public abstract boolean isEntityMember(AbstractEntity entity);
	
	public FamilyName getName() {
		return name;
	}
	public void setName(FamilyName name) {
		this.name = name;
	}
}
