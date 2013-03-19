package de.ka.javacity.node;

import de.ka.javacity.entity.AbstractEntity;

/**
 * Eine Node beinhaltet ein Mitglied der zugeh�rigen Familie.
 * 
 * @author Avenel
 * 
 */
public interface Node {
	public void addEntity(AbstractEntity entity);
	public AbstractEntity removeEntity();

	/**
	 * Beschreibt die notwendigen Komponenten die eine Entit�t besitzen muss, um
	 * ein Mitglied dieser Familie zu sein.
	 * 
	 * @param entity
	 * @return
	 */
	public boolean isEntityMember(AbstractEntity entity);
}
