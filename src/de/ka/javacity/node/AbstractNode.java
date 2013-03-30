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
	private FamilyName name;
	
	
	public void addEntity(AbstractEntity entity){}
	public AbstractEntity removeEntity(){
		return null;
	}

	/**
	 * Beschreibt die notwendigen Komponenten die eine Entität besitzen muss, um
	 * ein Mitglied dieser Familie zu sein.
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isEntityMember(AbstractEntity entity) {
		return false;
	}
	
	public FamilyName getName() {
		return name;
	}
	public void setName(FamilyName name) {
		this.name = name;
	}
}
