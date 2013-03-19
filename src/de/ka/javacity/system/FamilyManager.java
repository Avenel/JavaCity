package de.ka.javacity.system;

import java.util.List;

import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.Node;

public interface FamilyManager {
	public Node addEntityToFamily(FamilyName family, AbstractEntity entity);
	public Node addEntityToFamilies(AbstractEntity entity);
	
	public List<Node> getMembersOfFamily(FamilyName family);
	
	public void removeMemberFromFamily(Node member);
	public void removeMemberFromFamilies(Node member);
}