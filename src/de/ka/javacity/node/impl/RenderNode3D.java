package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Position2D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class RenderNode3D extends AbstractNode {
	private Display3D display;
	private Position2D position;

	public RenderNode3D() {
		super();
		this.name = FamilyName.RENDER;
	}
	
	public void addEntity(AbstractEntity entity){
		this.display = (Display3D) entity.getComponents().get(ComponentType.DISPLAY3D);
		this.position = (Position2D) entity.getComponents().get(ComponentType.POSITION2D);
	}
	
	public boolean isEntityMember(AbstractEntity entity) {
		if (entity.getComponents().containsKey(ComponentType.DISPLAY3D) &&
			entity.getComponents().containsKey(ComponentType.POSITION2D)) {
			return true;
		}
		
		return false;
	}
	
	public Display3D getDisplay() {
		return display;
	}

	public void setDisplay(Display3D display) {
		this.display = display;
	}
	
	public Position2D getPosition() {
		return position;
	}

	public void setPosition(Position2D position) {
		this.position = position;
	}

}
