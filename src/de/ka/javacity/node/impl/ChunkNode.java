package de.ka.javacity.node.impl;

import de.ka.javacity.component.ComponentType;
import de.ka.javacity.component.impl.Chunk;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.entity.AbstractEntity;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.system.FamilyName;

public class ChunkNode extends AbstractNode {

	private Position3D position;
	private Chunk chunk;
	private Display3D display;
	
	public ChunkNode() {
		this.name = FamilyName.CHUNK;
	}
	
	@Override
	public void addEntity(AbstractEntity entity) {
		this.position = (Position3D) entity.getComponents().get(ComponentType.POSITION3D);
		this.chunk = (Chunk) entity.getComponents().get(ComponentType.CHUNK);
		this.display= (Display3D) entity.getComponents().get(ComponentType.DISPLAY3D);
	}

	@Override
	public boolean isEntityMember(AbstractEntity entity) {
		if (entity.getComponents().containsKey(ComponentType.POSITION3D) &&
			entity.getComponents().containsKey(ComponentType.CHUNK) &&
			entity.getComponents().containsKey(ComponentType.DISPLAY3D)) { 
			return true;
		}
		return false;
	}

	public Position3D getPosition() {
		return position;
	}

	public void setPosition(Position3D position) {
		this.position = position;
	}

	public Chunk getChunk() {
		return chunk;
	}

	public void setChunk(Chunk chunk) {
		this.chunk = chunk;
	}

	public Display3D getDisplay() {
		return display;
	}

	public void setDisplay(Display3D display) {
		this.display = display;
	}

}
