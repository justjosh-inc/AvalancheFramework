package avalanche.loader;

import static org.lwjgl.opengl.GL11.*;
import static org.lwjgl.opengl.GL15.*;
import static org.lwjgl.opengl.GL20.*;
import static org.lwjgl.opengl.GL30.*;

import java.nio.FloatBuffer;
import java.nio.IntBuffer;
import java.util.ArrayList;
import java.util.List;

import avalanche.component.Mesh;
import avalanche.utils.Utils;

public class MeshLoader {

	private static List<Integer> vaos = new ArrayList<Integer>();
	private static List<Integer> vbos = new ArrayList<Integer>();

	public static Mesh load(float[] positions, float[] normals, float[] texCoords, int[] indices) {
		int id = createVAO();
		storeIndicesBuffer(indices);
		storeDataInAttribList(0, 3, positions);
		storeDataInAttribList(1, 2, texCoords);
		storeDataInAttribList(2, 3, normals);
		unbind();
		return new Mesh(id, positions, normals, texCoords, indices);
	}

	private static int createVAO() {
		int vaoID = glGenVertexArrays();
		vaos.add(vaoID);
		glBindVertexArray(vaoID);
		return vaoID;
	}

	private static void storeIndicesBuffer(int[] indices) {
		int vboID = glGenBuffers();
		vbos.add(vboID);
		glBindBuffer(GL_ELEMENT_ARRAY_BUFFER, vboID);
		IntBuffer buffer = Utils.storedDataInIntBuffer(indices);
		glBufferData(GL_ELEMENT_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
	}

	private static void storeDataInAttribList(int index, int size, float[] data) {
		int vboID = glGenBuffers();
		vbos.add(vboID);

		glBindBuffer(GL_ARRAY_BUFFER, vboID);
		FloatBuffer buffer = Utils.storedDataInFloatBuffer(data);
		glBufferData(GL_ARRAY_BUFFER, buffer, GL_STATIC_DRAW);
		glVertexAttribPointer(index, size, GL_FLOAT, false, 0, 0);
		glBindBuffer(GL_ARRAY_BUFFER, vboID);
	}

	private static void unbind() {
		glBindVertexArray(0);
	}

	public static void cleanUp() {
		for (int vao : vaos) {
			glDeleteVertexArrays(vao);
		}

		for (int vbo : vbos) {
			glDeleteBuffers(vbo);
		}
	}
}
