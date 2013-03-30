package de.ka.javacity.system;

import java.util.List;

import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.Node;

public interface FamilyManager {
	public Node registerEntityToFamilies(AbstractEntity entity);	
	public List<Node> getMembersOfFamily(FamilyName family);
	public void removeMemberFromFamilies(Node member);
}