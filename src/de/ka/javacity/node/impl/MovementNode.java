package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.impl.Motion2D;
import de.ka.javacity.component.impl.Position2D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class MovementNode extends AbstractNode {

	private Position2D position;
	private Motion2D motion;

	public MovementNode() {
		super();
		this.name = FamilyName.MOVEMENT;
	}
	
	public void addEntity(AbstractEntity entity) {
		this.position = (Position2D) entity.getComponents().get(ComponentType.POSITION2D);
		this.motion = (Motion2D) entity.getComponents().get(ComponentType.MOTION2D);
	}
	
	@Override
	public boolean isEntityMember(AbstractEntity entity) {
		if (entity.getComponents().containsKey(ComponentType.POSITION2D) &&
			entity.getComponents().containsKey(ComponentType.MOTION2D) ) { 
			return true;
		}
		return false;
	}

	public Position2D getPosition() {
		return position;
	}

	public void setPosition(Position2D position) {
		this.position = position;
	}
	
	public Motion2D getMotion() {
		return motion;
	}

	public void setMotion(Motion2D motion) {
		this.motion = motion;
	}
}
