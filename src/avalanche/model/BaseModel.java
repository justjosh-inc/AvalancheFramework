package avalanche.model;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import avalanche.components.Mesh;
import avalanche.engine.utils.MeshLoader;

public abstract class BaseModel {

	protected float[] verticies;
	protected int[] indicies;
	protected float[] textCoords;
	
	protected Mesh mesh;
	protected MeshLoader loader = MeshLoader.get();
	
	
	public float[] getVerticies() {
		return verticies;
	}
	
	public void setVerticies(float[] verticies) {
		this.verticies = verticies;
	}
	
	public int[] getIndicies() {
		return indicies;
	}
	
	public void setIndicies(int[] indicies) { 
		this.indicies = indicies;
	}
	
	public float[] getTextCoords() {
		return textCoords;
	}
	
	public void setTextCoords(float[] textCoords) {
		this.textCoords = textCoords;
	}
	
	public void render() {
	    glBindVertexArray(mesh.getVaoID());
	    glEnableVertexAttribArray(0);
	    
	    // Use glDrawElements to render using the indices
	    glDrawElements(GL_TRIANGLES, mesh.getVertexCount(), GL_UNSIGNED_INT, 0);
	    
	    glDisableVertexAttribArray(0);
	    glBindVertexArray(0);
	}
	
	public void cleanUp() {
		
	}
}
