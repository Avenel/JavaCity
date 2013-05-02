package de.ka.javacity.system.impl;

import java.util.ArrayList;

import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class RenderSystem implements ISystem {

	private IFamilyManager familyManager;
	
	public RenderSystem(IFamilyManager familyManager) {
		this.familyManager = familyManager;
	}
	
	@Override
	public void update() {
		ArrayList<AbstractNode> renderNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.RENDER);
		
		// Clear screen
		for (AbstractNode node : renderNodes) {
			RenderNode renderNode = (RenderNode) node;
			renderNode.getDisplay().getView().draw(renderNode.getPosition().getX(), renderNode.getPosition().getY(), 0);
		}
	}

}
