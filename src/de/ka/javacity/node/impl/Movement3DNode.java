package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.impl.Motion3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class Movement3DNode extends AbstractNode {
	
	private Position3D position;
	private Motion3D motion;

	public Movement3DNode() {
		super();
		this.name = FamilyName.MOVEMENT3D;
	}
	
	@Override
	public boolean isEntityMember(AbstractEntity entity) {
		if (entity.getComponents().containsKey(ComponentType.POSITION3D) &&
			entity.getComponents().containsKey(ComponentType.MOTION3D) ) { 
			return true;
		}
		return false;
	}
	
	@Override
	public void addEntity(AbstractEntity entity) {
		this.position = (Position3D) entity.getComponents().get(ComponentType.POSITION3D);
		this.motion = (Motion3D) entity.getComponents().get(ComponentType.MOTION3D);
	}

	public Position3D getPosition() {
		return position;
	}

	public void setPosition(Position3D position) {
		this.position = position;
	}

	public Motion3D getMotion() {
		return motion;
	}

	public void setMotion(Motion3D motion) {
		this.motion = motion;
	}

}
