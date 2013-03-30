package de.ka.javacity.game.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.Display2D;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.entity.impl.EntityManager;
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.graphic.impl.Blob;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystemManager;
import de.ka.javacity.system.impl.FamilyManager;
import de.ka.javacity.system.impl.RenderSystem;
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
		RenderSystem renderSystem = new RenderSystem(familyManager, canvas);
		
		this.systemManager.addSystem(renderSystem);
	
		// TODO Initialize Gamestatus/Gamelogic
	}
	
	public void createTestBlob() {
		// Test object: blob
		Display2D display = new Display2D();
		display.setView(new Blob());
		AbstractComponent components[] = {display};
		this.entityManager.createEntity(components);
	}
	
	public void update() {
		this.systemManager.updateSystems();
	}

}
