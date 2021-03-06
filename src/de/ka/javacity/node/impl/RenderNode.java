package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.impl.Display2D;
import de.ka.javacity.component.impl.Position2D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class RenderNode extends AbstractNode {
	private Display2D display;
	private Position2D position;

	public RenderNode() {
		super();
		this.name = FamilyName.RENDER;
	}
	
	public void addEntity(AbstractEntity entity){
		this.display = (Display2D) entity.getComponents().get(ComponentType.DISPLAY);
		this.position = (Position2D) entity.getComponents().get(ComponentType.POSITION2D);
	}
	
	public boolean isEntityMember(AbstractEntity entity) {
		if (entity.getComponents().containsKey(ComponentType.DISPLAY) &&
			entity.getComponents().containsKey(ComponentType.POSITION2D)) {
			return true;
		}
		
		return false;
	}
	
	public Display2D getDisplay() {
		return display;
	}

	public void setDisplay(Display2D display) {
		this.display = display;
	}
	
	public Position2D getPosition() {
		return position;
	}

	public void setPosition(Position2D position) {
		this.position = position;
	}

}
