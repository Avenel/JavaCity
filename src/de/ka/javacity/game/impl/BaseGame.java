package de.ka.javacity.game.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.impl.Chunk;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Motion3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.component.impl.Chunk.BoxType;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.entity.impl.EntityManager;
import de.ka.javacity.game.AbstractGame;
import de.ka.javacity.graphic.impl.Blob3D;
import de.ka.javacity.graphic.impl.Chunk3D;
import de.ka.javacity.helper.ChunkGenerator;
import de.ka.javacity.helper.HeightMapGenerator;
import de.ka.javacity.helper.WorldGenerator;
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
		//this.systemManager.addSystem(movementSystem);
		
		// TODO Initialize Gamestatus/Gamelogic
	
	}
	
	public void createTestBlob(float x, float y, float z) {
		int worldsize = 128;
		int worldHeight = 16; 
		int chunksize = 8;
		
		int soillevel = (int)((float)worldHeight * 0.33f);
		int waterlevel = (int)((float)worldHeight * 0.5f);
		int grasslevel = (int)((float)worldHeight * 0.65f);
		int rocklevel = (int)((float)worldHeight * 0.9f);
		
		HeightMapGenerator heightMapGenerator = new HeightMapGenerator(worldsize, worldHeight);
		int[][] map = heightMapGenerator.generate(5);
		
		WorldGenerator worldGenerator = new WorldGenerator(map, worldsize, worldHeight, soillevel, waterlevel, grasslevel, rocklevel);
		BoxType [][][] world = worldGenerator.generate();	
		
		ChunkGenerator chunkGenerator = new ChunkGenerator(chunksize, this.entityManager, world, 1f);
		chunkGenerator.generateWorldChunks();
	}
	
	public void update() {
		this.systemManager.updateSystems();
	}

}
