package de.ka.javacity.system.impl;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import de.ka.javacity.cam.GameCamera;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode3D;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

public class RenderSystem3D implements ISystem {

	private IFamilyManager familyManager;
	private GameCamera camera;
	private float renderDistance;
	
	public RenderSystem3D(IFamilyManager familyManager, GameCamera camera) {
		this.familyManager = familyManager;
		this.camera = camera;
		this.renderDistance = camera.getRenderDistance();
	}
	
	@Override
	public void update() {
		ArrayList<AbstractNode> renderNodes = (ArrayList<AbstractNode>) this.familyManager.getMembersOfFamily(FamilyName.RENDER3D);

		Vector3f cameraPosition = camera.getPosition(); 
		int chunkSize = camera.getChunkSize();
		float blockSize = camera.getBlockSize();
		
		float chunkX = (cameraPosition.getX() / ((float)chunkSize * blockSize)) / 2f;
		float chunkY = (cameraPosition.getY() / ((float)chunkSize * blockSize)) / 2f;
		float chunkZ = (cameraPosition.getZ() / ((float)chunkSize * blockSize)) / 2f;
				
		System.out.println("X, Y, Z: "+ chunkX + ", " + chunkY + ", " + chunkZ + " chunksize: "+ chunkSize);
		
		for (AbstractNode node : renderNodes) {
			RenderNode3D renderNode = (RenderNode3D) node;

			float x = renderNode.getPosition().getX();
			float y = renderNode.getPosition().getY();
			float z = renderNode.getPosition().getZ();
			
			float cx = x / chunkSize;
			float cy = y / chunkSize;
			float cz = z / chunkSize;
			
			float dx = Math.abs(chunkX - cx);
			float dy = Math.abs(chunkY - cy);
			float dz = Math.abs(chunkZ - cz);
			
			float distance = (float) Math.sqrt((double)(dx*dx + dy*dy + dz*dz));
						
			if (distance < this.renderDistance) {
				renderNode.getDisplay().getView().draw(x, y,z, renderNode.getMotion().getRx(), renderNode.getMotion().getRy(), renderNode.getMotion().getRz());
			}
		}
	}

}
