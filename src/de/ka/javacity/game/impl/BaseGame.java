package de.ka.javacity.game.impl;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;
import javafx.scene.shape.CullFace;
import javafx.scene.shape.DrawMode;
import de.ka.javacity.cam.GameCamera;
import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.impl.Display2D;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Motion2D;
import de.ka.javacity.component.impl.Position2D;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.entity.impl.EntityManager;
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.graphic.impl.Blob;
import de.ka.javacity.graphic.impl.Blob3D;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystemManager;
import de.ka.javacity.system.impl.FamilyManager;
import de.ka.javacity.system.impl.MovementSystem;
import de.ka.javacity.system.impl.RenderSystem;
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

	public void startUp(GameCamera gameCam) {
		// Initialize FamilyManager
		this.familyManager = new FamilyManager();
		
		// Initialize EntityManager
		this.entityManager = new EntityManager(this.familyManager);
		
		// Initialize SystemManager incl. Systems
		this.systemManager = new SystemManager();
	
		RenderSystem3D renderSystem = new RenderSystem3D(familyManager, gameCam);
		MovementSystem movementSystem = new MovementSystem(familyManager);
		
		this.systemManager.addSystem(renderSystem);
		this.systemManager.addSystem(movementSystem);
		
		// TODO Initialize Gamestatus/Gamelogic
	
	}
	
	public void createTestBlob(double vx, double vy) {
		// Test object: blob
		Display3D display = new Display3D();
		display.setView(new Blob3D());
		
		Position2D position = new Position2D();
		position.setX(0);
		position.setY(0);
		
		Motion2D motion = new Motion2D();
		motion.setVx(vx);//Math.random());
		motion.setVy(vy);//Math.random());
		motion.setVelocity(4.0);
		
		AbstractComponent components[] = {display, position, motion};
		this.entityManager.createEntity(components);
		RenderSystem3D renderSystem = (RenderSystem3D) this.systemManager.getSystem(RenderSystem3D.class.toString());
	
		if (renderSystem != null) {
			renderSystem.addObjectToWorld(display.getView());
		}
		
		
	}
	
	public void update() {
		this.systemManager.updateSystems();
	}

}
