package de.ka.javacity.game.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Motion3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.entity.impl.EntityManager;
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.graphic.impl.Blob3D;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystemManager;
import de.ka.javacity.system.impl.FamilyManager;
import de.ka.javacity.system.impl.Movement3DSystem;
import de.ka.javacity.system.impl.RenderSystem3D;
import de.ka.javacity.system.impl.SystemManager;

public class BaseGame extends AbstractGame {

	private IFamilyManager familyManager;
	private IEntityManager entityManager;
	private ISystemManager systemManager;
	
	public BaseGame() {
		this.title = "JavaCity";
		this.window_width = 800;
		this.window_height = 600;
		this.fullscreen = false;
	}

	public void startUp() {
		// Initialize FamilyManager
		this.familyManager = new FamilyManager();
		
		// Initialize EntityManager
		this.entityManager = new EntityManager(this.familyManager);
		
		// Initialize SystemManager incl. Systems
		this.systemManager = new SystemManager();
	
		RenderSystem3D renderSystem = new RenderSystem3D(familyManager);
		Movement3DSystem movementSystem = new Movement3DSystem(familyManager);
		
		this.systemManager.addSystem(renderSystem);
		this.systemManager.addSystem(movementSystem);
		
		// TODO Initialize Gamestatus/Gamelogic
	
	}
	
	public void createTestBlob(float x, float y) {
		// Test object: blob
		Display3D display = new Display3D();
		display.setView(new Blob3D());
		
		Position3D position = new Position3D(x, y, -100);
		
		Motion3D motion = new Motion3D();
		motion.setVx((float)Math.random() * 1.1f);
		motion.setVy((float)Math.random() * 1.1f);
		motion.setVz((float)Math.random() * 1.1f);
				
		AbstractComponent components[] = {display, position, motion};
		this.entityManager.createEntity(components);		
	}
	
	public void update() {
		this.systemManager.updateSystems();
	}

}
