package avalanche.engine.utils;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import avalanche.components.Mesh;

public class Loader {

	private List<Integer> vaos = new ArrayList<Integer>();
	private List<Integer> vbos = new ArrayList<Integer>();
	private List<Integer> textures = new ArrayList<Integer>();
	
	private static Loader instance = null;
	
	public static Loader get() {
		if (instance == null) {
			instance = new Loader();
		}
		return instance;
	}
	
	public Loader() {
		if (instance == null) {
			instance = this;
		}
	}
	
	public Mesh loadToVAO(float[] positions,int[] indicies) {
		int vaoID = createVAO();
		bindIndiciesBuffer(indicies);
		storeDataInAttributeList(0,3, positions);
		
		unbindVAO();
		return new Mesh(vaoID,indicies.length);
	}
	
	public void cleanUp() {
		for (int vao : vaos) {
			glDeleteVertexArrays(vao);
		}
		
		for (int vbo : vbos) {
			glDeleteBuffers(vbo);
		}
	}
	
	private int createVAO() {
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}
	
	private void storeDataInAttributeList(int attributeNumber,int size,float[] data) {
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = Util.storeDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(attributeNumber,size,GL_FLOAT,false,0,0);
		glBindBuffer(GL_ARRAY_BUFFER, 0);
	}
	
	private void unbindVAO() {
		glBindVertexArray(0);
	}
	
	private void bindIndiciesBuffer(int[] indicies) {
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = Util.storeDataInIntBuffer(indicies);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}
}
