package de.ka.javacity.helper;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.impl.Chunk;
import de.ka.javacity.component.impl.Chunk.BoxType;
import de.ka.javacity.component.impl.Display3D;
import de.ka.javacity.component.impl.Motion3D;
import de.ka.javacity.component.impl.Position3D;
import de.ka.javacity.entity.IEntityManager;
import de.ka.javacity.entity.impl.EntityManager;
import de.ka.javacity.graphic.impl.Chunk3D;

public class ChunkGenerator {

	// chunksize
	private int chunksize;
	private int worldsize;
	private IEntityManager entityManager;
	private BoxType[][][] world;
	private float blockSize;
	
	public ChunkGenerator(int chunksize, IEntityManager manager, BoxType [][][] world, float blockSize) {
		this.chunksize = chunksize;
		this.entityManager = manager;
		this.world = world;
		this.worldsize = world.length;
		this.blockSize = blockSize;
	}
	
	public void generateWorldChunks() {
		int chunksXCount = this.worldsize / this.chunksize;
		int chunksYCount = world[0].length / this.chunksize;
		int chunksZCount = this.world[0][0].length / this.chunksize;
		
		for (int x=0; x < chunksXCount; x++) {
			for (int y=0; y < chunksYCount; y++) {
				for (int z=0; z < chunksZCount; z++) {
					BoxType [][][] chunkBoxes = new BoxType[this.chunksize][this.chunksize][this.chunksize];
					
					// Get Chunk Boxes
					chunkBoxes = this.getChunkBoxes(x*this.chunksize, y*this.chunksize, z*this.chunksize, this.world);
					
					createChunk(this.chunksize, this.blockSize, x * this.chunksize, y * this.chunksize, z * this.chunksize, 
								chunkBoxes, this.entityManager);
				}
			}
		}
	}
	
	private static void createChunk(int size, float blockSize, float x, float y, float z, BoxType [][][] boxes, IEntityManager entityManager) {
		Chunk chunk = new Chunk(size, boxes, blockSize);
		
		Display3D display = new Display3D();
		display.setView(new Chunk3D(boxes));
		
		Position3D position = new Position3D(x, y, z);
		
		Motion3D motion = new Motion3D();
		
		AbstractComponent components[] = {display, position, motion, chunk};
		entityManager.createEntity(components);		
	} 
	
	private BoxType [][][] getChunkBoxes(int offsetX, int offsetY, int offsetZ, BoxType [][][] boxes) {
		BoxType [][][] chunkBoxes = new BoxType[this.chunksize][this.chunksize][this.chunksize];
		
		for (int x=offsetX; x < offsetX+this.chunksize; x++) {
			for (int y=offsetY; y < offsetY+this.chunksize; y++) {
				for (int z=offsetZ; z < offsetZ+this.chunksize; z++) {
					chunkBoxes[x - offsetX][y - offsetY][z - offsetZ] = boxes[x][y][z];
				}
			}
		}
		
		return chunkBoxes;
	}
	
}
