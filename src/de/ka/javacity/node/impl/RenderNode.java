package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.Display2D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class RenderNode extends AbstractNode {
	private Display2D display;

	public RenderNode() {
		super();
		this.name = FamilyName.RENDER;
	}
	
	public void addEntity(AbstractEntity entity){
		this.display = (Display2D) entity.getComponents().get(ComponentType.DISPLAY);
	}
	
	public Display2D getDisplay() {
		return display;
	}

	public void setDisplay(Display2D display) {
		this.display = display;
	}
	
	public boolean isEntityMember(AbstractEntity entity) {
		return true;
	}
}
