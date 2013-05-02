package de.ka.javacity.system.impl;

import java.util.ArrayList;

import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode3D;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class RenderSystem3D implements ISystem {

	private IFamilyManager familyManager;
	
	public RenderSystem3D(IFamilyManager familyManager) {
		this.familyManager = familyManager;
	}
	
	@Override
	public void update() {
		ArrayList<AbstractNode> renderNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.RENDER3D);
		
		for (AbstractNode node : renderNodes) {
			RenderNode3D renderNode = (RenderNode3D) node;
			renderNode.getDisplay().getView().draw(renderNode.getPosition().getPosition().getX(), renderNode.getPosition().getPosition().getY(), 
					renderNode.getPosition().getPosition().getZ(), renderNode.getMotion().getRx(), renderNode.getMotion().getRy(), renderNode.getMotion().getRz());
		}
	}

}
