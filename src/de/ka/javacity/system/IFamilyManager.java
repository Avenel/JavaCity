package de.ka.javacity.system;

import java.util.List;

import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;

public interface IFamilyManager {
	public void registerEntityToFamilies(AbstractEntity entity);	
	public List<AbstractNode> getMembersOfFamily(FamilyName family);
	public void deRegisterEntityFromFamilies(AbstractEntity entity);
}