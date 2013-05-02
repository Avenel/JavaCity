package de.ka.javacity.system.impl;

import java.util.ArrayList;

import de.ka.javacity.component.impl.Motion3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.Movement3DNode;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class Movement3DSystem implements ISystem {
private IFamilyManager familyManager;
	
	public Movement3DSystem(IFamilyManager familyManager) {
		this.familyManager = familyManager;
	}

	@Override
	public void update() {
		ArrayList<AbstractNode> movementNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.MOVEMENT3D);
		
		for (AbstractNode node : movementNodes) {
			Movement3DNode movementNode = (Movement3DNode) node;
			
			// calculate new positions
			Position3D position = movementNode.getPosition();
			Motion3D motion = movementNode.getMotion();
			
			// collision
			// TODO hardcoded
			float px = position.getX();
			float py = position.getY();
			
			if (px < 0 || px > 800) {
				 motion.setVx(-motion.getVx());
			}
				 
			if (py < 0 || py > 600) {
				 motion.setVy(-motion.getVy());
			}
			
			// set new positions
			position.setX(px + motion.getVx());
			position.setY(py + motion.getVy());
			
			motion.setRx(motion.getRx() + 1.0f);
			motion.setRy(motion.getRy() + 1.0f);
			motion.setRz(motion.getRz() + 1.0f);
		}
	}
}
