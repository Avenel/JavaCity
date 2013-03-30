package de.ka.javacity.system.impl;

import java.util.ArrayList;

import javafx.scene.canvas.Canvas;

import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class RenderSystem implements ISystem {

	private IFamilyManager familyManager;
	private Canvas canvas;
	
	public RenderSystem(IFamilyManager familyManager, Canvas canvas) {
		this.familyManager = familyManager;
		this.canvas = canvas;
	}
	
	@Override
	public void update() {
		ArrayList<AbstractNode> renderNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.RENDER);
		
		for (AbstractNode node : renderNodes) {
			RenderNode renderNode = (RenderNode) node;
			renderNode.getDisplay().getView().draw(canvas);
		}
	}

	public Canvas getCanvas() {
		return canvas;
	}

	public void setCanvas(Canvas canvas) {
		this.canvas = canvas;
	}

}
