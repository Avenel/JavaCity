package de.ka.javacity.component.impl;

import de.ka.javacity.component.AbstractComponent;
import de.ka.javacity.component.ComponentType;

public class Chunk extends AbstractComponent {
	
	// Defines available BlockTypes
	public enum BoxType {
		EMPTY,
		GRASS,
		SOIL,
		ROCK,
		WATER
	}
	
	// Size should be equal to 2^n
	private int size;
	private float blockSize;
	
	// 3D-Array of Blocks => {x, y, z}
	private BoxType[][][] boxes;
	
	
	/**
	 * Default Constructor
	 */
	public Chunk() {
		this.type = ComponentType.CHUNK;
		
		// Default size = 32
		this.size = 32;
		this.boxes = new BoxType [this.size][this.size][this.size];
		
		// initialize empty chunk
		for (int x=0; x < boxes.length; x++) {
			for (int y=0; y < boxes.length; y++) {
				for (int z=0; z < boxes.length; z++) {
					if (y < 10) {
						this.boxes[x][y][z] = BoxType.SOIL;
						continue;
					} 
					
					if (y < 15) {
						this.boxes[x][y][z] = BoxType.WATER;
						continue;
					}
					
					if (y < 25) {
						this.boxes[x][y][z] = BoxType.GRASS;
						continue;
					}
						
					if (y >= 25) {
						this.boxes[x][y][z] = BoxType.ROCK;
						continue;
					}
					
				}
			}
		}
		
		this.blockSize = 1f;
	}
	
	
	/**
	 * Constructor to initialize size and (or) boxes
	 * @param size
	 * @param boxes
	 */
	public Chunk(int size, BoxType[][][] boxes) {
		this.size = size;
		
		if (boxes != null) {
			this.boxes = boxes;
		} else {
			this.boxes = new BoxType [this.size][this.size][this.size];
		}
		
		this.blockSize = 1f;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public BoxType[][][] getBoxes() {
		return boxes;
	}

	public void setBoxes(BoxType[][][] boxes) {
		this.boxes = boxes;
	}


	public float getBlockSize() {
		return blockSize;
	}


	public void setBlockSize(float blockSize) {
		this.blockSize = blockSize;
	}	
	
}
