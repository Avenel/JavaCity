package de.ka.javacity.graphic.impl;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;

import de.ka.javacity.component.impl.Chunk.BoxType;
import de.ka.javacity.graphic.IView3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.LWJGLException;
import org.lwjgl.Sys;
import org.lwjgl.opengl.Display;
import org.lwjgl.opengl.DisplayMode;
import org.lwjgl.opengl.GL11;

import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.util.glu.GLU.gluPerspective;



public class Chunk3D implements IView3D {

	int bufferId;
	int amountOfVertices;
	int verticeSize;
	int colorSize;
	float blockSize;
	
	public Chunk3D() {
		this.bufferId = createVBOID();
		
		// we use x, y, z coordinates
		this.verticeSize = 3;
		
		// we use r, g, b color
		this.colorSize = 3;
		
		// default blockSize
		this.blockSize = 1f;
	}
	
	@Override
	public void draw(float x, float y, float z, float rx, float ry, float rz) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * Generate buffer id for upcoming data
	 * @return buffer id on graphics card
	 */
	private int createVBOID() {
	    IntBuffer buffer = BufferUtils.createIntBuffer(1);
	    glGenBuffers(buffer);	
	    return buffer.get(0);    
	}
	
	/**
	 * Push data into the buffer
	 * @param id
	 * @param buffer
	 */
	private void pushVertexBufferData(int id, FloatBuffer buffer) { //Not restricted to FloatBuffer
	    glBindBuffer(GL_ARRAY_BUFFER, id); //Bind buffer (also specifies type of buffer)
	    glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW); //Send up the data and specify usage hint.
	    glBindBuffer(GL_ARRAY_BUFFER, 0); // unbind the buffer
	}
	
	/**
	 * Generate Boxes
	 * @param boxes
	 * @return bufferData which contains the box-vertices 
	 */
	private FloatBuffer createVerticesBufferData(BoxType[][][] boxes) {
		this.amountOfVertices = (int) Math.pow(boxes.length, 3);
		
		// Initialize FloatBuffer
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(this.amountOfVertices * this.verticeSize);
		float allVertexData[];
		
		
		vertexData.put(new float[]{});
		
		return null;
	}
	
	private FloatBuffer createColorBufferData(BoxType[][][] boxes) {		
		this.amountOfVertices = (int) Math.pow(boxes.length, 3);
		
		// Initialize FloatBuffer
		FloatBuffer colorData = BufferUtils.createFloatBuffer(this.amountOfVertices * this.colorSize);
		colorData.put(new float[]{});
		
		return null;
	}
	
	/**
	 * Generate a single box
	 * @param type
	 * @param offset
	 * @return bufferData for a single box
	 */
	private float[] createSingleBoxData(float offset) {
		float boxData[] = new float[] {
			// left
			-blockSize + offset, -blockSize + offset, -blockSize + offset,
			-blockSize + offset, -blockSize + offset, blockSize + offset,
			-blockSize + offset, blockSize + offset, blockSize + offset,
			-blockSize + offset, blockSize + offset, -blockSize + offset,

			// right
			blockSize + offset, -blockSize + offset, -blockSize + offset,
			blockSize + offset, -blockSize + offset, blockSize + offset,
			-blockSize + offset, -blockSize + offset, blockSize + offset,
			-blockSize + offset, -blockSize + offset, -blockSize + offset,

			// bottom
			blockSize + offset, -blockSize + offset, -blockSize + offset,
			blockSize + offset, -blockSize + offset, blockSize + offset,
			-blockSize + offset, -blockSize + offset, blockSize + offset,
			-blockSize + offset, -blockSize + offset, -blockSize + offset,

			// top
			-blockSize + offset, blockSize + offset, -blockSize + offset,
			-blockSize + offset, blockSize + offset, blockSize + offset,
			blockSize + offset, blockSize + offset, blockSize + offset,
			blockSize + offset, blockSize + offset, -blockSize + offset,

			// back
			-blockSize + offset, -blockSize + offset, -blockSize + offset,
			-blockSize + offset, blockSize + offset, -blockSize + offset,
			blockSize + offset, blockSize + offset, -blockSize + offset,
			blockSize + offset, -blockSize + offset, -blockSize + offset,

			// front
			blockSize + offset, -blockSize + offset, blockSize + offset,
			blockSize + offset, blockSize + offset, blockSize + offset,
			-blockSize + offset, blockSize + offset, blockSize + offset,
			-blockSize + offset, -blockSize + offset, blockSize + offset
		};
		
		return boxData;
	}
	
	private float[] createBoxColorData(BoxType type) {
		float boxColorData[] = new float[]{0f, 0f, 0f};
		// Grass = green
		if (type == BoxType.GRASS) {
			boxColorData = new float[]{0f, 1f, 0f};
		}
		
		// Soil = brown
		if (type == BoxType.SOIL) {
			boxColorData = new float[]{1f, 1f, 0f};
		}
		
		// Water = blue
		if (type == BoxType.WATER) {
			boxColorData = new float[]{0f, 0f, 1f};
		}
		
		// Rock = grey
		if (type == BoxType.ROCK) {
			boxColorData = new float[]{0.3f, 0.3f, 0.3f};
		}
		
		return boxColorData;
	}

	public int getBufferId() {
		return bufferId;
	}

	public void setBufferId(int bufferId) {
		this.bufferId = bufferId;
	}

	public float getblockSize() {
		return blockSize;
	}

	public void setblockSize(float blockSize) {
		this.blockSize = blockSize;
	}
	
}
