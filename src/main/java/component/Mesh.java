package component;

public class Mesh {
	private int ID;
	private int vertexCount;
	
	private float[] positions;
	private float[] normals;
	private float[] texCoords;
	private int[] indices;
	
	public Mesh(int ID,float[] positions,float[] normals,float[] texCoords,int [] indices) {
		this.ID = ID;
		
		if (indices.length == 0) {
			this.vertexCount = positions.length / 3;
		}else {
			this.vertexCount = indices.length;
		}
		
		this.positions = positions;
		this.normals = normals;
		this.texCoords = texCoords;
		this.indices = indices;
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
