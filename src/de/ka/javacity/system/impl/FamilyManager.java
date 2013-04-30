package de.ka.javacity.system.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.MovementNode;
import de.ka.javacity.node.impl.RenderNode;
import de.ka.javacity.node.impl.RenderNode3D;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;

public class FamilyManager implements IFamilyManager {

	private Map<FamilyName, List<AbstractNode>> families;
	
	// not happy with this map... :/
	private Map<FamilyName, AbstractNode> availableFamilies;
	
	public FamilyManager() {
		// initialize available families, add all available families
		this.availableFamilies = new HashMap<FamilyName, AbstractNode>();
		this.availableFamilies.put(FamilyName.RENDER, new RenderNode());
		this.availableFamilies.put(FamilyName.RENDER3D, new RenderNode3D());
		this.availableFamilies.put(FamilyName.MOVEMENT, new MovementNode());
		
		// initialize family list, add empty lists for each available family
		this.families = new HashMap<FamilyName, List<AbstractNode>>();
		this.families.put(FamilyName.RENDER, new ArrayList<AbstractNode>());
		this.families.put(FamilyName.RENDER3D, new ArrayList<AbstractNode>());
		this.families.put(FamilyName.MOVEMENT, new ArrayList<AbstractNode>());
	}
	
	@Override
	public void registerEntityToFamilies(AbstractEntity entity) {
		for (AbstractNode familyNode : availableFamilies.values()) {
			if (familyNode.isEntityMember(entity)) {
				AbstractNode newNode;
				try {
					newNode = familyNode.getClass().newInstance();
					entity.addMembership(familyNode.getName());
					newNode.addEntity(entity);
					this.families.get(familyNode.getName()).add(newNode);
				} catch (InstantiationException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@Override
	public List<AbstractNode> getMembersOfFamily(FamilyName family) {
		return this.families.get(family);
	}

	@Override
	public void deRegisterEntityFromFamilies(AbstractEntity entity) {
		for (FamilyName familyName : entity.getMemberships().values()) {
			this.families.get(familyName).remove(entity);
		}
	}
	
}
