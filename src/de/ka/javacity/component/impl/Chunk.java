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
