package de.ka.javacity.system.impl;

import java.util.ArrayList;

import de.ka.javacity.component.impl.Motion2D;
import de.ka.javacity.component.impl.Position2D;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.MovementNode;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class MovementSystem implements ISystem {

	private IFamilyManager familyManager;
	
	public MovementSystem(IFamilyManager familyManager) {
		this.familyManager = familyManager;
	}
	
	
	@Override
	public void update() {
		ArrayList<AbstractNode> movementNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.MOVEMENT);
		
		for (AbstractNode node : movementNodes) {
			MovementNode movementNode = (MovementNode) node;
			
			// calculate new positions
			Position2D position = movementNode.getPosition();
			Motion2D motion = movementNode.getMotion();
			
			// collision
			// TODO hardcoded
			double px = position.getX();
			double py = position.getY();
			
			if (px < 0 || px > 400) {
				 motion.setVx(-motion.getVx());
			}
				 
			if (py < 0 || py > 400) {
				 motion.setVy(-motion.getVy());
			}
			
			// set new positions
			position.setX(position.getX()+motion.getVx()*motion.getVelocity());
			position.setY(position.getY()+motion.getVy()*motion.getVelocity());
			
			
			
		}
	}
	
}
