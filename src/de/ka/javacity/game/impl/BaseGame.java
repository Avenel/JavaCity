package de.ka.javacity.game.impl;

import de.ka.javacity.cam.GameCamera;
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
	
	private GameCamera camera;
	
	public BaseGame() {
		this.title = "JavaCity";
		this.window_width = 1280;
		this.window_height = 768;
		this.fullscreen = true;
	}

	public void startUp() {
		// Initialize FamilyManager
		this.familyManager = new FamilyManager();
		
		// Initialize EntityManager
		this.entityManager = new EntityManager(this.familyManager);
		
		// Initialize SystemManager incl. Systems
		this.systemManager = new SystemManager();
	
		RenderSystem3D renderSystem = new RenderSystem3D(familyManager, this.camera);
		Movement3DSystem movementSystem = new Movement3DSystem(familyManager);
		
		this.systemManager.addSystem(renderSystem);
		//this.systemManager.addSystem(movementSystem);
		
		// TODO Initialize Gamestatus/Gamelogic
	
	}
	
	public void createTestBlob(float x, float y, float z) {
		int worldsize = 256;
		int worldHeight = 32; 
		int chunksize = 16;
		float blockSize = 1.0f;
		
		this.camera.setChunkSize(chunksize);
		this.camera.setBlockSize(blockSize);
		
		int waterlevel = (int)((float)worldHeight * 0.05f);
		int soillevel = (int)((float)worldHeight * 0.1f);
		int grasslevel = (int)((float)worldHeight * 0.84f);
		int rocklevel = (int)((float)worldHeight * 0.85f);
		
		HeightMapGenerator heightMapGenerator = new HeightMapGenerator(worldsize, worldHeight);
		int[][] map = heightMapGenerator.generate(6);
		
		WorldGenerator worldGenerator = new WorldGenerator(map, worldsize, worldHeight, soillevel, waterlevel, grasslevel, rocklevel);
		BoxType [][][] world = worldGenerator.generate();	
		
		ChunkGenerator chunkGenerator = new ChunkGenerator(chunksize, this.entityManager, world, blockSize);
		chunkGenerator.generateWorldChunks();
	}
	
	public void update() {
		this.systemManager.updateSystems();
	}

	public GameCamera getCamera() {
		return camera;
	}

	public void setCamera(GameCamera camera) {
		this.camera = camera;
	}

}
