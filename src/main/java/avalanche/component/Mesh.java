package avalanche.component;

import avalanche.core.Shader;

public class Mesh {
	private int ID;
	private int vertexCount;

	private float[] positions;
	private float[] normals;
	private float[] texCoords;
	private int[] indices;

	private Texture texture = null;
	private Shader shader = null;

	public Mesh(int ID, float[] positions, float[] normals, float[] texCoords, int[] indices) {
		this.ID = ID;

		if (indices.length == 0) {
			this.vertexCount = positions.length / 3;
		} else {
			this.vertexCount = indices.length;
		}

		this.positions = positions;
		this.normals = normals;
		this.texCoords = texCoords;
		this.indices = indices;
	}

	public Mesh(Mesh mesh, Texture texture) {
		this.ID = mesh.ID;

		if (mesh.indices.length == 0) {
			this.vertexCount = mesh.positions.length / 3;
		} else {
			this.vertexCount = mesh.indices.length;
		}

		this.positions = mesh.positions;
		this.normals = mesh.normals;
		this.texCoords = mesh.texCoords;
		this.indices = mesh.indices;

		this.texture = texture;
	}

	public void setTexture(Texture texture) {
		this.texture = texture;
	}

	public Texture getTexture() {
		return this.texture;
	}

	public Shader getShader() {
		return shader;
	}

	public void setShader(Shader shader) {
		this.shader = shader;
	}

	public int getID() {
		return ID;
	}

	public int getVertexCount() {
		return vertexCount;
	}

	public float[] getPositions() {
		return positions;
	}

	public float[] getNormals() {
		return normals;
	}

	public float[] getTexCoords() {
		return texCoords;
	}

	public int[] getIndices() {
		return indices;
	}
}
