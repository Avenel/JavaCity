package de.ka.javacity.graphic.impl;

import java.nio.FloatBuffer;
import java.util.ArrayList;

import de.ka.javacity.component.impl.Chunk.BoxType;
import de.ka.javacity.graphic.IView3D;

import org.lwjgl.BufferUtils;
import org.lwjgl.util.vector.Vector3f;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;



public class Chunk3D implements IView3D {

	int bufferId;
	int normalsId;
	int colorId;
	
	int amountOfVertices;
	int verticeSize;
	int colorSize;
	
	float offsetX;
	float offsetY;
	float offsetZ;
	
	float blockSize;
	
	BoxType boxes [][][];
	boolean enabledFaces [][][][];
	int enabledFacesCount;
	ArrayList<Vector3f> enabledBoxes;

	public Chunk3D(BoxType boxes[][][], float blockSize) {
		this.bufferId = 0;
		this.normalsId = 0;
		this.colorId = 0;
		
		this.boxes = boxes;
		
		// we use x, y, z coordinates
		this.verticeSize = 3;
		
		// we use r, g, b color
		this.colorSize = 3;
		
		// default blockSize
		this.blockSize = blockSize;
		
		// default offset = 0 
		this.offsetX = 0f;
		this.offsetY = 0f;
		this.offsetZ = 0f;
	}
	
	@Override
	public void draw(float x, float y, float z, float rx, float ry, float rz) {
		if (this.bufferId == 0 || this.normalsId == 0 || this.colorId == 0) {
			this.bufferId = createVBOID();
			this.normalsId = createVBOID();
			this.colorId = createVBOID();
			
			this.offsetX = x;
			this.offsetY = y;
			this.offsetZ = z;
			
			// Create Boxes (BufferData)	
			this.enabledBoxes = this.memorizeNotEnclosedBoxes(boxes);
			this.amountOfVertices = this.enabledFacesCount*4;
			
			// Vertex Data
			this.pushVertexBufferData(this.bufferId, this.createVerticesBufferData(this.boxes));
			
			// Normal Data
			this.pushVertexBufferData(this.normalsId, this.createNormalsBufferData(this.boxes));
			
			// Color Data
			this.pushVertexBufferData(this.colorId, this.createColorBufferData(this.boxes));
		} else {
			// draw
			try {				
				glBindBuffer(GL_ARRAY_BUFFER, this.bufferId);
				glVertexPointer(this.verticeSize, GL_FLOAT, 0, 0L);
				
				glBindBuffer(GL_ARRAY_BUFFER, this.normalsId);
				glNormalPointer(GL_FLOAT, 0, 0L); 
				
				glBindBuffer(GL_ARRAY_BUFFER, this.colorId);
				glColorPointer(this.colorSize, GL_FLOAT, 0, 0L);
				
				glEnableClientState(GL_VERTEX_ARRAY);
				glEnableClientState(GL_NORMAL_ARRAY);
				glEnableClientState(GL_COLOR_ARRAY);
				
				glDrawArrays(GL_QUADS, 0, this.amountOfVertices);
				
				glDisableClientState(GL_VERTEX_ARRAY);
				glDisableClientState(GL_NORMAL_ARRAY);
				glDisableClientState(GL_COLOR_ARRAY);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * Generate buffer id for upcoming data
	 * @return buffer id on graphics card
	 */
	private int createVBOID() {
	    return glGenBuffers();	  
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
		// Initialize FloatBuffer
		FloatBuffer vertexData = BufferUtils.createFloatBuffer(this.amountOfVertices * this.verticeSize);

		for (Vector3f vector : this.enabledBoxes) {
			for (int i=0; i<6; i++) {
				if (this.enabledFaces[(int) vector.getX()][(int) vector.getY()][(int) vector.getZ()][i]) {
					vertexData.put(this.createFaceData(i,(vector.getX() + this.offsetX) * this.blockSize*2, 
								(vector.getY() + this.offsetY) * this.blockSize*2, 
								(vector.getZ() + this.offsetZ) * this.blockSize*2));
				}
			}
		}
		
		vertexData.flip();
		return vertexData;
	}
	
	/**
	 * Generate Boxes
	 * @param boxes
	 * @return bufferData which contains the box-vertices 
	 */
	private FloatBuffer createNormalsBufferData(BoxType[][][] boxes) {		
		// Initialize FloatBuffer
		FloatBuffer normalsData = BufferUtils.createFloatBuffer(this.amountOfVertices * this.verticeSize);

		for (Vector3f vector : this.enabledBoxes) {
			for (int i = 0; i < 6; i++) {
				if (this.enabledFaces[(int) vector.getX()][(int) vector.getY()][(int) vector.getZ()][i]) {
					normalsData.put(this.createFaceNormal(i));
				}
			}
		}
		
		normalsData.flip();
		return normalsData;
	}
	
	private FloatBuffer createColorBufferData(BoxType[][][] boxes) {				
		// Initialize FloatBuffer
		FloatBuffer colorData = BufferUtils.createFloatBuffer(this.amountOfVertices * this.colorSize);
		for (Vector3f vector : this.enabledBoxes) {
			
			BoxType type = boxes[(int)vector.getX()][(int)vector.getY()][(int)vector.getZ()];
			float[] colors = createBoxColorData(type);
		
			for (int i=0; i < 6; i++) {
				for (int j=0; j < 4; j++) {
					if (this.enabledFaces[(int) vector.getX()][(int) vector.getY()][(int) vector.getZ()][i]) {
						colorData.put(colors);
					}
				}
			}

		}

		colorData.flip();
		return colorData;
	}
	
	private float[] createFaceNormal(int face) {
		float[] faceNormal = new float[]{};
		
		switch(face) {
			// left
			case 0:
				faceNormal = new float[] {
						-1, 0, 0,
						-1, 0, 0,
						-1, 0, 0,
						-1, 0, 0
				};
				break;
			// right
			case 1:
				faceNormal = new float[] {
					1, 0, 0,
					1, 0, 0,
					1, 0, 0,
					1, 0, 0
				};
				break;
			// bottom
			case 2:
				faceNormal = new float[] {
					0, -1, 0,
					0, -1, 0,
					0, -1, 0,
					0, -1, 0
				};
				break;
			// top 
			case 3:
				faceNormal = new float[] {
					0, 1, 0,
					0, 1, 0,
					0, 1, 0,
					0, 1, 0
				};
				break;
			// back
			case 4:
				faceNormal = new float[] {
					0, 0, -1,
					0, 0, -1,
					0, 0, -1,
					0, 0, -1
				};
				break;
			// front
			case 5:
				faceNormal = new float[] {
					0, 0, 1,
					0, 0, 1,
					0, 0, 1,
					0, 0, 1
				};
				break;
		}
		
		return faceNormal;
		
	}
		
	private float[] createFaceData(int face, float offsetX, float offsetY, float offsetZ) {
		float[] faceData = new float[]{};
		
		switch(face) {
			// left
			case 0:
				faceData = new float[] {
					-this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ,
					-this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ,
					-this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
					-this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ
				};
				break;
			// right
			case 1:
				faceData = new float[] {
						this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ,
						this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
						this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ,
						this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ
				};
				break;
			// bottom
			case 2:
				faceData = new float[] {
						this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ,
						this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ,
						-this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ,
						-this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ
				};
				break;
			// top 
			case 3:
				faceData = new float[] {
						-this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ,
						-this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
						this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
						this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ
				};
				break;
			// back
			case 4:
				faceData = new float[] {
						-this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ,
						-this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ,
						this.blockSize + offsetX, this.blockSize + offsetY, -this.blockSize + offsetZ,
						this.blockSize + offsetX, -this.blockSize + offsetY, -this.blockSize + offsetZ
				};
				break;
			// front
			case 5:
				faceData = new float[] {
						this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ,
						this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
						-this.blockSize + offsetX, this.blockSize + offsetY, this.blockSize + offsetZ,
						-this.blockSize + offsetX, -this.blockSize + offsetY, this.blockSize + offsetZ
				};
				break;
		}
		
		return faceData;
	}
	
	private float[] createBoxColorData(BoxType type) {
		float boxColorData[] = new float[]{0f, 0f, 0f};
		// Grass = green
		if (type == BoxType.GRASS) {
			boxColorData = new float[]{0.1f, 1f, 0.1f};
		}
		
		// Soil = brown
		if (type == BoxType.SOIL) {
			boxColorData = new float[]{0.72f, 0.54f, 0f};
		}
		
		// Water = blue
		if (type == BoxType.WATER) {
			boxColorData = new float[]{0f, 0.2f, 1f};
		}
		
		// Rock = grey
		if (type == BoxType.ROCK) {
			boxColorData = new float[]{0.5f, 0.5f, 0.5f};
		}
		
		return boxColorData;
	}
	
	private ArrayList<Vector3f> memorizeNotEnclosedBoxes(BoxType[][][] boxes) {
		ArrayList<Vector3f> listOfBoxes = new ArrayList<Vector3f>();
		
		this.enabledFaces = new boolean[boxes.length][boxes[0].length][boxes[0][0].length][6];
		
		// go through cube (chunk)
		for (int x=0; x < boxes.length; x++) {
			for (int y=0; y < boxes.length; y++) {
				for (int z=0; z < boxes.length; z++) {
					// is it empty?
					if (boxes[x][y][z] != BoxType.EMPTY) {
						// add all boxes, who are on the outside of the cube
						if (x == 0 || x == boxes.length-1 || y == 0 || y == boxes[0].length-1 || 
							z == 0 || z == boxes[0][0].length-1) {
							listOfBoxes.add(new Vector3f(x, y, z));

							// enable all faces
							for (int i=0; i<6; i++) {
								this.enabledFaces[x][y][z][i] = true;
								this.enabledFacesCount++;
							}
						} else {					
							// are there any neighbours?
							if (	boxes[x-1][y][z] != BoxType.EMPTY && boxes[x+1][y][z] != BoxType.EMPTY &&
									boxes[x][y-1][z] != BoxType.EMPTY && boxes[x][y+1][z] != BoxType.EMPTY &&
									boxes[x][y][z-1] != BoxType.EMPTY && boxes[x][y][z+1] != BoxType.EMPTY ) {
							} else {
								listOfBoxes.add(new Vector3f(x, y, z));
								
								// activate faces
								// left
								if (boxes[x-1][y][z] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][0] = true;
									this.enabledFacesCount++;
								}
								// right
								if (boxes[x+1][y][z] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][1] = true;
									this.enabledFacesCount++;
								}
								// bottom
								if (boxes[x][y-1][z] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][2] = true;
									this.enabledFacesCount++;
								}
								// top
								if (boxes[x][y+1][z] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][3] = true;
									this.enabledFacesCount++;
								}
								// back
								if (boxes[x][y][z-1] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][4] = true;
									this.enabledFacesCount++;
								}
								// front
								if (boxes[x][y][z+1] == BoxType.EMPTY) {
									this.enabledFaces[x][y][z][5] = true;
									this.enabledFacesCount++;
								}
							}
						}
					}
				}
			}
		}
	
		return listOfBoxes;
	}
	
	private int countEnabledBoxes() {
		int count = this.enabledBoxes.size();
		return count;
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
