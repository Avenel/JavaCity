package de.ka.javacity.system.impl;

import java.util.ArrayList;

import org.lwjgl.util.vector.Vector3f;

import de.ka.javacity.cam.GameCamera;
import de.ka.javacity.helper.ShaderLoader;
import de.ka.javacity.node.AbstractNode;
import de.ka.javacity.node.impl.RenderNode3D;
import de.ka.javacity.system.FamilyName;
import de.ka.javacity.system.IFamilyManager;
import de.ka.javacity.system.ISystem;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
//import static org.lwjgl.opengl.GL20.glDeleteProgram;
//import static org.lwjgl.opengl.GL20.glUseProgram;

public class RenderSystem3D implements ISystem {

	private IFamilyManager familyManager;
	private GameCamera camera;
	private float renderDistance;
	
	private final String VERTEX_SHADER_LOCATION = "src/de/ka/javacity/shader/ScreenSpaceAmbientOcclusion.vert";
	private final String FRAGMENT_SHADER_LOCATION = "src/de/ka/javacity/shader/ScreenSpaceAmbientOcclusion_LowQuality.frag";
    private int shaderProgram;
	
	public RenderSystem3D(IFamilyManager familyManager, GameCamera camera) {
		this.familyManager = familyManager;
		this.camera = camera;
		this.renderDistance = camera.getRenderDistance();
		
		// load SSAO Shader
		shaderProgram = ShaderLoader.loadShaderPair(VERTEX_SHADER_LOCATION, FRAGMENT_SHADER_LOCATION);
		
		// Setup variables
		ShaderLoader.setFloatUniformVariable(shaderProgram, "TextureSurfaceHeightScale", 1.0f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "OcclusionSampleStepDistance", 0.005f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "OcclusionInvDistanceFactor", 1.0f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "OcclusionIntensity", 1.5f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "OcclusionAmbientIntensity", 1.3f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "OcclusionBias", 0.2f);
		ShaderLoader.setFloatUniformVariable(shaderProgram, "InvSurfaceHeightOcclusionFactor", 0.95f);
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
		
		// calculate cone (view, culling stuff)
		float camDirection = (float) ((float)((camera.getYaw()%360f) * Math.PI / 180f)+Math.PI/2);

		// use shader
//		glUseProgram(shaderProgram);
			
		for (AbstractNode node : renderNodes) {
			RenderNode3D renderNode = (RenderNode3D) node;

			float x = renderNode.getPosition().getX();
			float y = renderNode.getPosition().getY();
			float z = renderNode.getPosition().getZ();
			
			float cx = x / chunkSize;
			float cy = y / chunkSize;
			float cz = z / chunkSize;
			
			float dx = chunkX - cx;
			//float dy = chunkY - cy;
			float dz = chunkZ - cz;
			
			float distance = (float) Math.sqrt((double)(dx*dx + dz*dz));
			float angle = (float) (Math.atan2(dz, dx) + Math.PI);
						
			if (distance < this.renderDistance) { // && !(angle > camDirection - Math.PI/2 && angle < camDirection + Math.PI/2)) {
				renderNode.getDisplay().getView().draw(x, y,z, renderNode.getMotion().getRx(), renderNode.getMotion().getRy(), renderNode.getMotion().getRz());
			}
		}
		
		// deactivate shader
//		 glUseProgram(0);
	}

}
