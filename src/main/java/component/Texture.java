package component;

public class Texture {

	private int ID;
	private int vertexCount;
	
	
	
	public Texture(int iD, int vertexCount) {
		ID = iD;
		this.vertexCount = vertexCount;
	}
	
	public int getID() {
		return ID;
	}
	
	public int getVertexCount() {
		return vertexCount;
	}
	
	
}
