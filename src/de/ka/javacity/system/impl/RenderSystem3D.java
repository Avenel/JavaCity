package de.ka.javacity.system.impl;

import java.util.ArrayList;

import javafx.scene.Node;

import de.ka.javacity.cam.GameCamera;
import de.ka.javacity.graphic.IView3D;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode;
import de.ka.javacity.node.impl.RenderNode3D;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class RenderSystem3D implements ISystem {

	private IFamilyManager familyManager;
	private GameCamera gameCam;
	
	public RenderSystem3D(IFamilyManager familyManager, GameCamera gameCam) {
		this.familyManager = familyManager;
		this.gameCam = gameCam;
	}
	
	@Override
	public void update() {
		ArrayList<AbstractNode> renderNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.RENDER);
		
		for (AbstractNode node : renderNodes) {
			RenderNode3D renderNode = (RenderNode3D) node;
			renderNode.getDisplay().getView().draw(renderNode.getPosition().getX(), renderNode.getPosition().getY(), 0, 0, 0, 0);
		}
	}
	
	public void addObjectToWorld(IView3D view) {
		this.gameCam.addNodes((Node) view);
	}

}
